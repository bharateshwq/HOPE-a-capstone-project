import axios from "../axios";

const getPublicContent = () => {
  return axios.get("/api/auth/all");
};

const getUserBoard = () => {
  return axios.get("/api/auth/user");
};

const getAdminBoard = () => {
  return axios.get("admin");
};

const UserService = {
  getPublicContent,
  getUserBoard,
  getAdminBoard,
}

export default UserService;
