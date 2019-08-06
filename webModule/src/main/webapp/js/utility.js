function hideAllExcept(form) {
    let forms = ["mainForm", "editForm", "fullContactInfoForm", "createForm", "attachmentsForm", "searcher", "emailForm",
        "editAttachForm", "editPhoneForm", "createPhoneForm", "searchIcon"];
    if (document.getElementById(form).style.display === 'block') {
        return;
    }
    for (let i = 0; i < forms.length; i++) {
        if (forms[i] === form) {
            document.getElementById(form).style.display = 'block';
        } else {
            document.getElementById(forms[i]).style.display = 'none';
        }
    }
}

function selectAllCheckboxes(mainCheckbox, table) {
    let boxes = document.getElementById(table).getElementsByTagName("input");
    let isMainCheckboxChecked = document.getElementById(mainCheckbox).checked;
    for (let i = 0; i < boxes.length; i++) {
        boxes[i].checked = isMainCheckboxChecked;
    }
}

function selectCheckbox(mainCheckbox, table) {
    let boxes = document.getElementById(table).getElementsByTagName("input");
    for (let i = 0; i < boxes.length; i++) {
        if (document.getElementById(mainCheckbox).checked && boxes[i].checked === false) {
            document.getElementById(mainCheckbox).checked = false;
        }
    }
}

window.addEventListener('popstate', function () {
    showAllContacts(10, 1);
});

window.onload = function () {
    hideAllExcept("mainForm");
    changeContactsPerPage(10);
};