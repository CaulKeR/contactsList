function showAllAttachments(id) {
    history.pushState({ prevUrl: window.location.href }, 'Contacts list', '/contactsList/contact/' +
            id + '/attachments');
    hideAllExcept("attachmentsForm");
    fetch("/contactsList/api/contact/" + id + "/attachments",{
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
                    var attachments = data;
                    console.log(data);
                    var template = document.getElementById("dynamicAttachTable").innerHTML;
                    document.getElementById("attachTable").innerHTML = Mustache.to_html(template, attachments);
                    document.getElementById("attachCheckbox").value = id;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function uploadAttach(id) {
    var formData = new FormData();
    formData.append('file', document.getElementById("attachField").files[0]);
    fetch("/contactsList/api/contact/" + id + "/attachments",
        {
            method: "POST",
            body: formData
        })
        .then(function(res){
            showAllAttachments(id);
            alert("Attachment added");
            return res.statusText;});
}

function downloadAttach(id) {
    fetch("/contactsList/api/attachment/" + id,
        {
            method: "GET",
        })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.blob().then(function (data) {
                    var header = response.headers.get("Content-Disposition");
                    var filename = header.substring(21, header.length);
                    var file = new Blob([data], { type: 'application/octet-stream' });
                    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                        window.navigator.msSaveOrOpenBlob(file, filename);
                    } else {
                        var a = document.createElement("a");
                        document.body.appendChild(a);
                        a.style = "display: none";
                        a.href =  URL.createObjectURL(file);
                        a.download = filename;
                        a.click();
                        URL.revokeObjectURL(a.href);
                        a.remove();
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function editAttach(id) {
    console.log("Editing attach: " + id);
}