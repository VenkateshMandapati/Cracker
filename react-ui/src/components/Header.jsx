import React, {Component} from 'react'
import {Link} from 'react-router-dom'

class Header extends Component {

    render(){
        return (
            <div className="container-fluid">
                <header className="header">
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <Link className="navbar-brand" to="/">Logo</Link>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/">Home <span className="sr-only">(current)</span></Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to="/foodsearch">Search</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link disabled" to="/login">Login</Link>
                            </li>
                            </ul>
                        </div>
                    </nav> 
                </header>
            </div>          
        )
    }
}
export default Header