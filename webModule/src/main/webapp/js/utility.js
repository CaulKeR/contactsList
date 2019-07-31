function hideAllExcept(form) {
    var forms = ["mainForm", "editForm", "fullContactInfoForm", "createForm", "attachmentsForm", "searcher", "emailForm",
        "editAttachForm", "editPhoneForm", "createPhoneForm"];
    if (document.getElementById(form).style.display === 'block') {
        return;
    }
    for (var i = 0; i < forms.length; i++) {
        if (forms[i] === form) {
            document.getElementById(form).style.display = 'block';
        } else {
            document.getElementById(forms[i]).style.display = 'none';
        }
    }
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

window.addEventListener('popstate', function ()
{
    showAllContacts();
});