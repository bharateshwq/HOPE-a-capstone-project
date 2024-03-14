import React from 'react'
import { CarouselWithContent } from '../components/CarouselWithContent'

const Home = () => {
  return (
    <>
    <CarouselWithContent/>
    <div className='flex justify-center items-center w-full h-screen'>
      <h1 className='text-4xl'>Home</h1>
    </div>
    </>
  )
}

export default Home