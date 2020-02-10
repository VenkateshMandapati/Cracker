import React, {Component} from 'react'

class Welcome extends Component{

    render(){
        return(
            <div className="container-fluid">Welcome {this.props.location.state.firstName}</div>
        )
    }
}
export default Welcome