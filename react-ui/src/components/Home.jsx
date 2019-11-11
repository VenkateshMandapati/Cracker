import React, {Component} from 'react'
import FoodSearch from './FoodSearch'

class Home extends Component {

    render(){
        return (
            <div className="container-fluid">
                <div class="jumbotron">
                    <h1 class="display-4">Calories Tracker</h1>
                    <p class="lead">Track what you eat with this application</p>
                    <hr class="my-4" />
                    <p>Eat Healthy and Stay Fit</p>
                    <p class="lead">
                        <a class="btn btn-primary btn-lg" href="#" role="button">Login/SignUp</a>
                    </p>
                </div>
                <FoodSearch />
            </div>
        )
    }
}

export default Home