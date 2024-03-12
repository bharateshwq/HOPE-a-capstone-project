import axios from "axios";


const register = (username, email, password) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
  });
};

const login = async (username, password) => {
    try {
        const response = await axios.post('/api/auth/signin', {
          username: username,
          password: password
        });
  
        // Handle success response
        console.log(response.data); // Log the response data
    
        // Assuming you need to handle authentication or store tokens here
      } catch (error) {
        console.log("hello")
        // Handle error
        return error('Error:', error.response.data); // Log the error response data
      }
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
