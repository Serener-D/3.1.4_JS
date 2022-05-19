//This script populates the header and single table row with user's data
const populateHeader = () => {
    fetch('http://localhost:8080/user/json')
        .then(response => {
            response.json().then(user => {
            let roles = "";
            for (const role of user.roles) {
                roles += role.role;
                roles += " ";
            }

            document.querySelector("#headerName").text = user.name;
            document.querySelector("#headerRole").text = roles;
            })
        })
}

const populateSingleRowTable = () => {
    fetch('http://localhost:8080/user/json')
        .then(response => {
            response.json().then(user => {
                document.querySelector("#tableId").innerHTML = user.id;
                document.querySelector("#tableName").innerHTML = user.name;
                document.querySelector("#tablePassword").innerHTML = user.password;
                document.querySelector("#tableAge").innerHTML = user.age;
                document.querySelector("#tableRoles").innerHTML = document.querySelector("#headerRole").innerHTML;
                // Displaying Admin Panel link
                if (document.querySelector("#headerRole").text.includes("ROLE_ADMIN")) {
                    document.querySelector("#admin").style.display = "block";
                }
            })
       })
}

populateHeader()
populateSingleRowTable()