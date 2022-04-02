


// fetch("http://localhost:8080/admin/users").then(res => {
//     res.json().then(data =>{
//         console.log(data);
//         if (data.length >0){
//             let temp = "";
//             data.forEach(u => {
//                 temp += "<tr>";
//                 temp += "<td>" + u.id + "</td>"
//                 temp += "<td>" + u.name + "</td>"
//                 temp += "<td>" + u.surname + "</td>"
//                 temp += "<td>" + u.age + "</td>"
//                 temp += "<td>" + u.email + "</td>"
//                 temp += "<td>" + u.roles.map(r => r.name === "ROLE_ADMIN" ? "ADMIN" : "USER") + "</td></tr>"
//             })
//             document.getElementById("data").innerHTML = temp
//         }
//     })
// })

const getUserTable = (users) => {
    temp ="",
            users.forEach(user => {
                console.log(user)
                temp += `
            <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.roles.map(r => r.name === "ROLE_ADMIN" ? "ADMIN" : "USER")}</td>
            </tr>`
            })
        document.getElementById("data").innerHTML = temp
}
let users = [];
const updateUser = (user) => {
    const foundIndex = users.findIndex(x => x.id == user.id);
    users[foundIndex] = user;
    getUserTable(users);
    console.log('users');
}
const removeUser = (id) => {
    users = users.filter(user => user.id != id);
    console.log(users)
    getUserTable(users);
}
fetch("http://localhost:8080/admin/users")
    .then(res => res.json())
    .then(data => {
        users = data
        getUserTable(data)
    });
