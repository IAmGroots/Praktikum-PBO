public class Produk {
  String nama;
  int harga, stock;

  public Produk(String nama, int harga, int stock) {
    this.nama = nama;
    this.harga = harga;
    this.stock = stock;
  }

  void setNama(String namaBaru) {
    this.nama = namaBaru;
  }

  String getNama() {
    return nama;
  }

  void setHarga(int hargaBaru) {
    this.harga = hargaBaru;
  }

  int getHarga() {
    return harga;
  }

  void setStock(int stockBaru) {
    this.stock = stockBaru;
  }

  int getStock() {
    return stock;
  }
}
