function deleteContact(id) {
    if (confirm("Delete this contact?")) {
        fetch("/contactsList/api/contact/" + id,{
            method: "DELETE",
        })
            .then(function(res){
                showAllContacts();
                return res.statusText;})
    }
}

function deleteContacts() {
    if (confirm("Delete selected contact?")) {
        var boxes = document.getElementById("mainTable").getElementsByTagName("input");
        for (i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value != 'on') {
                fetch("/contactsList/api/contact/" + boxes[i].value, {
                    method: "DELETE",
                })
                    .then(function (res) {
                        return res.statusText;
                    })
            }
        }
        alert("All selected contact are deleted!");
        showAllContacts();
    }
}

function deleteAttach(id) {
    if (confirm("Delete this attachment?")) {
        fetch("/contactsList/api/attachment/" + id,{
            method: "DELETE",
        })
            .then(function(res){
                showAllAttachments(document.getElementById("attachCheckbox").value);
                return res.statusText;})
    }
}

function deleteAttachs(id) {
    if (confirm("Delete selected attachments?")) {
        var boxes = document.getElementById("attachTable").getElementsByTagName("input");
        for (i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value != 'on') {
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