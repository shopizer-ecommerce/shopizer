/**
 * Created by umesh on 3/26/17.
 */


$.fn.addItems = function(data) {
    $(".zone-list > option").remove();
    return this.each(function() {
        var list = this;
        $.each(data, function(index, itemData) {
            //alert(itemData.name + " " + itemData.id)
            var option = new Option(itemData.name, itemData.id);
            list.add(option);
        });
    });
};


function getZones(countryCode, url){
    $.ajax({
               type: 'POST',
               url: url,
               data: 'countryCode=' + countryCode,
               dataType: 'json',
               success: function(response){

                   var status = isc.XMLTools.selectObjects(response, "/response/status");
                   if(status==0 || status ==9999) {

                       var data = isc.XMLTools.selectObjects(response, "/response/data");
                       if(data && data.length>0) {

                           $('.zone-list').show();
                           $('#stateProvince').hide();
                           $(".zone-list").addItems(data);
                       } else {
                           $('.zone-list').hide();
                           $('#stateProvince').show();

                       }
                   } else {
                       $('.zone-list').hide();
                       $('#stateProvince').show();
                   }


               },
               error: function(xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
               }

           });
}

$(window).on('load', function () {

    $("#tax-country").select2();  // init select2

    $("#tax-country" ).change(function() {
        alert( $(this).val());
    });

    $("#tax-country").on("change",'select' ,function () {
        alert("hello");
        //$("#second option[value]").remove();

        //var newOptions = []; // the result of your JSON request

        //$("#second").append(newOptions).val("").trigger("change");
    });

});



