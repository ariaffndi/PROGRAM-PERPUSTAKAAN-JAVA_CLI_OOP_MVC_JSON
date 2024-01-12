package Model;

import Node.*;
import NodeJSON.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ModelPeminjaman {
    String fname = "src/Database/DatabasePeminjaman.json";

    NodeJSONPeminjaman nodeJSONPeminjaman = new NodeJSONPeminjaman();

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

    public JSONArray convertArrayListToArrayJSON(ArrayList<NodePeminjaman> listPeminjaman) {
        if (listPeminjaman == null) {
            return null;
        } else {
            JSONArray arrayPeminjaman = new JSONArray();
            for (NodePeminjaman peminjaman : listPeminjaman) {
                JSONObject objPeminjaman = new JSONObject();
                objPeminjaman.put(nodeJSONPeminjaman.getKodeTransaksi(), peminjaman.getKodeTransaksi());
                objPeminjaman.put(nodeJSONPeminjaman.getNama(), peminjaman.getAnggota().getNama());
                objPeminjaman.put(nodeJSONPeminjaman.getJudul(), peminjaman.getBuku().getJudulBuku());
                objPeminjaman.put(nodeJSONPeminjaman.getTglPinjam(), peminjaman.getTglPinjam());
                objPeminjaman.put(nodeJSONPeminjaman.getTglKembali(), peminjaman.getTglkembali());
                arrayPeminjaman.add(objPeminjaman);
            }
            return arrayPeminjaman;
        }
    }

    public void writeFileJSON(ArrayList<NodePeminjaman> listPeminjaman) {
        JSONArray jsonArray = convertArrayListToArrayJSON(listPeminjaman);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NodePeminjaman> convertArrayJSONToArraylist(JSONArray arrayPeminjaman) {
        if (arrayPeminjaman == null || arrayPeminjaman.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<NodePeminjaman> listPeminjaman = new ArrayList<>();
        for (Object objPeminjaman : arrayPeminjaman) {
            if (objPeminjaman instanceof JSONObject) {
                JSONObject peminjaman = (JSONObject) objPeminjaman;
                String kodeTransaksi = peminjaman.get(nodeJSONPeminjaman.getKodeTransaksi()).toString();
                String namaAnggota = peminjaman.get(nodeJSONPeminjaman.getNama()).toString();
                String judulBuku = peminjaman.get(nodeJSONPeminjaman.getJudul()).toString();
                int tglPinjam = Integer.parseInt(peminjaman.get(nodeJSONPeminjaman.getTglPinjam()).toString());
                int tglKembali = Integer.parseInt(peminjaman.get(nodeJSONPeminjaman.getTglKembali()).toString());

                NodeAnggota nodeAnggota = new NodeAnggota(null, namaAnggota, null, null);
                NodeBuku nodeBuku = new NodeBuku(null, judulBuku, null, 0, 0);

                listPeminjaman.add(new NodePeminjaman(kodeTransaksi, nodeAnggota, nodeBuku, tglPinjam, tglKembali));
            }
        }
        return listPeminjaman;
    }

    public ArrayList<NodePeminjaman> readFromFile() {
        if (!cekFile()) {
            return null;
        }
        ArrayList<NodePeminjaman> listPeminjaman = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayPeminjaman = (JSONArray) parser.parse(reader);
            listPeminjaman = convertArrayJSONToArraylist(arrayPeminjaman);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) { // Combine IOException and ParseException
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public void create(NodePeminjaman peminjaman) {
        ArrayList<NodePeminjaman> listPeminjaman = readFromFile();
        listPeminjaman.add(peminjaman);
        writeFileJSON(listPeminjaman);
    }

    public ArrayList<NodePeminjaman> read() {
        return readFromFile();
    }

    public void update(NodePeminjaman updatePeminjaman) {
        ArrayList<NodePeminjaman> listPeminjaman = readFromFile();
        if (listPeminjaman != null) {
            for (int i = 0; i < listPeminjaman.size(); i++) {
                NodePeminjaman peminjaman = listPeminjaman.get(i);
                if (Objects.equals(peminjaman.getKodeTransaksi(), updatePeminjaman.getKodeTransaksi())){
                    listPeminjaman.set(i,updatePeminjaman);
                    writeFileJSON(listPeminjaman);
                    return;
                }
            }
        }
    }

    public void delete(String kodeTransaksi){
        ArrayList<NodePeminjaman> listPeminjaman = readFromFile();
        if (listPeminjaman != null){
            listPeminjaman.removeIf(peminjaman -> Objects.equals(peminjaman.getKodeTransaksi(), kodeTransaksi));
            writeFileJSON(listPeminjaman);
        }
    }

    public NodePeminjaman searchLoanDatabyCode (String kode) {
        ArrayList<NodePeminjaman> listpeminjaman = readFromFile();
        NodePeminjaman findLoan = null;
        for (NodePeminjaman peminjaman : listpeminjaman) {
            if (kode.equals(peminjaman.getKodeTransaksi())) {
                findLoan = peminjaman;
            }
        }
        return findLoan;
    }

}
