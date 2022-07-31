document.addEventListener("DOMContentLoaded", () => {

    let submitButton = document.querySelector('button[type="submit"]');
    submitButton.addEventListener('click', submitForm);

    iterInputAndAddEventListener();
    iterInputAndSetFilledIfFilled();
});

function iterInputAndAddEventListener() {
    let inputs, textareas, input, textarea, resetbutton, i;
    inputs = document.querySelectorAll('input');
    textareas = document.querySelectorAll('textarea');
    resetbutton = document.querySelector('button[type="reset"]');
    for (i = 0; i < inputs.length; i++) {
        input = inputs[i];
        input.addEventListener("input", iterInputAndSetFilledIfFilled);
    }
    for (i = 0; i < textareas.length; i++) {
        textarea = textareas[i];
        textarea.addEventListener("input", iterInputAndSetFilledIfFilled);
    }
    resetbutton.addEventListener("click", () => {
        setTimeout(() => {
            resetForm();
        }, 100)
    });
}

function submitForm() {
    let checkboxes = document.querySelectorAll("input[type='checkbox']");
    checkboxes.forEach(checkbox => {
        if (checkbox.hasAttribute('required')) {
            let container = checkbox.closest('label');
            if (checkbox.checked) {
                removeErrorMessage(container);
            } else {
                createErrorMessage(checkbox, 'Akzeptieren Sie bitte das Feld', container);
            }
        }
    });

    validateEmail();
}

function resetForm() {
    let inputs = document.querySelectorAll('input');
    let textareas = document.querySelectorAll('textarea');
    for (let i = 0; i < inputs.length; i++) {
        input = inputs[i];
        input.value = "";
        input.checked = false;
        input.classList.remove("filled");
    }

    for (i = 0; i < textareas.length; i++) {
        textarea = textareas[i];
        textarea.value = "";
        textarea.classList.remove("filled");
    }

    let errorMessages = document.getElementsByClassName('error-message');
    Array.from(errorMessages).forEach(error => {
        removeErrorMessage(error);
    })
}

function validateEmail() {
    let email = document.querySelectorAll('input[name="email"]')[0];
    let emailRepeat = document.querySelectorAll('input[name="cemail"]')[0];
    let emailsContainer = emailRepeat.parentNode.closest('.row100');

    if (email.value === emailRepeat.value) {
        let children = emailsContainer.parentNode.childNodes;
        children.forEach(child => {
            if (child.classList && child.classList.contains('error-message')) {
                removeErrorMessage(emailsContainer);
            }
        });
    } else {
        createErrorMessage(emailRepeat, 'Emailadressen sind nicht gleich', emailsContainer);
    }
}

function createErrorMessage(errorField, error, fieldContainer) {

    let children = fieldContainer.parentNode.childNodes;
    let hasErrorMessage = false;
    children.forEach(child => {
        if (child.classList && child.classList.contains('error-message')) {
            hasErrorMessage = true;
        }
    });
    if (!hasErrorMessage) {
        let span = document.createElement("span");
        let errorText = document.createTextNode(error);
        span.appendChild(errorText);
        span.setAttribute('class', 'error-message');
        fieldContainer.parentNode.insertBefore(span, fieldContainer.nextSibling)
    }
}

function removeErrorMessage(errorField) {
    let errorMessage = errorField.parentNode.getElementsByClassName('error-message')[0];
    if (errorMessage) {
        errorField.parentNode.removeChild(errorMessage);
    }
}

function iterInputAndSetFilledIfFilled() {
    let inputs, textareas, input, textarea, i;
    inputs = document.querySelectorAll('input');
    textareas = document.querySelectorAll('textarea');
    for (i = 0; i < inputs.length; i++) {
        input = inputs[i];
        if (input.value !== "") {
            input.classList.add("filled");
        } else {
            input.classList.remove("filled");
        }
    }
    for (i = 0; i < textareas.length; i++) {
        textarea = textareas[i];
        if (textarea.value !== "") {
            textarea.classList.add("filled");
        } else {
            textarea.classList.remove("filled");
        }
    }
}
