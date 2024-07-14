document.addEventListener("DOMContentLoaded", function() {
    function confirmDelete(toDoId) {
        console.log("HOLA guapo id:" + toDoId);
        if (confirm("Are you sure you want to delete this item?")) {
            window.location.href = `/delete-todo?id=${toDoId}`;
        }
    }
    window.confirmDelete = confirmDelete;
});