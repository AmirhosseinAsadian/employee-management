import React, { Component } from 'react'
import { Trash2, PencilSquare, PersonPlusFill, EyeFill } from 'react-bootstrap-icons';
import MUIDataTable from 'mui-datatables';
import PersonService from '../services/PersonService';

export default class ListPersonComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            employees: []
        }
        this.addPerson = this.addPerson.bind(this);
        this.editPerson = this.editPerson.bind(this);
        this.deletePerson = this.deletePerson.bind(this);
    }

    viewPerson(id){
        this.props.history.push(`/viewPerson/${id}`);
    }

    editPerson(id) {
        this.props.history.push(`/addPerson/${id}`);
    }

    deletePerson(personCode) {
        PersonService.deletePerson(personCode).then((resp) => {
            console.log(resp);
            this.setState({ persons: this.state.persons.filter(person => person.id !== id) });
        })
    }

    addPerson() {
        this.props.history.push("/addPerson/_add");
    }

    componentDidMount() {
        PersonService.getPersons().then((res) => {
            this.setState({ persons: res["data"] });
        });
    };
    render() {

        const columns = [
            {
             name: "Name",
             label: "Name",
             options: {
                filter: true,
                sort: true,
             }
            },
            {
             name: "birthDate",
             label: "Birth Date",
             options: {
                filter: true,
                sort: true,
             }
            },
            {
             name: "code",
             label: "Code",
             options: {
                filter: false,
                sort: true,
             }
            },
            {
             name: "id",
             label: "Action",
             options: {
                filter: false,
                sort:false,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <span>
                        <button onClick={() => this.deletePerson(value)} className="row-btn delete-btn"><Trash2 size={15} /></button>
                        <button onClick={() => this.editPerson(value)} className="row-btn edit-btn"><PencilSquare size={13} /></button>
                        <button onClick={() => this.viewPerson(value)} className="row-btn view-btn"><EyeFill size={15} /></button>
                    </span>
                )
             }
            },
           ];

        const options = {
            filter: true,
            responsive: 'standard',
        };
           
           const data = this.state.persons;
        return (
            <div>
                <h2 className="text-center">Person List :</h2>
                <div className="row">
                    <button className="btn btn-success btn-sm row-btn mb-1" onClick={this.addPerson}><PersonPlusFill /> Add</button>
                </div>
                <br/>
                <MUIDataTable
                    title={"Person List"}
                    data={data}
                    columns={columns}
                />
            </div>
        )
    }
}
