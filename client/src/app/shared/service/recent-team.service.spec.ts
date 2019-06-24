import { TestBed } from '@angular/core/testing';

import { RecentTeamService } from './recent-team.service';

describe('RecentTeamService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecentTeamService = TestBed.get(RecentTeamService);
    expect(service).toBeTruthy();
  });
});
