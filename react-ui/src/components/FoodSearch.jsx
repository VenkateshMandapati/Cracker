import React, {Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CrackerService from '../javascript/CrackerService'
import DisplayNutrients from './DisplayNutrients'
import '../'

class FoodSearch extends Component{

    constructor(props){
        super(props)
        this.state = {
            nutrientsList: [],
            toDisplay: false,
            isError: false,
            measuredFoodWeight:0
        }
    }

    validate = (values) => {
        let errors = {}
        if(values.searchTerm===null || values.searchTerm.length === 0){
            console.log("Invalid search term");
            errors.searchTerm = "Invalid Search Term"
        }

        return errors
    }

    handleSubmit = (values) => {
        debugger
        console.log("search term is " + values.searchTerm);
        let termBody = {
                            foodName: values.searchTerm
                       }
        CrackerService.postFoodDetails(termBody).then((response) => {
            this.handleSuccessResponse(response, values.searchTerm)
        })
        .catch((error) => {
            console.log("error response is " + error);
            this.handleErrorResponse(error, values.searchTerm);
        })
    }

    handleSuccessResponse = (response, searchTerm) => {
        if(response.data.nutrients === undefined || response.data.nutrients.length === 0){
            console.warn("No details found for search term: " + searchTerm)
            this.setState({
                toDisplay: true,
                isError: true
            })
        }else{
            this.setState({
                toDisplay: true,
                measuredFoodWeight: response.data.foodWeightForTheMeasure,
                foodName:response.data.foodName,
                nutrientsList: response.data.nutrients
            })
        }
    }

    handleErrorResponse = (error, searchTerm) => {
        console.warn("Error from backend API for: " + searchTerm)
        this.setState({
            toDisplay: true,
            isError:true 
        })
    }

    displayNutrientsInfo = () => {
        debugger
        if(this.state.isError === true)
            return(
                <div>No Details found</div>
            )
        else{
            let totalFoodInfo = {
                nutrientsList:this.state.nutrientsList,
                measuredFoodWeight:this.state.measuredFoodWeight,
                foodName:this.state.foodName
            }
            return(
                <DisplayNutrients totalFoodInfo={totalFoodInfo} />
            )
        }
    }

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
                            <>
                                <div className="container-fluid p-2">
                                    <Form className="form-inline">
                                        <ErrorMessage name="searchTerm" component="div"
                                                className="alert alert-warning"/>
                                        <fieldset className="form-group pr-2">
                                            <Field className="form-control" type="text" name="searchTerm" id="searchTerm" placeholder="search for details about any food. ex:Egg, Apple etc..," />
                                        </fieldset>
                                        <button className="btn btn-success" type="submit">Search</button>
                                    </Form>

                                    {/* <div>
                                        Tbale should be here
                                        <DisplayNutrients nutrients={this.state.nutrientsList} />
                                    </div> */}
                                    {/* {this.state.toDisplay && <DisplayNutrients nutrients={this.state.nutrientsList} /> } */}
                                    {this.state.toDisplay && this.displayNutrientsInfo()}
                                </div>
                            </> 
                        )
                    }
                </Formik>
            </div>
        )
    }

}
export default FoodSearch