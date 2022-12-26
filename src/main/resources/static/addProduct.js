$(document).ready(function () {
    $("#add").click((event) => {
        fetch("/api/products/add", {
            method: "POST",
            headers: {"Accept": "application/json", "Content-Type": "application/json;charset=utf-8"},
            body: JSON.stringify({
                "name": document.getElementById("name").value
            })
        });
    });
});