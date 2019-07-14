function show(form) {
    document.getElementById(form).style.display = 'block';
}
function hide(form) {
    document.getElementById(form).style.display = 'none';
}

function selectAllCheckboxes(mainCheckbox, table) {
    var boxes = document.getElementById(table).getElementsByTagName("input");
    if (document.getElementById(mainCheckbox).checked) {
        for (i = 0; i < boxes.length; i++) {
            boxes[i].checked = true;
        }
    } else {
        for (i = 0; i < boxes.length; i++) {
            boxes[i].checked = false;
        }
    }
}

function selectCheckbox(mainCheckbox, table) {
    var boxes = document.getElementById(table).getElementsByTagName("input");
    for (i = 0; i < boxes.length; i++) {
        if (document.getElementById(mainCheckbox).checked && boxes[i].checked === false) {
            document.getElementById(mainCheckbox).checked = false;
        }
    }
}

function goBack() {
    var prevUrl = window.history.state.prevUrl;
    console.log(prevUrl);
    switch (prevUrl) {
        case '/contactsList/contacts' :
            show(mainForm);
            break;
        case '/contactsList/contact/' :
            show(fullContactInfoForm);
            break;
        default :
            alert("GG");
    }
    window.history.back();
}