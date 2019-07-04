function deleteContact(id) {
    fetch("/contactsList/api?command=deleteContact&id=" + id,
        {
            method: "POST",
        })
        .then(function(res){return res.text()})
}

function deleteContacts() {
    fetch("/contactsList/api?command=deleteContact&id=" + id,
        {
            method: "POST",
        })
        .then(function(res){return res.text()})
}