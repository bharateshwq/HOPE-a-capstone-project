import React, { useEffect } from 'react'
import { CarouselWithContent } from '../components/CarouselWithContent'
import axios from '../api/axios';
import CampaignService from '../api/services/CampaignService';


const Home = () => {
  useEffect(() => {
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