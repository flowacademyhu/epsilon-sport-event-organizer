interface User {
  id: String;
  googleName: String;
  email: String;
  imageUrl: String;
  emailVerified: boolean;
  providerId: String;
  provider: String;
  companyName: String;
  accessToken: String;
  teams: Team[];
  teamLeaders: Team[];
}
