/**
 * Created by umesh on 4/6/17.
 */

function getZones(url,countryCode,listDiv, textDiv, defaultValue) {
    alert(url + countryCode + listDiv + textDiv + defaultValue);
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
                           $(listDiv).show();
                           $(textDiv).hide();
                           $(listDiv).addItems(responseData.response.data);
                       } else {
                           $(listDiv).hide();
                           $(textDiv).show();
                           if(defaultValue!=null || defaultValue !='') {
                               $(textDiv).val(defaultValue);
                           }

                       }
                   } else {
                       $(listDiv).hide();
                       $(textDiv).show();
                   }

               },
               error: function (xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
               }

           });
}
