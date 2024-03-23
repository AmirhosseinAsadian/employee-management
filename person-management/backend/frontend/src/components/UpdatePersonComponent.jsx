import React, { Component } from 'react'
import { PersonLinesFill, PersonPlus } from 'react-bootstrap-icons';
import PersonService from '../services/PersonService';

export default class UpdatePersonComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            name: "",
            birthDate: "",
            code: ""
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeBirthDateHandler = this.changeBirthDateHandler.bind(this);
        this.updatePerson = this.updatePerson.bind(this);
    }

    componentDidMount(){
        
        PersonService.getPersonByCode(this.state.code).then( resp => {
            let person = resp.data;
            this.setState({
                name: person.name,
                birthDate: person.birthDate,
                code: person.code
            });
        });
    }

    updatePerson = (event)=>{
        event.preventDefault();

        let employee = {
            name: this.state.name,
            birthDate: this.state.birthDate,
            code: this.state.code
        };
        PersonService.updatePerson(employee, this.state.id).then((resp)=>{
            console.log(resp);
            this.props.history.push("/allPersons");
        });
    }
    cancel = () =>{
        this.props.history.push("/allPersons");
    }
    changeNameHandler = (event) => {
        this.setState({ firstName: event.target.value });
    }
    changeBirthDateHandler = (event) => {
        this.setState({ lastName: event.target.value });
    }
    changeCodeHandler = (event) => {
        this.setState({ emailId: event.target.value });
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3">
                            <h3><PersonLinesFill /> Update Person </h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name : </label>
                                        <input placeholder="Name" type="text" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Birth Date : </label>
                                        <input placeholder="Birth Date" type="text" name="birthDate" className="form-control"
                                            value={this.state.birthDate} onChange={this.changeBirthDateHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Code : </label>
                                        <input placeholder="Code" type="text" name="code" className="form-control"
                                            value={this.state.code} onChange={this.changeCodeHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.updatePerson}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        )
    }
}