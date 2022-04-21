
// let customer = new Customer();

// let deposit = new Deposit();

// function drawListCustomer(data) {
//     let str;
//     str = `
//         <tr id="tr_${data.id}">
//             <td>${data.id}</td>
//             <td>${data.fullName}</td>
//             <td>${data.email}</td>
//             <td>${data.phone}</td>
//             <td>${data.address}</td>
//             <td>${data.balance}</td>
//             <td>
//                 <button type="button" data-id="${data.id}" data-full-name="${data.fullName}" class="btn btn-outline-secondary btn-update">
//                     <i class="fa-solid fa-pen-to-square"></i>
//                 </button>
//             </td>
//             <td>
//                 <button type="button" data-id="${data.id}" class="btn btn-outline-success btn-deposit">
//                     <i class="fa-solid fa-plus"></i>
//                 </button>
//             </td>
//             <td>
//                 <button type="button" class="btn btn-outline-warning">
//                     <i class="fa-solid fa-minus"></i>
//                 </button>
//             </td>
//             <td>
//                 <button type="button" class="btn btn-outline-primary">
//                     <i class="fa-solid fa-arrow-right-arrow-left"></i>
//                 </button>
//             </td>
//             <td>
//                 <button type="button" class="btn btn-outline-danger">
//                     <i class="fa-solid fa-ban"></i>
//                 </button>
//             </td>
//         </tr>
//     `;

//     document.getElementById("tbCustomer").innerHTML += str;
// }


// function findById(id) {
//     return $.ajax({
//         headers: {
//             "Content": "application/json",
//             "Accept": "application/json"
//         },
//         url: "http://localhost:3000/customers/" + id,
//         success: function (data) {
//             customer = data;
//         },
//         error: function () {
            
//         }
//     })
// }

// function handlerShowUpdateModal() {

//     let btnUpdate = $(".btn-update");

//     btnUpdate.on("click", function () {
//         let id = $(this).data("id");

//         findById(id).then(function () {
//             $("#fullNameUp").val(customer.fullName);
//             $("#emailUp").val(customer.email);
//             $("#phoneUp").val(customer.phone);
//             $("#addressUp").val(customer.address);

//             $("#updateModal").modal("show");
//         }).catch(function () {
//             alert("Customer not found!");
//         });
//     });
// };

// function handlerShowDepositModal() {
//     let btnDeposit = $(".btn-deposit");

//             btnDeposit.on("click", function () {

//                 let id = $(this).data("id");

//                 findById(id).then(function () {
//                     $("#customerIdDep").val(customer.id);
//                     $("#fullNameDep").val(customer.fullName);
//                     $("#balanceDep").val(customer.balance);

//                     $("#depositModal").modal("show");
//                 }).catch(function () {
//                     alert("Customer not found!");
//                 });
//             })
// }



// function loadCustomers() {
//     return $.ajax({
//         headers: {
//             "Content": "application/json",
//             "Accept": "application/json"
//         },
//         url: "http://localhost:3000/customers",
//         success: function (data) {
//             data.forEach(item => {
//                 drawListCustomers(item);
//             });
//         }
//     })
// }

// // Edit customer
// function doUpdate() {
//     let customer = {
//         id: null,
//         fullName: $("#fullNameUp").val(),
//         email: $("#emailUp").val(),
//         phone: $("#phoneUp").val(),
//         address: $("#addressUp").val(),
//         balance: 0
//     }

//     $.ajax({
//         type: "PUT",
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         url: "http://localhost:3000/customers/" + customer.id,
//         data: JSON.stringify(customer),
//         success: function(data) {
//                  let str =
//             `
//                 <tr id="tr_${data.id}">
//                     <td>${data.id}</td>
//                     <td>${data.fullName}</td>
//                     <td>${data.email}</td>
//                     <td>${data.phone}</td>
//                     <td>${data.address}</td>
//                     <td>${data.balance}</td>
//                     <td>
//                         <button type="button" data-id="${data.id}" data-full-name="${data.fullName}" class="btn btn-outline-secondary btn-update">
//                             <i class="fa-solid fa-pen-to-square"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" data-id="${data.id}" class="btn btn-outline-success btn-deposit">
//                             <i class="fa-solid fa-plus"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-warning">
//                             <i class="fa-solid fa-minus"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-primary">
//                             <i class="fa-solid fa-arrow-right-arrow-left"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-danger">
//                             <i class="fa-solid fa-ban"></i>
//                         </button>
//                     </td>
//                 </tr>
//             `;

//             let currentRow = $("#tr_" + data.id);
//                     currentRow.replaceWith(str);


//                     $("#updateModal").modal("hide");

//                     handlerShowUpdateModal();


//             }
//         })
// }


// $("#btnUpdate").on("click", function() {
//     doUpdate();
// })


// loadCustomers().then(function() {
//     handlerShowUpdateModal();

//     handlerShowDepositModal
// })


// // Create Customer

// function createCustomer() {
//     let customer = {
//         id: null,
//         fullName: $("#fullNameUp").val(),
//         email: $("#emailUp").val(),
//         phone: $("#phoneUp").val(),
//         address: $("addressUp").val(),
//         balance: 0
//     };
//     $.ajax({
//         type: "POST",
//         headers: {
//             "Content": "application/json",
//             "Accept" : "application/json"
//         },
//         url: "http://localhost:3000/customers/",
//         data: JSON.stringify(customer),
//         success: function(data) {
//             let str =
//             `
//                     <tr id="tr_${data.id}">
//                     <td>${data.id}</td>
//                     <td>${data.fullName}</td>
//                     <td>${data.email}</td>
//                     <td>${data.phone}</td>
//                     <td>${data.address}</td>
//                     <td>${data.balance}</td>
//                     <td>
//                         <button type="button" data-id="${data.id}" data-full-name="${data.fullName}" class="btn btn-outline-secondary btn-update">
//                             <i class="fa-solid fa-pen-to-square"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-success btn-deposit ">
//                             <i class="fa-solid fa-plus"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-warning">
//                             <i class="fa-solid fa-minus"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-primary">
//                             <i class="fa-solid fa-arrow-right-arrow-left"></i>
//                         </button>
//                     </td>
//                     <td>
//                         <button type="button" class="btn btn-outline-danger">
//                             <i class="fa-solid fa-ban"></i>
//                         </button>
//                     </td>
//                 </tr>
//             `;
//             $("#tbCustomer").innerHTML = str;
            
//         }
//     })
// }