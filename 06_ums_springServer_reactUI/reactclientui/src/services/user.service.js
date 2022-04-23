import axios from "axios";
import authHeader from "./auth-header";

/**
 * for localhost
 */
// const API_URL = "http://localhost:8081/restSpringServer_war/resources/";

/**
 * for docker
 */
const API_URL = "http://localhost:8080/resources/";

const getUserBoard = (url, config) => {
    return axios.get(API_URL + "mod", {headers: authHeader()});
};
const getAdminBoard = () => {
    return axios.get(API_URL + "user", { headers: authHeader() });
};

export default {
    getUserBoard,
    getAdminBoard,
};