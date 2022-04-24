import { Component, OnInit } from '@angular/core';
import {UserAuthService} from "../user-auth.service";
import {Router} from "@angular/router";
import {Users} from "../users";
import {UserService} from "../user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userAuthService: UserAuthService,
              public userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  logout() {
    this.userAuthService.clear();
    this.router.navigate(['/login']);
  }
}
