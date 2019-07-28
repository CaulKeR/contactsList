function showFullContactInfoForm(id) {
    history.pushState({ prevUrl: window.location.href }, 'Full contact information', '/contactsList/contact/' + id);
    hideAllExcept("fullContactInfoForm");
    fetch("/contactsList/api/contact/" + id,{
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Charset': 'UTF-8'
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
                    if (contact.customAvatar === true) {
                        document.getElementById("fullContactInfoAvatar").src = "http://localhost:8080/contactsList/api/contact/" + id + "/photo";
                    } else {
                        document.getElementById("fullContactInfoAvatar").src = "/contactsList/images/defaultAvatar.png";
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}