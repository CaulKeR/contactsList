function showAllAttachments(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + id + '/attachments');
    hideAllExcept("attachmentsForm");
    fetch("/contactsList/api/contact/" + id + "/attachments", {
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
                response.json().then(function (attachments) {
                    document.getElementById("attachTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicAttachTable").innerHTML, attachments);
                    document.getElementById("attachCheckbox").value = id;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function showEditAttachForm(id) {
    let userId = document.getElementById("attachCheckbox").value;
    console.log('userId ' + userId);
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + userId + '/attachment/' + id);
    hideAllExcept("editAttachForm");
    fetch("/contactsList/api/contact/" + userId + '/attachment/' + id, {
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
                response.json().then(function (attachment) {
                    document.getElementById("attachEditor").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicAttachEditor").innerHTML, attachment);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function editAttach(id) {
    console.log(document.getElementById("hiddenFileType").innerText);
    let userId = document.getElementById("hiddenUserId").innerText;
    let attach = {
        id: id,
        title: document.getElementById("editAttachTitle").value,
        type: document.getElementById("hiddenFileType").innerText,
        comment: document.getElementById("attachComment").value,
    };
    fetch("/contactsList/api/contact/" + userId + "/attachment/" + id,
        {
            method: "PUT",
            headers: {
                'charset': 'utf-8',
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'mode': 'cors',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true
            },
            body: JSON.stringify(attach)
        })
        .then(function (res) {
            alert("Attachment edited!");
            showAllAttachments(userId);
            return res.statusText;
        })
}

function uploadAttach(id) {
    let formData = new FormData();
    formData.append('file', document.getElementById("attachField").files[0]);
    fetch("/contactsList/api/contact/" + id + "/attachments",
        {
            method: "POST",
            body: formData
        })
        .then(function (res) {
            showAllAttachments(id);
            alert("Attachment added");
            return res.statusText;
        });
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
                    const header = response.headers.get("Content-Disposition");
                    const filename = header.substring(21, header.length);
                    const file = new Blob([data], {type: 'application/octet-stream'});
                    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                        window.navigator.msSaveOrOpenBlob(file, filename);
                    } else {
                        let a = document.createElement("a");
                        document.body.appendChild(a);
                        a.style.display = '';
                        a.href = URL.createObjectURL(file);
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

function deleteAttachment(id) {
    if (confirm("Delete this attachment?")) {
        fetch("/contactsList/api/attachment/" + id, {
            method: "DELETE",
        })
            .then(function (res) {
                showAllAttachments(document.getElementById("attachCheckbox").value);
                return res.statusText;
            })
    }
}

function deleteAttachments(id) {
    if (confirm("Delete selected attachments?")) {
        let boxes = document.getElementById("attachTable").getElementsByTagName("input");
        for (let i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value !== 'on') {
                fetch("/contactsList/api/attachment/" + boxes[i].value, {
                    method: "DELETE",
                })
                    .then(function (res) {
                        return res.statusText;
                    })
            }
        }
        alert("All selected attachments are deleted!");
        showAllAttachments(id);
    }
}