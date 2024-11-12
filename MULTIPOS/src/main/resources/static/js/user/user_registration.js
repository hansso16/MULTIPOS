
// Wait for the DOM to fully load
document.addEventListener("DOMContentLoaded", function() {

    // Get all role containers
    const roleContainers = document.querySelectorAll("[data-role-id]");

    roleContainers.forEach(roleContainer => {
        // Get the role checkbox within each container
        const roleCheckbox = roleContainer.querySelector(".role-checkbox");
        const privilegeCheckboxes = roleContainer.querySelectorAll(".privilege-checkbox");

        privilegeCheckboxes.forEach(privilegeCheckbox => {
            privilegeCheckbox.addEventListener("change", function() {
                if (Array.from(privilegeCheckboxes).some(privilege => privilege.checked)) {
                    // If any privilege is checked, check the role checkbox and make it read-only
                    roleCheckbox.checked = true;
                    roleCheckbox.disabled = true;
                } else {
                    // If all privileges are unchecked, uncheck the role checkbox and make it editable
                    roleCheckbox.checked = false;
                    roleCheckbox.disabled = false;
                }
            });
        });
    });
});