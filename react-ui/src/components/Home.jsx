import React, {Component} from 'react'
import FoodSearch from './FoodSearch'

class Home extends Component {

    render(){
        return (
            <div className="container-fluid">
                <div className="jumbotron">
                    <h1 className="display-4">Calories Tracker</h1>
                    <p className="lead">Track what you eat with this application</p>
                    <hr className="my-4" />
                    <p>Eat Healthy and Stay Fit</p>
                    <p className="lead">
                        <a className="btn btn-primary btn-lg" href="/login" role="button">Login/SignUp</a>
                    </p>
                </div>
                <FoodSearch />
            </div>
        )
    }
}

export default Home