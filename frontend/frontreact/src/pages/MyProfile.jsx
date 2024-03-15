import React from 'react';
import ProfileContent from '../components/ProfileContent';
import ProfileDetails from '../components/ProfileDetails';

function MyProfile() {
  return (
<div className="flex flex-col justify-center items-center w-full h-screen mt-96">
  <ProfileContent />
  <ProfileDetails />
</div>

  )
}

export default MyProfile