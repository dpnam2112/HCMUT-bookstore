import React, { createContext } from 'react';
import { useState } from 'react';
import axios from 'axios';

export const UserContext = createContext({});

export function UserContextProvider({ children }) {
  const [user, setUser] = useState({});

  axios({
    method: 'get',
    url: 'http://localhost:8080/api/person-demo/'
  })
  .then(response => setUser(response.data));

  return (
    <UserContext.Provider value={{user, setUser}}>
      {children}
    </UserContext.Provider>
  )
}