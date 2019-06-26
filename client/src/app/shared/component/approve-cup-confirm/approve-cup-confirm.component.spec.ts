import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveCupConfirmComponent } from './approve-cup-confirm.component';

describe('ApproveCupConfirmComponent', () => {
  let component: ApproveCupConfirmComponent;
  let fixture: ComponentFixture<ApproveCupConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveCupConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveCupConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
