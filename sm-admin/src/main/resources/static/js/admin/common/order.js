function loadOrderList(){
    alert(TOKEN);
    //showLoader();
    var url = BACKEND + "/admin/orders";

    var dataTable =  $('#ordersList').DataTable( {
        columnDefs: [{
            "defaultContent": "-",
            "targets": "_all"
        }],
        processing:true,
        serverSide: true,
        pages: 3,
        searchDelay: 2000,
        ajax: {
            "url": url,
            "type": "GET",
            "dataSrc":"orders",
            "headers": {
                "Authorization": "Bearer " + TOKEN
            }
        },
        "order": [ 0, 'desc' ],
        "columns": [
            { "data": "id",
                "render": function ( data, type, row, meta ) {
                    return '<a href="/admin/order?id=' + data + '">' + data + '</a>';
                }
            },
            { "data": "orderStatus" },
            { "data": "paymentType" },
            { "data": "total.value" },
            { "data": "datePurchased" }
        ]
    });

    dataTable.on( 'search.dt', function (event) {
        //log('search');

        //$('#filterInfo').html( 'Currently applied global search: '+table.search() );
    } );
}