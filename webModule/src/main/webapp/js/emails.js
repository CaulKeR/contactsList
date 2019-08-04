function showEmailForm(id) {
    history.pushState({prevUrl: window.location.href}, null, '/contactsList/email');
    hideAllExcept("emailForm");
    document.getElementById("sendTo").innerHTML = "Send e-mail to: ";
    let boxes = document.getElementById("mainTable").getElementsByTagName("input");
    let ids = [];
    if (id !== null && id !== "" && id !== undefined) {
        ids.push(id);
    }
    for (let i = 0; i < boxes.length; i++) {
        if (boxes[i].type === "checkbox" && boxes[i].checked && boxes[i].value !== 'on') {
            ids.push(boxes[i].value);
        }
    }
    for (let i = 0; i < ids.length; i++) {
        fetch("/contactsList/api/contact/" + ids[i] + "/mail", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'charset': 'utf-8',
            }
        })
            .then(function (response) {
                    if (response.status < 200 || response.status >= 400) {
                        console.log('Looks like there was a problem. Status Code: ' + response.status);
                    }
                    response.json().then(function (contact) {
                        document.getElementById("sendTo").innerHTML += contact.email + " ";
                    });
                }
            )
            .catch(function (err) {
                console.log('Fetch Error :-S', err);
            });
    }
    fetch("/contactsList/api/mail/templates", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8',
        }
    })
        .then(function (response) {
            if (response.status < 200 || response.status >= 400) {
                console.log('Looks like there was a problem. Status Code: ' + response.status);
            }
            response.json().then(function (data) {
                console.log(data);
                document.getElementById("templateSelect").innerHTML = Mustache.to_html(document
                    .getElementById("dynamicTemplateSelect").innerHTML, data);
            });
        });
}

function sendEmail() {
    let email = {
        emails: document.getElementById("sendTo").innerText.replace("Send e-mail to: ", '')
            .split(' '),
        subject: document.getElementById("emailSubject").value,
        text: document.getElementById("emailText").value
    };
    console.log(email.emails);
    console.log(email.subject);
    console.log(email.text);
    fetch("/contactsList/api/mail",
        {
            method: "POST",
            headers: {
                'charset': 'utf-8',
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'mode': 'cors',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true
            },
            body: JSON.stringify(email)
        })
        .then(function (res) {
            alert("E-mail sent!");
            showAllContacts(10, 1);
            return res.statusText;
        })
}

function changeOption(value) {
    document.getElementById("emailText").value = value;
}