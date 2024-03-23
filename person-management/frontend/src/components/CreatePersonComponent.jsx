import React, { Component } from 'react';
import { PersonCheckFill, PersonPlus } from 'react-bootstrap-icons';
import PersonService from '../services/PersonService';

export default class CreatePersonComponent extends Component {
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
        this.saveOrUpdatePerson = this.saveOrUpdatePerson.bind(this);
    }

    componentDidMount(){
        if(this.state.code === "_add"){
            return;
        } else {
            PersonService.getPersonByCode(this.state.code).then( resp => {
                let person = resp.data;
                this.setState({
                    name: person.name,
                    birthDate: person.birthDate,
                    code: person.code
                });
            });
        }
    }

    saveOrUpdatePerson = (event)=>{
        event.preventDefault();

        let person = {
            name: this.state.name,
            birthDate: this.state.birthDate,
            code: this.state.code
        };

        if(this.state.id === '_add'){
            PersonService.createPerson(person).then( res => {
                this.props.history.push("/allPersons")
            });
        } else if(this.state.code != undefined) {
            PersonService.updatePerson(person, this.state.code).then((resp)=>{
                console.log(resp);
                this.props.history.push("/allPersons");
            });
        }
    }
    cancel = () =>{
        this.props.history.push("/allPersons");
    }
    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }
    changeBirthDateHandler = (event) => {
        this.setState({ birthDate: event.target.value });
    }
    changeCodeHandler = (event) => {
        this.setState({ code: event.target.value });
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3><PersonPlus /> Add Person </h3>
        } else {
           return <h3><PersonCheckFill /> Update Person </h3>
        }
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3">
                            {this.getTitle()}
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name : </label>
                                        <input placeholder="Name" type="text" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name : </label>
                                        <input placeholder="Birth Date" type="text" name="birthDate" className="form-control"
                                            value={this.state.birthDate} onChange={this.changeBirthDateHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Email-Id : </label>
                                        <input placeholder="code" type="text" name="code" className="form-control"
                                            value={this.state.code} onChange={this.changeCodeHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveOrUpdatePerson}>Save</button>
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
