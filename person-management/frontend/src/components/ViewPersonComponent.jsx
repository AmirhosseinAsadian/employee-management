import React, { Component } from 'react'
import { PersonLinesFill } from 'react-bootstrap-icons';
import PersonService from '../services/PersonService';

export default class ViewPersonComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            employee: {}
        }
    }

    componentDidMount() {
        PersonService.getPersonByCode(this.state.code).then((resp) => {
            this.setState({ person : resp.data});
        });
    }

    render() {
        return (
            <div>
                <br />
                <div className="card col-md-6 offset-md-3">
                    <br/>
                    <h3 className="text-center"><PersonLinesFill /> Employee Details :</h3>
                    <div className="card-body">
                    <div className="row">
                        <label>Name : </label>
                        <div> {this.state.person.name}</div>
                    </div>
                    <div className="row">
                        <label>Birth Date : </label>
                        <div> {this.state.person.birthDate}</div>
                    </div>
                    <div className="row">
                        <label>Code : </label>
                        <div> {this.state.person.code}</div>
                    </div>
                </div>
                </div>
            </div>
        )
    }
}
