import React from 'react'
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
  Input,
  Checkbox,
  Button,
} from "@material-tailwind/react";
import { Link } from 'react-router-dom';
const Login = () => {
  return (
    <div className='flex justify-center items-center mt-24'>
    <Card className="w-96">
      <CardHeader
        variant="gradient"
        color="gray"
        className="mb-4 grid h-28 place-items-center"
      >
        <Typography variant="h3" color="white">
          Login
        </Typography>
      </CardHeader>
      <CardBody className="flex flex-col gap-4">
        <Input type="text" label="User Name" size="lg" />
        <Input type="password" label="Password" size="lg" />
        <div className="-ml-2.5">
          <Checkbox label="Remember Me" />
        </div>
      </CardBody>
      <CardFooter className="pt-0">
        <Button variant="gradient" fullWidth>
          Login
        </Button>
        <Typography variant="small" className="mt-6 flex justify-center">
          Don&apos;t have an account?
          <Link to='/register'>
          <Typography
            as="a"
            href="#signup"
            variant="small"
            color="blue-gray"
            className="ml-1 font-bold"
          >
            Sign up
          </Typography>
          </Link>
        </Typography>
      </CardFooter>
    </Card>
    </div>

  )
}

export default Login