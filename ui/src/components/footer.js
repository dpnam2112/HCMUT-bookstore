import React from "react";

export function Footer() {
    return (
    <footer className="container d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <div className="col-md-4 d-flex align-items-center">
        <a href="/" className="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
            <img alt="hcmut-logo" className="bi" width="24" height="24" src="hcmut-logo.png"/>
        </a>
        <span className="text-muted">© HCMUT bookstore .inc</span>
        </div>

        <ul className="nav col-md-4 justify-content-end list-unstyled d-flex">
        <li className="ms-3">
            <a className="text-muted" href="/">
                <img  alt="fb-icon" src="./facebook.webp" height="24" width="24"/>
            </a>
        </li>
        <li className="ms-3">
            <a className="text-muted" href="/">
                <img alt="x-icon" src="./X.png" height="24" width="24"/>
            </a>
        </li>
        <li className="ms-3">
            <a className="text-muted" href="/">
                <img alt="instagram-icon" src="./instagram.png" height="24" width="24"/>
            </a>
        </li>
        </ul>
  </footer>
    )
}
