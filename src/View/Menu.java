package View;

import java.util.Objects;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    ViewBuku viewBuku = new ViewBuku();
    ViewAnggota viewAnggota = new ViewAnggota();
    ViewPeminjaman viewPeminjaman = new ViewPeminjaman();

    public void programUtama() {
        //program utama
        cekLogin();
        int pilihan;
        do {
            menuUtama();
            pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    int pilih1;
                    do {
                        subMenu1();
                        pilih1 = input.nextInt();
                        switch (pilih1) {
                            case 1 -> viewPeminjaman.insertLoan();
                            case 2 -> viewPeminjaman.viewLoanData();
                            case 3 -> viewPeminjaman.viewAllLoanData();
                            case 4 -> viewPeminjaman.updateLoanData();
                            case 5 -> viewPeminjaman.deleteLoanData();
                            case 6 -> {
                                continue;
                            }
                            default -> System.out.println("Masukan pilihan 1-6!");
                        }
                    } while (pilih1 != 6);
                    break;

                case 2:
                    int pilih2;
                    do {
                        subMenu2();
                        pilih2 = input.nextInt();
                        switch (pilih2) {
                            case 1 -> viewBuku.insertBook();
                            case 2 -> viewBuku.deleteBook();
                            case 3 -> viewBuku.updateBook();
                            case 4 -> viewBuku.viewBook();
                            case 5 -> viewBuku.viewAllBooks();
                            case 6 -> {
                                continue;
                            }
                            default -> System.out.println("Masukan pilihan 1-6!");
                        }
                    } while (pilih2 != 6);
                    break;

                case 3:
                    int pilih3;
                    do {
                        subMenu3();
                        pilih3 = input.nextInt();
                        switch (pilih3) {
                            case 1 -> viewAnggota.insertmember();
                            case 2 -> viewAnggota.deleteMember();
                            case 3 -> viewAnggota.updateMember();
                            case 4 -> viewAnggota.viewMember();
                            case 5 -> viewAnggota.viewAllMembers();
                            case 6 -> {
                                continue;
                            }
                            default -> System.out.println("Masukan pilihan 1-6!");
                        }
                    } while (pilih3 != 6);
                    break;

                case 99:
                    System.out.println("=== Terimakasih ===");
                    break;

                default:
                    System.out.println("Masukan pilihan 1-3!");
            }
        }while (pilihan != 99 ) ;
    }



    public void cekLogin() {
        boolean login = false;
        do {
            System.out.println("\n--- Silahkan Login Terlebih Dahulu! ---");
            System.out.print("Username : ");
            String inputUsername = input.next();
            System.out.print("Password : ");
            String inputPassword = input.next();
            if (Objects.equals(inputUsername, "admin") && Objects.equals(inputPassword, "pass")) {
                System.out.println("Login Berhasil !");
                login = true;
            } else {
                System.out.println("Login Gagal !");
            }
        } while (!login);
    }
    public void menuUtama() {
        System.out.println("\n-------------------------------------");
        System.out.println("=== Program Perpustakaan Wisteria ===");
        System.out.println("-------------------------------------");
        System.out.println("""
            1. Transaksi Peminjaman / Pengembalian\s
            2. Manajemen Data Buku\s
            3. Manajemen Data Anggota\s
            99. EXIT""");
        System.out.print("Masukkan pilihan : ");
    }
    public void subMenu1() {
        System.out.println("\n=== Transaksi Peminjaman ===");
        System.out.println("------------------------------");
        System.out.println("""
                1. Transaksi Peminjaman\s
                2. Cari Data Peminjaman\s
                3. Tampilkan Semua Data Peminjaman\s
                4. Update Data Peminjaman\s
                5. Transaksi Pengembalian\s
                6. kembali ke menu Utama""");
        System.out.print("Masukkan pilihan : ");
    }
    public void subMenu2() {
        System.out.println("\n=== Manajemen Data Buku ===");
        System.out.println("------------------------------");
        System.out.println("""
                1. Input Data Buku\s
                2. Delete Data Buku\s
                3. Update Data Buku\s
                4. Cari Data Buku\s
                5. Tampilkan Semua Data Buku\s
                6. kembali ke menu Utama""");
        System.out.print("Masukkan pilihan : ");
    }
    public void subMenu3() {
        System.out.println("\n=== Manajemen Data Anggota ===");
        System.out.println("------------------------------");
        System.out.println("""
                1. Input Data Anggota\s
                2. Delete Data Anggota\s
                3. Update Data Anggota\s
                4. Cari Data Anggota\s
                5. Tampilkan Semua Data Anggota\s
                6. kembali ke menu Utama""");
        System.out.print("Masukkan pilihan : ");
    }

}
