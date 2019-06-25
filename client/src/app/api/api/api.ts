export * from './authController.service';
import { AuthControllerService } from './authController.service';
export * from './cupResource.service';
import { CupResourceService } from './cupResource.service';
export * from './matchResource.service';
import { MatchResourceService } from './matchResource.service';
export * from './teamResource.service';
import { TeamResourceService } from './teamResource.service';
export * from './userResource.service';
import { UserResourceService } from './userResource.service';
export const APIS = [AuthControllerService, CupResourceService, MatchResourceService, TeamResourceService, UserResourceService];
