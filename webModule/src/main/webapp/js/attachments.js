function showAllAttachments(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contact/' + id + '/attachments');
    hideAllExcept("attachmentsForm");
    fetch("/api/contact/" + id + "/attachments", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
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
    history.pushState({prevUrl: window.location.href}, null, '/contact/' + userId + '/attachment/' + id);
    hideAllExcept("editAttachForm");
    fetch("/api/contact/" + userId + '/attachment/' + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
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
    let userId = document.getElementById("hiddenUserId").innerText;
    let attach = {
        id: id,
        title: document.getElementById("editAttachTitle").value,
        type: document.getElementById("hiddenFileType").innerText,
        comment: document.getElementById("attachComment").value,
    };
    if (validateAttachInputFields(attach)) {
        fetch("/api/contact/" + userId + "/attachment/" + id,
            {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=utf-8',
                    'mode': 'cors',
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Credentials": true
                },
                body: JSON.stringify(attach)
            })
            .then(function (res) {
                alert("Attachment edited!");
                cleanEditAttachFields();
                showAllAttachments(userId);
                return res.statusText;
            })
    }
}

function uploadAttach(id) {
    if (validateAttachFileField()) {
        let formData = new FormData();
        formData.append('file', document.getElementById("attachField").files[0]);
        fetch("/api/contact/" + id + "/attachments",
            {
                method: "POST",
                body: formData
            })
            .then(function (res) {
                showAllAttachments(id);
                alert("Attachment added");
                document.getElementById("attachField").value = '';
                return res.statusText;
            });
    }
}

function downloadAttach(id) {
    fetch("/api/attachment/" + id,
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
        fetch("/api/attachment/" + id, {
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
                fetch("/api/attachment/" + boxes[i].value, {
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

function validateAttachInputFields(attach) {
    let isValid = true;
    if (attach.title.length > 256) {
        isValid = false;
        alert("Title is too long!");
    }
    if (attach.title === "") {
        isValid = false;
        alert("Title cannot be empty!");
    }
    if (attach.comment.length > 300) {
        isValid = false;
        alert("Comment is too long!");
    }
    return isValid;
}

function validateAttachFileField() {
    console.log(document.getElementById("attachField").value);
    let isValid = true;
    if (document.getElementById("attachField").value === '' ||
        document.getElementById("attachField").value === null) {
        isValid = false;
        alert("You did not select file to upload!");
    }
    return isValid;
}

function cleanEditAttachFields() {
    document.getElementById("hiddenUserId").innerText = '';
    document.getElementById("editAttachTitle").value = '';
    document.getElementById("hiddenFileType").innerText = '';
    document.getElementById("attachComment").value = '';
}