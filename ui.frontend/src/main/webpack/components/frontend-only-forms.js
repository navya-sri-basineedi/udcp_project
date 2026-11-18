(function() {
    "use strict";

    function initialize() {
        var forms = document.querySelectorAll("[data-frontend-only-form]");

        for (var i = 0; i < forms.length; i++) {
            forms[i].addEventListener("submit", function(event) {
                event.preventDefault();

                var status = event.currentTarget.querySelector("[data-form-status]");
                if (!status) {
                    return;
                }

                status.hidden = false;
                status.textContent = event.currentTarget.getAttribute("data-frontend-only-form") === "sign-in"
                    ? "Authentication is not configured. No sign-in was performed."
                    : "This demonstration form is not connected. Your enquiry was not submitted or stored.";
            });
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initialize);
    } else {
        initialize();
    }
}());
