import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Users} from "./users";
import {UserAuthService} from "./user-auth.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = "http://localhost:8080/resources/";

  requestHeader = new HttpHeaders(
    {"No-Auth": "True"}
  );


  constructor(private httpClient: HttpClient,
              private userAuthService: UserAuthService) {
  }

  getUsersList(): Observable<any> {
    return this.httpClient.get<any>(`${this.baseUrl}user`,
      {
        headers: this.requestHeader.append('Authorization',
          'Bearer ' + this.userAuthService.getToken())
      }
    );
  }

  createUser(user: Users): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}user`, user,
      {
        headers: this.requestHeader.append('Authorization',
          'Bearer ' + this.userAuthService.getToken())
      });
  }

  registrationNewUser(user: Users): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}auth/register`, user,
      {headers: this.requestHeader});
  }

  getUserByLogin(login: string | undefined): Observable<any> {
    return this.httpClient.get<any>(`${this.baseUrl}user/${login}`,
      {
        headers: this.requestHeader.append('Authorization',
          'Bearer ' + this.userAuthService.getToken())
      });
  }

  updateUser(login: string | undefined, user: Users): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}user/${login}`, user,
      {
        headers: this.requestHeader.append('Authorization',
          'Bearer ' + this.userAuthService.getToken())
      });
  }

  deleteUser(login: string | undefined): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}user/${login}`,
      {
        headers: this.requestHeader.append('Authorization',
          'Bearer ' + this.userAuthService.getToken())
      });
  }

  public login(loginData: any) {
    return this.httpClient.post(`${this.baseUrl}auth/login`, loginData, {
      headers: this.requestHeader,
    });
  }

  public forUser() {
    return this.httpClient.get(`${this.baseUrl}user/` + 'info', {responseType: 'text'});
  }

  public forAdmin() {
    return this.httpClient.get(`${this.baseUrl}user`, {responseType: 'text',});
  }

  public roleMatch(allowedRoles: string | any[]): boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();
    if (userRoles != null && userRoles) {
      for (let j = 0; j < allowedRoles.length; j++) {
        if (userRoles === allowedRoles[j]) {
          isMatch = true;
          return isMatch;
        } else {
          return isMatch;
        }
      }
    }
    return isMatch;
  }
}
