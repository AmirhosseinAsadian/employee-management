import axios from 'axios';

const PERSON_API_BASE_URL = "http://localhost:8081/api/";
class PersonService {
    getPersons() {
       return axios.get(`${PERSON_API_BASE_URL}/getAllPersons?skip=0&limit=100`);
    }

    createPerson(personObj){
        console.log(`Person : ${JSON.stringify(personObj)}`);
        return axios.post(`${PERSON_API_BASE_URL}/savePerson`, personObj);
    }

    getPersonByCode(personCode){
        return axios.get(`${PERSON_API_BASE_URL}/getPerson?${personCode}`);
    }

    updatePerson(personObj, personCode){
        console.log(`Update Person : ${personObj}`);
        return axios.put(`${PERSON_API_BASE_URL}/${personCode}`, personObj);
    }

    deletePerson(personCode) {
        return axios.delete(`${PERSON_API_BASE_URL}/${personCode}`);
    }
}

export default new PersonService();