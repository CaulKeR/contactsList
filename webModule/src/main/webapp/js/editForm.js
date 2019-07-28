function showEditForm(id) {
    history.pushState({ prevUrl: window.location.href }, 'Create contact', '/contactsList/contact/' + id);
    hideAllExcept("editForm");
    fetch("/contactsList/api/contact/" + id,{
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
                response.json().then(function (contact) {
                    var template = document.getElementById("dynamicContactEditor").innerHTML;
                    document.getElementById("editor").innerHTML = Mustache.to_html(template, contact);
                    if (contact.customAvatar === true) {
                        console.log(id);
                        document.getElementById("editFormAvatar").src = "http://localhost:8080/contactsList/api/contact/" + id + "/photo";
                    } else {
                        document.getElementById("editFormAvatar").src = "/contactsList/images/defaultAvatar.png";
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}
function editContact(id) {
    console.log(document.getElementById("editCurrentWorkplace").value);
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
    var formData = new FormData();
    formData.append('file', document.getElementById("avatar").files[0]);
    fetch("/contactsList/api/contact/" + id + "/photo",
        {
            method: "POST",
            body: formData
        })
        .then(function(res){
            return res.statusText;});
    fetch("/contactsList/api/contact/" + id,
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
            body: JSON.stringify(contact)
        })
        .then(function(res){
            alert("Contact edited!");
            showFullContactInfoForm(id);
            return res.statusText;})
}