/**
 * Created by umesh on 4/6/17.
 */

function updateStatus(orderId){

    $.ajax({
               type: 'GET',
               url: '<c:url value="/admin/orders/updateStatus.html"/>?id=' + orderId,
               dataType: 'json',
               success: function(response){
                   var status = response.response.status;
                   var data = response.response.data;
                   //console.log(status);
                   if(status==0 || status ==9999) {
                       $(".alert-success").show();
                   } else {
                       $(".alert-error").show();
                   }
                   $('.sm').hideLoading();
               },
               error: function(xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
                   $('.sm').hideLoading();
               }

           });
}


function sendInvoice(orderId){

    $.ajax({
               type: 'GET',
               url: '<c:url value="/admin/orders/sendInvoice.html"/>?id=' + orderId,
               dataType: 'json',
               success: function(response){
                   var status = response.response.status;
                   var data = response.response.data;
                   //console.log(status);
                   if(status==0 || status ==9999) {
                       $(".alert-success").show();
                   } else {
                       $(".alert-error").show();
                   }
                   $('.sm').hideLoading();
               },
               error: function(xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
                   $('.sm').hideLoading();
               }

           });
}


function listTransactions(orderId){

    $.ajax({
               type: 'GET',
               url: '<c:url value="/admin/orders/listTransactions.html"/>?id=' + orderId,
               dataType: 'json',
               success: function(response){
                   var status = response.response.status;
                   var data = response.response.data;
                   console.log(status);
                   if((status==0 || status ==9999) && data) {
                       //console.log(data);
                       $('#transactionsModal').modal();
                       var transactions = data;
                       //console.log(transactions);
                       for(i=0;i<transactions.length;i++) {
                           var tr = '<tr><td>' + transactions[i].transactionId + '</td><td>' + transactions[i].transactionDate + '</td><td>' + transactions[i].transactionType + '</td><td>' + transactions[i].transactionAmount + '</td><td>' + JSON.stringify(transactions[i].transactionDetails) + '</td>';
                           $('#transactionList').append(tr);
                       }
                   }
               },
               error: function(xhr, textStatus, errorThrown) {
                   alert('error ' + errorThrown);
                   $('.sm').hideLoading();
               }

           });
}



$(window).on('load', function () {

    $("#countries,#shipping-country,#billing-country,#orderStatus").select2();  // init select2

     $('#order-date').datepicker({
                                    autoclose: true
                                });
    alert($("#isBillingZoneEmpty").val() );

     if($("#isBillingZoneEmpty").val() == 'false'){
         $('#billingZone').show();
         $('#billingZoneText').hide();
         var url = $("#adminApplicationContext").val() + "/common/reference/provinces.html";
         getZones(url, $("#billingcountryIso").val(),'#billingZone','#billingZoneText',$("#billingcountryZoneCode").val());
     }
     else{
         $('#billingZone').hide();
         $('#billingZoneText').show();
         $('#billingZoneText').val($("#billingdefaultText").val());
     }

   /* $("#tax-country").change(function () {
        var url = $("#adminApplicationContext").val() + "/common/reference/provinces.html";

        getZones($(this).val(), url);
    });*/

});