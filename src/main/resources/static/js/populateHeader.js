// Populates the header
const populateHeader = () => {
    fetch('http://localhost:8080/user/authenticated')
        .then(response => {
            response.json().then(user => {
            let roles = "";
            for (const role of user.roles) {
                roles += role.role + " ";
            }
            document.querySelector("#headerName").text = user.name;
            document.querySelector("#headerRole").text = roles;
            })
        })
}
populateHeader()