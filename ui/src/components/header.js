import React from "react"
import { useState, useContext } from "react"
import { UserContext } from "../context"
import { Link, useNavigate } from 'react-router-dom';
import '../index.css'

export function UserNavigation() {
  const userInfo = useContext(UserContext);

  if (userInfo != null) {
    return (
      <>
	<ul className="px-2 py-1 dropdown-menu dropdown-menu-end">
	  <li>Xin chào, {userInfo.username}!</li>
	</ul>
      </>
    )
  } else {
      return (
	<ul className="px-2 py-1 dropdown-menu dropdown-menu-end">
	  <li className="mb-2"><a href="/login" className="btn btn-primary w-100">Login</a></li>
	  <li className="mb-2"><a href="/signup" className="btn btn-secondary w-100">Sign up</a></li>
	</ul>
      )
  }
}

export function Header() {
  const [keyword, setKeyword] = useState("");

  const handleKeywordChange = (e) => {
    setKeyword(e.target.value);
  }

  return (
    <div className='component-sidebar'>
              <nav className="navbar navbar-expand-lg container d-flex justify-content-between py-2 bg-header">
                      <a href="/">
                        <div className="d-flex py-1 justify-content-between" style={{ padding: '10px', cursor: 'pointer'}}>
                           <img alt="hcmut-logo" className="mx-2" src="/hcmut-logo.png" width="35" height="35" />
                           <h4 className="py-1">HCMUT Bookstore</h4>
                        </div>
                      </a>

                <div className="container sidebar-item">
                  <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                          <Link className="nav-link sidebar-item-child" to="/">Trang chủ</Link>
                        </li>
                      <li className="nav-item">
                        <Link className="nav-link sidebar-item-child" to="/products">Sản phẩm</Link>
                      </li>
                      <li className="nav-item">
                        <Link className="nav-link sidebar-item-child" to="/addBook">Thêm sách</Link>
                      </li>
                      <li className="nav-item">
                        <Link className="nav-link sidebar-item-child" to="/modifyInfo">Cập nhật</Link>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="d-flex justify-content-end py-2 col-1 h-100">
    	<div role="button" className="p-0">
    	  <a href="/cart"><img className="m-0" src="/shopping-cart.svg" height="35" width="35" alt="shopping cart"/></a>
    	</div>
    	<div className="p-0 dropdown">
    	  <img src="/account.svg" data-bs-toggle="dropdown" role="button" className="dropdown-toggle" height="35" width="35" alt="account"/>
    	    <UserNavigation/>
    	</div>
          </div>
              </nav>
            </div>
    )
}
