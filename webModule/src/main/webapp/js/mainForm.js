function showAllContacts() {
    history.pushState({ prevUrl: window.location.href }, 'Contacts list', '/contactsList/contacts');
    hide("createForm");
    hide("fullContactInfoForm");
    hide("editForm");
    hide("attachmentsForm");
    show("mainForm");
    fetch("/contactsList/api/contacts",{
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
                    var contacts = data;
                    var template = document.getElementById("dynamicMainTable").innerHTML;
                    document.getElementById("mainTable").innerHTML = Mustache.to_html(template, contacts);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}