import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Injectable({
 providedIn: 'root'
})
export class GetService {
  param1: any;
 constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

 public user() {
  this.route.queryParams.subscribe(params => {
    this.param1 = params['token'];
  let headers: HttpHeaders = new HttpHeaders();
  headers = headers.append('Authorization', this.param1);
  });
  return this.httpClient.get('http://localhost:8080/auth/get-user');
}
}