document.addEventListener('DOMContentLoaded', function () {
    const complaintForm = document.querySelector('#complaintForm');
    const submitButton = complaintForm ? complaintForm.querySelector('button[type="submit"]') : null;
    const statusSelects = document.querySelectorAll('select[name="status"]');

    if (complaintForm && submitButton) {
        complaintForm.addEventListener('submit', function () {
            submitButton.textContent = 'Sending...';
            submitButton.disabled = true;
        });
    }

    statusSelects.forEach(function (select) {
        select.addEventListener('change', function () {
            const row = select.closest('tr');
            if (row) {
                row.classList.add('status-spark');
                window.setTimeout(function () {
                    row.classList.remove('status-spark');
                }, 900);
            }
        });
    });
});

function showToast(message) {
    let toast = document.querySelector('.toast-notification');
    if (!toast) {
        toast = document.createElement('div');
        toast.className = 'toast-notification';
        document.body.appendChild(toast);
    }
    toast.textContent = message;
    toast.classList.add('show');
    window.setTimeout(function () {
        toast.classList.remove('show');
    }, 2400);
}

function showToast(message) {
    let toast = document.querySelector('.toast-notification');
    if (!toast) {
        toast = document.createElement('div');
        toast.className = 'toast-notification';
        document.body.appendChild(toast);
    }
    toast.textContent = message;
    toast.classList.add('show');
    window.setTimeout(function () {
        toast.classList.remove('show');
    }, 2400);
}
