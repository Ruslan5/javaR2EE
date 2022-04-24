import {Component, OnInit} from '@angular/core';
import {Users} from "../users";
import {UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Role} from "../role";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  login: string | undefined;
  user: Users = new Users();
  roles: Role | undefined;

  userValid= new FormGroup({
    login: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required)
  });

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
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.login = this.route.snapshot.params['login'];

    this.userService.getUserByLogin(this.login).subscribe(data => {
      this.user = data;
    });
  }

  onSubmit() {
    this.userService.updateUser(this.login, this.user).subscribe( data =>{
        this.goToUsersList();
      });
  }

  private goToUsersList() {
    this.router.navigate(['/user']);
  }
  RoleToStr (role: Object) {
    role = Array.from(Object.values(role))[1];
    return  role;
  }
  RoleId (role: Object) {
    role = Array.from(Object.values(role))[0];
    return  role;
  }

}
