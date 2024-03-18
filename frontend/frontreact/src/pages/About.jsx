import React, { useEffect } from 'react';
const About = () => {
  useEffect(() => {
    // Smooth scrolling function
    const smoothScroll = (targetId) => {
      const targetElement = document.getElementById(targetId);
      if (targetElement) {
        window.scrollTo({
          top: targetElement.offsetTop,
          behavior: 'smooth',
        });
      }
    };
  // Check if there's a hash in the URL and scroll to the target section
  const hash = window.location.hash.substring(1);
  if (hash) {
    smoothScroll(hash);
  }
}, []);
  return (
    <div>
      {/* About Section */}
      <section id="about" className="bg-gray-100 py-16">
        <div className="container mx-auto flex px-10 py-20 items-center justify-center">
          <div className="flex flex-col w-1/2 mr-5">
            <h1 className="text-5xl font-semibold text-gray-800 mb-4">About <span className="text-green-200">Us</span></h1>
            <h2 className="text-lg italic text-gray-600 mb-8">"Help Others Prosper Eternally"</h2>
            <p className="text-gray-700">Welcome to HOPE. We believe in the power of collective action. Whether you're passionate about supporting medical treatments, education initiatives, environmental conservation, or any other social cause, HOPE empowers you to be part of something greater. With over thousands of generous volunteers and donors like you, we've already made an incredible impact, touching the lives of all the individuals and communities who are in need of help .
            Our platform seamlessly integrates fundraising campaigns and volunteer opportunities, ensuring a holistic approach to philanthropy. Through our intuitive interface, you can easily start campaigns, raise funds, organize events like beach clean-ups, and volunteer for causes close to your heart. Together, we can create positive change and build a brighter future for all.</p>
          </div>
          <div className="w-1/2 ml-auto mr-6 pr-8 flex items-center justify-end">
            <div className="rounded-lg" style={{ width: '80%', height:'100%', minWidth: '200px' }}>
              <img src="https://th.bing.com/th/id/OIP.xQe8MkHi3gsQfTRTKVgegwHaEK?w=328&h=184&c=7&r=0&o=5&pid=1.7" alt="img" className="w-full h-auto rounded-lg" />
            </div>
          </div>
        </div>
      </section>
      <section id="volunteer" className="bg-gray-100 py-16">
        <div className="container mx-auto flex px-10 py-20 items-center justify-center">
          <div className="flex flex-col w-1/2 mr-5">
            <h1 className="text-5xl font-semibold text-gray-800 mb-4">Volunteering <span className="text-green-200">Campaigns</span></h1>
            <h2 className="text-lg italic text-gray-600 mb-8">"Join Hands, Change Lives: Volunteer Today!"</h2>
            <p className="text-gray-700">Welcome to our Volunteering Campaigns arena, where passion meets purpose and hands join hearts to shape a better world. Here, every act of service is a spark igniting change, every volunteer a hero in their own right. From building homes to nurturing minds, from restoring habitats to spreading joy – your time and dedication are the threads weaving the fabric of our shared humanity. Join us as we paint the canvas of tomorrow with colors of compassion, resilience, and unity. Together, let's roll up our sleeves, lift spirits, and leave footprints of kindness that echo for generations to come.</p>
          </div>
          <div className="w-1/2 ml-auto mr-6 pr-8 flex items-center justify-end">
            <div className="rounded-lg" style={{ width: '70%', minWidth: '200px' }}>
              <img src="https://th.bing.com/th/id/OIP.ihSoFuWxyN3HFs3YhsBZUgHaFj?rs=1&pid=ImgDetMain" alt="img" className="w-full h-auto rounded-lg" />
            </div>
          </div>
        </div>
      </section>
      <section id="donation" className="bg-gray-100 py-16">
        <div className="container mx-auto flex px-10 py-20 items-center justify-center">
          <div className="flex flex-col w-1/2 mr-5">
            <h1 className="text-5xl font-semibold text-gray-800 mb-4">Donation <span className="text-green-200">Campaigns</span></h1>
            <h2 className="text-lg italic text-gray-600 mb-8">"Every Contribution Counts!"</h2>
            <p className="text-gray-700">Welcome to our Fundraising Campaigns hub, where generosity meets impact and dreams take flight. Here, every donation is a beacon of hope, illuminating pathways to brighter tomorrows. From empowering medical breakthroughs to fueling educational dreams, from safeguarding our planet to uplifting communities in need, your contributions are the catalysts for change. Join us in this journey of compassion, where every dollar raised is a testament to the boundless potential of human kindness. Together, let's write stories of resilience, love, and transformation.</p>
          </div>
          <div className="w-1/2 ml-auto mr-6 pr-8 flex items-center justify-end">
            <div className="rounded-lg" style={{ width: '70%', minWidth: '200px' }}>
              <img src="https://th.bing.com/th/id/OIP.1Rrk78CWEn0ZY2rgVCvoYAHaE8?w=285&h=191&c=7&r=0&o=5&pid=1.7" alt="img" className="w-full h-auto rounded-lg" />
            </div>
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