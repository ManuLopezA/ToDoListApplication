function confirmDelete(toDoId) {
    if (confirm("Are you sure? This operation cannot be undone.")) {
        window.location.href = `/delete-todo?id=${toDoId}`;
    }
}
window.confirmDelete = confirmDelete;