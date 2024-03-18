import React from 'react';
 
function Card({ imageSrc, title, description, progress }) {
  return (
    <div className="max-w-xs mx-auto bg-white shadow-lg rounded-lg overflow-hidden">
      <img className="w-full h-40 object-cover object-center" src={imageSrc} alt="Card Image" />
      <div className="px-6 py-4">
        <h3 className="text-xl font-bold mb-2">{title}</h3>
        <p className="text-gray-700 text-base">{description}</p>
      </div>
      <div className="px-6 pt-4 pb-2">
        <div className="w-full bg-gray-200 rounded-full">
          <div className="bg-blue-500 text-xs leading-none py-1 text-center text-white rounded-full" style={{ width: progress }}>{progress}</div>
        </div>
      </div>
    </div>
  );
}
 
export default Card;