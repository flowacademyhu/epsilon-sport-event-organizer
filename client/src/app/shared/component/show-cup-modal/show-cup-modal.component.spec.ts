import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCupModalComponent } from './show-cup-modal.component';

describe('ShowCupModalComponent', () => {
  let component: ShowCupModalComponent;
  let fixture: ComponentFixture<ShowCupModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCupModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCupModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
