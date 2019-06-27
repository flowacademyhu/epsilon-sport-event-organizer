import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyCupModalComponent } from './apply-cup-modal.component';

describe('ApplyCupModalComponent', () => {
  let component: ApplyCupModalComponent;
  let fixture: ComponentFixture<ApplyCupModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplyCupModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplyCupModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
