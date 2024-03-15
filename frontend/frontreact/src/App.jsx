import { useState } from 'react'
import React from 'react';

import { Home, About, CampaignForm,Login,Register, MyProfile, MyActivity, Help ,Missing,UnAuthorized} from './pages/indexRoute';
import RequireAuth from './components/RequireAuth';
import {Routes, Route } from 'react-router-dom'


import { ComplexNavbar } from './components/ComplexNavbar';
import AdminDash from './pages/AdminDash';
import Layout from './components/Layout';

function App() {
  

  return (

<div className='bg-green-100 w-full h-sccreen'>

  <ComplexNavbar/>
<Routes>

  <Route path='/' element={<Layout/>}>
    {/* public */}
  <Route path='/' element={<Home/>}></Route>
    <Route path='/login' element={<Login/>}></Route>
    <Route path='/register' element={<Register/>}></Route>
   
    <Route path='/about' element={<About/>}></Route>
    <Route path='/help' element={<Help />}> </Route>
    {/* <Route path='/signout' element={<Login />}> </Route>   */}

{/* user:  user acces paths */}
<Route element={<RequireAuth allowedRoles={['ROLE_USER']}/>}>
    <Route path='/myProfile' element={<MyProfile/>}></Route>
    <Route path='/myActivity' element={<MyActivity/>}></Route>
    <Route path='/CampaignForm' element={<CampaignForm/>}></Route>

    
    </Route>

    {/* admin :  admin access path*/}
    <Route element={<RequireAuth allowedRoles={['ROLE_ADMIN']}/>}>
    <Route path='/admin/dashboard' element={<AdminDash/>}></Route>


    </Route>

    {/* missing*/}
     <Route path='*' element={<Missing/>}></Route>
     <Route path="/unauthorized" element={<UnAuthorized/>}></Route>

</Route>
</Routes>
</div>
  );
}



//     <main className='App'>
//  <div className='bg-green-100 w-full h-screen'>
//   {/* <NavBar /> */}
//   <ComplexNavbar/>
//   <Routes>
//     <Route path='/' element={<Home/>}></Route>
//     <Route path='/login' element={<Login/>}></Route>
//     <Route path='/register' element={<Register/>}></Route>
//     <Route path='/contact' element={<Contact/>}></Route>
//     <Route path='/about' element={<About/>}></Route>
//     <Route path='/myProfile' element={<MyProfile/>}></Route>
//     <Route path='/myActivity' element={<MyActivity/>}></Route>
//     <Route path='/help' element={<Help />}> </Route>
//     <Route path='/signout' element={<Login />}> </Route>
//     <Route path='/admin/dashboard' element={<AdminDash/>}></Route>


    


  // </Routes>
  // </div>
  // </main>
  

export default App