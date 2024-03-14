import React, { useEffect } from "react";

const parseJwt = (token) => {
  try {
    return JSON.parse(atob(token.split(".")[1]));
  } catch (e) {
    return null;
  }
};

const AuthVerify = ({ logOut }) => {
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (user && user.jwttoken) {
      const decodedJwt = parseJwt(user.jwttoken);

      if (decodedJwt && decodedJwt.exp * 1000 < Date.now()) {
        logOut();
      }
    }
  }, [logOut]);

  return null;
};

export default AuthVerify;
