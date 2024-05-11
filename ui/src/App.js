import React from "react";
import { Header } from "./components/header";
import { Footer } from "./components/footer";

export default function App() {
  return (
    <>
      <Header/>
	<div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
	  <img className="bg-store" src="./bg_store.jpg" alt="Background" style={{ width: '70%', height: 'auto', maxWidth: '100%' }} />
	</div>
      <Footer/>
    </>
  )
}
