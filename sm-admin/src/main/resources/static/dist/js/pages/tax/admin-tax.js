/**
 * Created by umesh on 3/26/17.
 */


$.fn.addItems = function (data) {
    $(".zone-list > option").remove();
    return this.each(function () {
        var list = this;
        $.each(data, function (index, itemData) {
            //alert(itemData.name + " " + itemData.id)
            var option = new Option(itemData.name, itemData.id);
            list.add(option);
        });
    });
};

function getZones(countryCode, url) {
    $.ajax({
               type: 'GET',
               url: url,
               data: 'countryCode=' + countryCode,
               dataType: 'application/json',
               success: function (responseData) {

                   var status = responseData.response.status;
                   if (status == 0 || status == 9999) {

                       alert(responseData.response.data.length);
                       if (responseData.response.data.length > 0) {

                           $('.zone-list').show();
                           $('#stateProvince').hide();
                           $(".zone-list").addItems(responseData.response.data);
                       } else {
                           $('.zone-list').hide();
                           $('#stateProvince').show();

                       }
                   } else {
                       $('.zone-list').hide();
                       $('#stateProvince').show();
                   }

               },
               error: function (xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
               }

           });
}

$(window).on('load', function () {

    $("#tax-country").select2();  // init select2
    $("#stateProvince").hide();  // hide text box

    $("#tax-country").change(function () {
        var url = $("#adminApplicationContext").val() + "/common/reference/provinces.html";

        getZones($(this).val(), url);
    });

});



