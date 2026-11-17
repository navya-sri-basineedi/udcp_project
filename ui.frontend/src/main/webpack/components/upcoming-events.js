(function() {
    "use strict";

    var selector = ".upcoming-events__date[data-event-date]";

    function formatDate(value) {
        var match = /^(\d{4})-(\d{2})-(\d{2})(?:T.*)?$/.exec(value);
        if (!match) {
            return null;
        }

        var year = Number(match[1]);
        var month = Number(match[2]);
        var day = Number(match[3]);
        var date = new Date(Date.UTC(year, month - 1, day));
        if (date.getUTCFullYear() !== year || date.getUTCMonth() !== month - 1 || date.getUTCDate() !== day) {
            return null;
        }

        return new Intl.DateTimeFormat(undefined, {
            year: "numeric",
            month: "long",
            day: "numeric",
            timeZone: "UTC"
        }).format(date);
    }

    function initialize() {
        var dateElements = document.querySelectorAll(selector);
        for (var i = 0; i < dateElements.length; i++) {
            var element = dateElements[i];
            var formatted = formatDate(element.getAttribute("data-event-date"));
            if (formatted === null) {
                console.error("Unable to format the event date.", element.getAttribute("data-event-date"));
                continue;
            }
            element.textContent = formatted;
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initialize);
    } else {
        initialize();
    }
}());
