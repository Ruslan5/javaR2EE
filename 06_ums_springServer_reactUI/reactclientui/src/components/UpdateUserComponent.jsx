import React, {Component} from 'react'
import UserService from '../services/UserService';
import {FormErrors} from "./FormErrors";

class UpdateUserComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            login: this.props.match.params.login,

            formError: {login: '', password: '', email: '', firstName: '', lastName: '', birthday: '', role: ''},
            loginValidate: true,
            passwordValid: true,
            emailValid: true,
            firstNameValid: true,
            lastNameValid: true,
            birthdayValid: false,
            roleValid: true,
            formValid: true
        }
    }

    handleUserInput = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({[name]: value}, () => this.validateField(name, value));
    }

    componentDidMount() {
        UserService.getUserByLogin(this.state.login).then((res) => {
            let user = res.data;
            this.setState({
                password: user.password,
                email: user.email,
                firstName: user.firstName,
                lastName: user.lastName,
                birthday: user.birthday,
                role: user.role
            });
        });
    }

    updateUser = (e) => {
        e.preventDefault();
        const name = e.target.name;
        const value = e.target.value;
        this.setState({[name]: value}, () => this.validateField(name, value));
        let user = {
            password: this.state.password,
            email: this.state.email,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            birthday: this.state.birthday,
            role: this.state.role
        };
        UserService.updateUser(user, this.state.login).then(res => {
            this.props.history.push('/user');
        });
    }

    validateField(fieldName, value) {
        let fieldValidationErrors = this.state.formError;
        // let fieldValid = this.state.validForm;

        let loginValid = this.state.loginValid;
        let passwordValid = this.state.passwordValid;
        let emailValid = this.state.emailValid;
        let firstNameValid = this.state.firstNameValid;
        let lastNameValid = this.state.lastNameValid;
        let birthdayValid = this.state.birthdayValid;

        switch (fieldName) {
            case 'login':
                loginValid = (value.length >= 3);
                fieldValidationErrors.login = loginValid ? '' : 'The login must be min 3 symbols.';
                break;
            case 'password':
                passwordValid = value.length >= 6;
                fieldValidationErrors.password = passwordValid ? '' : ' is too short';
                break;

            case 'email':
                emailValid = value.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i);
                fieldValidationErrors.email = emailValid ? '' : ' is invalid';
                break;

            case 'firstName':
                firstNameValid = value.length >= 3;
                fieldValidationErrors.firstName = firstNameValid ? '' : ' is too short';
                break;
            case 'lastName':
                lastNameValid = value.length >= 6;
                fieldValidationErrors.lastName = lastNameValid ? '' : ' is too short';
                break;
            case 'birthday':
                birthdayValid = value !== '';
                fieldValidationErrors.birthday = birthdayValid ? '' : ' is empty';
                break;

            default:
                break;
        }
        this.setState({

            passwordValid: passwordValid,
            emailValid: emailValid,
            firstNameValid: firstNameValid,
            lastNameValid: lastNameValid,
            birthdayValid: birthdayValid,
            formError: fieldValidationErrors

        }, this.validateForm);
    }

    validateForm() {
        this.setState({
            formValid: this.state.passwordValid && this.state.emailValid
                && this.state.firstNameValid && this.state.lastNameValid && this.state.birthdayValid && this.state.roleValid
        });
    }

    errorClass(error) {
        return (error.length === 0 ? '' : 'has-error');
    }

    render() {
        return (
            <form className="demoForm">
                <h2>Update user</h2>
                <div className="panel panel-default">
                    <FormErrors formErrors={this.state.formError}/>
                </div>

                <div className={`form-group ${this.errorClass(this.state.formError.password)}`}>
                    <label htmlFor="password">Password</label>
                    <input type="password" className="form-control" name="password"
                           placeholder="Password"
                           value={this.state.password}
                           onChange={this.handleUserInput}/>
                </div>

                <div className={`form-group ${this.errorClass(this.state.formError.email)}`}>
                    <label htmlFor="email">Email address</label>
                    <input type="email" required className="form-control" name="email"
                           placeholder="Email"
                           value={this.state.email}
                           onChange={this.handleUserInput}/>
                </div>

                <div className={`form-group ${this.errorClass(this.state.formError.firstName)}`}>
                    <label htmlFor="firstName">firstName</label>
                    <input type="firstName" className="form-control" name="firstName"
                           placeholder="firstName"
                           value={this.state.firstName}
                           onChange={this.handleUserInput}/>
                </div>
                <div className={`form-group ${this.errorClass(this.state.formError.lastName)}`}>
                    <label htmlFor="lastName">lastName</label>
                    <input type="lastName" className="form-control" name="lastName"
                           placeholder="lastName"

                           value={this.state.lastName}
                           onChange={this.handleUserInput}/>
                </div>

                <div className={`form-group ${this.errorClass(this.state.formError.birthday)}`}>
                    <label> Birthday: </label>
                    <input type={"date"} data-date-format={"yyyy-MM-dd"} name="birthday" className="form-control"
                           onChange={this.handleUserInput} value={this.state.birthday || ''}/>


                </div>
                <div className={`form-group ${this.errorClass(this.state.formError.role)}`}>
                    <fieldset>
                        <legend>Role</legend>
                        <select name="role" size="1">
                            <option value={this.state.role = "1"}
                                    onChange={this.handleUserInput}>Admin
                            </option>
                            <option value={this.state.role = "2"}
                                    onChange={this.handleUserInput}>User
                            </option>
                        </select>
                    </fieldset>
                    <br/>
                </div>
                <button type="submit" className="btn btn-primary" disabled={!this.state.formValid}
                        onClick={this.updateUser}>Update
                </button>
            </form>
        )
    }
}

export default UpdateUserComponent