	$(function(){
		
		initBindings();


	});


	function initBindings() {
		

		
		/** numeric formats **/
		if($('#productPriceAmount')) {
			$('#productPriceAmount').blur(function() {
				$('#help-price').html(null);
				$(this).formatCurrency({ roundToDecimalPlace: 2, eventOnDecimalsEntered: true, symbol: ''});
			})
			.keyup(function(e) {
					var e = window.event || e;
					var keyUnicode = e.charCode || e.keyCode;
					if (e !== undefined) {
						switch (keyUnicode) {
							case 16: break; // Shift
							case 17: break; // Ctrl
							case 18: break; // Alt
							case 27: this.value = ''; break; // Esc: clear entry
							case 35: break; // End
							case 36: break; // Home
							case 37: break; // cursor left
							case 38: break; // cursor up
							case 39: break; // cursor right
							case 40: break; // cursor down
							case 78: break; // N (Opera 9.63+ maps the "." from the number key section to the "N" key too!) (See: http://unixpapa.com/js/key.html search for ". Del")
							case 110: break; // . number block (Opera 9.63+ maps the "." from the number block to the "N" key (78) !!!)
							case 190: break; // .
							default: $(this).formatCurrency({ colorize: true, negativeFormat: '-%s%n', roundToDecimalPlace: -1, eventOnDecimalsEntered: true, symbol: ''});
						}
					}
				})
			.bind('decimalsEntered', function(e, cents) {
				if (String(cents).length > 2) {
					var errorMsg = priceFormatMessage + ' (0.' + cents + ')';
					$('#help-price').html(errorMsg);
				}
			});
		}
	}