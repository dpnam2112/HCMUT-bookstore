import React from 'react'
import { Header } from '../components/header'
import { Footer } from '../components/footer'

function LoginForm() {
    const handleSubmit = (e) => {
	e.preventDefault();

        const formData = new FormData(e.target);

	const credential = {
	    username: formData.get("username"),
	    password: formData.get("password")
	}

	console.log(credential);

        fetch("http://localhost:8080/api/login", {
            method: "POST",
            mode: "cors",
            credentials: "include",
	    headers: {
		"Content-Type": "application/json"
	    },
	    body: JSON.stringify(credential)
        }).then(res => {
            if (res.ok) {
	      window.location.replace("http://localhost:3000/")
            } else {
		console.log(res);
                console.log("not logged in!");
            }
        });
    }

    return (
        <form onSubmit={handleSubmit} className="d-flex flex-column align-items-center">
            <div className="mb-2">
                <label htmlFor="username" className="form-label">Username</label>
                <input type="text" className="form-control" id="username" name="username"/>
            </div>
            <div className="mb-2">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" className="form-control" id="password" name="password"/>
            </div>
            <button type="submit" className="btn btn-primary">Login</button>
        </form>
    )
}

export function LoginPage() {
    return (
        <div className="vh-100">
            <Header/>
                <div className="h-75 d-flex flex-column align-items-center">
                    <h3 className="mb-2">Login</h3>
                    <LoginForm/>
                </div>
            <Footer/>
        </div>
    )
}
