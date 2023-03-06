import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Admin extends App {
  String nama, password, role;

  public Admin(String nama, String password, String role){
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

  InputStreamReader isr = new InputStreamReader(System.in);
  BufferedReader br = new BufferedReader(isr);

  static String NORMAL = "\u001b[0m";
  static String RED = "\u001b[31m";
  static String GREEN = "\u001b[32m";

  void lihatProduk() throws IOException {
    clearScreen();
    System.out.println("");
    if (listProduk.size() == 0) {
      System.out.println("Tidak Ada Data Produk");
      return;
    }
    System.out.println("|=========================================|");
    System.out.printf("| %-3s|        %-12s| %-6s| %5s |\n", "No", "Nama", "Harga", "Stock");
    System.out.println("|=========================================|");
    int no = 0;
    for (int i = 0; i < listProduk.size(); i++) {
      if (listProduk.get(i).getStock() > 0) {
        System.out.printf("| %-3d| %-19s| %-6d|  %-4d |\n", no + 1, listProduk.get(i).getNama(),
            listProduk.get(i).getHarga(), listProduk.get(i).getStock());
        no++;
      }
    }
    System.out.println("|=========================================|");
  }

  void tambahProduk() throws IOException {
    clearScreen();
    try {
      System.out.println("|=========================|");
      System.out.println("|      Tambah Produk      |");
      System.out.println("|=========================|");
      System.out.print("  Nama  >> ");
      String namaProduk = br.readLine();
      System.out.print("  Harga >> ");
      int hargaProduk = Integer.parseInt(br.readLine());
      System.out.print("  Stock >> ");
      int stockProduk = Integer.parseInt(br.readLine());
      System.out.println("|=========================|");

      for (Produk data : listProduk){
        if (data.getNama().equals(namaProduk)){
          System.out.println("");
          System.out.println("Produk sudah Tersedia");
          System.out.println("");
          System.out.print("Silahkan tekan Enter untuk melanjutkan...");
          br.readLine();
          return;
        }
      }

      Produk produk = new Produk(namaProduk, hargaProduk, stockProduk);
      listProduk.add(produk);

      System.out.println("");
      System.out.println(GREEN + "Data Produk Berhasil Ditambah" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
    } catch (NumberFormatException nfe) {
      System.out.println("|=========================|");
      System.out.println("");
      System.out.println(RED + "Harga dan Stock harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      tambahProduk();
    }
  }

  void ubahProduk() throws IOException {
    try {
      if (listProduk.size() == 0) {
        clearScreen();
        System.out.println("");
        System.out.println("Tidak Ada Data Produk");
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        return;
      }

      lihatProduk();
      System.out.print("  Silahkan Pilih Nomor Produk >> ");
      int pilihan = Integer.parseInt(br.readLine());

      if (pilihan > 0 && pilihan <= listProduk.size()) {
        System.out.println("|=========================================|");
        System.out.print("  Nama Baru  >> ");
        String namaBaru = br.readLine();
        System.out.print("  Harga Baru >> ");
        int hargaBaru = Integer.parseInt(br.readLine());
        System.out.print("  Stock Baru >> ");
        int stockBaru = Integer.parseInt(br.readLine());
        System.out.println("|=========================================|");

        listProduk.get(pilihan - 1).setNama(namaBaru);
        listProduk.get(pilihan - 1).setHarga(hargaBaru);
        listProduk.get(pilihan - 1).setStock(stockBaru);

        System.out.println("");
        System.out.println(GREEN + "Data Produk Berhasil Diubah" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
      } else {
        System.out.println("");
        System.out.println(RED + "Nomor Produk harus sesuai dengan Tabel diatas" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        ubahProduk();
      }
    } catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println(RED + "Nomor Produk harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      ubahProduk();
    }
  }

  void hapusProduk() throws IOException {
    try {
      if (listProduk.size() == 0) {
        clearScreen();
        System.out.println("");
        System.out.println("Tidak Ada Data Produk");
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        return;
      }

      lihatProduk();
      System.out.println("");
      System.out.print("  Silahkan Pilih Nomor Produk >> ");
      int pilihan = Integer.parseInt(br.readLine());

      if (pilihan > 0 && pilihan <= listProduk.size()) {
        listProduk.remove(pilihan - 1);

        System.out.println("");
        System.out.println(GREEN + "Data Produk Berhasil Dihapus" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
      } else {
        System.out.println("");
        System.out.println(RED + "Nomor Produk harus sesuai dengan Tabel diatas" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        hapusProduk();
      }
    } catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println(RED + "Nomor Produk harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      hapusProduk();
    }
  }
}
