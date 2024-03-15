import React from 'react'
import {
  Timeline,
  TimelineItem,
  TimelineConnector,
  TimelineHeader,
  TimelineIcon,
  TimelineBody,
  Typography,
} from "@material-tailwind/react";
import { HomeIcon, BellIcon, CurrencyDollarIcon } from "@heroicons/react/24/solid";
import { FaEdit } from "react-icons/fa";
const Help = () => {
  return (
   
    <div className='flex justify-center items-center mt-10'>
    <div className="w-[32rem]">
     
        <TimelineItem>
          <TimelineConnector />
          <TimelineHeader>
            <TimelineIcon className="p-2">
              <HomeIcon className="h-4 w-4" />
            </TimelineIcon>
            <Typography variant="h5" color="blue-gray">
              Dashboard
            </Typography>
          </TimelineHeader>
          <TimelineBody className="pb-8">
            <Typography color="gary" className="font-normal text-gray-600">
            Once the project is being posted u can view the project on the dashboard where you can select the campaigns u want to participate in and you search the project by using the search bar.
            </Typography>
          </TimelineBody>
        </TimelineItem>
        <Timeline >
        <TimelineItem>
          <TimelineConnector />
          <TimelineHeader>
            <TimelineIcon className="p-2">
            <FaEdit />
            </TimelineIcon>
            <Typography variant="h5" color="blue-gray">
               Add campaign
            </Typography>
          </TimelineHeader>
          <TimelineBody className="pb-8">
            <Typography color="gary" className="font-normal text-gray-600">
              Click on the add project icon in the navigation bar to add the type of campaign you want to post on the dashboard for various users to view and contribute to the project.
            </Typography>
          </TimelineBody>
        </TimelineItem>
        <TimelineItem>
          <TimelineConnector />
          <TimelineHeader>
            <TimelineIcon className="p-2">
              <BellIcon className="h-4 w-4" />
            </TimelineIcon>
            <Typography variant="h5" color="blue-gray">
             Donate or Volunteer
            </Typography>
          </TimelineHeader>
          <TimelineBody className="pb-8">
            <Typography color="gary" className="font-normal text-gray-600">
              While viewing the project u cant select the project to donate and you will be directed to the donation page and the same will happen for the volunteer also.
            </Typography>
          </TimelineBody>
        </TimelineItem>
        <TimelineItem>
          <TimelineHeader>
            <TimelineIcon className="p-2">
              <CurrencyDollarIcon className="h-4 w-4" />
            </TimelineIcon>
            <Typography variant="h5" color="blue-gray">
              Make Payment
            </Typography>
          </TimelineHeader>
          <TimelineBody>
            <Typography color="gary" className="font-normal text-gray-600">
              If you select on donation the user pursue payment by getting redirected to payment page, where the user can complete the payment by choosing the desired payment method.
            </Typography>
          </TimelineBody>
        </TimelineItem>
      </Timeline>
    </div>
    </div>
  );
}

export default Help