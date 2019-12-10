(function($) {
    "use strict";

    $(".click_menu").on('click', function() {
        $(".click_menu_show").slideToggle(400)
    });

    $(".click_menu2").on('click', function() {
        $(".click_menu_show2").slideToggle(400)
    });

    /*----------------------------
      Sticky Header
    ------------------------------ */
	var win = $(window);
    win.on('scroll', function() {
        var scroll = win.scrollTop();
        if (scroll < 200) {
            $("#main_h").removeClass("sticky");
        } else {
            $("#main_h").addClass("sticky");
        }
    });
    /*----------------------------
      MagnificPopup
    ------------------------------ */

    $('.image-link').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true
        }
    });
    /*----------------------------
    Slippry
    ------------------------------ */
    jQuery('#home-banner').slippry({
    	  // general elements & wrapper
    	  slippryWrapper: '<div class="sy-box banner-slider" />', // wrapper to wrap everything, including pager
    	  elements: 'article', // elments cointaining slide content

    	  // options
    	  adaptiveHeight: false, // height of the sliders adapts to current 
    	  captions: false,
    	  
    	  pager: false,

    	  // pager
    	  //pagerClass: 'banner-pager',

    	  // transitions
    	  transition: 'horizontal', // fade, horizontal, kenburns, false
    	  speed: 1200,
    	  pause: 8000,

    	  // slideshow
    	  autoDirection: 'prev'
    });
    
    
    /*----------------------------
     jQuery MeanMenu
    ------------------------------ */
    $('#mobile-menu').meanmenu();

    /*----------------------------
     wow js active
    ------------------------------ */
    new WOW().init();
    /*----------------------------
     slider-active
    ------------------------------ */
    $('.slider-active').owlCarousel({
            loop: true,
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 1
                },
                1000: {
                    items: 1
                }
            }
        })
        /*----------------------------
         Service-active
        ------------------------------ */
    $('.service-active').owlCarousel({
            loop: true,
            autoplay: true,
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 4
                },
                1000: {
                    items: 4
                },
                1200: {
                    items: 4
                }
            }
        })
        /*----------------------------
         Product-carousel-active
        ------------------------------ */
    $('.product-carousel-active').owlCarousel({
            loop: true,
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 3
                },
                1000: {
                    items: 4
                },
                1200: {
                    items: 5
                }
            }
        })
        /*----------------------------
         Top-interesting-active
        ------------------------------ */
    $('.top-interesting-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 3
                },
                1000: {
                    items: 4
                },
                1200: {
                    items: 6
                }
            }
        })
        /*----------------------------
         New-product-home-2-active
        ------------------------------ */
    $('.new-product-home-2-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 3
                },
                1000: {
                    items: 4
                },
                1200: {
                    items: 5
                }
            }
        })
        /*----------------------------
         New-product-item-3-active
        ------------------------------ */
    $('.new-product-item-3-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 3
                },
                1000: {
                    items: 3
                },
                1200: {
                    items: 4
                }
            }
        })
        /*----------------------------
        New-product-home-2-slider-active
        ------------------------------ */
    $('.new-product-home-2-slider-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 3
                },
                1000: {
                    items: 2
                },
                1200: {
                    items: 2
                }
            }
        })
        /*----------------------------
          New-product-home-4-active
        ------------------------------ */
    $('.new-product-home-4-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 3
                },
                1200: {
                    items: 3
                }
            }
        })
        /*----------------------------
          Hot-deal-active
        ------------------------------ */
    $('.hot-deal-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 1
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 1
                },
                1200: {
                    items: 1
                }
            }
        })
        /*----------------------------
          Feature-home-4-active
        ------------------------------ */
    $('.feature-home-4-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 1
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 2
                },
                1200: {
                    items: 3
                }
            }
        })
        /*---------------------
 countdown
--------------------- */
    $('[data-countdown]').each(function() {
        var $this = $(this),
            finalDate = $(this).data('countdown');
        $this.countdown(finalDate, function(event) {
            $this.html(event.strftime('<span class="cdown days"><span class="time-count">%-D</span> <p>Days</p></span> <span class="cdown hour"><span class="time-count">%-H</span> <p>Hour</p></span> <span class="cdown minutes"><span class="time-count">%M</span> <p>Min</p></span> <span class="cdown second"> <span><span class="time-count">%S</span> <p>Sec</p></span>'));
        });
    });
    /*----------------------------
      Feature-home-2-active
    ------------------------------ */
    $('.feature-home-2-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 1
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 1
                },
                1200: {
                    items: 1
                }
            }
        })
        /*----------------------------
          Shop-sideber-active
        ------------------------------ */
    $('.shop-sideber-active').owlCarousel({
            loop: true,
            autoplay: false,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 1
                },
                768: {
                    items: 1
                },
                1000: {
                    items: 1
                },
                1200: {
                    items: 1
                }
            }
        })
        /*----------------------------
          Static-slider-active
        ------------------------------ */
    $('.static-slider-active').owlCarousel({
            loop: true,
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 1
                },
                1000: {
                    items: 1
                }
            }
        })
        /*----------------------------
          Feature-preduct-active
        ------------------------------ */
    $('.feature-preduct-active').owlCarousel({
            loop: true,
            nav: true,
            navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 2
                }
            }
        })
        /*----------------------------
          Upsell-product-active
        ------------------------------ */
    $('.upsell-product-active').owlCarousel({
            loop: true,
            nav: true,
            navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                480: {
                    items: 2
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 3
                }
            }
        })
        /*----------------------------
  Blog-carousel-active 
------------------------------ */
    $('.blog-carousel-active').owlCarousel({
            loop: true,
            nav: true,
            navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
            autoplay: false,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 1
                },
                768: {
                    items: 2
                },
                1000: {
                    items: 3
                }
            }
        })
        /*----------------------------
  Blog-carousel-active2 
------------------------------ */
    $('.blog-carousel-active2').owlCarousel({
        loop: true,
        nav: true,
        navText: ["<i class='fa fa-angle-right'></i>", "<i class='fa fa-angle-left'></i>"],
        autoplay: false,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            768: {
                items: 2
            },
            1000: {
                items: 2
            }
        }
    })

    /*-------------------------
      showlogin toggle function
    --------------------------*/
    $('#showlogin').on('click', function() {
        $('#checkout-login').slideToggle(900);
    });

    /*-------------------------
      showcoupon toggle function
    --------------------------*/
    $('#showcoupon').on('click', function() {
        $('#checkout_coupon').slideToggle(900);
    });

    /*-------------------------
      Create an account toggle function
    --------------------------*/
    $('#cbox').on('click', function() {
        $('#cbox_info').slideToggle(900);
    });

    /*-------------------------
      Create an account toggle function
    --------------------------*/
    //$('#ship-box').on('click', function() {
    //	console.log('ship-box clicked');
    //    $('#ship-box-info').slideToggle(1000);
    //});

    /*----------------------------
     price-slider active
    ------------------------------ */
	var ammout = $("#amount");
	var sliderRange = $("#slider-range");
    sliderRange.slider({
        range: true,
        min: 40,
        max: 600,
        values: [60, 570],
        slide: function(event, ui) {
            ammout.val("$" + ui.values[0] + " - $" + ui.values[1]);
        }
    });
    ammout.val("$" + sliderRange.slider("values", 0) +
        " - $" + sliderRange.slider("values", 1));

    /*--------------------------
     scrollUp
    ---------------------------- */
    $.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });

})(jQuery);