import React, {useState, useEffect} from "react";
import {Switch, Route, Link, Redirect} from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/auth.service";
import Login from "./components/Login";
import BoardUser from "./components/BoardUser";
import ListUsersComponent from "./components/ListUsersComponent";
import CreateUserComponent from "./components/CreateUserComponent";
import UpdateUserComponent from "./components/UpdateUserComponent";
import Register from "./components/Register";

const App = () => {
    const [showUserBoard, setShowUserBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);
    const user = AuthService.getCurrentUser();

    useEffect(() => {
        if (user) {
            setCurrentUser(user);
            setShowUserBoard(JSON.parse(window.atob(user.split('.')[1])).roles.includes("user"));
            setShowAdminBoard(JSON.parse(window.atob(user.split('.')[1])).roles.includes("admin"));
        }
    }, []);
    const logOut = () => {
        AuthService.logout();
    };
    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark" style={{background: 'tomato'}}>
                <div className="navbar-nav mr-auto">
                    {showUserBoard && (
                        <li className="nav-item">
                            <Link to={"/mod"} className="nav-link">
                                User Board
                            </Link>
                        </li>
                    )}
                    {showAdminBoard && (
                        <li className="nav-item">
                            <Link to={"/user"} className="nav-link">
                                Admin Board
                            </Link>
                        </li>
                    )}
                </div>
                {currentUser ? (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={logOut}>
                                LogOut
                            </a>
                        </li>
                    </div>
                ) : (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link">
                                Login
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to={"/register"} className="nav-link">
                                Sign Up
                            </Link>
                        </li>
                    </div>
                )}
            </nav>
            <div className="container mt-3">
                <Switch>
                    <Route exact path={["/", "/login"]} component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    <Route exact path="/enter" render={() => (
                        (AuthService.getCurrentRole().includes("admin")) ? (
                            <Redirect to="/user"/>
                        ) : (
                            <Redirect to="/mod"/>
                        )
                    )}/>
                    <Route path="/mod" component={BoardUser}/>
                    <Route path="/user" component={ListUsersComponent}/>
                    <Route path="/add-user" component={CreateUserComponent}/>
                    <Route path="/update-user/:login" component={UpdateUserComponent}/>
                </Switch>
            </div>
        </div>
    );
};
export default App;