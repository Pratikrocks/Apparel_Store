import React from 'react';
import {Link,withRouter} from 'react-router-dom';
import {isAuthenticated, signout} from '../auth'
const isActive = (history,path)=>{
    // console.log(path)
    // console.log(history.location.pathname)
    if(history.location.pathname===path)
    {
        return {color:"#ff9900"}
    } else {
        return {color : "white"}
    }
   
}
// background-color: #A9C9FF;
// background-image: linear-gradient(180deg, #A9C9FF 0%, #FFBBEC 100%);


const Menu = ({history}) =>(
    <div>
       
        {!isAuthenticated() && (
            <ul class="nav nav-tabs " style={{background:"purple" }}>
                <div style={{flex:"1"}}>
                </div>              
                <li class="nav-item active">
                    <Link className="nav-link" style={isActive(history,"/")} to="/">Home</Link>
                </li>
                <li class="nav-item">
                    <Link className="nav-link" style={isActive(history,"/signin")} to="/signin">Signin</Link>
                </li>

                <li class="nav-item">
                    <Link className="nav-link" style={isActive(history,"/signup")} to="/signup">Signup</Link>  

                </li>
                       
                 
            </ul>        
    )}
        {isAuthenticated() && (
            <ul className="nav nav-tabs" style={{background:"purple"}}>
            <div style={{flex:"1"}}>
            </div>         
            <li class = "nav-item">
                    
                    <Link className="nav-link"
                        
                        style={isActive(history,"/")}                
                        to={'/'}
                        >
                            Home
                    </Link>  
              
            </li>        
{/* 
            <li class="nav-item">
                    <Link className="nav-link" 
                        style={isActive(history,"/users")} 
                        to="/users">
                            Users
                    </Link>
            </li>
            <li className="nav-item ">
                    <Link
                        className="nav-link"
                        style={isActive(history,`/findpeople`)}
                        to={`/findpeople/`}
                    >
                        Find People
                    </Link>  
            </li>  
            <li className="nav-item ">
                    <Link
                        className="nav-link"
                        style={isActive(history,`/create/post/${isAuthenticated().sub}`)}
                        to={`/create/post/${isAuthenticated().sub}`}
                        >
                            Create Post
                    </Link>  
            </li>  */}
            {/* <li className="nav-item ">
                    <Link
                        className="nav-link"
                        style={isActive(history,`/posts/`)}
                        to={`/posts/`}
                        >
                            Posts
                    </Link>  
            </li>    */}

            {
            isAuthenticated().auth[0].authority === "ROLE_ADMIN" ?
            <li className="nav-item ">
                    <Link
                        className="nav-link"
                        style={isActive(history,`/addProducts`)}
                        to={`/addProducts`}
                        >
                            ADD GARMENTS
                    </Link>  
            </li>   
            : null
            }

            <li className="nav-item ">
                    <Link
                        className="nav-link"
                        style={isActive(history,`/user/${isAuthenticated().sub}`)}
                        to={`/user/${isAuthenticated().sub}`}
                        >
                            Welcome  {isAuthenticated()["sub"]}!
                    </Link>  
            </li>  

            <li class = "navbar-item">
                    
                    <Link className="nav-link"
                        
                        style={isActive(history,"/"),{cursor:"pointer",color:"#fff"}}                
                        to={`/mycart`}
                        >
                            MyCart
                    </Link>  
              
            </li>

            <li class = "navbar-item">
                    
                    <Link className="nav-link"
                        
                        style={isActive(history,"/"),{cursor:"pointer",color:"#fff"}}                
                        onClick={()=>signout(()=>history.push('/'))}
                        >
                            Signout
                    </Link>  
              
            </li>

            

            </ul>
        )}
     
            
        
    </div>
)
export default withRouter(Menu);

