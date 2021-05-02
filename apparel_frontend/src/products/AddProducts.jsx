import React, { Component } from 'react'
import {Link} from "react-router-dom";
import axios from 'axios'
import { addProduct } from '../auth';

export default class AddProducts extends Component {
    constructor()
    {
        super()
        this.state = {
            image:"",
            description:"",
            genre:"",
            discount:"",
            price: "",
            error:"",
            open:false,

        }
    }

    clickSubmit = event =>{
        event.preventDefault();
        // const {image, description, genre, discount, price} = this.state;
        let bodyData = new FormData()
        bodyData.append("image", this.state.image)
        bodyData.append("description", this.state.description)
        bodyData.append("genre", this.state.genre)
        bodyData.append("discount", this.state.discount)
        bodyData.append("price", this.state.price)
        const token = localStorage.getItem("jwt")
        console.log(this.state)

        axios.get("https://localhost:8443/services/files")
        .then(res => {
            console.log(res)
        })


        axios.put("https://localhost:8443/users/addproduct", bodyData
        , {
            headers: {
                'Content-Type': `multipart/form-data; boundary=${bodyData._boundary}`,
                'Authorization': `Bearer ${token.slice(1, token.length-1)}`,
                'Access-Control-Allow-Origin': '*',
                // 'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT'
            }
        }
        )
        .then(res=> {
            console.log(res.data)
            this.setState({
                image: "",
                description:"",
                genre:"",
                discount:"",
                price: "",
                error:"",
            })
        })
        .catch(err => console.log(err))

       
      }

    handleChange = (name) => (event) =>{
        if(name !== "image") {
            this.setState({error:""})
            this.setState({[name]:event.target.value});
        } else {
            this.setState({
                image: event.target.files[0]
            })
        }
    }

    render() {
        return (
            <div>
                {/* {console.log(this.state.captcha_key,this.state.captcha)} */}
                <div className="container">
                    <h2 className="mt-5 mb-5">Add Products</h2>
                    <div className="alert alert-danger" style={{display:this.state.error ? "":"none"}}>
                        {this.state.error}
                    </div>
                    <div className="alert alert-info" style={{display:this.state.open ? "":"none"}}>
                        New Product is successfully added!! <Link to="/">Home</Link>
                    </div>
                    <form>
                        <div className="form-group">
                            <label className="font-weight-bold">Description</label>
                            <input onChange={this.handleChange("description")} type="text" className="form-control" value={this.state.name}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Genre</label>
                            <input onChange={this.handleChange("genre")} type="text" className="form-control" value={this.state.email}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Price</label>
                            <input onChange={this.handleChange("price")} type="number" className="form-control" value={this.state.password}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Discount</label>
                            <input onChange={this.handleChange("discount")} type="number" className="form-control" value={this.state.password}></input>
                        </div>
                        <input type="file" onChange={this.handleChange("image")} required></input>
                        
                        <br/>
                        <br></br>
                        <button onClick={this.clickSubmit} className="btn btn-raised btn-primary">
                            Submit
                        </button>
                    </form>
                    {/* {console.log(this.captcha)} */}
                </div>
            </div>
        )
    }
}
