import "./index.css"
import React, { useContext } from "react";
import { Header } from "./components/header";
import { Footer } from "./components/footer";
import { UserContext } from "./appContext/contextProviders";

export default function App() {
  const { user, setUser } = useContext(UserContext);

  return (
    <>
      <Header/>
        <div>{user.firstName}</div>
        <div>{user.lastName}</div>
        <div>{user.id}</div>
        <div>{user.age}</div>
      <Footer/>
    </>
  )
}
