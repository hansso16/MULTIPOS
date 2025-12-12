
document.addEventListener("DOMContentLoaded", function () {
    const customerSelect = document.getElementById("customerSelect");
    const availableList = document.getElementById("availableProducts");
    const assignedList = document.getElementById("assignedProducts");

    customerSelect.addEventListener("change", function () {
        const customerId = this.value;

        // Clear lists if no customer selected
        if (!customerId) {
            availableList.innerHTML = "";
            assignedList.innerHTML = "";
            return;
        }

        // Call your Spring Boot endpoint
        fetch('/api/customer/' + customerId + '/product')
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                renderProducts(availableList, data.availableProducts, false);
                renderProducts(assignedList, data.assignedProducts, true);
				document.getElementById("availableSearch").value = "";
				document.getElementById("assignedSearch"). value = "";
            })
            .catch(error => {
                console.error("Error loading products:", error);
                // you can show a toast/alert here if you want
            });
    });

    function renderProducts(container, products, assigned) {
        container.innerHTML = "";

        if (!products || products.length === 0) {
            container.innerHTML = `<div class="text-muted small p-2">No products</div>`;
            return;
        }

        products.forEach(p => {
            const item = document.createElement("label");
            item.className = "list-group-item";
			item.dataset.productId = p.productId;

			const checkbox = document.createElement("input");
			       checkbox.type = "checkbox";
			       checkbox.className = "form-check-input me-2";
				   
            const span = document.createElement("span");
            span.textContent = p.productCode + " - " + p.productName;

			item.appendChild(checkbox);
            item.appendChild(span);

            // Optional: add a hidden input or button depending on your UI
            // Example: hidden input used later when submitting form
            const hidden = document.createElement("input");
            hidden.type = "hidden";
            hidden.value = p.productCode;
            hidden.name = assigned ? "assignedProductIds" : "availableProductIds";
            item.appendChild(hidden);

            container.appendChild(item);
        });
    }
	
	document.getElementById("btnAssign")
	    .addEventListener("click", () => {
	        moveSelected(availableList, assignedList);
	    });

	document.getElementById("btnRemove")
	    .addEventListener("click", () => {
	        moveSelected(assignedList, availableList);
	    });
	
	function moveSelected(fromList, toList) {
	    const checked = fromList.querySelectorAll(
	        "input[type='checkbox']:checked"
	    );

	    if (checked.length === 0) {
	        return; // nothing selected
	    }

	    checked.forEach(cb => {
	        cb.checked = false;
	        const item = cb.closest("label");
	        toList.appendChild(item);
	    });
	}
	
	document.getElementById("saveChangesBtn").addEventListener("click", () => {
	    const customerId = document.getElementById("customerSelect").value;

	    if (!customerId) {
	        alert("Please select a customer.");
	        return;
	    }

	    const assignedProductIds = Array.from(
	        document.querySelectorAll("#assignedProducts label")
	    ).map(label => Number(label.dataset.productId));

		const csrf = getCsrf();
		
	    fetch('/api/customer/' + customerId + '/product', {
	        method: "POST",
	        headers: {
	            "Content-Type": "application/json",
				[csrf.header]: csrf.token
	        },
	        body: JSON.stringify(assignedProductIds)
	    })
	    .then(res => {
	        if (!res.ok) throw new Error("Save failed");
	        showToast("Changes saved successfully ✅");
	    })
	    .catch(err => {
	        console.error(err);
	        alert("Error saving products.");
	    });
	});
	
	function getCsrf() {
	    return {
	        token: document.querySelector('meta[name="_csrf"]').getAttribute('content'),
	        header: document.querySelector('meta[name="_csrf_header"]').getAttribute('content')
	    };
	}
	

	function setupSearch(inputId, listId) {
        const input = document.getElementById(inputId);
        const list = document.getElementById(listId);

        if (!input || !list) {
            console.warn('Missing element for', inputId, listId);
            return;
        }

        input.addEventListener('input', () => {
            const query = input.value.trim().toLowerCase();
            // get all items inside that list
            const items = list.querySelectorAll('.list-group-item');

            items.forEach(item => {
                // get the visible text of the item
                const nameSpan = item.querySelector('.product-name');
                const text = (nameSpan ? nameSpan.textContent : item.textContent)
                                .trim()
                                .toLowerCase();

                if (text.includes(query)) {
                    item.style.display = '';      // show
                } else {
                    item.style.display = 'none';  // hide
                }
            });
        });
    }
		
	setupSearch('availableSearch', 'availableProducts');
	setupSearch('assignedSearch', 'assignedProducts');
	
	function showToast(message, isError = false) {
	    const toastEl = document.getElementById("saveToast");
	    const body = toastEl.querySelector(".toast-body");

	    body.textContent = message;

	    toastEl.classList.toggle("text-bg-success", !isError);
	    toastEl.classList.toggle("text-bg-danger", isError);

	    const toast = bootstrap.Toast.getOrCreateInstance(toastEl);
	    toast.show();
	}
	
});