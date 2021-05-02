import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';
import {isAuthenticated} from "../auth"
import axios from "axios"
import "../core/Home.css"

export default class DisplayItems extends Component {
    constructor(props) {
        super(props)
        this.state = {
            products: []
        }
    }

    componentDidMount() {
        axios.get("https://localhost:8443/services/files")
        .then(res => {
            console.log(res.data)
            this.setState({
                products: res.data
            })
        })
    }

    onSearch = (genre) => () => {
        axios.get("https://localhost:8443/services/products/" + genre)
        .then(res => {
            this.setState({
                products: res.data
            })
        })
    }

    displayAll = () => {
        axios.get("https://localhost:8443/services/files")
        .then(res => {
            console.log(res.data)
            this.setState({
                products: res.data
            })
        })
    }

    renderProducts = Products =>{
        
        return ( 
         <div className="row">
             {Products.map((product,i) => {
                 // <div className="card" style={{width:"22rem"}}>
                 const class_ = i%2 ? "light" : "dark"
                 return (
                 <div className={"card col-md-4 buldge_cards curve_borders " + (i % 2 ? "light" : "dark")}  key={i}>

                 <div className="card-body border-info">
                     <img className="img-thumbnail mb-3"  src={`${product.url}` } alt=""></img>
                     <h5 className="card-title">{product.name.slice(0,-4)}</h5>
                     <p className="card-text">{product.description.substring(0,100)}{" ..."} </p>
                 
                     <br/>
                        <p className="font-italic mark">
                            Price {" "}{product.price} {" "}
                        </p>
                 
                        <p className="font-italic mark">
                            Discount {" "}{product.discount}%!!{" "}
                        </p>

                        <p className="font-italic mark">
                            Genre {" "}{product.genre}{" "}
                        </p>

                        <p>
                            <Link href="#" class="btn btn-raised btn-primary" to={`/view/${product.id}`}>View Product</Link>
                        </p>
                     

                 </div>    
                 </div>
                 )
                 // </div>
                 })}
         </div>
         )
     
     };

    render() {
        return(
            <div className="background">
                <div className="container">
                <Button variant="success" size="lg" block onClick={this.displayAll}>
                    View All
                </Button>
                <Button variant="success" size="lg" block onClick={this.onSearch("M")}>
                    Men's Dress
                </Button>
                <Button variant="success" size="lg" block onClick={this.onSearch("F")}>
                    Women's Dress
                </Button>
                    <h2 className="mt-0 mb-5 buldge"> </h2>
                        {this.renderProducts(this.state.products)}
                </div>
            </div>
        )
    }
}
