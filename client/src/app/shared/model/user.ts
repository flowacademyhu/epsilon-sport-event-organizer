interface User {
  id: String;
  googleName: String;
  email: String;
  imageUrl: String;
  emailVerified: boolean;
  providerId: String;
  provider: String;
  companyName: String;
  accesToken: String;
  teams: Team[];
  teamLeaders: Team[];
}
