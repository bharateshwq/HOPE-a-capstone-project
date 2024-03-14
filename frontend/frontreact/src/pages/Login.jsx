import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate,useLocation } from 'react-router-dom';
import useAuth from '../hooks/useAuth';

import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
  Input,
  Checkbox,
  Button,
  Alert
} from "@material-tailwind/react";
import LoadingBar from 'react-top-loading-bar'

import  AuthService from '../api/services/AuthService';


const USERNAME_REGEX = /^[A-z][A-z0-9-_ ]{2,22}$/;
// const PASSWORD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const PASSWORD_REGEX = /.*/;

const Login = () => {
  const {setAuth} = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || "/";
  
 
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const [message,setMessage] = useState('');
 
  const [isUsernameValid, setUsernameValid] = useState(false);
  const [isPasswordValid, setPasswordValid] = useState(false);

  const refLoading = useRef();

  useEffect(() => {
    // const [loggedIn,setLoggedIn] = useState('')
    
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

  // const handleLoadSomething = () => {
  //   refLoading.current.continuousStart();
  //   console.log("bar started")
  //   setTimeout(() => {
  //     console.err("...loading something");
  //     refLoading.current.complete();
  //   }, 2000);
  // };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    refLoading.current.continuousStart() 
    AuthService.login(username, password ,setAuth,setMessage)
      .then((data) => { // Changed 'response' to 'data'
        setUsername("")
       setPassword("")
        refLoading.current.complete();
       
        navigate(from, { replace:true });
        console.log("message is set to",message);
        // if (data.roles.includes("ROLE_ADMIN")) { // Changed 'response' to 'data'
        //   navigate("/admin/dashboard");
        // } else if(data.roles.includes("ROLE_USER")) {
        //   navigate("/");
        // }
       
        
      
       
      })
      .catch((error) => {
       
        
      });
};



  const isFormValid = isUsernameValid && isPasswordValid ;

  return (
    <div className='flex items-center justify-center flex-col'>
    
        {message? 
        <Alert
        className= {`w-96 h-26 text-sm m-5 p-5 bg-red-600`}
        open={open}
        onClose={() => setMessage('')}
        animate={{
          mount: { y: 0 },
          unmount: { y: 100 },
        }}
      >
        {message}
      </Alert>
        //GREENCOLOR
        : <><LoadingBar color='#4caf50' ref={refLoading} /></>}
         
    <div className='flex justify-center items-center mt-24' style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)' }}>
   
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
      {/* <AuthVerify logOut={AuthService.logout} /> */}
      {/* <AuthService/> */}
    </div>
    </div>
  );
}

export default Login;
