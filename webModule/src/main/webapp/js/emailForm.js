function showEmailForm(id) {
    history.pushState({ prevUrl: window.location.href }, 'Send e-mail', '/contactsList/email');
    hideAllExcept("emailForm");
    document.getElementById("sendTo").innerHTML = "Send e-mail to: ";
    var boxes = document.getElementById("mainTable").getElementsByTagName("input");
    var ids = [];
    if (id !== null && id !== "" && id !== undefined) {
        ids.push(id);
    }
    for (i = 0; i < boxes.length; i++) {
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
                    response.json().then(function (data) {
                        document.getElementById("sendTo").innerHTML += data.email + ", ";
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
                let template = document.getElementById("dynamicTemplateSelect").innerHTML;
                document.getElementById("templateSelect").innerHTML = Mustache.to_html(template, data);
            });
        });
}

function sendEmail() {
    var email = {
        emails: document.getElementById("sendTo").innerText.replace("Send e-mail to: ", '').replace(/\s+/g, '').split(','),
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
                "Access-Control-Allow-Origin" : "*",
                "Access-Control-Allow-Credentials" : true
            },
            body: JSON.stringify(email)
        })
        .then(function(res){
            alert("E-mail sent!");
            showAllContacts();
            return res.statusText;})
}

function changeOption(value) {
    document.getElementById("emailText").value = value;
}