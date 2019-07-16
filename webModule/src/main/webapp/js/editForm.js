function showEditForm(id) {
    history.pushState({ prevUrl: window.location.href }, 'Create contact', '/contactsList/contact/' + id);
    hideAllExcept("editForm");
    fetch("/contactsList/api/contact/" + id,{
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (contact) {
                    var template = document.getElementById("dynamicContactEditor").innerHTML;
                    console.log(contact);
                    document.getElementById("editor").innerHTML = Mustache.to_html(template, contact);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}
function editContact(id) {
    var contact = {
        id: id,
        firstName: document.getElementById("editFirstName").value,
        surname: document.getElementById("editSurname").value,
        patronymic: document.getElementById("editPatronymic").value,
        birthDate: document.getElementById("editBirthDate").value,
        sex: document.getElementById("editMale").checked ? "male" : "female",
        nationality: document.getElementById("editNationality").value,
        familyStatus: document.getElementById("editSingle").checked ? "single" :
            (document.getElementById("editDivorced").checked ? "divorced" : "married"),
        website: document.getElementById("editWebsite").value,
        email: document.getElementById("editEmail").value,
        currentWorkplace: document.getElementById("editCurrentWorkplace").value,
        address: {
            country: document.getElementById("editCountry").value,
            locality: document.getElementById("editLocality").value,
            street: document.getElementById("editStreet").value,
            house: document.getElementById("editHouse").value,
            apartment: document.getElementById("editApartment").value,
            postcode: document.getElementById("editPostcode").value
        }};
    console.log(JSON.stringify(contact));
    console.log(contact);
    fetch("/contactsList/api/contact/" + id,{
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (data) {
                    contact = JSON.parse(data);
                    console.log(contact);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
    fetch("/contactsList/api/contact/" + id,
        {
            method: "PUT",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'mode': 'cors',
                "Access-Control-Allow-Origin" : "*",
                "Access-Control-Allow-Credentials" : true
            },
            body: JSON.stringify(contact)
        })
        .then(function(res){
            alert("Contact edited!");
            showAllContacts();
            return res.statusText;})
}