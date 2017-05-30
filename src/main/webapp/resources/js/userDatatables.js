var ajaxUrl = 'ajax/admin/users/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function userCheck(id, cb) {
    $.ajax({
        url: ajaxUrl+"enable",
        data: "id="+id+"&enable="+cb.is(":checked"),
        type: 'POST',
        success: function () {
            cb.closest('tr').toggleClass("disabled");
            successNoty(cb.is(":checked")?"Enabled":"Disabled");
        }
    });
    //  console.log(id);
    //  console.log(cb.is(":checked"));
}

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();

    $('.userEnable').click(function () {
        userCheck($(this).parent().parent().attr("id"), $(this));
    });
});