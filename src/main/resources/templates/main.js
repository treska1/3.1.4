fetch("http://localhost:8080/admin/users/users").then(

    res=>{
        res.json().then(
            allusers=>{
                console.log(allusers)
                // if (allusers.length>0){
                //     let user = ""
                //
                //     allusers.forEach((u)=>{
                //         user += "<tr>"
                //         user += "<td>" + u.name + "</td>"
                //         user += "<td>" + u.surname + "</td>"
                //         user += "<td>" + u.name + "</td>"
                //         user += "<td>" + u.name + "</td>"
                //         user += "<td>" + u.name + "</td>"
                //
                //     })
                // }
            }
        )
    }
)
