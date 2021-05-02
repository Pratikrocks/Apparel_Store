import React,{Component} from 'react';
import {signUp} from '../auth';
import { Link } from 'react-router-dom';
import "./Signup.css"



export default class Signup extends Component {
    constructor()
    {
        // {
        //     "username" : "admin",
        //     "email" : "admin@gmail.com",
        //     "password" : "admin",
        //     "gender" : "F",
            // "roles" : [
            //     "ROLE_ADMIN"
            // ],
            // "m_count" : 0,
            // "f_count" : 0,
            // "d_count" : 0
        // }
        super()
        this.state = {
            username:"",
            email:"",
            password:"",
            error:"",
            open:false,
            gender:"",
            roles : [
                "ROLE_CLIENT"
            ],
            m_count : 0,
            f_count : 0,
            d_count : 0
        }
    }
    clickSubmit = event =>{
        event.preventDefault();
        const {username,email,password, gender, roles, m_count, f_count, d_count} = this.state;
        const user = {
            username,
            email,
            password,
            gender,
            roles,
            m_count,
            f_count,
            d_count
        };
        signUp(user)
            .then(data=>{
                if(data === "success"){
                    console.log("GHYYYH")
                    this.setState({
                        error:"",
                        username:"",
                        password:"",
                        email:"",
                        open:true,
                        gender:""
                    })
                    
                }
            })
      
    }
    handleChange = (name) => (event) =>{
        this.setState({error:""})
        this.setState({[name]:event.target.value});
    }
    componentWillMount() {

    }
    render(){
        
        return (
            <div>
                {/* {console.log(this.state.captcha_key,this.state.captcha)} */}
                <div className="container" style={{height:1000
            }}>
                    <h2 className="mt-5 mb-5">Signup</h2>
                    <div className="alert alert-danger" style={{display:this.state.error ? "":"none"}}>
                        {this.state.error}
                    </div>
                    <div className="alert alert-info" style={{display:this.state.open ? "":"none"}}>
                        New Account is successfully created please <Link to="/signin">Signin</Link>
                    </div>
                    <form>
                        <div className="form-group">
                            <label className="font-weight-bold">User Name</label>
                            <input onChange={this.handleChange("username")} type="text" className="form-control" value={this.state.name}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Email</label>
                            <input onChange={this.handleChange("email")} type="email" className="form-control" value={this.state.email}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Password</label>
                            <input onChange={this.handleChange("password")} type="password" className="form-control" value={this.state.password}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Gender</label>
                            <input onChange={this.handleChange("gender")} type="text" className="form-control" value={this.state.gender}></input>
                        </div>
                        <br/>
                        <button onClick={this.clickSubmit} className="btn btn-raised btn-primary">
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}