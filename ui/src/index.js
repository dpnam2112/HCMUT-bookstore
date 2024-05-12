import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap/dist/css/bootstrap.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import React, { useState, useEffect, createContext } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { RouterProvider } from 'react-router-dom';
import { router } from './router';
import { UserContext } from './context';
import App from "./App.js";
import { LoginPage } from './pages/login';
import { SignUpPage } from './pages/signup';


const root = ReactDOM.createRoot(document.getElementById('root'));

function AuthContextWrapper() {
  /* Retrieve user's information, if the user has logged in before. */
  const [user, setUser] = useState({});

  useEffect(() => {
    fetch("http://localhost:8080/api/get-user", {
      credentials: 'include',
      mode: "cors"
    }).then((response) => {
	if (response.status == 200) {
	  return response.json();
	} else {
	  console.log("Failed to fetch user data.");
	}
      })
      .then((user) => {
	console.log(user);
	setUser(user);
      });
  }, []);

  return (
    <UserContext.Provider value={user}>
      <RouterProvider router={router}/>
    </UserContext.Provider>
  );
};

root.render(
  <React.StrictMode>
    <AuthContextWrapper/>
  </React.StrictMode>
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
