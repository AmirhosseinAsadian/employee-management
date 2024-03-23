import axios from 'axios';

const url = "http://localhost:8080/api/employees";

class EmployeeService {
    
    getEmployees() {
        console.log('hi amir!'); 
        return axios.get(url);
    }
}

export default new EmployeeService()