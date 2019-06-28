import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteOrganizerConfirmComponent } from './delete-organizer-confirm.component';

describe('DeleteOrganizerConfirmComponent', () => {
  let component: DeleteOrganizerConfirmComponent;
  let fixture: ComponentFixture<DeleteOrganizerConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteOrganizerConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteOrganizerConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
