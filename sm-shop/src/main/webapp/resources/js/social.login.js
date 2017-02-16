 	  var facebookKey;
      function signOut() {
    	    var auth2 = gapi.auth2.getAuthInstance();
    	    auth2.signOut().then(function () {
    	      console.log('User signed out.');
    	    });
    	    logout();
    	    document.location = "/shop/customer/logout";
    	  }
      
      function onSuccess(googleUser) {
    	// Useful data for your client-side scripts:
          var profile = googleUser.getBasicProfile();
                     
    	  $('#name').val(profile.getGivenName());
    	  $('#surname').val(profile.getFamilyName());
    	  $('#email').val(profile.getEmail());
    	  $('#login-button').click();
    	  
      }
      function onFailure(error) {
        console.log(error);
      }
      function renderButton() {
        gapi.signin2.render('g-signin2', {
          'scope': 'profile email',
          'width': 300,
          'height': 50,
          'longtitle': true,
          'theme': 'dark',
          'onsuccess': onSuccess,
          'onfailure': onFailure
        });
      }

      // This is called with the results from from FB.getLoginStatus().
      function statusChangeCallback(response) {
       
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
          // Logged into your app and Facebook.
          storeCall();
        } else if (response.status === 'not_authorized') {
          // The person is logged into Facebook, but not your app.
          console.log("The person is logged into Facebook, but not your app.");
        } else {
          // The person is not logged into Facebook, so we're not sure if
          // they are logged into this app or not.
        }
      }

      // This function is called when someone finishes with the Login
      // Button.  See the onlogin handler attached to it in the sample
      // code below.
      function checkLoginState(keyFacebook) {
    	facebookKey = keyFacebook;
        FB.getLoginStatus(function(response) {
          statusChangeCallback(response);
        });
      }

      window.fbAsyncInit = function() {
        FB.init({
          appId: facebookKey,
          cookie: true, // enable cookies to allow the server to access 
          // the session
          xfbml: true, // parse social plugins on this page
          version: 'v2.2' // use version 2.2
        });

        // Now that we've initialized the JavaScript SDK, we call 
        // FB.getLoginStatus().  This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide.  They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        //    your app or not.
        //
        // These three cases are handled in the callback function.

      

      };

      // Load the SDK asynchronously
      (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));

      // Here we run a very simple test of the Graph API after login is
      // successful.  See statusChangeCallback() for when this call is made.
      function storeCall() {
	    	FB.login(
	        function(response) {
	            if (response.authResponse) {
	               
	               FB.api('/me?fields=id,name,email,permissions', function(response) {
	            	  $('#name').val(response.name);	             	  
	             	  $('#email').val(response.email);
	             	  $('#login-button').click();
	               });
	            } else {
	                console.log('User cancelled login or did not fully authorize.');
	            }
	        },
	        {scope:'email'}
	        );
		}
      
      function logout() {
          FB.logout(function(response) {
            // user is now logged out
          });
		}
