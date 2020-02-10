import React, {Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CrackerService from '../javascript/CrackerService';
import {Link} from 'react-router-dom'
import { withRouter } from 'react-router';

class Login extends Component {

    constructor(props){
        super(props)
        this.state = {
            email: "",
            password: "",
            invalidUser: false,
            toDisplayRegistrationMessage: this.isRegistrationMessageToBeDisplayed()
        }
    }

    isRegistrationMessageToBeDisplayed = () => {
        if( this.props.location.state && this.props.location.state.previouspage === 'signup'){
                return true;
        }
        return false;
    }

    validate = (values) => {
        debugger

        let errors = {}
        
        if ( !values.email || values.email.length === 0) {
            errors.email = 'Email Required';
        } 

        if(!values.password){
            errors.password = 'Password Required';
        }else if(values.password.length < 3){
            errors.password = 'password length should be atleast 3';
        }
        return errors;
    }

    handleSubmit = (values) => {
        let email = values.email;
        let password = values.password;
        console.log("email is: " + email + " password is: " + password)
        if(this.isValidUser(email, password)){
            console.log("Entered user logging activity");
            //CrackerService.getUserLoggingActivity(username, password)
        }else{
            console.log("Invalid User")
        }
    }

    isValidUser = (email, password) => {
        let loginCredentials = {
            email:email,
            password:password
        }
        CrackerService.isValidUser(loginCredentials).then((response) => {
            this.handleResponse(response)
        })
        .catch((error) => {
            console.log("error response is " + error);
            this.handleUserErrorResponse(error);
        })
    }

    handleResponse = (response) => {
        debugger;
        if(response.data.isCustomerValid){
            this.props.history.push(
                {
                    pathname: '/welcome',
                    state: {
                        firstName: response.data.firstName
                    }
                })
        }
        console.log("user success response");
    }

    handleUserErrorResponse = () => {
        console.log("user error response");
        this.setState({
            invalidUser:true
        })
    }

    render(){
        console.log("Login Props " + this.props);
        return(
            <div>
                <Formik
                    initialValues={
                        {
                            email: "",
                            password: "" 
                        }
                    }
                    onSubmit={this.handleSubmit}
                    validate={this.validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                    // enableReinitialize={true}
                >
                    {
                        (props) => (
                            <>
                                {this.state.toDisplayRegistrationMessage && <div>Succesfully Registered, login with your credentials</div>}
                                <div className="container-fluid mt-2">
                                    <Form className="w-50">
                                        <ErrorMessage name="email" component="div" className="alert alert-danger"/>
                                        <ErrorMessage name="password" component="div" className="alert alert-danger"/>
                                        {this.state.invalidUser && <div className="alert alert-danger">Username or password is incorrect</div>}
                                        <div className="form-group">
                                            <label>Email</label>
                                            <Field type="email" className="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email" />
                                            <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                                        </div>
                                        <div className="form-group">
                                            <label>Password</label>
                                            <Field type="password" className="form-control" name="password" id="password" placeholder="Password" />
                                        </div>
                                        <span>
                                            <button type="submit" className="btn btn-success mr-2">Submit</button>
                                            No Login Credentials, Signup <Link to="/signup">here</Link>
                                        </span>
                                    </Form>
                                </div>
                            </> 
                        )
                    }
                </Formik>
            </div>
        )
    }
}
export default withRouter(Login)