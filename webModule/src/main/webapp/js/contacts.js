function showAllContacts(contactsPerPage, pageNumber) {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contacts');
    hideAllExcept("mainForm");
    document.getElementById("searchIcon").style.display = 'block';
    if (contactsPerPage === undefined || pageNumber === undefined) {
        contactsPerPage = 10;
        pageNumber = 1;
    }
    fetch("/contactsList/api/contacts?count=" + contactsPerPage + "&page=" + pageNumber, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            'charset': 'utf-8'
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (contacts) {
                    document.getElementById("mainTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicMainTable").innerHTML, contacts);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function showCreateForm() {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact');
    hideAllExcept("createForm");
}

function createContact() {
    let contact = {
        firstName: document.getElementById("firstName").value,
        surname: document.getElementById("surname").value,
        patronymic: document.getElementById("patronymic").value,
        birthDate: document.getElementById("birthDate").value,
        sex: document.getElementById("male").checked ? "male" : "female",
        nationality: document.getElementById("nationality").value,
        familyStatus: document.getElementById("single").checked ? "single" :
            (document.getElementById("divorced").checked ? "divorced" : "married"),
        website: document.getElementById("website").value,
        email: document.getElementById("email").value,
        currentWorkplace: document.getElementById("currentWorkplace").value,
        address: {
            country: document.getElementById("country").value,
            locality: document.getElementById("locality").value,
            street: document.getElementById("street").value,
            house: document.getElementById("house").value,
            apartment: document.getElementById("apartment").value,
            postcode: document.getElementById("postcode").value
        }
    };
    fetch("/contactsList/api/contact",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'charset': 'utf-8',
                'mode': 'cors',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true
            },
            body: JSON.stringify(contact)
        })
        .then(function (res) {
            alert("Contact created!");
            showAllContacts();
            return res.statusText;
        });
}

function deleteContact(id) {
    if (confirm("Delete this contact?")) {
        fetch("/contactsList/api/contact/" + id, {
            method: "DELETE",
        })
            .then(function (res) {
                showAllContacts();
                return res.statusText;
            })
    }
}

function showEditForm(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + id);
    hideAllExcept("editForm");
    fetch("/contactsList/api/contact/" + id, {
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
                response.json().then(function (contact) {
                    document.getElementById("editor").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicContactEditor").innerHTML, contact);
                    if (contact.customAvatar === true) {
                        document.getElementById("editFormAvatar").src = "http://localhost:8080/contactsList/api/contact/" + id + "/photo";
                    } else {
                        document.getElementById("editFormAvatar").src = "/contactsList/images/defaultAvatar.png";
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function editContact(id) {
    let contact = {
        id: id,
        firstName: document.getElementById("editFirstName").value,
        surname: document.getElementById("editSurname").value,
        patronymic: document.getElementById("editPatronymic").value,
        birthDate: document.getElementById("editBirthDate").value,
        sex: document.getElementById("editMale").checked ? "male" : "female",
        nationality: document.getElementById("editNationality").value,
        familyStatus: document.getElementById("editSingle").checked ? "single" :
            (document.getElementById("editDivorced").checked ? "divorced" : "married"),
        website: document.getElementById("editWebsite").value,
        email: document.getElementById("editEmail").value,
        currentWorkplace: document.getElementById("editCurrentWorkplace").value,
        address: {
            country: document.getElementById("editCountry").value,
            locality: document.getElementById("editLocality").value,
            street: document.getElementById("editStreet").value,
            house: document.getElementById("editHouse").value,
            apartment: document.getElementById("editApartment").value,
            postcode: document.getElementById("editPostcode").value
        }
    };
    let formData = new FormData();
    formData.append('file', document.getElementById("avatar").files[0]);
    fetch("/contactsList/api/contact/" + id + "/photo",
        {
            method: "POST",
            body: formData
        })
        .then(function (res) {
            return res.statusText;
        });
    fetch("/contactsList/api/contact/" + id,
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
            body: JSON.stringify(contact)
        })
        .then(function (res) {
            alert("Contact edited!");
            showFullContactInfoForm(id);
            return res.statusText;
        })
}

function deleteContacts() {
    if (confirm("Delete selected contact?")) {
        let boxes = document.getElementById("mainTable").getElementsByTagName("input");
        for (i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value !== 'on') {
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

function showFullContactInfoForm(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/contact/' + id);
    hideAllExcept("fullContactInfoForm");
    fetch("/contactsList/api/contact/" + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Charset': 'UTF-8'
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (contact) {
                    document.getElementById("contact").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicContact").innerHTML, contact);
                    if (contact.customAvatar === true) {
                        document.getElementById("fullContactInfoAvatar").src = "http://localhost:8080/contactsList/api/contact/" + id + "/photo";
                    } else {
                        document.getElementById("fullContactInfoAvatar").src = "/contactsList/images/defaultAvatar.png";
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
    fetch("/contactsList/api/contact/" + id + "/phones", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            'charset': 'utf-8'
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (phones) {
                    document.getElementById("mainPhoneTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicPhoneTable").innerHTML, phones);
                    document.getElementById("mainPhoneCheckbox").value = id;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function switchSearchFormVisibility() {
    console.log(document.getElementById("searcher").style.display);
    if (document.getElementById("searcher").style.display === 'none') {
        document.getElementById("searcher").style.display = 'block';
    } else {
        document.getElementById("searcher").style.display = 'none';
    }
}

function searchContacts() {
    let contact = {
        firstName: document.getElementById("searchFirstName").value,
        surname: document.getElementById("searchSurname").value,
        patronymic: document.getElementById("searchPatronymic").value,
        date1: document.getElementById("searchBirthDate1").value,
        date2: document.getElementById("searchBirthDate2").value,
        sex: document.getElementById("searchMale").checked ? "male" :
            (document.getElementById("searchFemale").checked ? "female" : ""),
        nationality: document.getElementById("searchNationality").value,
        familyStatus: document.getElementById("searchSingle").checked ? "single" :
            (document.getElementById("searchDivorced").checked ? "divorced" :
                (document.getElementById("searchMarried").checked ? "married" : "")),
        currentWorkplace: document.getElementById("searchCurrentWorkplace").value,
        address: {
            country: "",
            locality: "",
            street: "",
            house: "",
            apartment: "",
            postcode: ""
        }
    };
    fetch("/contactsList/api/search", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            'charset': 'utf-8'
        },
        body: JSON.stringify(contact)
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (contacts) {
                    document.getElementById("mainTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicMainTable").innerHTML, contacts);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function changeContactsPerPage(contactsPerPage) {
    fetch("/contactsList/api/countOfContacts", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            'charset': 'utf-8'
        },
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.text().then(function (countOfContacts) {
                    let countOfPages = Math.trunc(countOfContacts / contactsPerPage) + 1;
                    document.getElementById("hiddenMaxPageNumber").innerText = countOfPages;
                    document.getElementById("pageController").innerHTML = '<a onclick=\"changePage(\'prev\')\">&laquo;</a>';
                    for (let i = 1; i < countOfPages + 1; i++) {
                        document.getElementById("pageController").innerHTML += '<a id=\"page' + i + '\" onclick=\"changePage(' + i + ')\">' + i + '</a>';
                    }
                    document.getElementById("pageController").innerHTML += '<a onclick=\"changePage(\'next\')\">&raquo;</a>';
                    document.getElementById("page1").style.backgroundColor = '#b0e0e6';
                    showAllContacts(contactsPerPage, 1);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function changePage(param) {
    switch (param) {
        case 'prev' :
            if (parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) > 1) {
                showAllContacts(document.getElementById("ContactsPerPage").value,
                    parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) - 1);
                document.getElementById("hiddenCurrentPageNumber").innerText = (parseInt(document
                    .getElementById("hiddenCurrentPageNumber").innerText) - 1).toString();
            }
            break;
        case 'next' :
            if (parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) < parseInt(document
                .getElementById("hiddenMaxPageNumber").innerText)) {
                showAllContacts(document.getElementById("ContactsPerPage").value,
                    parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) + 1);
                document.getElementById("hiddenCurrentPageNumber").innerText = (parseInt(document
                    .getElementById("hiddenCurrentPageNumber").innerText) + 1).toString();
            }
            break;
        default:
            for (let i = 1; i <= parseInt(document.getElementById("hiddenMaxPageNumber").innerText); i++) {
                let elemId = "page" + i;
                if (i === param) {
                    document.getElementById("hiddenCurrentPageNumber").innerText = i.toString();
                    document.getElementById(elemId).style.backgroundColor = '#b0e0e6';
                    showAllContacts(document.getElementById("ContactsPerPage").value, i);
                } else {
                    document.getElementById(elemId).style.backgroundColor = '';
                }
            }
    }
}