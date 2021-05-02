import React,{Component} from 'react';
import {Redirect} from 'react-router-dom'
import {signIn,authenticate} from '../auth';
import './Signup.css'

export default class Signin extends Component {
    constructor()
    {
        super()
        this.state = {
            username:"",
            password:"",
            error:"",
            redirectToRefer:false,
            loading:false,
            recaptcha:false,
        }
    }

    clickSubmit = event =>{
        event.preventDefault();
        this.setState({loading:true})
        const {username,password} = this.state;
        const user = {
            username,
            password
        };
        console.log(user)
        // if(this.state.recaptcha) {
        signIn(user)
        .then( data =>{
            if(data === "success"){
                console.log("GHYYYH")
                this.setState({
                    error:"",
                    redirectToRefer: true
                })
                
            }
        })
    //   }
    //   else {
    //       this.setState({error:"Please enter a correct Captcha"})
    //   }
    }
    handleChange = (name) => (event) =>{
        this.setState({error:""})
        this.setState({[name]:event.target.value});
    }
    render(){
        if(this.state.redirectToRefer)
        {
            return <Redirect to="/"/>
        }
        return (
            <div style={{height:1000
            }}>
                <div className="container">
                    <h2 className="mt-5 mb-5">Signin</h2>
                    <div className="alert alert-danger" style={{display:this.state.error ? "":"none"}}>
                        {this.state.error}
                    </div>
                    {this.state.loading?
                        <div className="jumbotron text-center">
                            <h2>Loading...</h2>
                        </div>:null}
                    <form>
                        <div className="form-group">
                            <label className="font-weight-bold">Username</label>
                            <input onChange={this.handleChange("username")} type="email" className="form-control" value={this.state.email}></input>
                        </div>
                        <div className="form-group">
                            <label className="font-weight-bold">Password</label>
                            <input onChange={this.handleChange("password")} type="password" className="form-control" value={this.state.password}></input>
                        </div>

                        <button onClick={this.clickSubmit} className="btn btn-raised btn-primary">
                            Submit
                        </button>
                    </form>
                    
                    
                </div>
            </div>
        )
    }
}