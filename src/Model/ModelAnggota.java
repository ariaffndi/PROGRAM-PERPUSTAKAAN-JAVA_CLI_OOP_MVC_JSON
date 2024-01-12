package Model;

import Node.NodeAnggota;
import NodeJSON.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ModelAnggota {
    String fname = "src/Database/DatabaseAnggota.json";
    NodeJSONAnggota nodeJSONAnggota = new NodeJSONAnggota();

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

    public JSONArray convertArrayListToArrayJSON(ArrayList<NodeAnggota> listAnggota) {
        if (listAnggota == null) {
            return null;
        } else {
            JSONArray arrayAnggota = new JSONArray();
            for (NodeAnggota anggota : listAnggota) {
                JSONObject objAnggota = new JSONObject();
                objAnggota.put(nodeJSONAnggota.getIdAnggota(), anggota.getIdAnggota());
                objAnggota.put(nodeJSONAnggota.getNama(), anggota.getNama());
                objAnggota.put(nodeJSONAnggota.getNoTelp(), anggota.getNoTelp());
                objAnggota.put(nodeJSONAnggota.getAlamat(), anggota.getAlamat());
                arrayAnggota.add(objAnggota);
            }
            return arrayAnggota;
        }
    }

    public void writeFileJSON(ArrayList<NodeAnggota> listAnggota) {
        JSONArray jsonArray = convertArrayListToArrayJSON(listAnggota);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<NodeAnggota> convertArrayJSONToArraylist(JSONArray arrayAnggota) {
        if (arrayAnggota == null || arrayAnggota.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<NodeAnggota> listAnggota = new ArrayList<>();
        for (Object objAnggota : arrayAnggota) {
            if (objAnggota instanceof JSONObject) {
                JSONObject anggota = (JSONObject) objAnggota;
                String idAnggota = anggota.get(nodeJSONAnggota.getIdAnggota()).toString();
                String nama = anggota.get(nodeJSONAnggota.getNama()).toString();
                String noTelp = anggota.get(nodeJSONAnggota.getNoTelp()).toString();
                String alamat = anggota.get(nodeJSONAnggota.getAlamat()).toString();
                listAnggota.add(new NodeAnggota(idAnggota, nama, noTelp, alamat));
            }
        }
        return listAnggota;
    }

    public ArrayList<NodeAnggota> readFromFile() {
        if (!cekFile()) {
            return null;
        }
        ArrayList<NodeAnggota> listAnggota = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayAnggota = (JSONArray) parser.parse(reader);
            listAnggota = convertArrayJSONToArraylist(arrayAnggota);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) { // Combine IOException and ParseException
            e.printStackTrace();
        }
        return listAnggota;
    }

    public void create(NodeAnggota anggota) {
        ArrayList<NodeAnggota> listAnggota = readFromFile();
        listAnggota.add(anggota);
        writeFileJSON(listAnggota);
    }

    public ArrayList<NodeAnggota> read() {
        return readFromFile();
    }

    public void update(NodeAnggota updateAnggota) {
        ArrayList<NodeAnggota> listAnggota = readFromFile();
        if (listAnggota != null) {
            for (int i = 0; i < listAnggota.size(); i++) {
                NodeAnggota anggota = listAnggota.get(i);
                if (Objects.equals(anggota.getIdAnggota(), updateAnggota.getIdAnggota())){
                    listAnggota.set(i,updateAnggota);
                    writeFileJSON(listAnggota);
                    return;
                }
            }
        }
    }

    public void delete(String idAnggota){
        ArrayList<NodeAnggota> listAnggota = readFromFile();
        if (listAnggota != null){
            listAnggota.removeIf(anggota -> Objects.equals(anggota.getIdAnggota(), idAnggota));
            writeFileJSON(listAnggota);
        }
    }

    public NodeAnggota searchMemberbyId (String id) {
        ArrayList<NodeAnggota> listAnggota = readFromFile();
        NodeAnggota findMember = null;
        for (NodeAnggota member : listAnggota) {
            if (id.equals(member.getIdAnggota())) {
                findMember = member;
            }
        }
        return findMember;
    }
    public NodeAnggota searchMemberbyName (String nama) {
        ArrayList<NodeAnggota> listAnggota = readFromFile();
        NodeAnggota findMember = null;
        for (NodeAnggota member : listAnggota) {
            if (nama.equals(member.getNama())) {
                findMember = member;
            }
        }
        return findMember;
    }

}
