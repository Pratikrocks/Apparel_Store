export const isAuthenticated = () =>{
    // console.log("jwt")
    if(typeof window==="undefined")
    {
        return false
    }
    if(localStorage.getItem("jwt"))
    {
        // console.log("jwt")
        const token = localStorage.getItem("jwt");
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
    
        // return JSON.parse(localStorage.getItem("jwt"))
    }
    else return false
}

export const signout = (next) =>{
    if(typeof window!="undefined")
    {
        localStorage.removeItem("jwt")
    }
};

export const signUp = (user) =>{
    return fetch(`https://localhost:8443/users/signup`,{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
    .then(response=>{
        console.log(response.body)
        return response.text();
    })
    .then(data => {
        console.log(typeof data)
        localStorage.setItem("jwt", JSON.stringify(data))
        return "success"
    })
    .catch(err=>console.log(err))
};

export const signIn = user =>{
    console.log(user)
    return fetch(`https://localhost:8443/users/signin?username=${user.username}&password=${user.password}`,{
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        },
        
    })
    .then(response=>{
        
        return response.text();
    })
    .then(data => {
        console.log(typeof data)
        localStorage.setItem("jwt", JSON.stringify(data))
        return "success"
    })
    .catch(err=>console.log(err))
};


export const addProduct = product =>{
    console.log(product)
    return fetch(`https://localhost:8443/products/addproduct`,{
        method: "PUT",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTYxODg0NzM4NywiZXhwIjoxNjE4ODQ3Njg3fQ.Jh74tj0QrSYd0qnyy2yD5qc-2ZsVVq9H8YnCX-r5Gcs",
            'Content-Type': `multipart/form-data;`
        },
        body : product
    })
    .then(response=>{
        
        return response.text();
    })
    .then(data => {
        console.log("DONE")
        return "success"
    })
    .catch(err=>console.log(err))
};