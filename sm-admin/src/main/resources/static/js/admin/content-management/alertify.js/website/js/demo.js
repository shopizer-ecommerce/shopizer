/*eslint strict: [2, "global"], global: {ga: false} */
"use strict";

(function() {

    function $(selector) {
        return document.querySelector(selector);
    }

    function reset (ev) {
        ev.preventDefault();
        alertify.reset();
    }

    function logDemo(selector) {
        (ga || function() { })("send", "event", "button", "click", "demo", selector);
    }

    function demo(selector, cb) {
        var el = $(selector);
        if(el) {
            el.addEventListener("click", function(ev) {
                ev.preventDefault();
                logDemo(selector);
                cb();
            });
        }
    }

    var ga = ga || function() {};

    // ==============================
    // Alert Dialog
    demo("#alert", function (event) {
        alertify
            .alert("This is an alert dialog", function (e, ui) {
                // you can handle click event here
                e.preventDefault();
            });
    });

    // ==============================
    // Confirm Dialog
    demo("#confirm", function (event) {
        alertify
            .confirm("This is a confirm dialog", function (e, ui) {
                alertify.success("You've clicked 'OK'");
            }, function (e, ui) {
                alertify.error("You've clicked 'Cancel'");
            });
    });

    // ==============================
    // Prompt Dialog
    demo("#prompt", function (event) {
        alertify
            .prompt("This is a prompt dialog", "Default value", function (e, ui) {
                // the value entered in the prompt input
                var inputValue = ui.getInputValue();

                alertify.success("You've clicked 'OK' and typed: " + inputValue);
            }, function (e, ui) {
                alertify.error("You've clicked 'Cancel'");
            });
    });

    // ==============================
    // Custom Button Labels
    demo("#custom-labels", function (event) {
        alertify
            .confirm("Confirm dialog with custom button labels",
            {
                label: "Accept",
                click: function (e, ui) {
                    e.preventDefault();
                    alertify.success("You've clicked \"Accept\"");
                }
            },{
                label: "Deny",
                click: function (e, ui) {
                    e.preventDefault();
                    alertify.error("You've clicked \"Deny\"");
                }
            });
    });

    // ==============================
    // Persistent Buttons
    demo("#autoclose-buttons", function (event) {
        alertify
            .confirm("Confirm dialog with persistent buttons",
            {
                autoClose: false,
                click: function (e, ui) {
                    e.preventDefault();
                    alertify.success("This is the persistent button");
                }
            },{
                autoClose: false,
                click: function (e, ui) {
                    e.preventDefault();
                    if (true) {
                        // method to close currently open dialog
                        ui.closeDialog();
                    }
                    alertify.error("This is the persistent button, but it was closed programmatically");
                }
            });
    });

    // ==============================
    // Comprehensive dialog demo
    demo("#custom-dialog", function (event) {

        function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

        function createInput(value) {
            return '<input type="radio" name="quiz" id="quiz_' + value + '" value="' + value + '"><label for="quiz_' + value + '">' + value + '</label>';
        }

        function buildQuiz(ui) {
            var min = 5;
            var max = 20;
            var html = "";
            var values = [];
            var numbers = [];
            var options = [];
            var solution = 0;
            var optionsLength = 3;
            var numbersLength = getRandomInt(2, 4);
            var scatter = Math.floor((min * numbersLength) / 2);

            for (var i = 0; i < numbersLength; i++) {
                var number = getRandomInt(min, max);
                numbers.push(number);
                solution += number;
            }

            while (values.length < optionsLength) {
                var value = getRandomInt(solution - scatter, solution + scatter);
                if(value !== solution && values.indexOf(value) === -1) {
                    values.push(value);
                }
            }
            // put correct answer
            values.splice(getRandomInt(0, optionsLength - 1), 0, solution);

            for (var n = 0; n < values.length; n++) {
                options.push(createInput(values[n]));
            }

            html += numbers.join(' + ') + ' = ?';
            html += '<br><br>';
            html += options.join('<br>');

            ui.setContent(html);
            ui.centerDialog();
            ui.solution = solution;
        }

        alertify
            .dialog("Click \"New quiz\" to create new quiz.", [
            {
                type: "ok",
                label: "Test",
                autoClose: false,
                click: function (e, ui) {

                    var dialog = ui.dom.dialog;
                    var checked = dialog.querySelector('input[name="quiz"]:checked');

                    if(checked === null) {
                        alertify.error("Choose an answer from the list");
                        buildQuiz(ui);
                    } else {
                        if(ui.solution === parseInt(checked.value)) {
                            alertify.success("Correct answer!");
                            ui.closeDialog();
                        } else {
                            alertify.error("Wrong answer. Bad luck");
                            buildQuiz(ui);
                        }
                    }
                }
            },{
                type: "custom",
                label: "New quiz",
                autoClose: false,
                click: function (e, ui) {
                    buildQuiz(ui);
                }
            },{
                type: "cancel",
                label: "Close"
            }
        ]);
    });

    // ==============================
    // Ajax - Multiple Dialog
    demo("#ajax", function (event) {
        alertify
            .confirm("Ajax requests",
            {
                autoClose: false,
                click: function (e, ui) {
                    e.preventDefault();

                    // AJAX request delay imitation
                    setTimeout(function () {

                        // updates message in the current dialog
                        ui.setMessage("Successful AJAX after \"OK\".<br>Without opening a new dialog.");

                        // center message vertically due to dialog height might be changed
                        ui.centerDialog();

                        alertify.log("Dialog message was updated using AJAX request.");
                    }, 200);
                }
            },{
                click: function (e, ui) {
                    e.preventDefault();

                    // AJAX request delay imitation
                    setTimeout(function () {

                        // notification in the new dialog window
                        alertify.alert("Successful AJAX after \"Cancel\"");
                    }, 200);
                }
            });
    });

    // ==============================
    // Promise Aware
    demo("#promise", function (event) {

        if ("function" !== typeof Promise) {
            alertify.alert("Your browser doesn't support promises");
            return;
        }

        alertify
            .confirm("Promise confirm").then(function (resolved) {
                // The click event object is accessible via "event" property.
                resolved.event.preventDefault();

                // UI object is accessible via "ui" property.
                alertify.alert("You clicked the " + resolved.ui.getButtonObject().label + " button!");
            });
    });


    /********** Log Messages **********/

    // ==============================
    // Standard Log
    demo("#notification", function (event) {
        alertify.log("Standard log message");
    });

    // ==============================
    // Log types
    demo("#success", function (event) {
        alertify.success("Success log message");
    });
    demo("#warning", function (event) {
        alertify.warning("Warning log message");
    });
    demo("#error", function (event) {
        alertify.error("Error log message");
    });

    // ==============================
    // Log With HTML
    demo("#notification-html", function (event) {
        alertify.log("<img src='https://placehold.it/256x128'><h3>This is HTML</h3><p>It's great, right?</p>");
    });

    // ==============================
    // Log with callback
    demo("#notification-callback", function(event) {
        alertify.log("Log message with callback", function(e, ui) {
            // you can use click event here
            e.preventDefault();

            // method to close current log message
            ui.closeLog();

            alertify.log("You clicked the notification");
        });
    });

    // ==============================
    // Hide in 10 seconds
    demo("#delay", function (event) {
        alertify
            .logDelay(10000)
            .log("Hiding in 10 seconds");
    });

    // ==============================
    // Persistent Log
    demo("#forever", function (event) {
        alertify
            .logDelay(0)
            .log("Will stay until clicked", function(e, ui) {
                ui.closeLog();
            });
    });

    // ==============================
    // Maximum Number of Log Messages
    demo("#max-log-items", function (event) {
        alertify
            .logMaxItems(1)
            .log("This is the first message");

        // The timeout is just for visual effect.
        setTimeout(function() {
            alertify.log("The second message will force the first to close.");
        }, 1000);
    });

    // ==============================
    // Template for Log messages
    demo("#log-message-template", function (event) {
        alertify
            .logMessageTemplate(function (input) { return 'log message: ' + input; })
            .log("This is the message");
    });

    // ==============================
    // Setting the Position
    demo("#log-position", function(event) {
        alertify.logDelay(1000); // This is just to make the demo go faster.
        alertify.log("Default bottom left position");
        setTimeout(function() {
            alertify.logPosition("top left");
            alertify.log("top left");
        }, 1500);
        setTimeout(function() {
            alertify.logPosition("top right");
            alertify.log("top right");
        }, 3000);
        setTimeout(function() {
            alertify.logPosition("bottom right");
            alertify.log("bottom right");
        }, 4500);
        setTimeout(function() {
            alertify.reset(); // Puts the message back to default position.
            alertify.log("Back to default");
        }, 6000);
    });


    /********** Template Customization **********/

    // ==============================
    // Predefined themes
    demo("#theme-predefined", function (event) {
        alertify
            .theme("bootstrap")
            .prompt("Bootstrap theme message");
    });

    demo("#theme-reset", function (event) {
        alertify
            .theme("default")
            .prompt("Default theme message");
    });

    // ==============================
    // Log templates
    demo("#template-log", function (event) {
        alertify.
            theme({
                logMessage: '<div class="wrapper">' +
                    '<div data-alertify-log-msg style="background: rgb(78, 150, 193);"></div>' +
                    '</div>'
            })
            .log("Log message with custom theme.");

        // set default options
        setTimeout(function() {alertify.reset()}, 500);
    });

    // ==============================
    // Dialog templates
    demo("#template-dialog", function (event) {
        alertify.
            theme({
                dialogMessage: '<div style="text-align: center; color: #3F51B5;">' +
                    '<div data-alertify-msg style="font-weight: bold"></div>' +
                    '</div>',
                dialogInput: '<div>' +
                    '<label for="template-dialog-input">LABEL - click to focus:</label>' +
                    '<input data-alertify-input id="template-dialog-input" type="text">' +
                    '<div style="font-size: 12px">Note: write any text to the input.</div>' +
                    '</div>',
                dialogButtonsHolder: '<nav data-alertify-btn-holder style="text-align: center"></nav>'
            })
            .prompt("Dialog prompt with custom template.", "Default value",
            {
                click: function (e, ui) {
                    alertify.success("You've typed: " + ui.getInputValue());
                }
            });

        // set default options
        setTimeout(function() {alertify.reset()}, 500);
    });


    /********** Other Options **********/

    // ==============================
    // Reset
    demo("#reset", function (event) {
        alertify
            .reset()
            .alert("Custom values were reset", {
                label: "Go For It!"
            });
    });

})();

var app = angular.module("alertifyDemo", ["ngAlertify"]);
app.controller("alertifyLogDemoCtrl", function($scope, $log, alertify) {

    $scope.type = "log";
    $scope.msg = "ngAlertify";
    $scope.log = alertify.log;
    $scope.error = alertify.error;
    $scope.success = alertify.success;
    $scope.logMaxItems = 2;
    $scope.logDelay = 5000;

    $scope.show = function(msg) {
        alertify[$scope.type](msg);
    };

    $scope.$watch("logMaxItems", alertify.logMaxItems);
    $scope.$watch("logDelay", alertify.logDelay);

});
