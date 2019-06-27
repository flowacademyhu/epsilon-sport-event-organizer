import { TestBed } from '@angular/core/testing';

import { CupStateService } from './cup-state.service';

describe('CupStateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CupStateService = TestBed.get(CupStateService);
    expect(service).toBeTruthy();
  });
});
