$(document).ready(async function () {
    const response = await fetch("/api/");
    if (response.ok) {
        let productsList = await response.json();
        for (let product of productsList) {
            innerProduct(product);
        }

        $(document).on("click", ".remove", async (event) => {
            const response = await fetch(`/api/remove/${event.target.parentNode.id}`, {
                method: "POST", headers: {"Accept": "application/json"}
            });
            if (response.ok) {
                $(event.target).parent().remove();
            } else {
                alert("Ошибка HTTP: " + response.status);
            }
        });
        $(document).on("click", ".update", async (event) => {
            await fetch(`/api/update/${event.target.parentNode.id}`, {
                method: "POST", headers: {"Accept": "application/json"}
            });
        });
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

    function innerProduct(product) {
        let products = document.getElementById("products");
        let child = document.createElement("div");
        child.id = `${product.id}`;
        child.innerHTML += `<span> ${product.name} </span>`;
        child.innerHTML += `<input type="button" class="remove" value="Удалить">`;
        child.innerHTML += `<input type="checkbox" class="update" ${product.bought ? 'checked' : ''} value="${product.bought}">`
        products.appendChild(child);
    }
});