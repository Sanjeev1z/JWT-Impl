import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from 'src/enviroments/enviroment';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthService
    ) { }

  private readonly API_URL = enviroment.api; 

  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  )

  public login(loginData: any) {
    const url = this.getUrl(this.API_URL.createJwtToken);
    return this.httpClient.post(url,loginData,{headers: this.requestHeader});
  }

  private getUrl(suffix: string): string {
    return this.API_URL.baseURL + suffix;
  }

  public roleMatch(allowedRoles: any) {
    console.log(allowedRoles);
    
    let isMatch = false;
    const userRoles = this.userAuthService.getRoles();

    for (let i = 0; i < userRoles.length; i++) {
      if (allowedRoles.includes(userRoles[i].roleName)) {
        isMatch = true;
        return isMatch;
      }
    } 
    return isMatch;

  }
}