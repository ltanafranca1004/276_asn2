document.addEventListener('DOMContentLoaded', () => {
    fetchStudents();
    document.getElementById('addStudentForm').addEventListener('submit', handleAddStudentFormSubmit);
});

function handleAddStudentFormSubmit(event) {
    event.preventDefault(); // Prevent the default form submission
    const formData = new FormData(event.target);
    addStudent(formData);
}

function addStudent(formData) {
    const studentData = Object.fromEntries(formData.entries());

    fetch('/api/students', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(studentData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(student => {
        fetchStudents(); // Re-fetch all students to update the list
    })
    .catch(error => {
        console.error('Error adding student:', error);
    });
}

function fetchStudents() {
    fetch('/api/students')
        .then(response => response.json())
        .then(students => {
            const studentsDisplay = document.getElementById('studentsDisplay');
            studentsDisplay.innerHTML = ''; // Clear previous students
            students.forEach(student => {
                const studentDiv = createStudentDiv(student);
                studentsDisplay.appendChild(studentDiv);
            });
        })
        .catch(error => console.error('Error fetching students:', error));
}

function createStudentDiv(student) {
    const div = document.createElement('div');
    div.className = 'student-rectangle';
    div.style.backgroundColor = student.hairColor;
    div.style.width = `${student.weight}px`;
    div.style.height = `${student.height}px`;
    div.innerHTML = `<span>${student.name}<br>GPA: ${student.gpa}</span>`;
    div.addEventListener('click', () => editStudent(student.id));
    return div;
}

function editStudent(studentId) {
    // Navigate to student edit page or open edit form modal
    window.location.href = `/edit-student/${studentId}`;
}

function deleteStudent(studentId) {
    fetch(`/api/students/${studentId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            fetchStudents(); // Refresh the list of students
        } else {
            throw new Error('Failed to delete student');
        }
    })
    .catch(error => console.error('Error deleting student:', error));
}

document.querySelectorAll('.delete-button').forEach(button => {
    button.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent the link from navigating
  
      // Get the delete URL from the button's data attribute
      var deleteUrl = this.dataset.deleteUrl;
  
      // Show the modal
      var deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmationModal'));
      deleteModal.show();
  
      // Find the confirm delete button in the modal and attach an event listener
      document.getElementById('confirmDeleteButton').addEventListener('click', function() {
        // Perform the delete action, for example, by navigating to the delete URL or sending an AJAX request
        window.location.href = deleteUrl;
      });
    });
  });