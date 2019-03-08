(function() {

    "use strict";

    var logsUI;
    var TRANSITION_FALLBACK_DURATION = 500;
    var hideElement = function(el) {
        if (! el) {
            return;
        }

        var removeThis = function() {
            if (el && el.parentNode) {
                el.parentNode.removeChild(el);
            }
        };

        removeClass(el, "show");
        addClass(el, "hide");
        el.addEventListener("transitionend", removeThis);

        // Fallback for no transitions.
        setTimeout(removeThis, TRANSITION_FALLBACK_DURATION);
    };

    function centerDialog(node) {
        var nodeHeight = node.offsetHeight;
        var windowHeight = window.innerHeight ? window.innerHeight
            : document.documentElement.clientHeight ? document.documentElement.clientHeight
            : screen.height;
        var top = (windowHeight / 2) - (nodeHeight / 2);
        node.style.top = top + "px";
    }

    function createElementFromHtml(html) {
        var dummy = document.createElement("div");
        dummy.innerHTML = html;
        return dummy.firstChild;
    }

    function findElementByData(node, name) {
        var attributeName = 'data-' + name;
        var dummy = document.createElement("div");
        dummy.appendChild(node);
        var element = dummy.querySelector('[' + attributeName + ']');
        if(!element) {
            throw new Error('Unable to find: "' + attributeName + '" attribute.');
        }
        return element;
    }

    function removeClass(node, className) {
        var attribute = node.getAttribute("class");
        var classList = attribute ? attribute.split(' ') : [];
        var classIndex = classList.indexOf(className);
        if(classIndex !== -1) {
            classList.splice(classIndex, 1);
        }
        node.className = classList.join(' ');
    }

    function addClass(node, className) {
        var attribute = node.getAttribute("class");
        var classList = attribute ? attribute.split(' ') : [];
        classList.push(className);
        node.className = classList.join(' ');
    }

    function clone(object) {
        return JSON.parse(JSON.stringify(object));
    }

    function Alertify() {

        var _defaults = {
            parent: document.body,
            // dialog options
            dialogWidth: '400px',
            dialogPersistent: true,
            dialogContainerClass: "alertify",
            dialogButtons: {
                ok: {
                    label: "Ok",
                    autoClose: true,
                    template: '<button data-alertify-btn="ok" tabindex="1"></button>'
                },
                cancel: {
                    label: "Cancel",
                    autoClose: true,
                    template: '<button data-alertify-btn="cancel" tabindex="2"></button>'
                },
                custom: {
                    label: "Custom",
                    autoClose: false,
                    template: '<button data-alertify-btn tabindex="3"></button>'
                }
            },
            // log options
            logDelay: 5000,
            logMaxItems: 2,
            logPosition: "bottom left",
            logContainerClass: "alertify-logs",
            logTemplateMethod: null,
            // html templates
            templates: {
                dialogButtonsHolder: '<nav data-alertify-btn-holder></nav>',
                dialogMessage: '<div data-alertify-msg></div>',
                dialogInput: '<input data-alertify-input type="text">',
                logMessage: '<div data-alertify-log-msg></div>'
            }
        };

        /**
         * Alertify private object
         * @type {Object}
         */
        var _alertify = {

            version: "1.0.11",
            parent: _defaults.parent,
            // dialog options
            dialogWidth: _defaults.dialogWidth,
            dialogPersistent: _defaults.dialogPersistent,
            dialogContainerClass: _defaults.dialogContainerClass,
            dialogButtons: clone(_defaults.dialogButtons),
            promptValue: "",
            // log options
            logDelay: _defaults.logDelay,
            logMaxItems: _defaults.logMaxItems,
            logPosition: _defaults.logPosition,
            logContainerClass: _defaults.logContainerClass,
            logTemplateMethod: _defaults.logTemplateMethod,
            templates: clone(_defaults.templates),

            /**
             * Build the proper message box
             *
             * @param  {Object} item    Current object in the queue
             * @param  {Array} buttons  Buttons definition array
             *
             * @return {String}         An HTML string of the message box
             */
            build: function(item, buttons) {
                var dom = {};

                dom.container = document.createElement("div");
                dom.container.className = this.dialogContainerClass + " hide";

                dom.wrapper = document.createElement("div");
                dom.wrapper.className = "dialog";

                dom.dialog = document.createElement("div");
                dom.dialog.style.width = this.dialogWidth;

                dom.content = document.createElement("div");
                dom.content.className = "content";

                if(item.type === "dialog") {
                    dom.content.innerHTML = item.message;
                } else {
                    dom.messageWrapper = createElementFromHtml(this.templates.dialogMessage);
                    dom.message = findElementByData(dom.messageWrapper, "alertify-msg");
                    dom.message.innerHTML = item.message;
                    dom.content.appendChild(dom.messageWrapper);
                }

                dom.buttonsWrapper = createElementFromHtml(this.templates.dialogButtonsHolder);
                dom.buttonsHolder = findElementByData(dom.buttonsWrapper, 'alertify-btn-holder');

                if (item.type === "prompt") {
                    var inputEl = createElementFromHtml(this.templates.dialogInput);
                    dom.input = findElementByData(inputEl, "alertify-input");
                    dom.content.appendChild(inputEl);
                }

                dom.container.appendChild(dom.wrapper);
                dom.wrapper.appendChild(dom.dialog);
                dom.dialog.appendChild(dom.content);
                dom.dialog.appendChild(dom.buttonsWrapper);
                dom.buttonsHolder.innerHTML = "";

                dom.buttons = [];
                for (var i = 0; i < buttons.length; i++) {
                    var btnLabelEl = findElementByData(buttons[i].element, "alertify-btn");
                    btnLabelEl.innerHTML = buttons[i].label;
                    dom.buttonsHolder.appendChild(buttons[i].element);
                }

                return dom;
            },

            prepareDialogButton: function(type, button) {
                var buttonObject = {};
                if(button && typeof button === "object" && !(button instanceof Array)) {
                    buttonObject = button;
                }
                if(typeof button === "function") {
                    buttonObject.click = button;
                }
                buttonObject.type = type;
                return buttonObject;
            },

            createButtonsDefinition: function(item) {
                var definitions = [];
                for (var i = 0; i < item.buttons.length; i++) {
                    var btn = this.buildButtonObject(item.buttons[i]);

                    if ((item.type === "dialog") ||
                        (item.type === "alert" && btn.type === "ok") ||
                        (["confirm", "prompt"].indexOf(item.type) !== -1 && ["ok", "cancel"].indexOf(btn.type) !== -1)
                    ) {
                        btn.element = createElementFromHtml(btn.template);
                        definitions.push(btn);
                    }
                }
                return definitions;
            },

            buildButtonObject: function(obj) {
                var btn = {};
                var type = obj.type || "custom";
                var db = this.dialogButtons;

                var allowedTypes = ["ok", "cancel", "custom"];
                if(typeof obj.type !== "undefined" && allowedTypes.indexOf(obj.type) === -1) {
                    throw new Error('Wrong button type: "' + obj.type + '". Valid values: "' + allowedTypes.join('", "') + '"');
                }

                btn.type = type;
                btn.label = (typeof obj.label !== "undefined") ? obj.label : db[type].label;
                btn.autoClose = (typeof obj.autoClose !== "undefined") ? obj.autoClose : db[type].autoClose;
                btn.template = (typeof obj.template !== "undefined") ? obj.template : db[type].template;
                btn.click = (typeof obj.click !== "undefined") ? obj.click : db[type].click;

                return btn;
            },

            /**
             * Close the log messages
             *
             * @param  {Object} elem    HTML Element of log message to close
             * @param  {Number} wait    [optional] Time (in ms) to wait before automatically hiding the message, if 0 never hide
             *
             * @return {undefined}
             */
            close: function(elem, wait) {

                wait = wait && !isNaN(+wait) ? +wait : this.logDelay;

                if (wait < 0) {
                    hideElement(elem);
                } else if(wait > 0) {
                    setTimeout(function() {
                        hideElement(elem);
                    }, wait);
                }

            },

            /**
             * Create a dialog box
             *
             * @param  {String}   message      The message passed from the callee
             * @param  {String}   type         Type of dialog to create
             * @param  {Array}    buttons      [Optional] Array of button objects
             *
             * @return {Object}
             */
            dialog: function(message, type, buttons) {
                return this.setup({
                    type: type,
                    message: message,
                    buttons: buttons
                });
            },

            /**
             * Show a new log message box
             *
             * @param  {String} message    The message passed from the callee
             * @param  {String} type       [Optional] Optional type of log message
             * @param  {Function} click    [Optional] Callback function when clicked the log
             *
             * @return {Object}
             */
            log: function(message, type, click) {

                if (logsUI && logsUI.elements.length) {
                    var diff = logsUI.elements.length - this.logMaxItems;
                    if (diff >= 0) {
                        for (var i = 0, _i = diff + 1; i < _i; i++) {
                            this.close(logsUI.elements[i], -1);
                        }
                    }
                }

                this.notify(message, type, click);
            },

            setLogContainerClass: function(string) {
                this.logContainerClass = _defaults.logContainerClass + " " + string;
            },

            setLogPosition: function(string) {
                var position = string.split(' ');
                if( ["top", "bottom"].indexOf(position[0]) !== -1 &&
                    ["left", "right"].indexOf(position[1]) !== -1) {
                    this.logPosition = string;
                }
            },

            setupLogContainer: function() {

                var className = this.logContainerClass + " " + this.logPosition;
                var recreateContainer = (logsUI && logsUI.container.parentNode !== this.parent);

                if (! logsUI || recreateContainer) {
                    if(recreateContainer) {
                        hideElement(logsUI.container);
                    }
                    logsUI = {};
                    logsUI.container = document.createElement("div");
                    logsUI.container.className = className;
                    this.parent.appendChild(logsUI.container);
                }

                // Make sure it's positioned properly.
                if (logsUI.container.className !== className) {
                    logsUI.container.className = className;
                }
            },

            /**
             * Add new log message
             * If a type is passed, a class name "{type}" will get added.
             * This allows for custom look and feel for various types of notifications.
             *
             * @param  {String} message    The message passed from the callee
             * @param  {String} type       [Optional] Type of log message
             * @param  {Function} click    [Optional] Callback function when clicked the log
             *
             * @return {undefined}
             */
            notify: function(message, type, click) {

                this.setupLogContainer();
                var ui = {};
                var domLog = {};
                ui.dom = domLog;

                domLog.wrapper = createElementFromHtml(this.templates.logMessage);
                domLog.message = findElementByData(domLog.wrapper, 'alertify-log-msg');

                addClass(domLog.message, type);
                if (_alertify.logTemplateMethod) {
                    domLog.message.innerHTML = _alertify.logTemplateMethod(message);
                } else {
                    domLog.message.innerHTML = message;
                }

                ui.closeLog = function() {
                    hideElement(domLog.wrapper);
                };

                // Add the click handler, if specified.
                if ("function" === typeof click) {
                    domLog.wrapper.addEventListener("click", function (event) {
                        click(event, ui);
                    });
                }

                if(!logsUI.elements) {
                    logsUI.elements = [];
                }
                logsUI.elements.push(domLog.wrapper);
                logsUI.container.appendChild(domLog.wrapper);
                setTimeout(function() {
                    domLog.wrapper.className += " show";
                }, 10);

                this.close(domLog.wrapper, this.logDelay);
            },

            /**
             * Initiate all the required pieces for the dialog box
             *
             * @return {undefined}
             */
            setup: function(item) {

                var buttons = this.createButtonsDefinition(item);
                var domDialog = this.build(item, buttons);

                var btnOK;
                var dialogUI = {};
                var clickedButton;
                var input = domDialog.input;

                for (var i = 0; i < buttons.length; i++) {
                    if(buttons[i].type === "ok") {
                        btnOK = buttons[i].element;
                    }
                }

                // Set default value of input
                if (input && typeof this.promptValue === "string") {
                    input.value = this.promptValue;
                }

                dialogUI.dom = domDialog;

                dialogUI.closeDialog = function() {
                    hideElement(domDialog.container);
                };

                dialogUI.centerDialog = function() {
                    centerDialog(domDialog.wrapper);
                };

                dialogUI.setMessage = function(message) {
                    domDialog.message.innerHTML = message;
                };

                dialogUI.setContent = function(content) {
                    domDialog.content.innerHTML = content;
                };

                dialogUI.getInputValue = function() {
                    if(domDialog.input) {
                        return domDialog.input.value;
                    }
                };

                dialogUI.getButtonObject = function() {
                    if(clickedButton) {
                        return {
                            type: clickedButton.type,
                            label: clickedButton.label,
                            autoClose: clickedButton.autoClose,
                            element: clickedButton.element
                        };
                    }
                };

                function setupHandlers(resolve) {
                    if ("function" !== typeof resolve) {
                        // promises are not available so resolve is a no-op
                        resolve = function () {};
                    }

                    for (var i = 0; i < buttons.length; i++) {
                        var btn = buttons[i];

                        var listener = (function (button) {return function(event) {
                            clickedButton = button;
                            if (button.click && "function" === typeof button.click) {
                                button.click(event, dialogUI);
                            }

                            resolve({
                                ui: dialogUI,
                                event: event
                            });

                            if (button.autoClose === true) {
                                dialogUI.closeDialog();
                            }
                        }}(btn));

                        btn.element.addEventListener("click", listener);
                    }

                    if (input) {
                        input.addEventListener("keyup", function(event) {
                            if (event.which === 13) {
                                btnOK.click();
                            }
                        });
                    }
                }

                var promise;

                if (typeof Promise === "function") {
                    promise = new Promise(setupHandlers);
                } else {
                    setupHandlers();
                }

                if(this.dialogPersistent === false) {
                    domDialog.container.addEventListener("click", function(e) {
                        if(e.target === this || e.target === domDialog.wrapper) {
                            hideElement(domDialog.container);
                        }
                    });
                }

                window.onresize = function(){
                    dialogUI.centerDialog();
                };

                this.parent.appendChild(domDialog.container);
                setTimeout(function() {
                    removeClass(domDialog.container, "hide");
                    dialogUI.centerDialog();
                    if(input && item.type && item.type === "prompt") {
                        input.select();
                        input.focus();
                    } else {
                        if (btnOK) {
                            btnOK.focus();
                        }
                    }
                }, 100);

                return promise;
            },

            setLogDelay: function(time) {
                time = time || 0;
                this.logDelay = isNaN(time) ? _defaults.logDelay : parseInt(time, 10);
                return this;
            },

            setLogMaxItems: function(num) {
                this.logMaxItems = parseInt(num || _defaults.logMaxItems);
            },

            setDialogWidth: function(width) {
                if(typeof width === "number") {
                    width += 'px';
                }
                this.dialogWidth = (typeof width === "string") ? width : _defaults.dialogWidth;
            },

            setDialogPersistent: function(bool) {
                this.dialogPersistent = bool;
            },

            setDialogContainerClass: function(string) {
                this.dialogContainerClass = _defaults.dialogContainerClass + " " + string;
            },

            setTheme: function(theme) {
                if(!theme) return;
                if(typeof theme === "string") {
                    switch (theme.toLowerCase()) {
                        case "bootstrap":
                            this.dialogButtons.ok.template = '<button data-alertify-btn="ok" class="btn btn-primary" tabindex="1"></button>';
                            this.dialogButtons.cancel.template = '<button data-alertify-btn="cancel" class="btn btn-danger" tabindex="2"></button>';
                            this.dialogButtons.custom.template = '<button data-alertify-btn="custom" class="btn btn-default" tabindex="3"></button>';
                            this.templates.dialogInput = "<input data-alertify-input class='form-control' type='text'>";
                            break;
                        case "purecss":
                            this.dialogButtons.ok.template = '<button data-alertify-btn="ok" class="pure-button" tabindex="1"></button>';
                            this.dialogButtons.cancel.template = '<button data-alertify-btn="cancel" class="pure-button" tabindex="2"></button>';
                            this.dialogButtons.custom.template = '<button data-alertify-btn="custom" class="pure-button" tabindex="3"></button>';
                            break;
                        case "mdl":
                        case "material-design-light":
                            this.dialogButtons.ok.template = '<button data-alertify-btn="ok" class=" mdl-button mdl-js-button mdl-js-ripple-effect"  tabindex="1"></button>';
                            this.dialogButtons.cancel.template = '<button data-alertify-btn="cancel" class=" mdl-button mdl-js-button mdl-js-ripple-effect" tabindex="2"></button>';
                            this.dialogButtons.custom.template = '<button data-alertify-btn="custom" class=" mdl-button mdl-js-button mdl-js-ripple-effect" tabindex="3"></button>';
                            this.templates.dialogInput = '<div class="mdl-textfield mdl-js-textfield"><input data-alertify-input class="mdl-textfield__input"></div>';
                            break;
                        case "angular-material":
                            this.dialogButtons.ok.template = '<button data-alertify-btn="ok" class="md-primary md-button" tabindex="1"></button>';
                            this.dialogButtons.cancel.template = '<button data-alertify-btn="cancel" class="md-button" tabindex="2"></button>';
                            this.dialogButtons.custom.template = '<button data-alertify-btn="custom" class="md-button" tabindex="3"></button>';
                            this.templates.dialogInput = '<div layout="column"><md-input-container md-no-float><input data-alertify-input type="text"></md-input-container></div>';
                            break;
                        case "default":
                        default:
                            this.dialogButtons = clone(_defaults.dialogButtons);
                            this.templates = clone(_defaults.templates);
                            break;
                    }
                }
                if(typeof theme === "object") {
                    var tmplList = Object.keys(this.templates);
                    for (var name in theme) {
                        if(tmplList.indexOf(name) === -1) {
                            throw new Error('Wrong template name: "' + name + '". Valid values: "' + tmplList.join('", "') + '"');
                        }
                        this.templates[name] = theme[name];
                    }
                }
            },

            reset: function() {
                this.setTheme("default");
                this.parent = _defaults.parent;
                this.dialogWidth = _defaults.dialogWidth;
                this.dialogPersistent = _defaults.dialogPersistent;
                this.dialogContainerClass = _defaults.dialogContainerClass;
                this.promptValue = "";
                this.logDelay = _defaults.logDelay;
                this.logMaxItems = _defaults.logMaxItems;
                this.logPosition = _defaults.logPosition;
                this.logContainerClass = _defaults.logContainerClass;
                this.logTemplateMethod = null;
            },

            injectCSS: function() {
                if (!document.querySelector("#alertifyCSS")) {
                    var head = document.getElementsByTagName("head")[0];
                    var css = document.createElement("style");
                    css.type = "text/css";
                    css.id = "alertifyCSS";
                    head.insertBefore(css, head.firstChild);
                }
            },

            removeCSS: function() {
                var css = document.querySelector("#alertifyCSS");
                if (css && css.parentNode) {
                    css.parentNode.removeChild(css);
                }
            }

        };

        _alertify.injectCSS();

        return {
            _$$alertify: _alertify,
            _$$defaults: _defaults,
            parent: function(elem) {
                _alertify.parent = elem;
            },
            reset: function() {
                _alertify.reset();
                return this;
            },
            dialog: function(message, buttons) {
                return _alertify.dialog(message, "dialog", buttons) || this;
            },
            alert: function(message, okButton) {
                var buttons = [_alertify.prepareDialogButton("ok", okButton)];
                return _alertify.dialog(message, "alert", buttons) || this;
            },
            confirm: function(message, okButton, cancelButton) {
                var buttons = [
                    _alertify.prepareDialogButton("ok", okButton),
                    _alertify.prepareDialogButton("cancel", cancelButton)
                ];
                return _alertify.dialog(message, "confirm", buttons) || this;
            },
            prompt: function(message, defaultValue, okButton, cancelButton) {
                var buttons = [
                    _alertify.prepareDialogButton("ok", okButton),
                    _alertify.prepareDialogButton("cancel", cancelButton)
                ];
                _alertify.promptValue = defaultValue || '';
                return _alertify.dialog(message, "prompt", buttons) || this;
            },
            log: function(message, click) {
                _alertify.log(message, "default", click);
                return this;
            },
            success: function(message, click) {
                _alertify.log(message, "success", click);
                return this;
            },
            warning: function(message, click) {
                _alertify.log(message, "warning", click);
                return this;
            },
            error: function(message, click) {
                _alertify.log(message, "error", click);
                return this;
            },
            dialogWidth: function(width) {
                _alertify.setDialogWidth(width);
                return this;
            },
            dialogPersistent: function(bool) {
                _alertify.setDialogPersistent(bool);
                return this;
            },
            dialogContainerClass: function(str) {
                _alertify.setDialogContainerClass(str || "");
                return this;
            },
            logDelay: function(time) {
                _alertify.setLogDelay(time);
                return this;
            },
            logMaxItems: function(num) {
                _alertify.setLogMaxItems(num);
                return this;
            },
            logPosition: function(str) {
                _alertify.setLogPosition(str || "");
                return this;
            },
            logContainerClass: function(str) {
                _alertify.setLogContainerClass(str || "");
                return this;
            },
            logMessageTemplate: function(templateMethod) {
                _alertify.logTemplateMethod = templateMethod;
                return this;
            },
            theme: function(theme) {
                _alertify.setTheme(theme);
                return this;
            },
            clearDialogs: function() {
                var dialog;
                while(dialog = _alertify.parent.querySelector(':scope > .' + _defaults.dialogContainerClass)) {
                    _alertify.parent.removeChild(dialog);
                }
                return this;
            },
            clearLogs: function() {
                if(logsUI) {
                    logsUI.container.innerHTML = "";
                }
                return this;
            },
            version: _alertify.version
        };
    }

    // AMD, window, and NPM support
    if ("undefined" !== typeof module && !! module && !! module.exports) {
        // Preserve backwards compatibility
        module.exports = function() {
            return new Alertify();
        };
        var obj = new Alertify();
        for (var key in obj) {
            module.exports[key] = obj[key];
        }
    } else if (typeof define === "function" && define.amd) {
        define(function() {
            return new Alertify();
        });
    } else {
        window.alertify = new Alertify();
    }

}());
