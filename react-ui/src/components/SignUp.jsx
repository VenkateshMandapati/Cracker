import React, {Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CrackerService from '../javascript/CrackerService'
import { withRouter } from 'react-router';

class SignUp extends Component{

    constructor(props){
        super(props)
        this.state = {
            firstName: "",
            lastname: "",
            email: "",
            password: "",
            reEnteredPassword: "",
            registrationFailed: false
        }
    }

    handleSubmit = (values) => {
        CrackerService.registerNewUser(values).then((response) => {
            this.handleUserSuccessResponse(response)
        })
        .catch((error) => {
            console.log("error response is " + error)
            this.handleUserErrorResponse(error);
        })
    }

    handleUserSuccessResponse = (response) => {
        debugger;
        console.log("Registration Succesfull");
        this.props.history.push(
                        {
                            pathname: '/login',
                            state: {
                                previouspage: 'signup'
                            }
                        })
    }

    handleUserErrorResponse = () => {
        this.setState({
            firstName: "",
            lastname: "",
            email: "",
            password: "",
            reEnteredPassword: "",
            registrationFailed: true
        })
        console.log("user error response");
    }

    validate = (values) => {
        console.log("Entered validation");
        let errors = {}
        if(this.checkIfEmpty(values.firstName)){
            errors.firstName = "FirstName should not be empty"
        }

        if(this.checkIfEmpty(values.lastName)){
            errors.lastName = "LastName should not be empty"
        }

        if(this.checkIfEmpty(values.email)){
            errors.email = "Email should not be empty"
        }

        if(this.checkIfEmpty(values.password)){
            errors.password = "Password should not be empty"
        }

        if(!this.checkIfEmpty(values.password) && values.password !== values.reEnteredPassword){
            errors.reEnteredPassword = "Re-Entered Password should match with Password"
        }

        return errors
    }

    checkIfEmpty = (value) => {
        if( !value || value.length === 0){
            return true;
        }
        return false;
    }

    displayMessageOnRegistrationFailure(){
        if(this.state.registrationFailed){
            return <div className="alert alert-danger">Registration Failed, please try again</div>;
        }
        return <div className="container-fluid">Please provide the below details for Signing Up</div>;
    }

    render(){
        return (
                <div>
                    <Formik
                        initialValues={
                            {
                                firstName: "",
                                lastName: "" ,
                                email: "",
                                password: "",
                                reEnteredPassword: ""

                            }
                        }
                        onSubmit={this.handleSubmit}
                        validate={this.validate}
                        validateOnChange={false}
                        validateOnBlur={false}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <div className="w-50">
                                    {this.displayMessageOnRegistrationFailure()}
                                    <br/>
                                    <div className="container-fluid">
                                        <Form>
                                            <ErrorMessage name="firstName" component="div" className="alert alert-danger"/>
                                            <ErrorMessage name="lastName" component="div" className="alert alert-danger"/>
                                            <ErrorMessage name="email" component="div" className="alert alert-danger"/>
                                            <ErrorMessage name="password" component="div" className="alert alert-danger"/>
                                            <ErrorMessage name="reEnteredPassword" component="div" className="alert alert-danger"/>
                                            <div className="form-group">
                                                <label>FirstName</label>
                                                <Field type="text" className="form-control" name="firstName" id="firstName" aria-describedby="emailHelp" placeholder="Required" />
                                            </div>
                                            <div className="form-group">
                                                <label>LastName</label>
                                                <Field type="text" className="form-control" name="lastName" id="lastName" placeholder="Required" />
                                            </div>
                                            <div className="form-group">
                                                <label>Email</label>
                                                <Field type="email" className="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Required" />
                                                <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                                            </div>
                                            <div className="form-group">
                                                <label>Password</label>
                                                <Field type="password" className="form-control" name="password" id="password" placeholder="Password" />
                                            </div>
                                            <div className="form-group">
                                                <label>Re-Enter Password</label>
                                                <Field type="password" className="form-control" name="reEnteredPassword" id="reEnteredPassword" placeholder="Please enter the same password" />
                                            </div>
                                            <span>
                                                <button type="submit" className="btn btn-success">Save</button>
                                            </span>
                                        </Form>
                                    </div>
                                </div> 
                            )
                        }
                    </Formik>
                </div>
        )
    }


}
export default withRouter(SignUp)