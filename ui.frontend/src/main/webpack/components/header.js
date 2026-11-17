(() => {
    const initializedHeaders = new WeakSet();
    const desktopQuery = window.matchMedia('(min-width: 1351px)');

    const initializeHeader = (header) => {
        if (initializedHeaders.has(header)) {
            return;
        }

        initializedHeaders.add(header);

        const menu = header.querySelector('.udcp-header__menu');
        const menuToggle = menu && menu.querySelector('.udcp-header__menu-toggle');
        const submenus = header.querySelectorAll('.udcp-header__submenu');
        let desktopMenu = desktopQuery.matches;

        if (menu) {
            menu.open = desktopMenu;
        }

        submenus.forEach((submenu) => {
            const summary = submenu.querySelector('summary');

            submenu.addEventListener('mouseenter', () => {
                if (desktopQuery.matches) {
                    submenu.open = true;
                }
            });

            submenu.addEventListener('mouseleave', () => {
                if (desktopQuery.matches && !submenu.contains(document.activeElement)) {
                    submenu.open = false;
                }
            });

            submenu.addEventListener('focusout', (event) => {
                if (desktopQuery.matches && !submenu.contains(event.relatedTarget)) {
                    submenu.open = false;
                }
            });

            submenu.addEventListener('keydown', (event) => {
                if (event.key === 'Escape') {
                    event.preventDefault();
                    event.stopPropagation();
                    submenu.open = false;
                    if (summary) {
                        summary.focus();
                    }
                }
            });
        });

        if (!menu) {
            return;
        }

        menu.addEventListener('click', (event) => {
            const target = event.target;
            if (!(target instanceof Element)) {
                return;
            }

            const link = target.closest('a.udcp-header__nav-link, a.udcp-header__submenu-link');
            if (link) {
                menu.open = false;
                submenus.forEach((submenu) => {
                    submenu.open = false;
                });
            }
        });

        menu.addEventListener('keydown', (event) => {
            if (event.key === 'Escape') {
                event.preventDefault();
                menu.open = false;
                if (menuToggle) {
                    menuToggle.focus();
                }
            }
        });

        window.addEventListener('resize', () => {
            const isDesktop = desktopQuery.matches;
            if (isDesktop !== desktopMenu) {
                desktopMenu = isDesktop;
                menu.open = isDesktop;

                if (!isDesktop) {
                    submenus.forEach((submenu) => {
                        submenu.open = false;
                    });
                }
            }
        });
    };

    const initializeHeaders = () => {
        document.querySelectorAll('.udcp-header').forEach(initializeHeader);
    };

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initializeHeaders, { once: true });
    } else {
        initializeHeaders();
    }
})();
