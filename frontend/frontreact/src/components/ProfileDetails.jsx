import React from 'react';

export default function ProfileDetails() {
  return (
    <div className="w-full max-w-sm rounded-xl border">
      <div className="p-6 grid gap-4 bg-white">
        <div className="text-center">
          <img
            className="mx-auto rounded-full"
            height="96"
            src="https://th.bing.com/th/id/OIP.Vzu9LsVapjfVOcPQ0YolxgHaHa?rs=1&pid=ImgDetMain"
            style={{
              aspectRatio: "1",
              objectFit: "cover",
            }}
            width="96"
            alt="Profile"
          />
          <h2 className="mt-4 text-lg font-semibold">Profile</h2>
          <p className="text-sm text-gray-600">Personal details</p>
        </div>
        <div className="grid gap-2">
          <div className="text-sm font-medium text-gray-600">Username</div>
          <div className="text-sm text-blue-700 font-semibold">pulkit123</div>
          <div className="text-sm font-medium text-gray-600">Email</div>
          <div className="text-sm text-blue-700 font-semibold">jauharipulkit17@example.com</div>
          <div className="text-sm font-medium text-gray-600">Donation Information</div>
          <div className="border rounded p-4 bg-gray-100">
            <table className="w-full">
              <tbody>
                <tr>
                  <td className="text-sm font-semibold">Campaign</td>
                  <td className="text-sm font-semibold">Amount</td>
                </tr>
                <tr>
                  <td className="text-sm">Campaign A</td>
                  <td className="text-sm">₹1000</td>
                </tr>
                <tr>
                  <td className="text-sm">Campaign B</td>
                  <td className="text-sm">₹5000</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div className="grid gap-2">
          <div className="text-sm font-medium text-gray-600">Volunteering Information</div>
          <div className="border rounded p-4 bg-gray-100">
            <table className="w-full">
              <tbody>
                <tr>
                  <td className="text-sm font-semibold">Campaign</td>
                  <td className="text-sm font-semibold">Description</td>
                </tr>
                <tr>
                  <td className="text-sm">Plantation</td>
                  <td className="text-sm">Planted Trees in different areas</td>
                </tr>
                <tr>
                  <td className="text-sm">Swachh</td>
                  <td className="text-sm">Cleaned the beach</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}
