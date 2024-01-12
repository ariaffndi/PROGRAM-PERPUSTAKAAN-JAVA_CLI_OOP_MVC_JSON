package NodeJSON;

public class NodeJSONPeminjaman {
    String kodeTransaksi, nama, judul, tglPinjam, tglKembali;

    public NodeJSONPeminjaman() {
        this.kodeTransaksi = "kodeTransaksi";
        this.nama = "nama";
        this.judul = "judul";
        this.tglPinjam = "tglPinjam";
        this.tglKembali = "tglKembali";
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getNama() {
        return nama;
    }

    public String getJudul() {
        return judul;
    }

    public String getTglPinjam() {
        return tglPinjam;
    }

    public String getTglKembali() {
        return tglKembali;
    }

}
