/**
 This is the common JS file to handle caching feature
 @Author : Umesh Awasthi
 **/

$("#clearCache").click(function(event){

    //log('Deleting');

    showLoader();
    event.preventDefault(); //prevent default action
    resetMessages();

    //create url
    url = BACKEND + "/cache/store/" +STORECODE+"/clear";
    var type ='DELETE';

    $.ajax({
        type:type,
        crossDomain: true,
        cache: false,
        url: url,
        contentType : 'application/json; charset=utf-8',
        headers: {
            "Authorization": "Bearer " + TOKEN
        },
        success: function(data){
            log('success');
            success('Cache cleared successfully');
            hideLoader();
            //reset form
           // $('#store').trigger("reset");
            //becomes creat mode
            //$("#action").val('CREATE');
            //$("#code").prop("disabled", false);

        },
        error: function(response,textStatus,errorThrown) {
            log('error');
            error(JSON.stringify(response.message));
            hideLoader();
        }
    });

});