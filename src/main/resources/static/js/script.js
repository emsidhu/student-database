// const deleteButtons = document.querySelectorAll('[data-bs-toggle="modal"]');
// deleteButtons.forEach(button => {
//     button.addEventListener('click', deleteStudent);
// });

function deleteStudent(id) {
    fetch(`/students/delete/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        // If student was successfully deleted, reload the page
        location.reload();
    })
    .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
        // handle error
    });
}

