import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class App {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);

	static ArrayList<Pengguna> listPengguna = new ArrayList<Pengguna>() {
		{
			add(new Pengguna("asep", "123", "pembeli"));
			add(new Pengguna("admin", "admin", "admin"));
		}
	};

	static ArrayList<Produk> listProduk = new ArrayList<>() {
    {
      add(new Produk("Kalkulator", 20000, 20));
      add(new Produk("Pensil", 5000, 10));
      add(new Produk("Stabilo", 10000, 15));
      add(new Produk("Selotip", 15000, 30));
    };
  };

	static String NORMAL = "\u001b[0m";
	static String RED = "\u001b[31m";
	static String GREEN = "\u001b[32m";

	static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	static void menuPembeli(Pembeli pembeli) throws IOException {
		// Pembeli pembeli = new Pembeli(name, pass, role);
		// Pembeli pembeli = new Pembeli();
		boolean loop = true;
		while (loop) {
			try {
				clearScreen();
				System.out.println("========================");
				System.out.println(" Selamat Datang " + pembeli.getNama());
				System.out.println("========================");
				System.out.println(" [1] Daftar Produk");
				System.out.println(" [2] Keranjang Belanja");
				System.out.println(" [3] Pesan Produk");
				System.out.println(" [4] Hapus Produk");
				System.out.println(" [5] Pembayaran");
				System.out.println(" [6] Keluar");
				System.out.println("========================");
				System.out.print("Silahkan Pilih >> ");
				int pilihan = Integer.parseInt(br.readLine());

				if (pilihan == 1) {
					pembeli.lihatProduk();
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
				} else if (pilihan == 2) {
					pembeli.lihatKeranjang();
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
				} else if (pilihan == 3) {
					pembeli.pesanProduk();
				} else if (pilihan == 4) {
					pembeli.hapusProduk();
				} else if (pilihan == 5) {
					pembeli.pembayaran();
				} else if (pilihan == 6) {
					System.out.println("");
					System.out.println(GREEN + "Logout Berhasil" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
					loop = false;
				} else {
					System.out.println("");
					System.out.println(RED + "Pilihan tidak Tersedia" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
					menuPembeli(pembeli);
					return;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("");
				System.out.println(RED + "Pilihan harus Angka" + NORMAL);
				System.out.println("");
				System.out.print("Silahkan tekan Enter untuk melanjutkan...");
				br.readLine();
				menuPembeli(pembeli);
				return;
			}
		}
	}

	static void menuAdmin(Admin admin) throws IOException {
		// Admin admin = new Admin(name, pass, role);
		boolean loop = true;
		while (loop){
			try {
				clearScreen();
				System.out.println("========================");
				System.out.println(" Selamat Datang " + admin.getNama());
				System.out.println("========================");
				System.out.println(" [1] Daftar Produk");
				System.out.println(" [2] Tambah Produk ");
				System.out.println(" [3] Ubah Produk");
				System.out.println(" [4] Hapus Produk");
				System.out.println(" [5] Keluar");
				System.out.println("========================");
				System.out.print("Silahkan Pilih >> ");
				int pilihan = Integer.parseInt(br.readLine());
				if (pilihan == 1) {
					admin.lihatProduk();
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
				} else if (pilihan == 2) {
					admin.tambahProduk();
				} else if (pilihan == 3) {
					admin.ubahProduk();
				} else if (pilihan == 4) {
					admin.hapusProduk();
				} else if (pilihan == 5) {
					System.out.println("");
					System.out.println(GREEN + "Logout Berhasil" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
					loop = false;
				}
				else {
					System.out.println("");
					System.out.println(RED + "Pilihan tidak Tersedia" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();
					menuAdmin(admin);
					return;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("");
				System.out.println(RED + "Pilihan harus Angka" + NORMAL);
				System.out.println("");
				System.out.print("Silahkan tekan Enter untuk melanjutkan...");
				br.readLine();
				menuAdmin(admin);
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		boolean loop = true;

		while (loop) {
			clearScreen();
			System.out.println("");
			System.out.println("LOGIN");
			System.out.println("");
			System.out.print("Username >> ");
			String nama = br.readLine();
			System.out.print("Password >> ");
			String pass = br.readLine();
			Pengguna penggunaAktif = null;

			for (Pengguna data : listPengguna) {
				if (data.getNama().equals(nama) && data.getPassword().equals(pass)) {
					penggunaAktif = data;
				}
			}

			if (penggunaAktif != null) {
				if (penggunaAktif.getRole() == "pembeli") {
					System.out.println("");
					System.out.println(GREEN + "Login Berhasil" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();

					Pembeli pembeli = new Pembeli(penggunaAktif.getNama(), penggunaAktif.getPassword(), penggunaAktif.getRole());
					menuPembeli(pembeli);
				} else if (penggunaAktif.getRole() == "admin") {
					System.out.println("");
					System.out.println(GREEN + "Login Berhasil" + NORMAL);
					System.out.println("");
					System.out.print("Silahkan tekan Enter untuk melanjutkan...");
					br.readLine();

					Admin admin = new Admin(penggunaAktif.getNama(), penggunaAktif.getPassword(), penggunaAktif.getRole());
					menuAdmin(admin);
				}
			} else {
				System.out.println("");
				System.out.println(RED + "Username dan Password tidak ditemukan" + NORMAL);
				System.out.println("");
				System.out.print("Silahkan tekan Enter untuk melanjutkan...");
				br.readLine();
			}
		}
	}
}