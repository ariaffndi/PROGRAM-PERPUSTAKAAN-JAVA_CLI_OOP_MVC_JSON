package Node;

public class NodeAnggota {
    private String idAnggota, nama, noTelp,  alamat;

    public NodeAnggota(String idAnggota, String nama, String noTelp,  String alamat) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getAlamat() {
        return alamat;
    }


}