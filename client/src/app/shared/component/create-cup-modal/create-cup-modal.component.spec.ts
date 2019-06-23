import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCupModalComponent } from './create-cup-modal.component';

describe('CreateCupModalComponent', () => {
  let component: CreateCupModalComponent;
  let fixture: ComponentFixture<CreateCupModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCupModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCupModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
