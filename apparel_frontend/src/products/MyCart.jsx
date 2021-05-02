import React, { Component } from 'react'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import "./AddProducts.css"
import Col from 'react-bootstrap/Col';
import axios from "axios"

export default class MyCart extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cart: []
        }
    }

    componentDidMount() {
        const token = localStorage.getItem("jwt")
        axios.get(`https://localhost:8443/services/mycart`
        , {
            headers: {
                'Authorization': `Bearer ${token.slice(1, token.length-1)}`,
            }
        }
        )
        .then(res=> {
            console.log(res.data)
            this.setState({
                cart: res.data
            })
            this.state.cart.map((item, i) => {
                console.log(item.description)
            })
        })
        .catch(err => console.log(err))
    }

    render() {
        return (
            <div className="container">
                <h1>ITEMS PURCHASED</h1>
                {
                    this.state.cart.map((product, i) => {
                        return(
                        
                        <div className="row add-space" style={{ background: i % 2 == 0 ? "#e28743" : "#2a9ec5" }}>
                            <img 
                                className="img-thumbnail mb-3"  
                                src={`${product.url}` } 
                                alt=""
                                style={{height:"300px", width:"100%"}}
                            />
                            <Col>{product.description}</Col>
                            <Col>{product.name.slice(0,-4)}</Col>

                        </div>
                        
                        
                        )
                    })
                }
                
            </div>
        )
    }
}
