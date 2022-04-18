// class Customer{
//     constructor(id ,fullName,email,phone,address,balance) {
//         this.id = id;
//         this.fullName = fullName;
//         this.email = email;
//         this.phone = phone;
//         this.address = address ;
//         this.balance = balance;
//     }
// }
function showCustomers() {
    $.ajax({
        type: "GET",
        //tên API
        url: "/api/customers",
        //xử lý khi thành công
        success: function (data) {
            // console.log(data)
            // hien thi danh sach o day
            let content = "";
            for (let i = data.length-1; i >=0; i--) {
                content += getCustomers(data[i]);
            }
            $("#showListCustomers").html(content);
            handlerEdit();
            handerDeposit();
            handerWithdraw();

        }
    });
}

function getCustomers(customer) {
    return `<tr>
            <td>${customer.id}</td>
            <td>${customer.fullName}</td>
            <td>${customer.email}</td>
            <td>${customer.phone}</td>
            <td>${customer.address}</td>
            <td style="text-align: right">${customer.balance}</td>
            <td>
                <button type="button"  class="btn btn-outline-secondary btn-edit"
                   data-toggle="tooltip" data-placement="top" title="Edit" 
                   data-bs-toggle="modal"
                   data-bs-target="#modalEdit" 
                     data-id="${customer.id}">
                    <i class="far fa-edit"></i>
                </button>
            </td>
            <td>
                <button type="button"  class="btn btn-outline-success btn-deposit"
                   data-toggle="tooltip" data-placement="top" title="Desposit"
                   data-bs-toggle="modal"
                   data-bs-target="#modalDeposit"
                   data-id="${customer.id}"><i class="far fa-plus-square"></i>
                </button>
            </td>
            <td>
                <button class="btn btn-outline-warning btn-withdraw"
                   data-toggle="tooltip" data-placement="top" title="Withdraw"
                   data-bs-toggle="modal"
                   data-bs-target="#modalWithdraw"
                   data-id="${customer.id}"><i class="fas fa-minus"></i></button>
            </td>
            <td>
                <button type="button"  class="btn btn-outline-primary"
                   data-toggle="tooltip" data-placement="top" title="Transfer"
                   data-bs-toggle="modal"
                   data-bs-target="#modalTransfer"
                  data-id="${customer.id}"> <i class="fas fa-exchange-alt"></i></button>
            </td>
            <td>
                <button type="button"  class="btn btn-outline-danger"
                   data-toggle="tooltip" data-placement="top" title="Delete"
                   data-bs-toggle="modal"
                   data-bs-target="#modalDelete"
                 data-id="${customer.id}"><i class="fas fa-ban"></i></button>
            </td>
        </tr>`
}

$(function () {
    $('.btn.btn-create').click(function () {
        //lay du lieu
        let fullName = $('#fullName').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let address = $('#address').val();

        let newCustomer = {
            fullName: fullName,
            email: email,
            phone: phone,
            address: address,
        };
        // goi ajax
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(newCustomer),
            //tên API
            url: "/api/customers",
            //xử lý khi thành công
            success: function () {
                $('#modalCreate').modal('hide');
                alert("Create success !")
                clear();
                showCustomers();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#modalCreate .modal-body .modal-alert-danger").empty();
                $("#modalCreate .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

                let str = `Error !`;
                // $.each( jqXHR.responseJSON, function( key, value ) {
                //     str += `<label id="${key}-error" class="error" for="${key}">${value}</label>`;
                //     $("#" + key).addClass("error");
                // });
                $("#modalCreate .modal-body .modal-alert-danger").html(str);
            }
        });
        //chặn sự kiện mặc định của thẻ
        // event.preventDefault();
        // return false;
    });
});


function clear() {
    $('#fullName').val("");
    $('#email').val("");
    $('#phone').val("");
    $('#address').val("");

}


function handlerEdit() {
    $(".btn.btn-outline-secondary.btn-edit").click(function () {
        let customerId = $(this).data("id");

        // goi ajax
        $.ajax({
            type: "GET",
            //tên API
            url: `/api/customers/${customerId}`,
            //xử lý khi thành công
            success: function (data) {
                // console.log(data)
                // $(".modal-alert-danger").hide() ;  
                $("#idUp").val(data.id)
                $("#fullNameUp").val(data.fullName)
                $("#emailUp").val(data.email)
                $("#phoneUp").val(data.phone)
                $("#addressUp").val(data.address)

            }
        });
        return false;
    });
}

function updateCustomer() {
    $('.btn.btn-update').click(function () {
        let id = $('#idUp').val();
        let fullName = $('#fullNameUp').val();
        let email = $('#emailUp').val();
        let phone = $('#phoneUp').val();
        let address = $('#addressUp').val();

        let oldCustomer = {
            id: id,
            fullName: fullName,
            email: email,
            phone: phone,
            address: address,
        };
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            data: JSON.stringify(oldCustomer),
            //tên API
            url: "/api/customers",
            //xử lý khi thành công
            success: function () {
                $('#modalEdit').modal('hide');
                alert(" Update success !")
                showCustomers();

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#modalEdit .modal-body .modal-alert-danger").empty();
                $("#modalEdit .modal-body .modal-alert-danger").removeClass("hide").addClass("show");
                let str = `Error !`;
                // $.each( jqXHR.responseJSON, function( key, value ) {
                //     str += `<label id="${key}-error" class="error" for="${key}">${value}</label>`;
                //     $("#" + key).addClass("error");
                // });
                $("#modalEdit .modal-body .modal-alert-danger").html(str);
            }

        });
        //chặn sự kiện mặc định của thẻ
        event.preventDefault();
    })
}


function handerDeposit() {
    $(".btn.btn-outline-success.btn-deposit").click(function () {
        let customerId = $(this).data("id");
        //lay du lieu
        // goi ajax
        $.ajax({
            type: "GET",
            //tên API
            url: `/api/customers/${customerId}`,
            //xử lý khi thành công
            success: function (data) {
                console.log(data)
                $("#customerIdCD").val(data.id)
                $("#fullNameCD").val(data.fullName)
                $("#currentBalanceCD").val(data.balance)
                // $("#transactionAmountCD").val("0")
                // $.ajax({})
            },
            error: function () {
                alert("Customer not exist  !")
            }

        });
        //chặn sự kiện mặc định của thẻ
        return false;
    })
}

// function deploi


function handerWithdraw() {
    $(".btn.btn-outline-warning.btn-withdraw").click(function () {
        let customerId = $(this).data("id");
        //lay du lieu
        // goi ajax
        $.ajax({
            type: "GET",
            //tên API
            url: `/api/customers/${customerId}`,
            //xử lý khi thành công
            success: function (data) {
                console.log(data)
                $("#customerIdCW").val(data.id)
                $("#fullNameCW").val(data.fullName)
                $("#currentBalanceCW").val(data.balance)

            },
            error: function () {
                alert("Customer not exist  !")
            }

        });
        //chặn sự kiện mặc định của thẻ
        return false;
    })
}

$(document).ready(function () {
    getCustomers().then(function () {
        $("#frmDeposit .num-space").number(true, 0, ',', ' ');
        $("#frmWithdraw .num-space").number(true, 0, ',', ' ');
        $("#frmTransfer .num-space").number(true, 0, ',', ' ');
        $('[data-toggle="tooltip"]').tooltip();
    });
});
showCustomers()
updateCustomer();