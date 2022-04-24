import { Component, OnInit } from '@angular/core';
import {Users} from "../users";
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {UserAuthService} from "../user-auth.service";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {
  user: Users = new Users();

  constructor(private userService: UserService,
              private userAuthService: UserAuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.userAuthService.clear();
    this.router.navigate(['/login']);
  }
}
