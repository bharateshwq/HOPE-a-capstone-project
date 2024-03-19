import { useNavigate } from "react";
import axios from "../axios";
import { jwtDecode } from "jwt-decode";
import Cookies from "js-cookie";
import useAuth from "../../hooks/useAuth";


const register = async (user, email, pwd,setMessage) => {
  try {
    
    const response = await axios.post('/api/auth/signup', {
      username: user,
      email: email,
      password: pwd,
      roles: ['user'] // Assuming the default role is 'user'
    });
   
  
    console.log(response.message);
    return response.data; // Return the data if the request is successful
  } catch (error) {
    // Handle error here
    
    console.log(error.response.status)
    if (!error?.response) {
      setMessage("Server not Responding, wait until we get it working");

      console.log("JUST STOP CODING");
    } else if (error.code == 'ERR_NETWORK') {
      setMessage("Server not Reachable, wait until we get it working")
      // If the error has a response property but the status is null, indicating a CORS error
      console.log("RUN THE BACKEND YOU IDIOT");
    }
    else if(error.response.status == 400){
      setMessage(error.response.data.message)
      // console.log(`ERROR:${error.response.data.message}`)
      
    }
    else {
      setMessage(error.response.status)
    }
    setTimeout(() => {
      setMessage('')
    }, 5000);
   
    
   
    throw error; // Rethrow the error to be handled by the caller
  }
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


 const login = async (username, password,setAuth,setMessage) => {
  try {
    
    const response = await axios.post('/api/auth/signin', {
      username: username,
      password: password
    });
    
    console.log(response);
  
    if (response.data.username) {
      console.log(response.data.jwttoken);
      // decodeToken(response.data.jwttoken)
      const accessToken = response?.data?.jwttoken;
      const roles = response?.data?.roles;
      localStorage.setItem("user", JSON.stringify(response.data));
      localStorage.setItem("role", roles);
      setAuth({ username, password, roles, accessToken });
      const { auth } = useAuth();
      // console.log("hello")
      if (auth.accessToken) {
        // Set the access token in a cookie named 'accessToken'
        Cookies.set('accessToken', auth.accessToken, { expires: 1, secure: true, sameSite: 'none' });
      }
    }
  
    return response.data;
  } catch (error) {
    // Handle error here
    if (!error?.response) {
      setMessage("Server not Responding, wait until we get it working");

      console.log("JUST STOP CODING");
    } else if (error.code == 'ERR_NETWORK') {
      setMessage("Server not Reachable, wait until we get it working")
      // If the error has a response property but the status is null, indicating a CORS error
      console.log("RUN THE BACKEND YOU IDIOT");
    }
    else if(error.response?.status === 400){
      setMessage('Missing Username or Password')
      console.log("states are messed up")
      
    }
    else if(error.response?.status ==401){
      setMessage('Unauthorized Access, Your entered Credentials are Wrong')
      console.log("wrong creds")
    }
    else{
      setMessage("Error: LOGIN FAILED" )
    }
    setTimeout(() => {
      setMessage('')
    }, 5000);

    // console.log(JSON.stringify(error))
    // console.error('Error:', "WE ARE DOOMED");
    // throw error; // Rethrow the error to be handled by the caller
  }
  
};

 const logout = async (setAuth,navigate) => {
  
  console.log("deleted")
   localStorage.removeItem("user");
   localStorage.removeItem("role");
   Cookies.remove('accessToken');
   console.log("hello world  ")
   setAuth({})
   navigate("/");
  return "done"
  
 
  // return axios.post("/signout").then((response) => {
  //   return response.data;
  // });
  
};

 const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};



const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
  decodeToken,
  
}

export default AuthService;