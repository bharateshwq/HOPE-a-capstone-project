import { useState } from 'react'
import React from 'react';

import {Routes, Route } from 'react-router-dom'
import { Home, About, Contact,Login,Register, MyProfile, MyActivity, Help } from './pages/indexRoute';
import NavBar from './components/NavBar'
import { ComplexNavbar } from './components/ComplexNavbar';

function App() {
  const [count, setCount] = useState(0)

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


  </Routes>
  </div>
  )
}
export default App
