import React, { Component } from 'react'
import DisplayItems from './DisplayItems'
import './Home.css'

class Home extends Component {
    render(){
        return (
            <body className="background">
                <div className="container gradient">
                        <p className="lead">Welcome</p>
                        <div>
                            Hey its Apparel Store
                            <DisplayItems/>
                        </div>
                </div>
            </body>
        )}
}
export default Home;