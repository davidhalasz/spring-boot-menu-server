export default class User {
  constructor(id, username, password, role, token) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.token = token;
  }
}
