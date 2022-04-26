class App {

    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                position: 'middle',
                icon: 'error',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static ShowConfirmSuspend() {
            return Swal.fire({
                position: 'middle',
                icon: 'warning',
                title: "Are you sure suspend this client?",
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                reverseButtons: true
            });
        }
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.show({
                title: 'Success',
                message: t,
                position: 'topRight',
                color: 'green',
                timeout: 2500,
            });
        }

        static showErrorAlert(t) {
            iziToast.show({
                title: 'Error',
                message: t,
                position: 'topRight',
                color: 'red',
                timeout: 2500,
            });
        }
    }
    
}

class Customer {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Sender extends Customer {
    constructor() {
        super()
    }
}

class Recipient extends Customer {
    constructor() {
        super()
    }
}

class Deposit {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Withdraw {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(id, senderId, recipientId, transferAmount, fees, feesAmount, transactionAmount) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}