function showCreateForm() {
    history.pushState({ prevUrl: window.location.href }, 'Create contact', '/contactsList/contact');
    hideAllExcept("createForm");
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
    .then(function(res){
        alert("Contact created!");
        showAllContacts();
        return res.statusText;});
}