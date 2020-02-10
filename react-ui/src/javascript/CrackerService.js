import axios from 'axios'

class CrackerService{

    setupAxiosInterceptor = () =>{

        let userName = 'cracker'
        let password = 'password';

        let basicHeader = 'Basic ' + window.btoa(userName + ':' + password)
        axios.interceptors.request.use((config) => {
                config.headers.authorization=basicHeader
                return config;
            }
        );
    }


    getFoodDetails = (searchterm) => {
        return axios.get(`http://localhost:8080/api/foodsearch?item=${searchterm}`);
    }

    postFoodDetails(termBody){
        return axios.post("http://localhost:8080/api/nutrients", termBody);
    }

    isValidUser = (loginCredentials) => {
        return axios.post("http://localhost:8080/api/login", loginCredentials);
    }

    registerNewUser = (userInfo) => {
        return axios.post("http://localhost:8080/api/customers", userInfo)
    }
}
export default new CrackerService();