import React, {Component} from 'react'
import Header from './Header'
import Footer from './Footer'
import Home from './Home'

class CrackerRoot extends Component{

    render(){
        return(
            <>
                <Header />
                <Home />
                <Footer />
            </>
        )
    }

}export default CrackerRoot