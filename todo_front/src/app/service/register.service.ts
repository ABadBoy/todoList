import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { NotificationService } from './notification.service';

const baseUrl = 'http://www.leyiwangyou.top:8081/';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {


  constructor(private http: HttpClient,private notificationService: NotificationService) { }



  public register(user: any):Observable<any> {
    return this.http.post<any>(baseUrl + 'sign-up', user).pipe(
      retry(1)    );
  }

  public login(user: any):Observable<any> {
    return this.http.post<any>(baseUrl + 'sign-in', user).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  handleError(error:any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    this.notificationService.warn(errorMessage);
    console.log(errorMessage);
    return throwError(() => {
        return errorMessage;
    });
  }
}
