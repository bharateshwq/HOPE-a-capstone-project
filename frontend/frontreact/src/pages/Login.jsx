import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
  Input,
  Checkbox,
  Button,
} from "@material-tailwind/react";

import  AuthService from '../api/services/AuthService';
import AuthVerify from '../api/AuthVerify';

const USERNAME_REGEX = /^[A-z][A-z0-9-_ ]{2,22}$/;
// const PASSWORD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const PASSWORD_REGEX = /.*/;

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const navigate = useNavigate();
  const [message,setMessage] =useState('');
 
  const [isUsernameValid, setUsernameValid] = useState(false);
  const [isPasswordValid, setPasswordValid] = useState(false);

  useEffect(() => {
    // const [loggedIn,setLoggedIn] = useState('')
    console.log(loginVar)
    setUsernameValid(USERNAME_REGEX.test(username));
  }, [username]);

  useEffect(() => {
    setPasswordValid(PASSWORD_REGEX.test(password));
  }, [password]);

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setMessage("");
    
    AuthService.login(username, password)
      .then((data) => { // Changed 'response' to 'data'
     
        if (data.roles.includes("ROLE_ADMIN")) { // Changed 'response' to 'data'
          navigate("/admin/dashboard");
        } else {
          navigate("/");
        }
        
      })
      .catch((error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        setMessage(resMessage);
      });
};



  const isFormValid = isUsernameValid && isPasswordValid ;

  return (
    <div className='flex justify-center items-center mt-24'>
      <Card className="w-96">
        <CardHeader
          variant="gradient"
          color="gray"
          className="mb-4 grid h-28 place-items-center"
        >
          <Typography variant="h3" color="white">
            Login
          </Typography>
        </CardHeader>
        <CardBody className="flex flex-col gap-4">
          <Input type="text" label="User Name" size="lg" value={username} onChange={handleUsernameChange} />
          <Input type="password" label="Password" size="lg" value={password} onChange={handlePasswordChange} />
          
        </CardBody>
        <CardFooter className="pt-0">
          <Button variant="gradient" fullWidth disabled={!isFormValid} onClick={handleSubmit}>
            Login
          </Button>
          <Typography variant="small" className="mt-6 flex justify-center">
            Don&apos;t have an account?
            <Link to='/register'>
              <Typography
                as="a"
                href="#signup"
                variant="small"
                color="blue-gray"
                className="ml-1 font-bold"
              >
                Sign up
              </Typography>
            </Link>
          </Typography>
        </CardFooter>
      </Card>
      <AuthVerify logOut={AuthService.logout} />
    </div>
  );
}

export default Login;
