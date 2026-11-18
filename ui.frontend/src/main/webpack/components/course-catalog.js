(function() {
    "use strict";

    var endpoint = "/graphql/execute.json/udcp/courses-by-school";
    var selectors = {
        component: "[data-course-catalog]",
        school: "[data-course-catalog-school]",
        status: "[data-course-catalog-status]",
        items: "[data-course-catalog-items]"
    };
    var nextInstanceId = 0;

    function firstValue(course, fields) {
        for (var i = 0; i < fields.length; i++) {
            var value = course[fields[i]];
            if (value !== undefined && value !== null && value !== "") {
                return value;
            }
        }
        return null;
    }

    function plainTextValue(value) {
        if (value && typeof value === "object") {
            value = firstValue(value, ["plaintext"]);
        }
        return value;
    }

    function textValue(value) {
        value = plainTextValue(value);
        return value === undefined || value === null || value === "" ? "Not available" : String(value);
    }

    function createAcademicIcon() {
        var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        var path = document.createElementNS("http://www.w3.org/2000/svg", "path");

        svg.setAttribute("class", "course-catalog__icon");
        svg.setAttribute("viewBox", "0 0 24 24");
        svg.setAttribute("aria-hidden", "true");
        svg.setAttribute("focusable", "false");
        path.setAttribute("d", "M12 3 1 8.5 12 14l9-4.5V16h2V8.5L12 3zm-7 8v4.5c2.8 2.7 11.2 2.7 14 0V11l-7 3.5L5 11z");
        svg.appendChild(path);
        return svg;
    }

    function appendField(article, label, value, className) {
        var paragraph = document.createElement("p");
        var labelElement = document.createElement("strong");
        paragraph.className = className;
        labelElement.textContent = label + ": ";
        paragraph.appendChild(labelElement);
        paragraph.appendChild(document.createTextNode(textValue(value)));
        article.appendChild(paragraph);
    }

    function appendDetail(details, headingText, value) {
        var section = document.createElement("section");
        var heading = document.createElement("h4");
        var paragraph = document.createElement("p");

        section.className = "course-catalog__detail";
        heading.textContent = headingText;
        paragraph.textContent = textValue(value);
        section.appendChild(heading);
        section.appendChild(paragraph);
        details.appendChild(section);
    }

    function createCourseCard(course, detailsId) {
        var article = document.createElement("article");
        var heading = document.createElement("h3");
        var detailsButton = document.createElement("button");
        var details = document.createElement("div");
        var school = firstValue(course, ["school", "schoolName"]);

        article.className = "course-catalog__card";
        heading.className = "course-catalog__title";
        heading.appendChild(createAcademicIcon());
        heading.appendChild(document.createTextNode(textValue(firstValue(course, ["courseName", "name", "title"]))));
        article.appendChild(heading);

        appendField(article, "School", school, "course-catalog__meta course-catalog__meta--school");
        appendField(article, "Duration", firstValue(course, ["duration"]), "course-catalog__meta");
        appendField(article, "Annual fees", firstValue(course, ["fees", "annualFees"]), "course-catalog__meta");

        detailsButton.className = "course-catalog__details-toggle";
        detailsButton.type = "button";
        detailsButton.setAttribute("aria-expanded", "false");
        detailsButton.setAttribute("aria-controls", detailsId);
        detailsButton.textContent = "View Details";

        details.id = detailsId;
        details.className = "course-catalog__details";
        details.hidden = true;
        appendDetail(details, "About this Course", course.description);
        appendDetail(details, "Eligibility", course.eligibility);

        detailsButton.addEventListener("click", function() {
            var expanded = detailsButton.getAttribute("aria-expanded") === "true";
            detailsButton.setAttribute("aria-expanded", expanded ? "false" : "true");
            detailsButton.textContent = expanded ? "View Details" : "Hide Details";
            details.hidden = expanded;
        });

        article.appendChild(detailsButton);
        article.appendChild(details);
        return article;
    }

    function setStatus(status, message, isError) {
        status.hidden = false;
        status.textContent = message;
        status.setAttribute("role", isError ? "alert" : "status");
    }

    function renderCatalog(component, state) {
        var status = component.querySelector(selectors.status);
        var items = component.querySelector(selectors.items);
        var courses = state.courses;

        if (!status || !items || state.loading) {
            return;
        }

        items.textContent = "";

        if (courses.length === 0) {
            setStatus(status, "No courses are available for this school.", false);
            return;
        }

        var fragment = document.createDocumentFragment();
        for (var i = 0; i < courses.length; i++) {
            fragment.appendChild(createCourseCard(
                courses[i],
                "course-catalog-details-" + state.instanceId + "-" + i
            ));
        }
        items.appendChild(fragment);
        status.hidden = true;
    }

    function loadCatalog(component, school, state) {
        var status = component.querySelector(selectors.status);
        var items = component.querySelector(selectors.items);
        var requestId = ++state.requestId;
        var url = endpoint;

        if (!status || !items) {
            return;
        }

        state.loading = true;
        items.textContent = "";
        setStatus(status, "Loading courses...", false);

        if (school && school.value) {
            url += "%3Bschool%3D" + encodeURIComponent(school.value);
        }

        fetch(url, {
            headers: {
                Accept: "application/json"
            }
        })
            .then(function(response) {
                if (requestId !== state.requestId) {
                    return null;
                }
                if (!response.ok) {
                    throw new Error("Course catalog request failed with status " + response.status);
                }
                return response.json();
            })
            .then(function(result) {
                if (requestId !== state.requestId || result === null) {
                    return;
                }

                var courses = result && result.data && result.data.courseList && result.data.courseList.items;
                if (!Array.isArray(courses)) {
                    throw new Error("The course catalog response did not contain data.courseList.items.");
                }

                state.loading = false;
                state.courses = courses.filter(function(course) {
                    return course && typeof course === "object";
                });
                renderCatalog(component, state);
            })
            .catch(function(error) {
                if (requestId !== state.requestId) {
                    return;
                }
                state.loading = false;
                console.error("Unable to load the course catalog.", error);
                setStatus(status, "Unable to load courses right now. Please try again later.", true);
            });
    }

    function initialize() {
        var components = document.querySelectorAll(selectors.component);
        for (var i = 0; i < components.length; i++) {
            if (components[i].getAttribute("data-course-catalog-loaded") !== "true") {
                var component = components[i];
                var school = component.querySelector(selectors.school);
                var state = {
                    requestId: 0,
                    courses: [],
                    loading: false,
                    instanceId: nextInstanceId++
                };

                component.setAttribute("data-course-catalog-loaded", "true");
                if (school) {
                    school.addEventListener("change", function(currentComponent, currentSchool, currentState) {
                        return function() {
                            loadCatalog(currentComponent, currentSchool, currentState);
                        };
                    }(component, school, state));
                }
                loadCatalog(component, school, state);
            }
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initialize);
    } else {
        initialize();
    }
}());
