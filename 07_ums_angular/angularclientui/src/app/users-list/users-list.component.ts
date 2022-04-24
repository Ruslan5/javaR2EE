import { Component, OnInit } from '@angular/core';
import {Users} from "../users";
import {UserService} from "../user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
})
export class UsersListComponent implements OnInit {
  users: Users[] = [];

  constructor(private userService: UserService,
              private router: Router) { }

  RoleToStr (role: Object) {
    role = Array.from(Object.values(role))[1];
    return  role;
  }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(){
    this.userService.getUsersList().subscribe(
      response => {
        this.users = Array.from(Object.values(response.body));
      });
  }

  updateUser(login:string | undefined) {
    this.router.navigate(['update-user', login]);
  }
  updateUserId(id: number) {
    this.router.navigate(['update-user', id]);
  }

  deleteUser(login: string | undefined) {
    this.userService.deleteUser(login).subscribe(data =>{
      this.getUsers();
    })
  }
}
