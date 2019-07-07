function deleteContact(id) {
    var desighfh = confirm("Delete this contact?");
    if (desighfh) {
        fetch("/contactsList/api/contact/" + id,{
            method: "DELETE",
        })
            .then(function(res){return res.statusText;})
    }
    showAllContacts();
}

function deleteContacts() {
   var boxes = document.getElementById("mainTable").getElementsByTagName("input");
    for (i = 0; i < boxes.length; i++) {
        if (boxes[i].type === "checkbox" && boxes[i].checked) {
            fetch("/contactsList/api/contact/" + id,{
                method: "DELETE",
            })
                .then(function(res){return res.statusText;})
        }
    }
    alert("All selected contact are deleted!");
}