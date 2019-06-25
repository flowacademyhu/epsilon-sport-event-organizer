/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Match } from './match';
import { Team } from './team';
import { User } from './user';


export interface Cup { 
    approved?: Array<Team>;
    company?: string;
    courtCounter?: number;
    deleted?: boolean;
    description?: string;
    eventDate?: string;
    imageUrl?: string;
    matches?: Array<Match>;
    name?: string;
    organizers?: Array<User>;
    place?: string;
    registrationEndDate?: string;
    teams?: Array<Team>;
}
