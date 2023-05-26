import { Component, OnInit } from '@angular/core';
import { UserService } from '../core/services/user.service';
import { UserAuthService } from '../core/services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router,
    ){}

  ngOnInit(): void {
  }

  login(loginForm: any){
    let data = {
      userName: loginForm.value.userName,
      userPassword: loginForm.value.userPassword
    }    
    this.userService.login(data).subscribe({
      next: (data:any) => {
        this.userAuthService.setToken(data.jwtToken);
        this.userAuthService.setRoles(data.user.role);

        const role = data.user.role[0].roleName;
        if(role == 'Admin'){
          this.router.navigate(['/admin']);
        }        
        else if(role == 'User'){
          this.router.navigate(['/user']);
        }
        else() => {
          this.router.navigate(['/error']);
        }
      },
      error: (err) => {
        console.log(err);        
      },
      complete: () => console.info('complete')
    }); 
    
  }
}
