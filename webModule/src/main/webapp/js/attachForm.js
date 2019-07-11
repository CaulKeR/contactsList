function showAllAttachments(id) {
    history.pushState(null, 'Contacts list', '/contactsList/contact/' + id + '/attachments');
    hide("createForm");
    hide("fullContactInfoForm");
    hide("editForm");
    hide("mainForm");
    show("attachmentsForm");
    fetch("/contactsList/api/contact/" + id + "/attachments",{
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
                    var template = document.getElementById("dynamicAttachTable").innerHTML;
                    document.getElementById("attachTable").innerHTML = Mustache.to_html(template, contacts);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function uploadAttach(id) {
    console.log(id);
    var formData = new FormData();
    formData.append('file', document.getElementById("attachField").files[0]);
    fetch("/contactsList/api/contact/" + id + "/attachments",
        {
            method: "POST",
            body: formData
        })
        .then(function(res){return res.statusText;});
    alert("Attachment added");
}