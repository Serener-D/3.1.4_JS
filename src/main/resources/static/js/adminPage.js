// Gets all existent roles
const getRoles = () => {
    fetch('http://localhost:8080/user/roles')
        .then(response => {
            return response.json();
        })
        .then(roles => {
            let content = "";
            for (let j = 0; j < roles.length; j++) {
                content += "<option value=" + roles[j].id + ">" + roles[j].role + "</option>";
            }
            let rolesSelections = document.querySelectorAll("#rolesCreate, #rolesPatch, #rolesDelete")
            for (let i = 0; i < rolesSelections.length; i++) {
                rolesSelections[i].innerHTML = content;
            }
        })
}
getRoles()

// Creating table with Users
const createTable = () => {
    fetch('http://localhost:8080/api/users')
        .then(response => {
            response.json().then(data => {
                if (data.length > 0) {
                    let temp = "";
                    data.forEach((user) => {
                        temp += "<tr>";
                        temp += "<td>" + user.id + "</td>";
                        temp += "<td>" + user.name + "</td>";
                        temp += "<td>" + user.password + "</td>";
                        temp += "<td>" + user.age + "</td>";

                        if (user.roles.length > 0) {
                            let roles = "";
                            for (const role of user.roles) {
                                roles += role.role;
                                roles += " ";
                            }
                            temp += "<td>" + roles + "</td>";
                        } else {
                            temp += "<td>" + "No role" + "</td>";
                        }
                        temp += "<td> <a type=\"button\" data-id=" + user.id + " class=\"btn btn-info text-white\" id='editButton' data-bs-toggle=\"modal\" data-bs-target=\"#editModal\">Edit</a></td>";
                        temp += "<td> <a type=\"button\" data-id=" + user.id + " class=\"btn btn-danger\" id='deleteButton' data-bs-toggle=\"modal\" data-bs-target=\"#deleteModal\">Delete</a></td>";
                    })
                    document.querySelector("#userTableContent").innerHTML = temp;
                }
            })
        })
}
createTable()

// Populating Edit and Delete modal windows
const populateModals = () => {
    setTimeout(() => {
        let editButtons = document.querySelectorAll("#editButton, #deleteButton")
        for (let i = 0; i < editButtons.length; i++) {
            editButtons[i].addEventListener('click', function (event) {
                // event.preventDefault();
                const userId = editButtons[i].getAttribute('data-id')
                fetch('http://localhost:8080/api/users/' + userId)
                    .then(response => {
                        return response.json();
                    })
                    .then(user => {
                        document.querySelector("#idPatch").value = user.id
                        document.querySelector("#namePatch").value = user.name
                        document.querySelector("#agePatch").value = user.age
                        document.querySelector("#passwordPatch").value = user.password

                        document.querySelector("#idDelete").value = user.id
                        document.querySelector("#nameDelete").value = user.name
                        document.querySelector("#ageDelete").value = user.age
                    })
            })
        }
    }, 800);
};
populateModals()

// Creating new User
const createUser = (roleIds) => {
    fetch('http://localhost:8080/api/users?roleIds=' + roleIds, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: document.querySelector("#name").value,
            age: document.querySelector("#age").value,
            password: document.querySelector("#password").value,

        })
    })
        .then(response => {
            createTable()
            populateModals()
            return response
        })
        .catch(error => console.error(error))
};

// Patching existing User
const patchUser = (roleIds) => {
    fetch('http://localhost:8080/api/users/' + roleIds, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: document.querySelector("#namePatch").value,
            age: document.querySelector("#agePatch").value,
            password: document.querySelector("#passwordPatch").value,
        })
    })
        .then(response => {
            createTable()
            populateModals()
            return response
        })
        .catch(error => console.error(error))
};

// Deleting existing User
const deleteUser = (id) => {
    fetch('http://localhost:8080/api/users/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            createTable()
            populateModals()
            return response
        })
        .catch(error => console.error(error))
};


// Event listener for submitting Create User form
let createForm = document.querySelector('#addUserForm');
createForm.addEventListener('submit', function (e) {
    e.preventDefault();
    let ids = Array.from(document.getElementById("rolesCreate").options).filter(option => option.selected).map(option => option.value)
    createUser(ids)
    createForm.reset();
});

// Event listener for submitting Edit User form
let editForm = document.querySelector('#editUserForm');
editForm.addEventListener('submit', function (e) {
    e.preventDefault();
    let ids = Array.from(document.getElementById("rolesPatch").options).filter(option => option.selected).map(option => option.value)
    patchUser(ids)
    editModal.hide();
    editForm.reset();
});

// Event listener for submitting Delete User form
let deleteForm = document.querySelector('#deleteUserForm');
deleteForm.addEventListener('submit', function (e) {
    e.preventDefault();
    deleteUser(document.querySelector("#idDelete").value)
    deleteModal.hide();
    deleteForm.reset();
});

// Modals to be able to hide them later
const editModal = new bootstrap.Modal(document.querySelector("#editModal"));
const deleteModal = new bootstrap.Modal(document.querySelector("#deleteModal"));