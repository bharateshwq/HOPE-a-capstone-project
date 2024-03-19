import React from 'react';
import AdminService from '../api/services/AdminService';
import { useEffect } from 'react';
import useAuth from "../hooks/useAuth";
import Cookies from 'js-cookie';
import axios from '../api/axios';

const About = () => {
  const { auth } = useAuth();
  useEffect(() => {
    const accessToken = Cookies.get('accessToken');
    const fetchData = async () => {
      try {
        const response = await axios.get('/api/campaigns/mainDash', {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${auth.accessToken}`, // Include the JWT token in the Authorization header
          },
          withCredentials: true, // Include cookies in the request
        });

        console.log(response.data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {/* About Section */}
      <section id="about" className="bg-gray-100 py-16">
        <div className="container mx-auto flex px-10 py-20 items-center justify-center">
          <div className="flex flex-col w-1/2 mr-5">
            <h1 className="text-5xl font-semibold text-gray-800 mb-4">About <span className="text-green-200">Us</span></h1>
            <h2 className="text-lg italic text-gray-600 mb-8">"Helping Others Prosper Eternally"</h2>
            <p className="text-gray-700">At HOPE, we believe in the power of collective action. Whether you're passionate about supporting medical treatments, education initiatives, environmental conservation, or any other social cause, HOPE empowers you to be part of something greater. With over [insert number] generous volunteers and donors like you, we've already made an incredible impact, touching the lives of [insert number] individuals and communities.</p>
            <p className="text-gray-700 mt-4">Our platform seamlessly integrates fundraising campaigns and volunteer opportunities, ensuring a holistic approach to philanthropy. Through our intuitive interface, you can easily start campaigns, raise funds, organize events like beach clean-ups, and volunteer for causes close to your heart. Together, we can create positive change and build a brighter future for all.</p>
          </div>
          <div className="w-1/2 ml-5">
            <img src="..\images\HHHH.jpg" alt="img" className="rounded-lg" />
          </div>
        </div>
      </section> 
      <section id="contact" className="bg-white py-16">
        <div className="container mx-auto flex flex-col items-center justify-center">
          <h1 className="text-4xl font-semibold text-gray-800 mb-8">Contact <span className="text-blue-500">Info</span></h1>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="contact-item flex items-center justify-center">
              <img src="https://img.icons8.com/bubbles/100/000000/phone.png" alt="phone" className="mr-4" />
              <div className="contact-info">
                <h1 className="text-lg font-semibold text-gray-700">Phone</h1>
                <h2 className="text-gray-600">+91 234 123 1234</h2>
                <h2 className="text-gray-600">+91 234 123 1234</h2>
              </div>
            </div>
            <div className="contact-item flex items-center justify-center">
              <img src="https://img.icons8.com/bubbles/100/000000/new-post.png" alt="email" className="mr-4" />
              <div className="contact-info">
                <h1 className="text-lg font-semibold text-gray-700">Email</h1>
                <h2 className="text-gray-600">Hopeinfo@gmail.com</h2>
                <h2 className="text-gray-600">Hope.helpline@gmail.com</h2>
              </div>
            </div>
            <div className="contact-item flex items-center justify-center">
              <img src="https://img.icons8.com/bubbles/100/000000/map-marker.png" alt="address" className="mr-4" />
              <div className="contact-info">
                <h1 className="text-lg font-semibold text-gray-700">Address</h1>
                <h2 className="text-gray-600">kalasipalya, bangalore-530001, india</h2>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section id="footer" className="bg-gray-800 py-16">
        <div className="container mx-auto flex flex-col items-center justify-center">
          <div className="brand">
            <h1 className="text-3xl font-semibold text-white">H.<span className="text-blue-500">O</span>.P.<span className="text-blue-500">E</span></h1>
          </div>
          <h2 className="text-lg text-gray-400 mb-8">Keep connected To us</h2>
          <div className="social-icon flex items-center justify-center">
            <div className="social-item mr-4">
              <a href="#"><img src="https://img.icons8.com/bubbles/100/000000/facebook-new.png" alt="facebook" /></a>
            </div>
            <div className="social-item">
              <a href="#"><img src="https://img.icons8.com/bubbles/100/000000/instagram-new.png" alt="instagram" /></a>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default About;
