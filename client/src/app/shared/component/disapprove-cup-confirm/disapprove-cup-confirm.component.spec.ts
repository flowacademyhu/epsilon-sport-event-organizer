import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisapproveCupConfirmComponent } from './disapprove-cup-confirm.component';

describe('DisapproveCupConfirmComponent', () => {
  let component: DisapproveCupConfirmComponent;
  let fixture: ComponentFixture<DisapproveCupConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisapproveCupConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisapproveCupConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
