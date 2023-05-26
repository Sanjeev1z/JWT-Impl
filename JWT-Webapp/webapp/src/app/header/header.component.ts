import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../core/services/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from '../core/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  ngOnInit(): void { }

  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    private userService: UserService
  ){}

  public isLoggedIn(){
    console.log(this.userAuthService.isLoggedIn());    
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/home']);
  }

  public roleMatch(role: any){
    return this.userService.roleMatch(role);
  }

}
