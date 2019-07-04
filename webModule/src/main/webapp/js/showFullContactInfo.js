function showFullContactInfoForm(id) {
    hide("mainForm");
    hide("createForm");
    show("fullContactInfoForm");
    console.log(id);
    fetch("/contactsList/api?command=getContactById&id=" + id,{
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
                    var template = document.getElementById("dynamicContact").innerHTML;
                    console.log(contact);
                    document.getElementById("contact").innerHTML = Mustache.to_html(template, contact);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}