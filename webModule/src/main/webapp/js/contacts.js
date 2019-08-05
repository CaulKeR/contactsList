function showAllContacts(contactsPerPage, pageNumber) {
    document.getElementById("pagination").style.visibility = 'visible';
    history.pushState({prevUrl: window.location.href}, null, '/contacts');
    hideAllExcept("mainForm");
    document.getElementById("searchIcon").style.display = 'block';
    if (contactsPerPage === undefined || pageNumber === undefined) {
        contactsPerPage = 10;
        pageNumber = 1;
        changeContactsPerPage(contactsPerPage);
    }
    fetch("/api/contacts?count=" + contactsPerPage + "&page=" + pageNumber, {
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

function showCreateContactForm() {
    history.pushState({prevUrl: window.location.href}, null, '/contact');
    hideAllExcept("createForm");
}

function createContact() {
    let contact = {
        firstName: document.getElementById("firstName").value,
        surname: document.getElementById("surname").value,
        patronymic: document.getElementById("patronymic").value,
        birthDate: document.getElementById("birthDate").value,
        sex: document.getElementById("male").checked ? "male" :
            (document.getElementById("female").checked ? "female" : ""),
        nationality: document.getElementById("nationality").value,
        familyStatus: document.getElementById("single").checked ? "single" :
            (document.getElementById("divorced").checked ? "divorced" :
                (document.getElementById("married").checked ? "married" : "")),
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
    if (validateContactInputFields(contact)) {
        fetch("/api/contact",
            {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=utf-8',
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
}

function deleteContact(id) {
    if (confirm("Delete this contact?")) {
        fetch("/api/contact/" + id, {
            method: "DELETE",
        })
            .then(function (res) {
                showAllContacts();
                alert("Contact successfully deleted!");
                return res.statusText;
            })
    }
}

function showEditForm(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contact/' + id);
    hideAllExcept("editForm");
    fetch("/api/contact/" + id, {
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
                    document.getElementById("editFormAvatar").src = getAvatarUrl(contact.customAvatar, id);
                    switch (contact.sex) {
                        case 'male' :
                            document.getElementById("editMale").checked = true;
                            break;
                        case 'female' :
                            document.getElementById("editFemale").checked = true;
                            break;
                    }
                    switch (contact.familyStatus) {
                        case 'single' :
                            document.getElementById("editSingle").checked = true;
                            break;
                        case 'divorced' :
                            document.getElementById("editDivorced").checked = true;
                            break;
                        case 'married' :
                            document.getElementById("editMarried").checked = true;
                            break;
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
        sex: document.getElementById("editMale").checked ? "male" :
            (document.getElementById("editFemale").checked ? "female" : ""),
        nationality: document.getElementById("editNationality").value,
        familyStatus: document.getElementById("editSingle").checked ? "single" :
            (document.getElementById("editDivorced").checked ? "divorced" :
                (document.getElementById("editMarried").checked ? "married" : "")),
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
    if (validateContactInputFields(contact)) {
        let formData = new FormData();
        formData.append('file', document.getElementById("avatar").files[0]);
        fetch("/api/contact/" + id + "/photo",
            {
                method: "POST",
                body: formData
            })
            .then(function (res) {
                return res.statusText;
            });
        fetch("/api/contact/" + id,
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
}

function deleteContacts() {
    if (confirm("Delete selected contact?")) {
        let boxes = document.getElementById("mainTable").getElementsByTagName("input");
        for (i = 0; i < boxes.length; i++) {
            if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value !== 'on') {
                fetch("/api/contact/" + boxes[i].value, {
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
    history.pushState({prevUrl: window.location.href}, null, '/contact/' + id);
    hideAllExcept("fullContactInfoForm");
    fetch("/api/contact/" + id, {
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
                    document.getElementById("fullContactInfoAvatar").src = getAvatarUrl(contact.customAvatar, id);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
    fetch("/api/contact/" + id + "/phones", {
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
    if (document.getElementById("searcher").style.display === 'none') {
        document.getElementById("searcher").style.display = 'block';
    } else {
        document.getElementById("searcher").style.display = 'none';
    }
}

function searchContacts() {
    document.getElementById("pagination").style.visibility = 'hidden';
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
    fetch("/api/search", {
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
    document.getElementById("ContactsPerPage").value = contactsPerPage;
    fetch("/api/countOfContacts", {
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
                    let countOfPages = Math.ceil(countOfContacts / contactsPerPage);
                    document.getElementById("hiddenMaxPageNumber").innerText = countOfPages.toString();
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
                document.getElementById('page' + document.getElementById("hiddenCurrentPageNumber").innerText).style.backgroundColor = '';
                document.getElementById("hiddenCurrentPageNumber").innerText = (parseInt(document
                    .getElementById("hiddenCurrentPageNumber").innerText) - 1).toString();
                document.getElementById('page' + document.getElementById("hiddenCurrentPageNumber").innerText).style.backgroundColor = '#b0e0e6';
            }
            break;
        case 'next' :
            if (parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) < parseInt(document
                .getElementById("hiddenMaxPageNumber").innerText)) {
                showAllContacts(document.getElementById("ContactsPerPage").value,
                    parseInt(document.getElementById("hiddenCurrentPageNumber").innerText) + 1);
                document.getElementById("page" + document.getElementById("hiddenCurrentPageNumber").innerText).style.backgroundColor = '';
                document.getElementById("hiddenCurrentPageNumber").innerText = (parseInt(document
                    .getElementById("hiddenCurrentPageNumber").innerText) + 1).toString();
                document.getElementById("page" + document.getElementById("hiddenCurrentPageNumber").innerText).style.backgroundColor = '#b0e0e6';
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

function getAvatarUrl(isAvatarCustom, id) {
    return isAvatarCustom ? "http://localhost:8080/api/contact/" + id + "/photo" : "/images/defaultAvatar.png";
}

function validateContactInputFields(contact) {
    let isValid = true;
    if (contact.firstName.length > 30) {
        isValid = false;
        alert("First name is too long!");
    }
    if (contact.firstName === "") {
        isValid = false;
        alert("First name cannot be empty!");
    }
    if (contact.surname.length > 30) {
        isValid = false;
        alert("Surname is too long!");
    }
    if (contact.surname === "") {
        isValid = false;
        alert("Surname cannot be empty!");
    }
    if (contact.patronymic.length > 30) {
        isValid = false;
        alert("Patronymic is too long!");
    }
    if (contact.birthDate === undefined || contact.birthDate === "") {
        isValid = false;
        alert("Birth date cannot be empty!");
    }
    if (contact.nationality.length > 30) {
        isValid = false;
        alert("Nationality is too long!");
    }
    if (!/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w.-]+)+[\w\-._~:/?#[\]@!$&'()\*\+,;=.]+$/gm.test(contact.website)) {
        isValid = false;
        alert("Website is not URL!");
    }
    if (contact.website.length > 512) {
        isValid = false;
        alert("Website is too long!");
    }
    if (contact.email.length > 512) {
        isValid = false;
        alert("E-mail is too long!");
    }
    if (!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(contact.email)) {
        isValid = false;
        alert("E-mail is not correct!");
    }
    if (contact.currentWorkplace.length > 100) {
        isValid = false;
        alert("Current workplace is too long!");
    }
    if (contact.address.country.length > 60) {
        isValid = false;
        alert("Country is too long!");
    }
    if (contact.address.locality.length > 150) {
        isValid = false;
        alert("Locality is too long!");
    }
    if (contact.address.street.length > 100) {
        isValid = false;
        alert("Street is too long!");
    }
    if (contact.address.house.length > 10) {
        isValid = false;
        alert("House is too long!");
    }
    if (contact.address.apartment > 32767) {
        isValid = false;
        alert("Apartment is too long!");
    }
    if (contact.address.postcode.length > 25) {
        isValid = false;
        alert("Postcode is too long!");
    }
    return isValid;
}