import { Injectable } from '@angular/core';
import { Cup, CupResourceService } from 'src/app/api';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CupStateService {

  constructor(
    private cupService: CupResourceService
  ) { }

  private readonly _cups = new BehaviorSubject<Cup[]>([]);

  readonly cups$ = this._cups.asObservable();

  get cups(): Cup[] {
    return this._cups.getValue();
  }

  set cups(val: Cup[]) {
    this._cups.next(val);
  }

  getCups() {
    return this.cupService.getAllCupsUsingGET().subscribe(
      cups => {
        this.cups = cups;
      }
    );
  }
}
