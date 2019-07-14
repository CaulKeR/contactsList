function showFullContactInfoForm(id) {
    history.pushState({ prevUrl: window.location.href }, 'Full contact information', '/contactsList/contact/' + id);
    hide("mainForm");
    hide("createForm");
    hide("editForm");
    hide("attachmentsForm");
    show("fullContactInfoForm");
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
                    var contact = data;
                    var template = document.getElementById("dynamicContact").innerHTML;
                    document.getElementById("contact").innerHTML = Mustache.to_html(template, contact);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}