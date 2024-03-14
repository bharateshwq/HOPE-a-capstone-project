import { useState } from "react";
import axios from "../axios";
import { jwtDecode } from "jwt-decode";

const [loggedIn,setLoggedIn] =useState();
const register = (user, email, pwd) => {
  return axios.post('/api/auth/signup', {
    username: user,
    email: email,
    password: pwd,
    roles: ['user'] // Assuming the default role is 'user'
  }).catch((error) => {
    // Handle error here
    console.error('Error:', error);
    throw error; // Rethrow the error to be handled by the caller
  });
};

const decodeToken = (token) => {
  try {
    const decodedToken = jwtDecode(token);
    console.log(decodedToken)
    return decodedToken;
  } catch (error) {
    console.error('Error decoding token:', error);
    return null;
  }
}


 const login = (username, password) => {
    return axios.post('/api/auth/signin', {
        username: username,
        password: password
      })
      .then((response) => {
   
        if (response.data.username) {
          // console.log(response)
          decodeToken(response.data.jwttoken)
          localStorage.setItem("user", JSON.stringify(response.data));
          localStorage.setItem("role", response.data.roles);
          console.log("hello")
          setCurrentRole(localStorage.getItem("roles")[0])
          console.log(loggedIn)
        }
        return response.data;
      })
      .catch((error) => {
        // Handle error here
        console.error('Error:', error);
        throw error; // Rethrow the error to be handled by the caller
      });
      
};

 const logout = () => {
  console.log("deleted")
  localStorage.removeItem("user");
  localStorage.removeItem("role");
 
  // return axios.post("/signout").then((response) => {
  //   return response.data;
  // });
  
};

 const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const getCurrentRole = () => {
  return loggedIn;
}
const setCurrentRole = (role) => {
  setLoggedIn(role)
}

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
  decodeToken,
  getCurrentRole,
  setCurrentRole,
  loggedIn
}

export default AuthService;