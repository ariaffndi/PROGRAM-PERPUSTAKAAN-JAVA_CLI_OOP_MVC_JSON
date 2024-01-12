package View;

import Controller.ControllerAnggota;
import Node.NodeAnggota;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewAnggota {
    Scanner input = new Scanner(System.in);
    ControllerAnggota controllerAnggota = new ControllerAnggota();

    public void insertmember(){
        System.out.println("\n--- Input Data Anggota ---");
        System.out.println("---------------------");
        System.out.print("Id Anggota \t: ");
        String inputId = input.next();
        if (!controllerAnggota.controllerSearchMemberbyId(inputId)) {
            input.nextLine();
            System.out.print("Nama \t\t: ");
            String inputNama = input.next();
            System.out.print("No Telp \t: +62-");
            long inputNoTelp = input.nextLong();
            String noTelp = "+62" + inputNoTelp;
            System.out.print("Alamat \t\t: ");
            String inputAlamat = input.next();
            controllerAnggota.controllerInsertmember(inputId, inputNama, noTelp, inputAlamat);
            System.out.println("Data Berhasil Ditambah!");
        } else {
            System.out.println("Id Sudah Digunakan!");
        }
    }

    public void updateMember() {
        System.out.println("\n--- Update Data Anggota ---");
        System.out.println("---------------------");
        System.out.print("Id Anggota \t: ");
        String searchId = input.next();
        if (controllerAnggota.controllerSearchMemberbyId(searchId)) {
            input.nextLine();
            System.out.print("Nama \t\t: ");
            String inputNama = input.next();
            System.out.print("No Telp \t: +62-");
            long inputNoTelp = input.nextLong();
            String noTelp = "+62" + inputNoTelp;
            System.out.print("Alamat \t\t: ");
            String inputAlamat = input.next();
            controllerAnggota.controllerUpdateMember(searchId, inputNama, noTelp, inputAlamat);
            System.out.println("Data Berhasil Diupdate!");
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void deleteMember(){
        System.out.println("\n--- Delete Data Anggota ---");
        System.out.println("---------------------");
        System.out.print("Id Anggota : ");
        String inputId = input.next();
        if (controllerAnggota.controllerSearchMemberbyId(inputId)) {
            controllerAnggota.controllerDeleteMember(inputId);
            System.out.println("Data Berhasil Dihapus!");
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void viewAllMembers() {
        ArrayList<NodeAnggota> listAnggota = new ControllerAnggota().controllerViewMember();
        System.out.println("\n--- Semua Data Anggota ---");
        System.out.println("---------------------");
        if (listAnggota.isEmpty()) {
            System.out.println("Data Masih Kosong !");
        } else {
            for (NodeAnggota member : listAnggota) {
                membersData(member);
            }
        }
    }

    public void viewMember() {
        System.out.println("\n--- Data Anggota ---");
        System.out.println("---------------------");
        System.out.print("Nama Anggota : ");
        String searchName = input.next();
        if (controllerAnggota.controllerSearchMemberbyName(searchName)) {
            NodeAnggota nodeAnggota =  controllerAnggota.controllerMember(searchName);
            membersData(nodeAnggota);
        } else {
            System.out.println("Data Tidak Ditemukan !");
        }
    }

    public void membersData(NodeAnggota member){
        System.out.println("Id Anggota \t: " + member.getIdAnggota());
        System.out.println("Nama \t\t: " + member.getNama());
        System.out.println("No Telp \t: " + member.getNoTelp());
        System.out.println("Alamat \t\t: " + member.getAlamat());
        System.out.println("---------------------");
    }
}
