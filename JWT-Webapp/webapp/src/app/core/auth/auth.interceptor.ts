import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, catchError, throwError } from "rxjs";
import { UserAuthService } from "../services/user-auth.service";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(
        private userAuthService: UserAuthService,
        private router: Router
    ){ }


    intercept(req: HttpRequest<any>, next: HttpHandler ): Observable<HttpEvent<any>> {
        
        
        if(req.headers.get("No-Auth") === 'True') {
            return next.handle(req.clone());
        }
        
        const token = this.userAuthService.getToken();
        this.addToken(req, token);

        return next.handle(req).pipe(
            catchError(
                (error:HttpErrorResponse) => {
                    console.log(error.status);
                    if(error.status === 401) {
                        this.router.navigate(['/login']);
                    } else if(error.status === 403) {
                        this.router.navigate(['/error']);
                    }
                    return throwError(() => error);
                }
            )
        );


    }    

    private addToken(request: HttpRequest<any>, token: any){
        return request.clone({
            setHeaders: {
                Authorization : `Bearer ${token}`
            }
        });
    }
}