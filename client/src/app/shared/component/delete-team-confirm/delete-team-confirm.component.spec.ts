import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTeamConfirmComponent } from './delete-team-confirm.component';

describe('DeleteTeamConfirmComponent', () => {
  let component: DeleteTeamConfirmComponent;
  let fixture: ComponentFixture<DeleteTeamConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteTeamConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTeamConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
