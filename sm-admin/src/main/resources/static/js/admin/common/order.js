function loadOrderList(){
    //showLoader();
    var url = BACKEND + "/admin/orders";

    var dataTable =  $('#ordersList').DataTable( {
        columnDefs: [{
            "defaultContent": "-",
            "targets": [2],
            "orderable": false
        }],
        processing:true,
        colReorder: true,
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
                    return '<a href="/admin/orders/order/' + data + '">' + data + '</a>';
                }
            },
            { "data": "orderStatus" },
            { "data": "paymentType" },
            { "data": "total.value" },
            { "data": "datePurchased",
                render: function(data, type, row){
                    if(type === "sort" || type === "type"){
                        return data;
                    }
                    return moment(data).format("dddd D, MMMM YYYY");
                }
            }
        ]
    });
    dataTable.on( 'search.dt', function (event) {
        //log('search');

        //$('#filterInfo').html( 'Currently applied global search: '+table.search() );
    } );
}