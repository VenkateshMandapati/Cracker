import React, {Component} from 'react'
import Header from './Header'
import Welcome from './Welcome'
import Home from './Home'
import Login from './Login'
import FoodSearch from './FoodSearch'
import ErrorComponent from './ErrorComponent'
import SignUp from './SignUp'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import CrackerService from '../javascript/CrackerService'

class CrackerRoot extends Component{

    render(){
        CrackerService.setupAxiosInterceptor();
        return(
            <>
                <Router>
                    <>
                        <Header />
                        <Switch>
                            <Route path="/" exact component={Home}/>
                            <Route path="/login" component={Login}/>
                            <Route path="/foodsearch" component={FoodSearch}/>
                            <Route path="/signup" component={SignUp}/>
                            <Route path="/welcome" component={Welcome}/>
                            <Route component={ErrorComponent}/>
                        </Switch>
                    </>
                </Router>
                {/* <Footer /> */}
            </>
        )
    }

}export default CrackerRoot