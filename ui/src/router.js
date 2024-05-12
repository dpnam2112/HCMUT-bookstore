import React from 'react';
import { LoginPage } from './pages/login';
import { SignUpPage } from './pages/signup';
import { ProductsPage } from './pages/products';
import { CheckoutPage  } from './pages/checkout';
import { createBrowserRouter } from 'react-router-dom';
import { BookDetail } from './pages/bookDetail.js'
import App from './App';

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>
  },
  {
    path: "/login",
    element: <LoginPage/>
  },
  {
    path: "/signup",
    element: <SignUpPage/>
  },
  {
    path: "/products",
    element: <ProductsPage/>
  },
  {
    path: "/checkout",
    element: <CheckoutPage/>
  },
  {
    path: "/bookDetail",
    element: <BookDetail/>
  }
]);

