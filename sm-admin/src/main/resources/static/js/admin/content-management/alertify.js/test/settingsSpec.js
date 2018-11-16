/* eslint-env karma, jasmine */
/* eslint strict: [2, "global"] */
"use strict";

describe("settings unit tests", function() {

    var $alertify;
    var $defaults;

    beforeEach(function() {
        alertify.reset();
        $alertify = alertify._$$alertify;
        $defaults = alertify._$$defaults;
    });

    it("should set a version number", function() {
        expect(typeof alertify.version).toBe("string");
        expect(alertify.version).toEqual($alertify.version);
    });

    it("should set default logMaxItems", function() {
        expect($alertify.logMaxItems).toBe(2);
    });

    it("should set default logMaxItems", function() {
        alertify.logMaxItems(10);
        expect($alertify.logMaxItems).toBe(10);
    });

    it("should set empty default input prompt value", function() {
        expect($alertify.promptValue).toBe("");
    });

    it("should set input prompt value", function() {
        alertify.inputDefaultValue("alertify rocks");
        expect($alertify.promptValue).toBe("alertify rocks");
    });

    it("should set default ok btn", function() {
        expect($alertify.okLabel).toBe("Ok");
    });

    it("should set ok btn text", function() {
        alertify.okBtn("Yes");
        expect($alertify.okLabel).toBe("Yes");
    });

    it("should set default cancel btn", function() {
        expect($alertify.cancelLabel).toBe("Cancel");
    });

    it("should set cancel btn text", function() {
        alertify.cancelBtn("No");
        expect($alertify.cancelLabel).toBe("No");
    });

    it("should set the default delay to 5000", function() {
        expect($alertify.logDelay).toBe(5000);
    });

    it("should set delay option", function() {
        alertify.logDelay(1000);
        expect($alertify.logDelay).toBe(1000);
    });

    it("should reset all options when reset called", function() {
        alertify.logDelay(1000);
        alertify.cancelBtn("No");
        alertify.okBtn("Yes");
        alertify.inputDefaultValue("alertify rocks");
        alertify.logMaxItems(10);
        alertify.reset();
        expect($alertify.logDelay).toBe(5000);
        expect($alertify.cancelLabel).toBe("Cancel");
        expect($alertify.okLabel).toBe("Ok");
        expect($alertify.promptValue).toBe("");
        expect($alertify.logMaxItems).toBe(2);
    });

    it("should inject CSS by default, only once", function() {
        expect(!!document.querySelector("#alertifyCSS")).toBe(true);
    });

    it("should remove CSS", function() {
        $alertify.removeCSS();
        expect(!!document.querySelector("#alertifyCSS")).toBe(false);
    });

    it("should not inject CSS if element already exists", function() {
        $alertify.removeCSS();

        var fakeCSS = document.createElement("fake");
        fakeCSS.id = "alertifyCSS";
        document.body.appendChild(fakeCSS);

        $alertify.injectCSS();
        expect(document.querySelector("#alertifyCSS").tagName).toBe("FAKE");
    });

    it("should change the delay", function() {
        var newDelay;
        newDelay = 100;
        alertify.logDelay(newDelay);
        expect($alertify.logDelay).toBe(newDelay);

        // should be transform to integer
        alertify.logDelay("200");
        expect($alertify.logDelay).toBe(200);
        
        // should be reset to the default delay
        alertify.logDelay("a");
        expect($alertify.logDelay).not.toBe(undefined);
        expect($alertify.logDelay).toBe($defaults.logDelay);
    });
});
