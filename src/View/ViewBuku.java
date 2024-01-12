package View;

import Controller.ControllerBuku;
import Node.NodeBuku;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewBuku {
    Scanner input = new Scanner(System.in);
    ControllerBuku controllerBuku = new ControllerBuku();

    public void insertBook(){
        System.out.println("\n--- Input Data Buku ---");
        System.out.println("---------------------");
        System.out.print("Kode buku \t\t: ");
        String inputKode = input.next();
        if (!controllerBuku.controllerSearchBookbyCode(inputKode)) {
            input.nextLine();
            System.out.print("Judul \t\t\t: ");
            String inputJudulBuku = input.next();
            System.out.print("Pengarang \t\t: ");
            String inputPengarangBuku = input.next();
            System.out.print("Tahun Terbit \t: ");
            int inputTahunBuku = input.nextInt();
            System.out.print("Stok \t\t\t: ");
            int inputStokBuku = input.nextInt();
            controllerBuku.controllerInsertBook(inputKode, inputJudulBuku, inputPengarangBuku, inputTahunBuku, inputStokBuku);
            System.out.println("Data Berhasil Ditambah!");
        } else {
            System.out.println("Kode Sudah Digunakan!");
        }
    }

    public void updateBook() {
        System.out.println("\n--- Update Data Buku ---");
        System.out.println("---------------------");
        System.out.print("Kode buku \t\t: ");
        String searchKodeBuku = input.next();
        if (controllerBuku.controllerSearchBookbyCode(searchKodeBuku)) {
            input.nextLine();
            System.out.print("Judul \t\t\t: ");
            String updateJudul = input.next();
            System.out.print("Pengarang \t\t: ");
            String updatePengarang = input.next();
            System.out.print("Tahun Terbit \t: ");
            int updateTahunTerbit = input.nextInt();
            System.out.print("Stok \t\t\t: ");
            int updateStok = input.nextInt();
            controllerBuku.controllerUpdateBook(searchKodeBuku, updateJudul, updatePengarang, updateTahunTerbit, updateStok);
            System.out.println("Data Berhasil Diupdate!");
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void deleteBook(){
        System.out.println("\n--- Delete Data Buku ---");
        System.out.println("---------------------");
        System.out.print("Kode buku : ");
        String searchKodeBuku = input.next();
        if (controllerBuku.controllerSearchBookbyCode(searchKodeBuku)) {
            controllerBuku.controllerDeleteBook(searchKodeBuku);
            System.out.println("Data Berhasil Dihapus!");
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void viewAllBooks() {
        ArrayList<NodeBuku> listBuku = new ControllerBuku().controllerViewBook();
        System.out.println("\n--- Semua Data Buku ---");
        System.out.println("---------------------");
        if (listBuku.isEmpty()) {
            System.out.println("Data Masih Kosong !");
        } else {
            for (NodeBuku book : listBuku) {
                booksData(book);
            }
        }
    }

    public void viewBook() {
        System.out.println("\n--- Data Buku ---");
        System.out.println("---------------------");
        System.out.print("Judul Buku : ");
        String searchJudul = input.next();
        if (controllerBuku.controllerSearchBookbyTitle(searchJudul)) {
            NodeBuku nodeBuku =  controllerBuku.controllerBook(searchJudul);
            booksData(nodeBuku);
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void booksData(NodeBuku book){
        System.out.println("Kode Buku \t\t: " + book.getKodeBuku());
        System.out.println("Judul \t\t\t: " + book.getJudulBuku());
        System.out.println("Pengarang \t\t: " + book.getPengarang());
        System.out.println("Tahun Terbit \t: " + book.getTahunTerbit());
        System.out.println("Stok \t\t\t: " + book.getStok());
        System.out.println("---------------------");
    }

}
