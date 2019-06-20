export * from './authController.service';
import { AuthControllerService } from './authController.service';
export * from './cupController.service';
import { CupControllerService } from './cupController.service';
export * from './teamController.service';
import { TeamControllerService } from './teamController.service';
export * from './userController.service';
import { UserControllerService } from './userController.service';
export const APIS = [AuthControllerService, CupControllerService, TeamControllerService, UserControllerService];
