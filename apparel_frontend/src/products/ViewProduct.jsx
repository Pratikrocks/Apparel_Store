import axios from 'axios'
import React, { Component } from 'react'
import { Link ,Redirect} from 'react-router-dom';
import {isAuthenticated} from '../auth'

export default class  extends Component {
    constructor(props) {
        super(props)
        this.state = {
            product: [],
            loading: true
        }
    }

    AddToCart = (event) => {
        event.preventDefault()
        const token = localStorage.getItem("jwt")
        const productURI = this.props.match.params.productId
        axios.post(`https://localhost:8443/services/cart/${productURI}`
        , {} ,{
            headers: {
                'Authorization': `Bearer ${token.slice(1, token.length-1)}`,
            }
        }
        )
        .then(res=> {
            console.log(res.data)
            this.props.history.push("/mycart")
        })
        .catch(err => console.log(err))
    }

    componentDidMount() {
        axios.get(`https://localhost:8443/services/view/product/${this.props.match.params.productId}`)
        .then(response => {
            console.log(response.data)
            this.setState({
                product: response.data,
                loading: false
            })
        })
    }

    render() {
        const {product} = this.state
        return (
            <div className="container">
                {
                this.state.loading?
                        <div className="jumbotron text-center">
                            <h2>Loading...</h2>
                        </div>:
            <div className="container" stye={{position:"relative"}}>
            <h2 className="display-2 mt-2 mb-3">{ product.name.slice(0, -4) }</h2>
                     
              <div className="row">
                            <div className="card-body border-info">
                    <img 
                        className="img-thumbnail mb-3"  
                        src={`${product.url}` } 
                        alt=""
                        style={{height:"500px", width:"90%"}}
                        ></img>
                      {/* <h5 className="card-title">{title}</h5> */}
                            <p className="card-text">{product.description}{" ..."} </p>
                      <br/>
                        <p className="font-italic mark">
                            Price {" "} {product.price} {" "}
                        </p>

                        <p className="font-italic mark">
                            Discount {" "} {product.discount}%{" "}
                        </p>

                      <p>
                           <Link href="#" class="btn btn-raised btn-primary mr-5" to={`/`}>Back to Post</Link>
                           {"                 "}{"    "}
                            {isAuthenticated()  ? 
                            <>
                           <Link href="#" class="btn btn-raised btn-info mr-5" >
                                <span onClick={this.AddToCart}>
                                    Add To Cart    
                                </span>    
                            </Link>
                           
                           </> : null }
                   
                      </p>

                </div>    
                </div>
                
            </div>

            
                }
             
        </div>
        )




    }
}
