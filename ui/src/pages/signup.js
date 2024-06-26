import React from 'react'
import { Header } from '../components/header'
import { Footer } from '../components/footer'

function SignUp() {

    return (
        <form action="/" className="d-flex flex-column align-items-center">
            <input type="text" className="form-control mb-2" id="username" placeholder="Username"/>
            <input type="text" className="form-control mb-2" id="username" placeholder="Password"/>
            <div className="row mb-2">
                <div className="col">
                    <input type="text" id="firstName" className="form-control" placeholder="Firstname" aria-label="Firstname" required />
                </div>
                <div className="col">
                    <input type="text" id="lastName" className="form-control" placeholder="Lastname" aria-label="Lastname" required />
                </div>
            </div>
            <input type="tel" name="phone" id="phoneNumber" placeholder="Phone number" className="form-control mb-2" required/>
            <button type="submit" className="btn btn-primary">Sign up</button>
        </form>
    )
}

export function SignUpPage() {
    return (
        <div className="vh-100">
            <Header/>
                <div className="h-75 d-flex flex-column align-items-center">
                    <h3 className="mb-2">Signup</h3>
                    <SignUp/>
                </div>
            <Footer/>
        </div>
    )
}
