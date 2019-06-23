var xmlHttpRequest = new XMLHttpRequest();
function showAllContacts() {
    xmlHttpRequest.open("POST", "/contactsList/servlet?command=getMainContactsInfo", true);
    xmlHttpRequest.onreadystatechange = processContacts;
    xmlHttpRequest.send(null);
}
function processContacts() {
    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
        var contacts = JSON.parse(xmlHttpRequest.response);
        var text = "<table id=\"contactsTable\" style=\"border:1px solid black\"><tbody>";
        text += "<tr>" +
            "<th></th>" +
            "<th>Name</th>" +
            "<th>Birth Date</th>" +
            "<th>Address</th>" +
            "<th>Current Workplace</th>" +
            "<th></th>" +
            "</tr>";
        for (var i = 0; i < contacts.length; i++) {
            text+= "<tr>" +
                "<td><input type=\"checkbox\" name=i></td>" +
                "<td><a onclick=\"editContact()\">" + contacts[i].firstName + " " + contacts[i].surname + " " +
                        contacts[i].patronymic + "</a></td>" +
                "<td>" + contacts[i].birthDate + "</td>" +
                "<td>" + contacts[i].address.country + ", " + contacts[i].address.locality + ", " +
                        contacts[i].address.street + ", " + contacts[i].address.house + ", " +
                        contacts[i].address.apartment + ", " + contacts[i].address.postcode + "</td>" +
                "<td>" + contacts[i].currentWorkplace + "</td>" +
                "<td><button type=\"button\" onclick=\"editContact()\">Edit contact</button></td>" +
                "</tr>";
        }
        text += "</tbody></table>";
        document.getElementById("users").innerHTML = text;
    } else {
        console.log("cannot get all contacts");
    }
}
function editContact() {
    alert("Редактирую");
}
function deleteContacts() {
    alert("Удаляю");
}
function createContact() {
    alert("Создаю");
}