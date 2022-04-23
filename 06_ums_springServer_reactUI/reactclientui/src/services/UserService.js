import axios from 'axios';
import authHeader from "./auth-header";

/**
 * for localhost
 */
// const USER_API_URL = "http://localhost:8081/restSpringServer_war/resources/user";
/**
 * for docker
 */
const USER_API_URL = "http://localhost:8080/resources/user";

class UserService {

    getAllUsers(){
        return axios.get(USER_API_URL,  { headers: authHeader() });
    }

    createUser(user){
        return axios.post(USER_API_URL, user, { headers: authHeader() });
    }

    getUserByLogin(login){
        return axios.get(USER_API_URL + '/' + login, { headers: authHeader() });
    }
    updateUser(user, login){
        return axios.put(USER_API_URL + '/' + login, user, { headers: authHeader() });
    }

    deleteUser(login){
        return axios.delete(USER_API_URL + '/' + login, { headers: authHeader() });
    }
}

export default new UserService()