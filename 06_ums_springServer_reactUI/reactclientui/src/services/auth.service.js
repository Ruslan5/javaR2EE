import axios from "axios";

/**
 * for localhost
 */
// const API_URL = "http://localhost:8081/restSpringServer_war/resources/";
/**
 * for docker
 */
const API_URL = "http://localhost:8080/resources/";
const registration = (user) => {
    return axios.post(API_URL + "auth/register", user)
};

const login = (username, password) => {
    return axios
        .post(API_URL + "auth/login", {
            username,
            password,
        })
        .then((response) => {
            if (response.data.token) {
                localStorage.setItem("token", JSON.stringify(response.data.token));
            }
            return response.data;
        });
};
const logout = () => {
    localStorage.removeItem("token");
};
const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("token") || 'null');

};
const getCurrentRole = () => {
    return JSON.parse(window.atob((JSON.parse(localStorage.getItem("token") || 'null')).toString().split('.')[1])).roles;
}
export default {
    registration,
    login,
    logout,
    getCurrentUser,
    getCurrentRole,
};