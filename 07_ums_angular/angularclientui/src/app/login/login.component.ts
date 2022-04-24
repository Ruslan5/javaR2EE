import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {UserAuthService} from "../user-auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
              private userAuthService: UserAuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        const roleUser = response.roles;
        this.userAuthService.setRoles(response.roles)
        this.userAuthService.setToken(response.token)
        if (roleUser === "admin") {
          this.router.navigate(['user']);
        } else {
          this.router.navigate(['info']);

        }
      }
    )
  }
}
