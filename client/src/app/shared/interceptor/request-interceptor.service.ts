import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';

@Injectable()
export class RequestInterceptorService implements HttpInterceptor {

  constructor(private _snackBar: MatSnackBar,
    private translate: TranslateService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

     if (localStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });
    }
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400 || error.status === 404 || error.status === 403) {
          this.translate.get(error.error).subscribe((translatedValue) => {
            this._snackBar.open(translatedValue);
          });
        }
        return throwError(error);
      })
    );
  }
}
