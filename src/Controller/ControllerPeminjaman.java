package Controller;

import Model.ModelPeminjaman;
import Node.NodeAnggota;
import Node.NodeBuku;
import Node.NodePeminjaman;

import java.util.ArrayList;

public class ControllerPeminjaman {
    ModelPeminjaman modelPeminjaman = new ModelPeminjaman();

    public void controllerInsertLoan(String kodeTransaksi, NodeAnggota nodeAnggota, NodeBuku nodeBuku, int tglPinjam, int tglKembali){
        NodePeminjaman peminjaman = new NodePeminjaman(kodeTransaksi, nodeAnggota, nodeBuku, tglPinjam, tglKembali);
        modelPeminjaman.create(peminjaman);
    }

    public ArrayList<NodePeminjaman> controllerViewLoan(){
        try {
            return modelPeminjaman.read();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void controllerUpdateLoan(String kodeTransaksi, NodeAnggota nodeAnggota, NodeBuku nodeBuku, int tglPinjam, int tglKembali){
        NodePeminjaman peminjaman = new NodePeminjaman(kodeTransaksi, nodeAnggota, nodeBuku, tglPinjam, tglKembali);
        modelPeminjaman.update(peminjaman);
    }

    public void controllerDeleteLoan(String kodeTransaksi){
        modelPeminjaman.delete(kodeTransaksi);
    }

    public boolean controllerSearchLoanDatabyCode(String kode) {
        NodePeminjaman peminjaman = modelPeminjaman.searchLoanDatabyCode(kode);
        return peminjaman != null;
    }

    public NodePeminjaman controllerLoanData (String kode) {
        return modelPeminjaman.searchLoanDatabyCode(kode);
    }

}
