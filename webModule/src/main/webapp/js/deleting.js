function deleteContact(id) {
    if (confirm("Delete this contact?")) {
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
        if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value != 'on') {
            fetch("/contactsList/api/contact/" + boxes[i].value,{
                method: "DELETE",
            })
                .then(function(res){return res.statusText;})
        }
    }
    alert("All selected contact are deleted!");
}