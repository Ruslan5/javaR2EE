import { Component, OnInit } from '@angular/core';
import {Users} from "../users";
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {Validators} from "@angular/forms";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  user: Users = new Users();
  userValid= new FormGroup({
    login: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
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
              private router: Router) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.userService.createUser(this.user).subscribe(data => {
        this.goToUsersList();
    });
  }

  goToUsersList(){
    this.router.navigate(['/user']);
  }

  onSubmit() {
    this.saveUser();

  }
}
