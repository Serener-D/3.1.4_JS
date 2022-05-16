//This script populates the header and single table row with user's data
fetch('http://localhost:8080/user/json')
    .then(response => {
        return response.json();
    })
    .then(user => {

        let roles = "";
        if (user.roles.length > 0) {
            for (const role of user.roles) {
                roles += role.role;
                roles += " ";
            }
        } else {
            roles += "No role";
        }

        // Populating header
        document.querySelector("#headerName").text = user.name;
        document.querySelector("#headerRole").text = roles;

        // Populating table
        document.querySelector("#tableId").innerHTML = user.id;
        document.querySelector("#tableName").innerHTML = user.name;
        document.querySelector("#tablePassword").innerHTML = user.password;
        document.querySelector("#tableAge").innerHTML = user.age;
        document.querySelector("#tableRoles").innerHTML = roles;

        // Displaying Admin Panel link
        if (roles.includes("ROLE_ADMIN")) {
            document.querySelector("#admin").style.display = "block";
        }
    })