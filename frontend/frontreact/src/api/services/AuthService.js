import axios from "../axios";

const API_URL = "http://localhost:3000/api/auth/";

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

const login = (username, password) => {
    return axios.post('/api/auth/signin', {
        username: username,
        password: password
      })
      .then((response) => {
        if (response.data.username) {
          localStorage.setItem("user", JSON.stringify(response.data));
          localStorage.setItem("role", response.data.roles[0]);
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