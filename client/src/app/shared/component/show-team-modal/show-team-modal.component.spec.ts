import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTeamModalComponent } from './show-team-modal.component';

describe('ShowTeamModalComponent', () => {
  let component: ShowTeamModalComponent;
  let fixture: ComponentFixture<ShowTeamModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowTeamModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowTeamModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
