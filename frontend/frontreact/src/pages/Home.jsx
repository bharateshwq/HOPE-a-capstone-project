import React, { useEffect } from 'react'
import { CarouselWithContent } from '../components/CarouselWithContent'
import axios from '../api/axios';
import CampaignService from '../api/services/CampaignService';
import useAuth from '../hooks/useAuth';
import Cookies from "js-cookie";


const Home = () => {
  const { auth } = useAuth();
  useEffect(() => {
    
      
    if (auth.accessToken) {
      console.log("cookie creation started")
      // Extract the token without 'bearer=' prefix
      let tokenWithoutBearer = auth.accessToken.split("bearer=")[1];
      let jwt = tokenWithoutBearer.split(";")[0];
   
      console.log("auth",auth)
      console.log("jwt token",jwt)
      // Set the cookie with the given attributes
      Cookies.set('bearer', jwt);
  }
    CampaignService.getMainDash()

    
  }, []);
  return (
    <>
    <div className='flex justify-center items-center w-full h-screen'>
      <h1 className='text-4xl'>Home</h1>
      <button onClick={CampaignService.getSorted}>sorted list</button>
    </div>
    </>
  )
}

export default Home