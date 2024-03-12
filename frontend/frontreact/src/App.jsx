import { useState } from 'react'
import React from 'react';

import {Routes, Route } from 'react-router-dom'
import { Home, About, Contact,Login,Register, MyProfile, MyActivity, Help } from './pages/indexRoute';

import { ComplexNavbar } from './components/ComplexNavbar';
import AdminDash from './pages/AdminDash';

function App() {
  

  return (
 <div className='bg-green-100 w-full h-screen'>
  {/* <NavBar /> */}
  <ComplexNavbar/>
  <Routes>
    <Route path='/' element={<Home/>}></Route>
    <Route path='/login' element={<Login/>}></Route>
    <Route path='/register' element={<Register/>}></Route>
    <Route path='/contact' element={<Contact/>}></Route>
    <Route path='/about' element={<About/>}></Route>
    <Route path='/myProfile' element={<MyProfile/>}></Route>
    <Route path='/myActivity' element={<MyActivity/>}></Route>
    <Route path='/help' element={<Help />}> </Route>
    <Route path='/signout' element={<Login />}> </Route>
    <Route path='/admin/dashboard' element={<AdminDash/>}></Route>


    


  </Routes>
  </div>
  )
}
export default App
