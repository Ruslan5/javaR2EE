import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {Users} from "../users";
import {UserAuthService} from "../user-auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: Users = new Users();
  userValid= new FormGroup({
    login: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    role: new FormControl('', Validators.minLength(1)),
  });
  get login(){
    return this.userValid.get('login');
  }
  get password(){
    return this.userValid.get('password');
  }
  get email(){
    return this.userValid.get('email');
  }
  get firstName(){
    return this.userValid.get('firstName');
  }
  get lastName(){
    return this.userValid.get('lastName');
  }
  get birthday(){
    return this.userValid.get('birthday');
  }
  get role(){
    return this.userValid.get('role');
  }
  constructor(private userService: UserService,
              private userAuthService: UserAuthService,
              private router: Router) { }
  registrationUser(){
    this.userService.registrationNewUser(this.user).subscribe(data => {
        this.goToLogin();
      });
  }
  ngOnInit(): void {
  }

  onSubmit() {
    this.registrationUser();
  }

  private goToLogin() {
    this.router.navigate(['/login']);

  }
}
