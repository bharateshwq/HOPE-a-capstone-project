import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { RxHamburgerMenu } from "react-icons/rx";
import { IoMdClose } from "react-icons/io";
import { ComplexNavbar } from './ComplexNavbar';

const NavBar = () => {
  let [nav, setNav] = useState(true);
  const handleClick = () => setNav(!nav)

  return (
    <>
   <nav className='flex justify-between p-5 items-center border-b'>
    <Link to='/'><h1 className='font-serif text-4xl'>HOPE</h1></Link>
    <ul className='hidden md:flex gap-6'>
      <Link to='/contact'><li>Contact Us</li></Link>
      <Link to='/about'><li>About Us</li></Link>
      <Link to='/login'><li>Login</li></Link>
      <Link to='/register'><li>SignUp</li></Link>
   
    </ul>
    {/* mobile view */}
    <div className='md:hidden z-10' onClick={handleClick}>
      {nav ? <IoMdClose size={35}/> :  <RxHamburgerMenu size={35}/> }
    </div>
    <ul className={`${
      nav ? 'text-white opacity-100 transform translate-x-0':
      'opacity-0 tansform translate-y-full'} transition-transform 
      absolute top-0 left-0 w-full h-screen bg-zinc-800/80 flex
       flex-col justify-center items-center text-2xl` } onClick= {handleClick} >
    <Link to='/contact'><li className='p-5 hover:text-teal-300'>Contact Us</li></Link>
      <Link to='/about'><li className='p-5 hover:text-teal-300'>About Us</li></Link>
      <Link to='/login'><li className='p-5 hover:text-teal-300'>Login</li></Link>
      <Link to='/register'><li className='p-5 hover:text-teal-300'>SignUp</li></Link>
    </ul>
   </nav>
   
   </>
  )
}

export default NavBar