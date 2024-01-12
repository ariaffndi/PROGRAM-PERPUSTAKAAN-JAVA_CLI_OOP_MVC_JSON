package View;

import Controller.*;
import Node.*;

import java.util.ArrayList;
import java.util.Scanner;


public class ViewPeminjaman {
    Scanner input = new Scanner(System.in);

    ControllerPeminjaman controllerPeminjaman = new ControllerPeminjaman();
    ControllerAnggota controllerAnggota = new ControllerAnggota();
    ControllerBuku controllerBuku = new ControllerBuku();

    public void insertLoan(){
        System.out.println("\n--- Input Data Peminjaman ---");
        System.out.println("---------------------");
        System.out.print("Kode Transaksi \t\t: ");
        String inputKode = input.next();

        boolean findMember = false;
        boolean findBook = false;
        NodeAnggota nodeAnggota = null;
        NodeBuku nodeBuku = null;

        if (!controllerPeminjaman.controllerSearchLoanDatabyCode(inputKode)) {
            do {
                System.out.print("Nama Peminjam : ");
                String searchName = input.next();
                NodeAnggota cariAnggota = controllerAnggota.controllerMember(searchName);
                if (cariAnggota != null) {
                    findMember = true;
                    nodeAnggota = cariAnggota;
                } else {
                    System.out.println("Nama Belum Terdaftar Sebagai Member !\n");
                }
            } while (!findMember);

            do {
                System.out.print("Judul Buku : ");
                String searchBook = input.next();
                NodeBuku cariBuku = controllerBuku.controllerBook(searchBook);
                if (cariBuku != null) {
                    if (cariBuku.getStok() > 0) {
                        nodeBuku = cariBuku;
                        findBook = true;
                    }
                    else {
                        System.out.println("Stok Buku Kosong !\n");
                    }
                } else {
                    System.out.println("Judul Buku Tidak Ditemukan !\n");
                }
            } while (!findBook);
            System.out.print("Tanggal Pinjam : ");
            int inputTglPinjam = input.nextInt();
            System.out.print("Tanggal Kembali : ");
            int inputTglKembali = input.nextInt();
            controllerPeminjaman.controllerInsertLoan(inputKode, nodeAnggota, nodeBuku, inputTglPinjam, inputTglKembali);
            controllerBuku.controllerUpdateBook(nodeBuku.getKodeBuku(), nodeBuku.getJudulBuku(), nodeBuku.getPengarang(), nodeBuku.getTahunTerbit(), nodeBuku.getStok()-1);
            System.out.println("Data Berhasil Ditambah!");

        } else {
            System.out.println("Kode Sudah Digunakan!");
        }
    }

    public void deleteLoanData() {
        System.out.println("\n--- Input Data Pengembalian ---");
        System.out.print("Kode Transaksi : ");
        String searchKode = input.next();
        if (controllerPeminjaman.controllerSearchLoanDatabyCode(searchKode)) {
            NodePeminjaman nodePeminjaman = controllerPeminjaman.controllerLoanData(searchKode);
            controllerPeminjaman.controllerDeleteLoan(searchKode);
            String judul = nodePeminjaman.getBuku().getJudulBuku();
            NodeBuku nodeBuku = controllerBuku.controllerBook(judul);
            controllerBuku.controllerUpdateBook(nodeBuku.getKodeBuku(), nodeBuku.getJudulBuku(), nodeBuku.getPengarang(), nodeBuku.getTahunTerbit(), nodeBuku.getStok()+1);
            System.out.println("Transaksi Pengembalian Berhasil!");
        } else {
            System.out.println("Kode Transaksi Tidak Ditemukan !");
        }
    }

    public void updateLoanData() {
        System.out.println("\n--- Update Tanggal Pengembalian ---");
        System.out.print("Kode Transaksi : ");
        String searchKode = input.next();
        if (controllerPeminjaman.controllerSearchLoanDatabyCode(searchKode)) {
            NodePeminjaman nodePeminjaman = controllerPeminjaman.controllerLoanData(searchKode);
            String nama = nodePeminjaman.getAnggota().getNama();
            String judul = nodePeminjaman.getBuku().getJudulBuku();
            NodeBuku nodeBuku = controllerBuku.controllerBook(judul);
            NodeAnggota nodeAnggota = controllerAnggota.controllerMember(nama);
            System.out.print("Tanggal Pengembalian Baru : ");
            int updateTglKembali = input.nextInt();
            controllerPeminjaman.controllerUpdateLoan(searchKode,nodeAnggota,nodeBuku, nodePeminjaman.getTglPinjam(), updateTglKembali);
            System.out.println("Transaksi Pengembalian Berhasil!");
        } else {
            System.out.println("Kode Transaksi Tidak Ditemukan !");
        }
    }

    public void viewAllLoanData() {
        ArrayList<NodePeminjaman> listPeminjaman = new ControllerPeminjaman().controllerViewLoan();
        System.out.println("\n--- Semua Data Peminjaman ---");
        System.out.println("---------------------");
        if (listPeminjaman.isEmpty()) {
            System.out.println("Data Masih Kosong !");
        } else {
            for (NodePeminjaman loan : listPeminjaman) {
                loanData(loan);
            }
        }
    }

    public void viewLoanData() {
        System.out.println("\n--- Data Peminjaman ---");
        System.out.println("---------------------");
        System.out.print("Kode Transaksi : ");
        String searchKode = input.next();
        if (controllerPeminjaman.controllerSearchLoanDatabyCode(searchKode)) {
            NodePeminjaman nodePeminjaman =  controllerPeminjaman.controllerLoanData(searchKode);
            loanData(nodePeminjaman);
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void loanData(NodePeminjaman loan){
        System.out.println("Kode Transaksi \t\t: " + loan.getKodeTransaksi());
        System.out.println("Nama Peminjam \t\t: " + loan.getAnggota().getNama());
        System.out.println("Buku yang Dipinjam \t: " + loan.getBuku().getJudulBuku());
        System.out.println("Tanggal Peminjaman \t: " + loan.getTglPinjam());
        System.out.println("Tanggal Pengembalian \t: " + loan.getTglkembali());
        System.out.println("---------------------");
    }

}
