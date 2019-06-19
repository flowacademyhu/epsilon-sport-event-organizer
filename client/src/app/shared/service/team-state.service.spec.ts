import { TestBed } from '@angular/core/testing';

import { TeamStateService } from './team-state.service';

describe('TeamStateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeamStateService = TestBed.get(TeamStateService);
    expect(service).toBeTruthy();
  });
});
