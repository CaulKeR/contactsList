function showAllContacts() {
    history.pushState({ prevUrl: window.location.href }, 'Contacts list', '/contactsList/contacts');
    hideAllExcept("mainForm");
    fetch("/contactsList/api/contacts",{
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
                response.json().then(function (data) {
                    var contacts = data;
                    var template = document.getElementById("dynamicMainTable").innerHTML;
                    document.getElementById("mainTable").innerHTML = Mustache.to_html(template, contacts);
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
    var contact = {
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
        }};
    fetch("/contactsList/api/search",{
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
                response.json().then(function (data) {
                    var contacts = data;
                    console.log(contacts);
                    var template = document.getElementById("dynamicMainTable").innerHTML;
                    document.getElementById("mainTable").innerHTML = Mustache.to_html(template, contacts);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}