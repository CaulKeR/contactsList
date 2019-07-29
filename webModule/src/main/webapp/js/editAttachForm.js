function showEditAttachForm(id) {
    let userId = document.getElementById("attachCheckbox").value;
    console.log('userId ' + userId);
    history.pushState({ prevUrl: window.location.href }, 'Create contact', '/contactsList/contact/' + userId + '/attachment/' + id);
    hideAllExcept("editAttachForm");
    fetch("/contactsList/api/contact/" + userId + '/attachment/' + id,{
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
                    let template = document.getElementById("dynamicAttachEditor").innerHTML;
                    document.getElementById("attachEditor").innerHTML = Mustache.to_html(template, attachment);
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
                "Access-Control-Allow-Origin" : "*",
                "Access-Control-Allow-Credentials" : true
            },
            body: JSON.stringify(attach)
        })
        .then(function(res){
            alert("Attachment edited!");
            showAllAttachments(userId);
            return res.statusText;})
}