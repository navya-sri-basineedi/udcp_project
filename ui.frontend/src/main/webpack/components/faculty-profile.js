(function() {
    "use strict";

    function isAuthorMode() {
        var documentElement = document.documentElement;
        var body = document.body;

        return (
            (documentElement && documentElement.classList.contains("aem-AuthorLayer-Edit")) ||
            (body && body.classList.contains("cq-wcm-edit")) ||
            document.querySelector(".cq-Editable-dom, cq[data-path]")
        );
    }

    function hasAuthorMarkers(columns) {
        return columns.every(function(column) {
            return (
                column.classList.contains("cq-Editable-dom") &&
                column.querySelector(":scope > cq[data-path]")
            );
        });
    }

    function initializeFacultyRow(intro, authorMode) {
        if (intro.classList.contains("faculty-profile__intro--scrolling")) {
            return;
        }

        var firstColumn = intro.closest(".aem-GridColumn");
        var grid = firstColumn && firstColumn.parentElement;
        if (!grid || !grid.classList.contains("aem-Grid")) {
            return;
        }

        var columns = [];
        var currentColumn = firstColumn;
        while (
            currentColumn &&
            currentColumn.classList.contains("aem-GridColumn") &&
            currentColumn.querySelector(":scope > .faculty-profile > .faculty-profile__card")
        ) {
            columns.push(currentColumn);
            currentColumn = currentColumn.nextElementSibling;
        }

        if (columns.length < 2 || (authorMode && !hasAuthorMarkers(columns))) {
            return;
        }

        var cards = document.createElement("div");
        cards.className = "faculty-profile__cards";
        cards.setAttribute("role", "region");
        cards.setAttribute("aria-label", "Faculty profiles");
        cards.tabIndex = 0;

        grid.insertBefore(intro, firstColumn);
        grid.insertBefore(cards, firstColumn);
        columns.forEach(function(column) {
            cards.appendChild(column);
        });

        intro.classList.add("faculty-profile__intro--scrolling");
    }

    function initialize() {
        var authorMode = isAuthorMode();
        var intros = document.querySelectorAll(".faculty-profile__intro");
        Array.prototype.forEach.call(intros, function(intro) {
            initializeFacultyRow(intro, authorMode);
        });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initialize, {
            once: true
        });
    } else {
        initialize();
    }
}());
