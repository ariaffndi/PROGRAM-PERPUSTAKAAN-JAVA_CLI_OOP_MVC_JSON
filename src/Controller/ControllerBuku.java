package Controller;

import Model.ModelBuku;
import Node.NodeBuku;

import java.util.ArrayList;


public class ControllerBuku{
    ModelBuku modelBuku = new ModelBuku();
    public void controllerInsertBook(String kodeBuku,String judulBuku,String pengarang,int tahunTerbit,int stok ){
        NodeBuku book = new NodeBuku(kodeBuku,judulBuku,pengarang,tahunTerbit,stok);
        modelBuku.create(book);
    }
    public ArrayList<NodeBuku> controllerViewBook(){
        try {
            return modelBuku.read();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public void controllerUpdateBook(String kodeBuku,String judulBuku,String pengarang,int tahunTerbit,int stok ){
        NodeBuku book = new NodeBuku(kodeBuku,judulBuku,pengarang,tahunTerbit,stok);
        modelBuku.update(book);
    }
    public void controllerDeleteBook(String kodeBuku){
        modelBuku.delete(kodeBuku);
    }

    public boolean controllerSearchBookbyCode(String kode) {
        NodeBuku book = modelBuku.searchBookbyCode(kode);
        return book != null;
    }

    public boolean controllerSearchBookbyTitle(String judul) {
        NodeBuku book = modelBuku.searchBookbyTitle(judul);
        return book != null;
    }

    public NodeBuku controllerBook (String judul) {
        return modelBuku.searchBookbyTitle(judul);
    }
}