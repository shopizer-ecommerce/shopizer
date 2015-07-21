/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */

!function(a){"function"==typeof define&&define.amd?define(["jquery"],a):"object"==typeof exports?a(require("jquery")):a(jQuery)}(function(a){function c(a){return h.raw?a:encodeURIComponent(a)}function d(a){return h.raw?a:decodeURIComponent(a)}function e(a){return c(h.json?JSON.stringify(a):String(a))}function f(a){0===a.indexOf('"')&&(a=a.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return a=decodeURIComponent(a.replace(b," ")),h.json?JSON.parse(a):a}catch(c){}}function g(b,c){var d=h.raw?b:f(b);return a.isFunction(c)?c(d):d}var b=/\+/g,h=a.cookie=function(b,f,i){if(arguments.length>1&&!a.isFunction(f)){if(i=a.extend({},h.defaults,i),"number"==typeof i.expires){var j=i.expires,k=i.expires=new Date;k.setTime(+k+864e5*j)}return document.cookie=[c(b),"=",e(f),i.expires?"; expires="+i.expires.toUTCString():"",i.path?"; path="+i.path:"",i.domain?"; domain="+i.domain:"",i.secure?"; secure":""].join("")}for(var l=b?void 0:{},m=document.cookie?document.cookie.split("; "):[],n=0,o=m.length;o>n;n++){var p=m[n].split("="),q=d(p.shift()),r=p.join("=");if(b&&b===q){l=g(r,f);break}b||void 0===(r=g(r))||(l[q]=r)}return l};h.defaults={},a.removeCookie=function(b,c){return void 0===a.cookie(b)?!1:(a.cookie(b,"",a.extend({},c,{expires:-1})),!a.cookie(b))}});

/* Style Switcher
 * Author: HtmlCoder
 * Author URI:http://www.htmlcoder.me
 * Author e-mail:htmlcoder.me@gmail.com
 * Version:1.2.0
 * Created:20 May 2014
 * Updated:19 Oct 2014
 * License URI:http://wrapbootstrap.com
 * File Description: Style Switcher
 */

jQuery(document).ready(function($) {

		//SIDE PANEL 
		//--------------------------------------------------------
		style_switcher = $('.style-switcher'),
		panelWidth = style_switcher.outerWidth(true);
			
		$('.style-switcher .trigger').on("click", function(){
			var $this = $(this);
			if ($(".style-switcher.closed").length>0) {
				style_switcher.animate({"left" : "0px"});
				$(".style-switcher.closed").removeClass("closed");
				$(".style-switcher").addClass("opened");
				$(".style-switcher .trigger i").removeClass("icon-tools").addClass("fa fa-times");
			} else {
				$(".style-switcher.opened").removeClass("opened");
				$(".style-switcher").addClass("closed");
				$(".style-switcher .trigger i").removeClass("fa fa-times").addClass("icon-tools");
				style_switcher.animate({"left" : '-' + panelWidth});
			}
			return false;
		});
		
		// style change 
		var link = $('link[data-style="styles"]');

		// resume last chosen style
		var stylesheet = $.cookie('stylesheet'),
			footer_bg = $.cookie('footer_bg'),
			header_bg = $.cookie('header_bg'),
			layout_mode = $.cookie('layout_mode'),		
			pattern = $.cookie('pattern');

		$(".style-switcher .selected").removeClass("selected");
		if (!($.cookie('stylesheet'))) {
			$.cookie('stylesheet', 'red', 365);
			stylesheet = $.cookie('stylesheet');
			$('.style-switcher .styleChange li[data-style="'+stylesheet+'"]').addClass("selected");
		} else {
			link.attr('href','css/skins/' + stylesheet + '.css');
			$('.style-switcher .styleChange li[data-style="'+stylesheet+'"]').addClass("selected");
			if (($.cookie('header_bg')=="light") && !($(".header-page-dark").length>0)) {
				document.getElementById("logo").src="images/logo_" + stylesheet + ".png";
			} else if (($.cookie('header_bg')=="dark") && ($(".header-page-light").length>0)) { 
				document.getElementById("logo").src="images/logo_" + stylesheet + ".png";
			} else {
				document.getElementById("logo").src="images/logo_dark_header_" + stylesheet + ".png";
			};
		};

		if (!($.cookie('layout_mode'))) {
			$.cookie('layout_mode', 'wide', 365);
			layout_mode = $.cookie('layout_mode');
			$("body").addClass(layout_mode);
			$('.style-switcher .layoutChange li[data-style="wide"]').addClass("selected");
		} else {
			if (layout_mode=="boxed") {
				$("body").addClass(layout_mode);
				$("body").removeClass("wide");
				$('.style-switcher .layoutChange li[data-style="boxed"]').addClass("selected");
				$('.style-switcher .layoutChange li[data-style="wide"]').removeClass("selected");
				$(".testimonial .container").css("marginLeft", "0");
			} else { 
				$("body").addClass(layout_mode);
				$("body").removeClass("boxed pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9");
				$('.style-switcher .layoutChange li[data-style="boxed"]').removeClass("selected");
				$('.style-switcher .layoutChange li[data-style="wide"]').addClass("selected");
				$(".testimonial .container").css("marginLeft", "auto");
			};
		};

		if ((layout_mode =="boxed") && $.cookie('pattern')) {
			$('.style-switcher .patternChange li[data-style="'+pattern+'"]').addClass("selected");
			$("body").removeClass("pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9 wide");
			$("body").addClass(pattern); 
		} else if (layout_mode =="boxed") {
			$("body").removeClass("pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9");
			$('.style-switcher .patternChange li[data-style="pattern-0"]').addClass("selected");
		} else {
			$('.style-switcher .patternChange li.selected').removeClass("selected");
			$("body").removeClass("pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9 boxed");
		};

		if (!($.cookie('footer_bg'))) {
			$.cookie('footer_bg', 'dark', 365);
			footer_bg = $.cookie('footer_bg');
			$('.style-switcher .footerChange li[data-style="dark"]').addClass("selected");
		} else {
			if (footer_bg=="dark") {
				$("#footer").removeClass("light");
				$('.style-switcher .footerChange li[data-style="dark"]').addClass("selected");
				$('.style-switcher .footerChange li[data-style="light"]').removeClass("selected");
			} else { 
				$("#footer").addClass("light");
				$('.style-switcher .footerChange li[data-style="dark"]').removeClass("selected");
				$('.style-switcher .footerChange li[data-style="light"]').addClass("selected");
			};
		};

		if (!($(".header-page-dark").length>0) && !($(".header-page-light").length>0)) {
			if (!($.cookie('header_bg'))) {
				$.cookie('header_bg', 'light', 365);
				header_bg = $.cookie('header_bg');
				$('.style-switcher .headerChange li[data-style="light"]').addClass("selected");
			} else {
				if (header_bg=="light") {
					if ($(".header-top-white-bg").length>0) {
						$(".header-top").removeClass("header-top-white-bg");
						$(".header-top").addClass("white-bg");
					};
					if ($(".header-gray-bg").length>0) {
						$(".header-gray-bg").addClass("gray-bg");
						$(".header-gray-bg").removeClass("header-gray-bg");
					};
					$("header.header, header .header").removeClass("dark");
					$(".header-top").removeClass("dark");
					$('.style-switcher .headerChange li[data-style="dark"]').removeClass("selected");
					$('.style-switcher .headerChange li[data-style="light"]').addClass("selected");
				} else { 
					if ($(".header-top.white-bg").length>0) {
						$(".header-top").removeClass("white-bg");
						$(".header-top").addClass("header-top-white-bg");
					};
					if ($(".header.gray-bg").length>0) {
						$(".header.gray-bg").addClass("header-gray-bg");
						$(".header.gray-bg").removeClass("gray-bg");
					};
					$("header.header, header .header").addClass("dark");
					$(".header-top").addClass("dark");
					$('.style-switcher .headerChange li[data-style="dark"]').addClass("selected");
					$('.style-switcher .headerChange li[data-style="light"]').removeClass("selected");
				};
			};
		};

		// switch colors
		$('.style-switcher .styleChange li').on('click',function(){
		var $this = $(this),
			stylesheet = $this.data('style');
		$(".style-switcher .styleChange .selected").removeClass("selected");
		$this.addClass("selected");
		link.attr('href', 'css/skins/' + stylesheet + '.css');
		if ($.cookie('header_bg')=="light") {
			document.getElementById("logo").src="images/logo_" + stylesheet + ".png";
		} else {
			document.getElementById("logo").src="images/logo_dark_header_" + stylesheet + ".png";
		};
		$.cookie('stylesheet', stylesheet, 365);		
		});

		// switch patterns
		$('.style-switcher .patternChange li').on('click',function(){
		var $this = $(this),
			pattern = $this.data('style');
		$(".style-switcher .patternChange .selected").removeClass("selected");
		$this.addClass("selected");
		$("body").removeClass("pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9 wide");
		$("body").addClass(pattern);
		$("body").addClass("boxed");
		$('.style-switcher .layoutChange li[data-style="boxed"]').addClass("selected");
		$('.style-switcher .layoutChange li[data-style="wide"]').removeClass("selected");
		$(".testimonial .container").css("marginLeft", "0");
		$(".style-switcher select").val("boxed");
		$.cookie('pattern', pattern, 365);
		$.cookie('layout_mode', 'boxed', 365);
		});

		// Switch layout
		// Boxed Layout
		$('.style-switcher .layoutChange li.boxed').on('click',function(){ 
			$("body").addClass("boxed");
			$("body").removeClass("wide");
			$('.style-switcher .layoutChange li[data-style="boxed"]').addClass("selected");
			$('.style-switcher .layoutChange li[data-style="wide"]').removeClass("selected");
			$(".testimonial .container").css("marginLeft", "0");
			$.cookie('layout_mode', 'boxed', 365);
			if ($.cookie('pattern')) {
				var pattern = $.cookie('pattern');
				$('.style-switcher .patternChange li[data-style="'+pattern+'"]').addClass("selected");
				$("body").removeClass("pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9");
				$("body").addClass(pattern);
			} else {
				$('.style-switcher .patternChange li[data-style="pattern-0"]').addClass("selected");
			}
		});

		// Wide Layout
		$('.style-switcher .layoutChange li.wide').on('click',function(){ 
			$("body").addClass("wide");
			$("body").removeClass("boxed pattern-0 pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9");
			$('.style-switcher .layoutChange li[data-style="boxed"]').removeClass("selected");
			$('.style-switcher .layoutChange li[data-style="wide"]').addClass("selected");
			$(".testimonial .container").css("marginLeft", "auto");
			$('.style-switcher .patternChange li.selected').removeClass("selected");
			$.cookie('layout_mode', 'wide', 365);
		});

		// Footer bg
		$('.style-switcher .footerChange li.dark').on('click',function(){ 
			$("#footer").removeClass("light");
			$('.style-switcher .footerChange li[data-style="dark"]').addClass("selected");
			$('.style-switcher .footerChange li[data-style="light"]').removeClass("selected");
			$.cookie('footer_bg', 'dark', 365);
		});

		$('.style-switcher .footerChange li.light').on('click',function(){ 
			$("#footer").addClass("light");
			$('.style-switcher .footerChange li[data-style="dark"]').removeClass("selected");
			$('.style-switcher .footerChange li[data-style="light"]').addClass("selected");
			$.cookie('footer_bg', 'light', 365);
		});

		// Header bg
		$('.style-switcher .headerChange li.dark').on('click',function(){
			var stylesheet = $.cookie('stylesheet');
			if ($(".header-top.white-bg").length>0) {
				$(".header-top").removeClass("white-bg");
				$(".header-top").addClass("header-top-white-bg");
			};
			if ($(".header.gray-bg").length>0) {
				$(".header.gray-bg").addClass("header-gray-bg");
				$(".header.gray-bg").removeClass("gray-bg");
			};
			$("header.header, header .header").addClass("dark");
			$(".header-top").addClass("dark");
			$('.style-switcher .headerChange li[data-style="dark"]').addClass("selected");
			$('.style-switcher .headerChange li[data-style="light"]').removeClass("selected");
			document.getElementById("logo").src="images/logo_dark_header_" + stylesheet + ".png";
			$.cookie('header_bg', 'dark', 365);
		});

		$('.style-switcher .headerChange li.light').on('click',function(){
			var stylesheet = $.cookie('stylesheet');
			if ($(".header-top-white-bg").length>0) {
				$(".header-top").removeClass("header-top-white-bg");
				$(".header-top").addClass("white-bg");
			};
			if ($(".header-gray-bg").length>0) {
				$(".header-gray-bg").addClass("gray-bg");
				$(".header-gray-bg").removeClass("header-gray-bg");
			};
			$("header.header, header .header").removeClass("dark");
			$(".header-top").removeClass("dark");
			$('.style-switcher .headerChange li[data-style="dark"]').removeClass("selected");
			$('.style-switcher .headerChange li[data-style="light"]').addClass("selected");
			document.getElementById("logo").src="images/logo_" + stylesheet + ".png";
			$.cookie('header_bg', 'light', 365);
		});

		//Reset All
		$('.style-switcher .resetAll li').on('click',function() { 
			// header bg
			$.cookie('header_bg', 'light', 365);
			$("header.header, header .header").removeClass("dark");
			$(".header-top").removeClass("dark");
			$('.style-switcher .headerChange li[data-style="dark"]').removeClass("selected");
			$('.style-switcher .headerChange li[data-style="light"]').addClass("selected");
			$(".header-gray-bg").addClass("gray-bg");
			$(".header-gray-bg").removeClass("header-gray-bg");
			$(".header-top-white-bg").addClass("white-bg");
			$(".header-top-white-bg").removeClass("header-top-white-bg");
			// footer bg
			$.cookie('footer_bg', 'dark', 365);
			$("#footer").removeClass("light");
			$('.style-switcher .footerChange li[data-style="dark"]').addClass("selected");
			$('.style-switcher .footerChange li[data-style="light"]').removeClass("selected");
			// layout mode
			$.cookie('layout_mode', 'wide', 365);
			$("body").addClass("wide");
			$("body").removeClass("boxed");
			$('.style-switcher .layoutChange li[data-style="boxed"]').removeClass("selected");
			$('.style-switcher .layoutChange li[data-style="wide"]').addClass("selected");
			$(".testimonial .container").css("marginLeft", "auto");
			$('.style-switcher .patternChange li.selected').removeClass("selected");
			//pattern
			$.cookie('pattern', 'pattern-0', 365);
			$("body").removeClass("reset pattern-1 pattern-2 pattern-3 pattern-4 pattern-5 pattern-6 pattern-7 pattern-8 pattern-9");
			$(".style-switcher .patternChange .selected").removeClass("selected");
			//Stylesheet
			$.cookie('stylesheet', 'red', 365);
			var stylesheet = $.cookie('stylesheet');
			$('.style-switcher .styleChange li.selected').removeClass("selected");
			$('.style-switcher .styleChange li[data-style="'+stylesheet+'"]').addClass("selected");
			link.attr('href', 'css/skins/' + stylesheet + '.css');
			document.getElementById("logo").src="images/logo_" + stylesheet + ".png";
		});

});    	