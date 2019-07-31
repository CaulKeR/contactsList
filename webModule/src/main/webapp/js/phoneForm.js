function showCreatePhoneForm() {
    let userId = document.getElementById("hiddenContactId").innerText;
    history.pushState({ prevUrl: window.location.href }, null, '/contactsList/contact/' + userId +'/phone');
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
                "Access-Control-Allow-Origin" : "*",
                "Access-Control-Allow-Credentials" : true
            },
            body: JSON.stringify(phone)
        })
        .then(function(res){
            alert("Phone created!");
            showFullContactInfoForm(userId);
            return res.statusText;});
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
                "Access-Control-Allow-Origin" : "*",
                "Access-Control-Allow-Credentials" : true
            },
            body: JSON.stringify(phone)
        })
        .then(function(res){
            alert("Phone edited!");
            showFullContactInfoForm(userId);
            return res.statusText;})
}

function showPhoneEditForm(id) {
    let userId = document.getElementById("mainPhoneCheckbox").value;
    console.log("userId " + userId);
    history.pushState({ prevUrl: window.location.href }, null, '/contactsList/contact/' + userId + '/phone/' + id);
    hideAllExcept("editPhoneForm");
    fetch("/contactsList/api/contact/" + userId + "/phone/" + id,{
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
                    let template = document.getElementById("dynamicPhoneEditor").innerHTML;
                    document.getElementById("phoneEditor").innerHTML = Mustache.to_html(template, phone);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}