public class ProdukKeranjang extends Produk {
  int jumlah, total;

  public ProdukKeranjang(String nama, int harga, int stock, int jumlah, int total) {
    super(nama, harga, stock);
    this.jumlah = jumlah;
    this.total = total;
  }

  void setJumlah(int jumlahBaru) {
    this.jumlah = jumlahBaru;
  }

  int getJumlah() {
    return this.jumlah;
  }

  void setTotal(int totalBaru) {
    this.total = totalBaru;
  }

  int getTotal() {
    return this.total;
  }
}
