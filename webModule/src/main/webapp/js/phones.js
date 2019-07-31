function showCreatePhoneForm() {
    let userId = document.getElementById("hiddenContactId").innerText;
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + userId + '/phone');
    hideAllExcept("createPhoneForm");
    document.getElementById("contactId").innerText = userId;
}

function createPhone() {
    let userId = document.getElementById("contactId").innerText;
    let phone = {
        contactId: userId,
        countryCode: document.getElementById("countryCode").value,
        operatorsCode: document.getElementById("operatorsCode").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        type: document.getElementById("home").checked ? "home" : "mobile",
        comment: document.getElementById("phoneCreateComment").value
    };
    fetch("/contactsList/api/contact/" + userId + "/phone",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'charset': 'utf-8',
                'mode': 'cors',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true
            },
            body: JSON.stringify(phone)
        })
        .then(function (res) {
            alert("Phone created!");
            showFullContactInfoForm(userId);
            return res.statusText;
        });
}

function showPhoneEditForm(id) {
    let userId = document.getElementById("mainPhoneCheckbox").value;
    console.log("userId " + userId);
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + userId + '/phone/' + id);
    hideAllExcept("editPhoneForm");
    fetch("/contactsList/api/contact/" + userId + "/phone/" + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8',
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (phone) {
                    document.getElementById("phoneEditor").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicPhoneEditor").innerHTML, phone);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function editPhone(id) {
    let userId = document.getElementById("hiddenPhoneUserId").innerText;
    console.log("userId " + userId);
    let phone = {
        id: id,
        countryCode: document.getElementById("editCountryCode").value,
        operatorsCode: document.getElementById("editOperatorsCode").value,
        phoneNumber: document.getElementById("editPhoneNumber").value,
        type: document.getElementById("editHome").checked ? "home" : "mobile",
        comment: document.getElementById("phoneComment").value
    };
    fetch("/contactsList/api/contact/" + userId + "/phone/" + id,
        {
            method: "PUT",
            headers: {
                'charset': 'utf-8',
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'mode': 'cors',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true
            },
            body: JSON.stringify(phone)
        })
        .then(function (res) {
            alert("Phone edited!");
            showFullContactInfoForm(userId);
            return res.statusText;
        })
}

function deletePhone(id) {
    if (confirm("Delete this phone?")) {
        fetch("/contactsList/api/phone/" + id, {
            method: "DELETE",
        })
            .then(function (res) {
                showFullContactInfoForm(document.getElementById("mainPhoneCheckbox").value);
                return res.statusText;
            })
    }
}

function deletePhones() {
    if (confirm("Delete selected phones?")) {
        let userId = document.getElementById("mainPhoneCheckbox").value;
        let boxes = document.getElementById("mainPhoneTable").getElementsByTagName("input");
        for (let i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value != 'on') {
                fetch("/contactsList/api/phone/" + boxes[i].value, {
                    method: "DELETE",
                })
                    .then(function (res) {
                        return res.statusText;
                    })
            }
        }
        alert("All selected phones are deleted!");
        showFullContactInfoForm(userId);
    }
}