import React from 'react'
import { Switch, Route } from 'react-router'
import "./user/Signin.css"
import Home from "./core/Home"
import Menu from './core/Menu'
import AddProducts from './products/AddProducts'
import MyCart from './products/MyCart'
import ViewProduct from './products/ViewProduct'
import Signin from './user/Signin'
import Signup from './user/Signup'


export default function MainRouter() {
    return (
        <div className="background" style={{height:"100%"
        }}>
            <Menu/>
            <Switch>
            <Route exact path="/" component={Home}></Route>
            <Route exact path="/signup" component={Signup}></Route>
            <Route exact path="/signin" component={Signin}></Route>
            <Route exact path="/addProducts" component={AddProducts}></Route>
            <Route exact path="/view/:productId" component={ViewProduct}></Route>
            <Route exact paht="mycart" component={MyCart}/>
            </Switch>
        </div>
    )
}
