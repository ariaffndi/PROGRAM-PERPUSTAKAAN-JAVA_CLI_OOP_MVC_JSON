package Controller;

import Model.ModelAnggota;
import Node.NodeAnggota;

import java.util.ArrayList;


public class ControllerAnggota {
    ModelAnggota modelAnggota = new ModelAnggota();
    public void controllerInsertmember(String idAnggota,String nama, String noTelp, String alamat){
        NodeAnggota member = new NodeAnggota(idAnggota, nama, noTelp, alamat);
        modelAnggota.create(member);
    }

    public ArrayList<NodeAnggota> controllerViewMember(){
        try {
            return modelAnggota.read();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void controllerUpdateMember(String idAnggota,String nama, String noTelp, String alamat){
        NodeAnggota member = new NodeAnggota(idAnggota, nama, noTelp, alamat);
        modelAnggota.update(member);
    }

    public void controllerDeleteMember(String idAnggota){
        modelAnggota.delete(idAnggota);
    }

    public boolean controllerSearchMemberbyId(String idAnggota) {
        NodeAnggota member = modelAnggota.searchMemberbyId(idAnggota);
        return member != null;
    }

    public boolean controllerSearchMemberbyName(String nama) {
        NodeAnggota member = modelAnggota.searchMemberbyName(nama);
        return member != null;
    }

    public NodeAnggota controllerMember (String nama) {
        return modelAnggota.searchMemberbyName(nama);
    }

}
