function showEditForm(id) {
    history.pushState(null, 'Create contact', '/contactsList/contact/' + id);
    hide("mainForm");
    hide("fullContactInfoForm");
    hide("createForm");
    show("editForm");
    fetch("/contactsList/api/contact/" + id,{
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (data) {
                    var contact = data;
                    var template = document.getElementById("dynamicContactEditor").innerHTML;
                    console.log(contact);
                    document.getElementById("editor").innerHTML = Mustache.to_html(template, contact);
                    radioEdit(contact.sex);
                    familyStatusEdit(contact.familyStatus);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}
function editContact(id) {
    alert("Contact edited!");
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
        .then(function(res){return res.statusText;})
    showAllContacts();
}
function radioEdit(sex) {
    var man = document.getElementById('editMale');
    var woman = document.getElementById('editFemale');
    if (sex === 'male') {
        man.checked = true;
        woman.checked = false;
    } else {
        woman.checked = true;
        man.checked = false;
    }
}
function familyStatusEdit(status) {
    var single = document.getElementById('editSingle');
    var divorced = document.getElementById('editDivorced');
    var married = document.getElementById('editMarried');
    if (status === 'single') {
        single.checked = true;
        divorced.checked = false;
        married.checked = false;
    } else {
        if (status === 'divorced') {
            single.checked = false;
            divorced.checked = true;
            married.checked = false;
        } else {
            single.checked = false;
            divorced.checked = false;
            married.checked = true;
        }
    }
}

function showPopUp() {
    document.getElementById("show228").show();
}

function closePopUp() {
    document.getElementById("show228").hide();
}