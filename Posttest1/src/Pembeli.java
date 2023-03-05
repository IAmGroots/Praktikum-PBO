import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Pembeli extends App {
  String nama, password, role;

  public Pembeli(String nama, String password, String role) {
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

  ArrayList<ProdukKeranjang> keranjang = new ArrayList<ProdukKeranjang>() {
    {
      add(new ProdukKeranjang("Kalkulator", 20000, 20, 2, 40000));
    };
  };

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

  void lihatKeranjang() throws IOException {
    clearScreen();
    System.out.println("");
    if (keranjang.size() == 0) {
      System.out.println("Keranjang masih Kosong");
      return;
    }

    System.out.println("|============================================|");
    System.out.printf("| %-3s|        %-12s| %6s |  %-6s |\n", "No", "Nama", "Jumlah", "Harga");
    System.out.println("|============================================|");
    int total = 0;
    int no = 0;
    for (int i = 0; i < keranjang.size(); i++) {
      String namaProduk = keranjang.get(i).getNama();
      int hargaProduk = keranjang.get(i).getHarga();
      int jumlahProduk = keranjang.get(i).getJumlah();
      int totalHarga = keranjang.get(i).getTotal();

      System.out.printf("| %-3d| %-19s| %4d   |  %-7d|\n", no + 1, namaProduk, jumlahProduk, hargaProduk);
      no++;
      total += totalHarga;
    }
    System.out.println("|============================================|");
    System.out.printf("| %32s | %-7d |\n", "Total Harga", total);
    System.out.println("|============================================|");
  }

  void pesanProduk() throws IOException {
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
      int nomorProduk = Integer.parseInt(br.readLine());
      if (nomorProduk <= 0 || nomorProduk > listProduk.size()) {
        System.out.println("");
        System.out.println(RED + "Nomor Produk tidak Tersedia" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        pesanProduk();
        return;
      }

      System.out.print("  Masukkan Jumlah Produk      >> ");
      int jumlahProduk = Integer.parseInt(br.readLine());
      if (jumlahProduk == 0) {
        System.out.println("");
        System.out.println(RED + "Minimal Jumlah Produk adalah 1" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        pesanProduk();
        return;
      }
      if (jumlahProduk > listProduk.get(nomorProduk - 1).getStock()) {
        System.out.println("");
        System.out.println(RED + "Jumlah Produk tidak Tersedia" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        pesanProduk();
        return;
      }

      String namaProduk = listProduk.get(nomorProduk - 1).getNama();
      int hargaProduk = listProduk.get(nomorProduk - 1).getHarga();
      int stockProduk = listProduk.get(nomorProduk - 1).getStock();
      int totalHarga = hargaProduk * jumlahProduk;

      ProdukKeranjang produk = null;
      for (ProdukKeranjang data : keranjang) {
        if (data.getNama().equals(namaProduk)) {
          int jumlahLama = data.getJumlah();
          int totalLama = data.getTotal();

          data.setJumlah(jumlahLama + jumlahProduk);
          data.setTotal(totalLama + totalHarga);

          produk = data;
        }
      }

      if (produk == null) {
        produk = new ProdukKeranjang(namaProduk, hargaProduk, stockProduk, jumlahProduk, totalHarga);
        keranjang.add(produk);
      }

      int stockLama = listProduk.get(nomorProduk - 1).getStock();
      int stockBaru = stockLama - jumlahProduk;
      listProduk.get(nomorProduk - 1).setStock(stockBaru);

      if (listProduk.get(nomorProduk - 1).getStock() == 0) {
        listProduk.remove(nomorProduk - 1);
      }

      System.out.println("");
      System.out.println(GREEN + "Produk Berhasil Dipesan" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      return;
    } catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println(RED + "Nomor dan Jumlah Produk harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      pesanProduk();
    }
  }

  void hapusProduk() throws IOException {
    try {
      if (keranjang.size() == 0) {
        clearScreen();
        System.out.println("");
        System.out.println("Keranjang masih Kosong");
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        return;
      }

      lihatKeranjang();
      System.out.println("");
      System.out.print("  Silahkan Pilih Nomor Produk >> ");
      int nomorProduk = Integer.parseInt(br.readLine());
      if (nomorProduk <= 0 || nomorProduk > keranjang.size()) {
        System.out.println("");
        System.out.println(RED + "Nomor Produk tidak Tersedia" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        hapusProduk();
        return;
      }

      System.out.print("  Masukkan Jumlah Produk      >> ");
      int jumlahProduk = Integer.parseInt(br.readLine());
      if (jumlahProduk < 0) {
        System.out.println("");
        System.out.println(RED + "Minimal Jumlah Produk adalah 0" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        hapusProduk();
        return;
      }
      if (jumlahProduk > keranjang.get(nomorProduk - 1).getJumlah()) {
        System.out.println("");
        System.out.println(RED + "Jumlah Produk tidak Tersedia" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        hapusProduk();
        return;
      }

      System.out.println("");
      System.out.print("Apakah Anda Yakin untuk menghapus Produk ? [Y/N] >> ");
      String konfirmasi = br.readLine();
      if (konfirmasi.equals("Y") || konfirmasi.equals("y") || konfirmasi.equals("Yes") || konfirmasi.equals("yes")) {
        int jumlahProdukLama = keranjang.get(nomorProduk - 1).getJumlah();
        int jumlahProdukBaru = jumlahProdukLama - jumlahProduk;
        int hargaProduk = keranjang.get(nomorProduk - 1).getHarga();
        int totalHargaBaru = jumlahProdukBaru * hargaProduk;

        Produk produk = null;
        for (Produk data : listProduk) {
          if (data.getNama().equals(keranjang.get(nomorProduk - 1).getNama())) {
            produk = data;
          }
        }

        if (produk != null) {
          int stockLama = produk.getStock();
          int stockBaru = stockLama + jumlahProduk;
          produk.setStock(stockBaru);
        } else {
          String nama = keranjang.get(nomorProduk - 1).getNama();
          int harga = keranjang.get(nomorProduk - 1).getHarga();
          int stock = jumlahProduk;

          Produk produkBaru = new Produk(nama, harga, stock);
          listProduk.add(produkBaru);
        }

        if (jumlahProdukBaru == 0) {
          keranjang.remove(nomorProduk - 1);
          System.out.println("");
          System.out.println(GREEN + "Produk Berhasil Dihapus" + NORMAL);
        } else {
          keranjang.get(nomorProduk - 1).setJumlah(jumlahProdukBaru);
          keranjang.get(nomorProduk - 1).setTotal(totalHargaBaru);
          System.out.println("");
          System.out.println(GREEN + "Perubahan Berhasil Disimpan" + NORMAL);
        }

        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
      } else if (konfirmasi.equals("N") || konfirmasi.equals("n") || konfirmasi.equals("No")
          || konfirmasi.equals("no")) {
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
      } else {
        System.out.println("");
        System.out.print("Jawaban tidak Valid");
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
      }

    } catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println(RED + "Nomor dan Jumlah Produk harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      hapusProduk();
    }
  }

  void pembayaran() throws IOException {
    try {
      if (keranjang.size() == 0) {
        clearScreen();
        System.out.println("");
        System.out.println("Tidak ada Tagihan Pembayaran");
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        return;
      }

      lihatKeranjang();
      System.out.println("");
      System.out.print("  Jumlah Uang Tunai >> ");
      int jumlahUang = Integer.parseInt(br.readLine());

      int totalHarga = 0;
      for (ProdukKeranjang data : keranjang) {
        totalHarga += data.getTotal();
      }

      if (jumlahUang < totalHarga) {
        System.out.println("");
        System.out.println(RED + "Mohon maaf Jumlah Uang Tunai tidak cukup" + NORMAL);
        System.out.println("");
        System.out.print("Silahkan tekan Enter untuk melanjutkan...");
        br.readLine();
        return;
      }

      for (int i = 0; i < keranjang.size(); i++) {
        keranjang.remove(i);
      }

      int kembalian = jumlahUang - totalHarga;
      System.out.println("          Kembalian >> " + kembalian);
      System.out.println("");
      System.out.println("|============================================|");
      System.out.println("|" + GREEN + "        Terima Kasih Telah Berbelanja       " + NORMAL + "|");
      System.out.println("|============================================|");
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
    } catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println(RED + "Jumlah Uang Tunai harus Angka" + NORMAL);
      System.out.println("");
      System.out.print("Silahkan tekan Enter untuk melanjutkan...");
      br.readLine();
      pembayaran();
    }
  }
}
