import React from 'react'
import {
  List,
  ListItem,
  ListItemSuffix,
  Card,
  IconButton,
} from "@material-tailwind/react";
import { ImCross } from "react-icons/im";

import { TiTick } from "react-icons/ti";


const AdminDashList = (props) => {
  return (
    <Card className="w-96">
      <List>
        <ListItem ripple={false} className="py-1 pr-1 pl-4">
          <div className='pr-6'>
              <h1 className='text-xl font-extrabold'>Title</h1>
              <p className='pl-3'>Donate for Education</p>
          <div>
              <h2 className='text-sm font-bold'>Email</h2>
              <p className='pl-3'><a href="mailto:example@example.com">example@example.com</a></p>
              
              <h2 className='text-sm font-bold'>Phone</h2>
              <p className='pl-3'>+1 (555) 123-4567</p>
          </div>
          </div>
          <ListItemSuffix>
            <div className='flex flex-row'>
            <IconButton className="bg-gray-200 mr-3 " style={{ fontSize: '22px' }} variant="text" color="green">
            <TiTick />
            </IconButton>
            <IconButton className="bg-gray-200 " variant="text" color="red">
            <ImCross />
          
            </IconButton>
            </div>
          </ListItemSuffix>
        </ListItem>
       
      </List>
    </Card>
  )
}

export default AdminDashList