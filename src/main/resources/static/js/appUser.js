// USER PAGE

fetch('http://localhost:8080/api/user')
    .then(res => res.json())
    .then(user => {
            let output =` <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.roles.map(r => r.name === "ROLE_ADMIN" ? "ADMIN" : "USER")}</td>
            </tr>`
    document.querySelector('#userPage').innerHTML = output
    })


// NAVIGATION BAR

fetch('http://localhost:8080/api/user')
    .then(res => res.json())
    .then(logUser => {
        document.querySelector('#navbarDark').innerHTML = `
                    <span class="align-middle font-weight-bold mr-1" >${logUser.name}</span>
                    <span class="align-middle mr-1">with roles:</span>
                    <span> ${(logUser.roles).map(rn => rn.name === "ROLE_ADMIN" ? "ADMIN" : "USER")}</span>`
    })
