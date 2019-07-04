function showAllContacts() {
    hide("createForm");
    hide("fullContactInfoForm");
    show("mainForm");
    fetch("/contactsList/api?command=getMainContactsInfo",{
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