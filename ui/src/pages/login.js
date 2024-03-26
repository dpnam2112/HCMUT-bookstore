import React, { useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css';
import { Header } from '../components/header';
import { Footer } from '../components/footer';

function LoginForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const submitCredentials = () => {
        
    }

    return (
        <form action="/" className="d-flex flex-column align-items-center">
            <div className="mb-2">
                <label for="username" className="form-label">Username</label>
                <input type="text" className="form-control" id="username" value={username}/>
            </div>
            <div className="mb-2">
                <label for="password" className="form-label">Password</label>
                <input type="password" className="form-control" id="password" value={password}/>
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