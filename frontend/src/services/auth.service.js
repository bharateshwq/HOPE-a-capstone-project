import axios from "axios";

const API_URL = "http://localhost:2710/api/auth/";

const register = (username, email, password) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
  });
};

const login = (username, password) => {
  return fetch(API_URL + "signin", {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username,
      password
    })
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then((data) => {
    if (data.username) {
      localStorage.setItem("user", JSON.stringify(data));
    }
    console.log(data);
    return data;
  })
  .catch((error) => {
    console.error('There was a problem with the fetch operation:', error);
    throw error; // You might want to handle the error differently
  });
};


const logout = () => {
  localStorage.removeItem("user");
  return axios.post(API_URL + "signout").then((response) => {
    return response.data;
  });
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
}

export default AuthService;
