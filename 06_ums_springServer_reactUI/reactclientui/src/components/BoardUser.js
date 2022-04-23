import React from "react";
import AuthService from "../services/auth.service";
const BoardUser = () => {
    const currentUser = AuthService.getCurrentUser();
    const logOut = () => {
        AuthService.logout();
    };
    return (
        <div className="container">
            <header className="jumbotron">
                <h3>
                    <strong>Hello, {currentUser.username} !</strong>
                    <p>for LogOut, click  <a href="/login" className="nav-link" onClick={logOut}> Here </a></p>
                </h3>
            </header>
        </div>
    );
};
export default BoardUser;