import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteMemberConfirmComponent } from './delete-member-confirm.component';

describe('DeleteMemberConfirmComponent', () => {
  let component: DeleteMemberConfirmComponent;
  let fixture: ComponentFixture<DeleteMemberConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteMemberConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteMemberConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
