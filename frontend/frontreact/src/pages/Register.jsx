import React, { useState } from 'react';
import { useEffect, useRef } from 'react';
import { Link ,useNavigate} from 'react-router-dom';
import { Alert, Typography } from "@material-tailwind/react";
import LoadingBar from 'react-top-loading-bar'
import AuthService from '../api/services/AuthService';

const USER_REGEX = /^[A-z][A-z0-9-_ ]{2,22}$/;
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const PWD_REGEX = /.*/;




const Register = () => {
  const userRef = useRef();
  const emailRef = useRef();
  const pwdRef = useRef();

  const [validName, setValidName] = useState(false);
  const [validEmail, setValidEmail] = useState(false);
  const [validPwd, setValidPwd] = useState(false);

  const [userFocus, setUserFocus] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [user, setUser] = useState('');
  const [email, setEmail] = useState('');
  const [pwd, setPwd] = useState('');

  const [agreeTerms, setAgreeTerms] = useState(false);
  // const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState('');
  const refLoading = useRef();
  const [success,setSuccess] = useState(false);

  const navigate = useNavigate();


  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    const result = USER_REGEX.test(user);
    setValidName(result);
  }, [user]);

  useEffect(() => {
    const result = PWD_REGEX.test(pwd);
    setValidPwd(result);
  }, [pwd]);

  useEffect(() => {
    setValidEmail(validateEmail(email));
  }, [email]);

  const validateEmail = (value) => {
    // Basic email validation
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
  };

  const handleSubmit =  (e) => {
    e.preventDefault();
    
    setMessage('')
    refLoading.current.continuousStart() 
   
    AuthService.register(user, email, pwd,setMessage)
      .then((data) => {
        setMessage('')
        // setSuccess(true)
        // Redirect to login page after successful registration
        refLoading.current.complete();
      
        navigate("/login");
      })
      .catch((error) => {
      // setSuccess(false)
      console.log(message)
        // const resMessage =
        //   (error.response &&
        //     error.response.data &&
        //     error.response.data.message) ||
        //   error.message ||
        //   error.toString();
        // setMessage(resMessage);
      });
  };
  return (
<div className='flex items-center justify-center flex-col'>

        {message? 
          <Alert
          className= {`w-96 h-26 text-sm m-5 p-5 bg-red-600 z-50`}
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
          : <><LoadingBar color='#4caf50' ref={refLoading}  /></>}


    <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)' }} >

    <div className="flex justify-center items-center h-screen mt-12"  >
      
    
     
      <div className="bg-white p-8 rounded-xl shadow-lg max-w-md "  >
        <div className="relative flex flex-col text-gray-700 bg-transparent shadow-none rounded-xl bg-clip-border  "  >
          <h4 className="block font-sans text-2xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900">
            Sign Up
          </h4>
          <p className="block mt-1 font-sans text-base antialiased font-normal leading-relaxed text-gray-700">
            Nice to meet you! Enter your details to register.
          </p>
          <form className="max-w-screen-lg mt-8 mb-2 w-80 sm:w-96" onSubmit={handleSubmit}>
            <div className="flex flex-col gap-6 mb-1">
              <h6 className="block -mb-3 font-sans text-base antialiased font-semibold leading-relaxed tracking-normal text-blue-gray-900">
                Your Name
              </h6>
              <div className="relative h-11 w-full min-w-[200px]">
                <input
                  placeholder="Your Name"
                  type="text"
                  ref={userRef}
                  value={user}
                  onChange={(e) => setUser(e.target.value)}
                  autoComplete="off"
                  required
                  onFocus={() => setUserFocus(true)}
                  onBlur={() => setUserFocus(false)}
                  className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent !border-t-blue-gray-200 bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-gray-900 focus:border-t-transparent focus:!border-t-gray-900 focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                />
                
              </div>
              <h6 className="block -mb-3 font-sans text-base antialiased font-semibold leading-relaxed tracking-normal text-blue-gray-900">
                Your Email
              </h6>
              <div className="relative h-11 w-full min-w-[200px]">
                <input
                  placeholder="name@mail.com"
                  type="email"
                  ref={emailRef}
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  autoComplete="off"
                  required
                  onFocus={() => setEmailFocus(true)}
                  onBlur={() => setEmailFocus(false)}
                  className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent !border-t-blue-gray-200 bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-gray-900 focus:border-t-transparent focus:!border-t-gray-900 focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                />
               
              </div>
              <h6 className="block -mb-3 font-sans text-base antialiased font-semibold leading-relaxed tracking-normal text-blue-gray-900">
                Password
              </h6>
              <div className="relative h-11 w-full min-w-[200px]">
                <input
                  type="password"
                  placeholder="********"
                  ref={pwdRef}
                  value={pwd}
                  onChange={(e) => setPwd(e.target.value)}
                  autoComplete="off"
                  required
                  onFocus={() => setPwdFocus(true)}
                  onBlur={() => setPwdFocus(false)}
                  className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent !border-t-blue-gray-200 bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-gray-900 focus:border-t-transparent focus:!border-t-gray-900 focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                />
               
              </div>
            </div>
            <div className="inline-flex items-center">
              <label className="relative -ml-2.5 flex cursor-pointer items-center rounded-full p-3" htmlFor="remember">
              <input
  type="checkbox"
  checked={agreeTerms}
  onChange={(e) => setAgreeTerms(e.target.checked)}
  className="before:content[''] peer relative h-5 w-5 cursor-pointer appearance-none rounded-md border border-blue-gray-200 transition-all before:absolute before:top-2/4 before:left-2/4 before:block before:h-12 before:w-12 before:-translate-y-2/4 before:-translate-x-2/4 before:rounded-full before:bg-blue-gray-500 before:opacity-0 before:transition-opacity checked:border-gray-900 checked:bg-gray-900 checked:before:bg-gray-900 hover:before:opacity-10"
  id="remember"
/>
                <span className="absolute text-white transition-opacity opacity-0 pointer-events-none top-2/4 left-2/4 -translate-y-2/4 -translate-x-2/4 peer-checked:opacity-100">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-3.5 w-3.5" viewBox="0 0 20 20" fill="currentColor" stroke="currentColor" strokeWidth="1">
                    <path fillRule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clipRule="evenodd"></path>
                  </svg>
                </span>
              </label>
              <label className="mt-px font-light text-gray-700 cursor-pointer select-none" htmlFor="remember">
                <p className="flex items-center font-sans text-sm antialiased font-normal leading-normal text-gray-700">
                  I agree the
                  <a href="#" className="font-medium transition-colors hover:text-gray-900">
                    &nbsp;Terms and Conditions
                  </a>
                </p>
              </label>
            </div>
            <button
              className="mt-6 block w-full select-none rounded-lg bg-gray-900 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-900/10 transition-all hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
              type="submit"
              disabled={!validName || !validEmail || !validPwd || !agreeTerms}
             onClick={handleSubmit}
            >
              Sign Up
            </button>
            <p className="block mt-4 font-sans text-base antialiased font-normal leading-relaxed text-center text-gray-700">
              Already have an account?
              <Link to='/login'>
                <a href="#" className="font-medium text-gray-900">
                  Sign In
                </a>
              </Link>
            </p>
          </form>
          {pwdFocus && pwd && !validPwd && <AlertPwd />}
          {emailFocus && email && !validEmail && <AlertEmail />}
          {userFocus && user && !validName && <AlertName />}

        </div>
      </div>
    </div>
    </div>
    </div>
  );
};

function AlertName() {
  return (
    <div className="flex w-full flex-col gap-2 mt-4">
      <Alert variant="gradient" icon={<IconOutlined />}>
        <Typography className="font-medium">
          Ensure that these requirements are met:
        </Typography>
        <ul className="mt-2 ml-2 list-inside list-disc">
          <li>Start with a letter</li>
          <li>Can include letters, numbers, hyphens, and underscores</li>
          <li>Must be between 3 and 23 characters in length</li>
        </ul>
      </Alert>
    </div>
  );
}

function AlertEmail() {
  return (
    <div className="flex w-full flex-col gap-2 mt-4">
      <Alert variant="gradient" icon={<IconOutlined />}>
        <Typography className="font-medium">
          Please enter a valid email address.
        </Typography>
      </Alert>
    </div>
  );
}

function AlertPwd() {
  return (
    <div className="flex w-full flex-col gap-2 mt-4">
      <Alert variant="gradient" icon={<IconOutlined />}>
        <Typography className="font-medium">
          Ensure that these requirements are met:
        </Typography>
        <ul className="mt-2 ml-2 list-inside list-disc">
          <li>At least one lowercase character</li>
          <li>At least one uppercase character</li>
          <li>At least one digit</li>
          <li>At least one special character (!@#$%)</li>
          <li>Must be between 8 and 24 characters in length</li>
        </ul>
      </Alert>
    </div>
  );
}

function IconOutlined() {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={2}
      stroke="currentColor"
      className="h-6 w-6"
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M11.25 11.25l.041-.02a.75.75 0 011.063.852l-.708 2.836a.75.75 0 001.063.853l.041-.021M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-9-3.75h.008v.008H12V8.25z"
      />
    </svg>
  );
}

export default Register;
