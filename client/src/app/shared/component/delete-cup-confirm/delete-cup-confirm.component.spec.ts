import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteCupConfirmComponent } from './delete-cup-confirm.component';

describe('DeleteCupConfirmComponent', () => {
  let component: DeleteCupConfirmComponent;
  let fixture: ComponentFixture<DeleteCupConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteCupConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteCupConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
