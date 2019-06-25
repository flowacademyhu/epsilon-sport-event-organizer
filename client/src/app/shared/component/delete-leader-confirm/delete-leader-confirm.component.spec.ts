import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteLeaderConfirmComponent } from './delete-leader-confirm.component';

describe('DeleteLeaderConfirmComponent', () => {
  let component: DeleteLeaderConfirmComponent;
  let fixture: ComponentFixture<DeleteLeaderConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteLeaderConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteLeaderConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
