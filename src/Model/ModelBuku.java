package Model;

import Node.NodeBuku;
import NodeJSON.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ModelBuku {
    String fname = "src/Database/DatabaseBuku.json";
    NodeJSONBuku nodeJSONBuku = new NodeJSONBuku();

    public boolean cekFile() {
        boolean cek = false;
        try {
            java.io.File file = new java.io.File(fname);
            if (file.exists()) {
                cek = true;
            }
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        return cek;
    }

    public JSONArray convertArrayListToArrayJSON(ArrayList<NodeBuku> listBuku) {
        if (listBuku == null) {
            return null;
        } else {
            JSONArray arrayBuku = new JSONArray();
            for (NodeBuku buku : listBuku) {
                JSONObject objBuku = new JSONObject();
                objBuku.put(nodeJSONBuku.getKodeBuku(), buku.getKodeBuku());
                objBuku.put(nodeJSONBuku.getJudulBuku(), buku.getJudulBuku());
                objBuku.put(nodeJSONBuku.getPengarang(), buku.getPengarang());
                objBuku.put(nodeJSONBuku.getTahunTerbit(), buku.getTahunTerbit());
                objBuku.put(nodeJSONBuku.getStok(), buku.getStok());
                arrayBuku.add(objBuku);
            }
            return arrayBuku;
        }
    }

    public void writeFileJSON(ArrayList<NodeBuku> listBuku) {
        JSONArray jsonArray = convertArrayListToArrayJSON(listBuku);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NodeBuku> convertArrayJSONToArraylist(JSONArray arrayBuku) {
        if (arrayBuku == null || arrayBuku.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<NodeBuku> listBuku = new ArrayList<>();
        for (Object objBuku : arrayBuku) {
            if (objBuku instanceof JSONObject) {
                JSONObject buku = (JSONObject) objBuku;
                String kodeBuku = buku.get(nodeJSONBuku.getKodeBuku()).toString();
                String judulBuku = buku.get(nodeJSONBuku.getJudulBuku()).toString();
                String pengarang = buku.get(nodeJSONBuku.getPengarang()).toString();
                int tahunTerbit = Integer.parseInt(buku.get(nodeJSONBuku.getTahunTerbit()).toString());
                int stok = Integer.parseInt(buku.get(nodeJSONBuku.getStok()).toString());
                listBuku.add(new NodeBuku(kodeBuku, judulBuku, pengarang, tahunTerbit, stok));
            }
        }
        return listBuku;
    }

    public ArrayList<NodeBuku> readFromFile() {
        if (!cekFile()) {
            return null;
        }
        ArrayList<NodeBuku> listBuku = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayBuku = (JSONArray) parser.parse(reader);
            listBuku = convertArrayJSONToArraylist(arrayBuku);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) { // Combine IOException and ParseException
            e.printStackTrace();
        }
        return listBuku;
    }

    public void create(NodeBuku buku) {
        ArrayList<NodeBuku> listBuku = readFromFile();
        listBuku.add(buku);
        writeFileJSON(listBuku);

    }

    public ArrayList<NodeBuku> read() {
        return readFromFile();
    }

    public void update(NodeBuku updateBuku) {
        ArrayList<NodeBuku> listBuku = readFromFile();
        if (listBuku != null) {
            for (int i = 0; i < listBuku.size(); i++) {
                NodeBuku buku = listBuku.get(i);
                if (Objects.equals(buku.getKodeBuku(), updateBuku.getKodeBuku())){
                    listBuku.set(i,updateBuku);
                    writeFileJSON(listBuku);
                    return;
                }
            }
        }
    }
    public void delete(String kodeBuku){
        ArrayList<NodeBuku> listBuku = readFromFile();
        if (listBuku != null){
            listBuku.removeIf(buku -> Objects.equals(buku.getKodeBuku(), kodeBuku));
            writeFileJSON(listBuku);
        }
    }

    public NodeBuku searchBookbyCode (String kode) {
        ArrayList<NodeBuku> listBuku = readFromFile();
        NodeBuku findBook = null;
        for (NodeBuku book : listBuku) {
            if (kode.equals(book.getKodeBuku())) {
                findBook = book;
            }
        }
        return findBook;
    }
    public NodeBuku searchBookbyTitle (String judul) {
        ArrayList<NodeBuku> listBuku = readFromFile();
        NodeBuku findBook = null;
        for (NodeBuku book : listBuku) {
            if (judul.equals(book.getJudulBuku())) {
                findBook = book;
            }
        }
        return findBook;
    }
}
