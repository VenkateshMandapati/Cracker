import React, {Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';

class FoodSearch extends Component{

    render(){
        return(
            <div>
            <Formik
                initialValues={
                    {
                        searchTerm: "", 
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
                        <Form>
                            <ErrorMessage name="description" component="div"
                                    className="alert alert-warning"/>
                            <fieldset className="form-group">
                                <label>SearchTerm</label>
                                <Field className="form-control" type="text" name="searchTerm" />
                            </fieldset>
                            <button className="btn btn-success" type="submit">Search</button>
                        </Form>
                    )
                }
            </Formik>
        </div>
        )
    }

}
export default FoodSearch