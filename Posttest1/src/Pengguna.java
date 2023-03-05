public class Pengguna {
  String nama, password, role;
  public Pengguna(String nama, String password, String role) {
    this.nama = nama;
    this.password = password;
    this.role = role;
  }

  String getNama() {
    return this.nama;
  }

  String getPassword() {
    return this.password;
  }

  String getRole() {
    return this.role;
  }
}
