import React, {Component, useState} from 'react';
import UserService from "../services/UserService";

class ListUsersComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            users: [],
            isLoaded: false,
            redirectToReferrer: false,
            token: ''
        }
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
    }

    componentDidMount() {
        UserService.getAllUsers().then((res) => {
            this.setState({users: res.data.body});
        });
    }

    addUser() {
        this.props.history.push('/add-user');
    }

    deleteUser(login) {
        UserService.deleteUser(login).then(res => {
            this.setState({users: this.state.users.filter(user => user.login !== login)});
        });
    }

    editUser(login) {
        this.props.history.push(`/update-user/${login}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Users List</h2>
                <div className="btn btn-info">
                    <button className="btn btn-info" onClick={this.addUser}>Add User</button>
                </div>

                <div className="row">
                    <table className="table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Login</th>
                            <th>Email</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Birthday</th>
                            <th>Role</th>
                            <th>Active</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.users.map(
                                user =>
                                    <tr key={user.id}>
                                        <td>{user.id}</td>
                                        <td>{user.login}</td>
                                        <td>{user.email}</td>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>

                                        <td>{new Date(user.birthday).toISOString()}</td>
                                        <td> {user.role.name}</td>
                                        <td>
                                            <button onClick={() => this.editUser(user.login)}
                                                    className="btn btn-info">Update
                                            </button>
                                            <button style={{marginLeft: "10px"}}
                                                    onClick={() => this.deleteUser(user.login)}
                                                    className="btn btn-danger">Delete
                                            </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListUsersComponent