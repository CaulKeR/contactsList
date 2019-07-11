function showCreateForm() {
    history.pushState(null, 'Create contact', '/contactsList/contact');
    hide("mainForm");
    hide("fullContactInfoForm");
    hide("editForm");
    hide("attachmentsForm");
    show("createForm");
}
function createContact() {
    var contact = {
        firstName: document.getElementById("firstName").value,
        surname: document.getElementById("surname").value,
        patronymic: document.getElementById("patronymic").value,
        birthDate: document.getElementById("birthDate").value,
        sex: document.getElementById("male").checked ? "male" : "female",
        nationality: document.getElementById("nationality").value,
        familyStatus: document.getElementById("single").checked ? "single" :
                    (document.getElementById("divorced").checked ? "divorced" : "married"),
        website: document.getElementById("website").value,
        email: document.getElementById("email").value,
        currentWorkplace: document.getElementById("currentWorkplace").value,
        address: {
            country: document.getElementById("country").value,
            locality: document.getElementById("locality").value,
            street: document.getElementById("street").value,
            house: document.getElementById("house").value,
            apartment: document.getElementById("apartment").value,
            postcode: document.getElementById("postcode").value
        }};
    console.log(JSON.stringify(contact));
    console.log(contact);
    fetch("/contactsList/api/contact",
{
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'mode': 'cors',
            "Access-Control-Allow-Origin" : "*",
            "Access-Control-Allow-Credentials" : true
        },
        body: JSON.stringify(contact)
    })
    .then(function(res){return res.statusText;});
    alert("Contact created!");
    showAllContacts();
}
function radio(sex) {
    var man = document.getElementById('male');
    var woman = document.getElementById('female');
    if (sex === 'male') {
        man.checked = true;
        woman.checked = false;
    } else {
        woman.checked = true;
        man.checked = false;
    }
}
function familyStatus(status) {
    var single = document.getElementById('single');
    var divorced = document.getElementById('divorced');
    var married = document.getElementById('married');
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