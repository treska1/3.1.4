
const url = 'http://localhost:8080/api/admin/users'
const userList = document.querySelector('#userTable');
let output = '';

const renderUsers = (users) => {
    users.forEach(user => {
        output += `
            <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.roles.map(r => r.name === "ROLE_ADMIN" ? "ADMIN" : "USER")}</td>
            <td> 
                <button type="button" class="btn btn-info"  id="edituser"  data-action="edit"  
                    data-toggle="modal" data-target="#modalEdit"  data-id="${user.id}">Edit</button> 
            </td> 
            <td> 
                <button type="button" class="btn btn-danger" id="deleteuser" data-action="delete" 
                    data-toggle="modal" data-target="modal" data-id="${user.id}">Delete</button> 
            </td> 
            </tr>`;
    });
    userList.innerHTML = output
}

// GET Method

fetch(url)
    .then(res => res.json())
    .then(data => renderUsers(data))

// POST Method

// Открытый вопрос
const userCreate = document.querySelector('#userCreate');
let name = document.getElementById("nameC")
let surname = document.getElementById("surnameC")
let age = document.getElementById("ageC")
let email = document.getElementById("emailC")
let password = document.getElementById("passwordC")
let roles = document.getElementById("rolesC")

userCreate.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name.value,
            surname: surname.value,
            age: age.value,
            email: email.value,
            password: password.value,
            roles: [{
                "id" : roles.value
            },
                {
                    "id" : roles.value
                }
            ]
        })
    })
        .then(res => res.json())
        .then(data => {
            users = data;
            renderUsers(users)
        })
})

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)){
            handler(e)
        }
    })
}

on(document, 'click', '#edituser', e => {
    const userInfo = e.target.parentNode.parentNode
    document.getElementById('idU').value = userInfo.children[0].innerHTML
    document.getElementById('nameU').value = userInfo.children[1].innerHTML
    document.getElementById('surnameU').value = userInfo.children[2].innerHTML
    document.getElementById('ageU').value = userInfo.children[3].innerHTML
    document.getElementById('emailU').value = userInfo.children[4].innerHTML
    document.getElementById('passwordU').value = userInfo.children[5].innerHTML

    $('#modalEdit').modal('show')
})

const editUser = document.querySelector('#modalEdit')
editUser.addEventListener('submit', e => {
    e.preventDefault();
    fetch(url, {
        method: 'PUT',
        headers: {
            'Context-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('idU').value,
            name: document.getElementById('nameU').value,
            surname: document.getElementById('surnameU').value,
            age: document.getElementById('ageU').value,
            email: document.getElementById('emailU').value,
            roles: [document.getElementById('idU').value]

        })
    })
        .then(res => res.json())
        .then(data => editUser(data))
        .catch((e) => console.error(e))
    $("#modalEdit").modal('hide')
})
let users = [];
const updateUser = (user) => {
    const foundIndex = users.findIndex(i => i.id == user.id);
    users[foundIndex] = user;
    renderUsers(users);
    console.log('users');
}