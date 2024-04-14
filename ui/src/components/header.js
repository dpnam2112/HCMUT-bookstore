import React from "react"
import { useState } from "react"

export function Header() {
  const [keyword, setKeyword] = useState("");

  const handleKeywordChange = (e) => {
    setKeyword(e.target.value);
  }

  return (
    <header className="container d-flex justify-content-between py-2">
      <div className="d-flex py-1 justify-content-between">
	<img alt="hcmut-logo" className="mx-2" src="/hcmut-logo.png" width="35" height="35"/>
	<h4 className="py-1">HCMUT Bookstore</h4>
      </div>
      <form className="vw-50 py-2 col-7">
	<div className="input-group input-group-sm">
	  <button className="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Categories</button>
	  <ul className="dropdown-menu">
	    <li><a className="dropdown-item" href="/">Action</a></li>
	  </ul>
	  <input type="text" className="form-control input-sm" value={keyword} onChange={handleKeywordChange}/>
	  <a href={`/products?per_page=12&page=1&name=${keyword}`} className="btn btn-primary">Search</a>
	</div>
      </form>
      <div className="d-flex justify-content-end py-2 col-1 h-100">
	<div role="button" className="p-0">
	  <img className="m-0" src="/shopping-cart.svg" height="35" width="35" alt="shopping cart"/>
	</div>
	<div className="p-0 dropdown">
	  <img src="/account.svg" data-bs-toggle="dropdown" role="button" className="dropdown-toggle" height="35" width="35" alt="account"/>
	  <ul className="px-2 py-1 dropdown-menu dropdown-menu-end">
	    <li className="mb-2"><a href="/login" className="btn btn-primary w-100">Login</a></li>
	    <li className="mb-2"><a href="/signup " className="btn btn-secondary w-100">Sign up</a></li>
	  </ul>
	</div>
      </div>
    </header>
    )
}
