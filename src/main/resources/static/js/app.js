
const url = 'http://localhost:8080/api/admin/users'
const userList = document.querySelector('#userTable');

// GET Method SHOW


const renderUsers = (users) => {
    let output = '';
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

// {mode: "cors"}
fetch(url)
    .then(res => res.json())
    .then(data =>
        renderUsers(data)
    )

// POST Method CREATE

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
// PUT Method UPDATE

on(document, 'click', '#edituser', e => {
    const userInfo = e.target.parentNode.parentNode

    document.getElementById('idU').value = userInfo.children[0].innerHTML
    document.getElementById('nameU').value = userInfo.children[1].innerHTML
    document.getElementById('surnameU').value = userInfo.children[2].innerHTML
    document.getElementById('ageU').value = userInfo.children[3].innerHTML
    document.getElementById('emailU').value = userInfo.children[4].innerHTML
    document.getElementById('passwordU').value = userInfo.children[5].innerHTML
    document.getElementById('rolesU').value = userInfo.children[5].innerHTML

    $('#modalEdit').modal('show')
})

const editUser = document.querySelector('#modalEdit')
editUser.addEventListener('submit', e => {
    e.preventDefault();
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('idU').value,
            name: document.getElementById('nameU').value,
            surname: document.getElementById('surnameU').value,
            age: document.getElementById('ageU').value,
            email: document.getElementById('emailU').value,
            // password: document.getElementById('passwordU').value,
            roles: [{
                "id" : document.getElementById('rolesU').value
            }]
        })
    })
        .then(res => res.json())
        .then(data => updateUser(data))
        .catch((e) => console.error(e))
    $("#modalEdit").modal('hide')
})

// PROBLEM WITH INDEX
let users = [];
const updateUser = (user) => {
    const foundIndex = users.findIndex(i => i.id === user.id);
    console.log(users.findIndex(i => i.id === user.id))
    users[foundIndex] = user;
    renderUsers(user);
    console.log(user);
}

// DELETE Method DELETE

let currentUser = null
on(document, 'click', '#deleteuser', e => {
    const userInfoDelete = e.target.parentNode.parentNode
    currentUser = userInfoDelete.children[0].innerHTML
    console.log(userInfoDelete.children[0].innerHTML)
    document.getElementById('idD').value = userInfoDelete.children[0].innerHTML
    document.getElementById('nameD').value = userInfoDelete.children[1].innerHTML
    document.getElementById('surnameD').value = userInfoDelete.children[2].innerHTML
    document.getElementById('ageD').value = userInfoDelete.children[3].innerHTML
    document.getElementById('emailD').value = userInfoDelete.children[4].innerHTML
    document.getElementById('rolesD').value = userInfoDelete.children[5].innerHTML


    $('#modalDelete').modal('show')
})

const removeUser = (id) => {
    users = users.filter(user => user.id != id);
    console.log(users)
    renderUsers(users);
}

const deleteUser = document.querySelector('#modalDelete')
deleteUser.addEventListener('submit', e => {
    e.preventDefault();
    e.stopPropagation()
    // e.stopPropagation()
    fetch(url + '/' + currentUser, {
        method: 'DELETE'
    })
        .then(res => res.json())
        .then(data => {
            removeUser(currentUser)
            console.log(currentUser)
            deleteUser.removeEventListener('submit', () =>{})
        })
    $("#modalDelete").modal('hide')
})

// NAVIGATION BAR

fetch('http://localhost:8080/api/user')
    .then(res => res.json())
    .then(data => {
        document.querySelector('#navbarDark').innerHTML = `
                    <span class="align-middle font-weight-bold mr-1" >${data.name}</span>
                    <span class="align-middle mr-1">with roles:</span>
                    <span> ${(data.roles).map(rn => rn.name === "ROLE_ADMIN" ? "ADMIN" : "USER")}</span>`
    })
