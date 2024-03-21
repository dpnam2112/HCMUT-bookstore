import "./index.css"
import React from "react";
import { Header } from "./components/header";
import { Footer } from "./components/footer";

export default function App() {
  return (
    <>
      <Header/>
        This is the main page.
      <Footer/>
    </>
  )
}