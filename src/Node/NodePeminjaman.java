package Node;

public class NodePeminjaman {
    private String kodeTransaksi;
    private NodeAnggota anggota;
    private NodeBuku buku;

    private int tglPinjam, tglkembali;

    public NodePeminjaman(String kodeTransaksi, NodeAnggota anggota, NodeBuku buku, int tlgPinjam, int tlgKembali) {
        this.kodeTransaksi = kodeTransaksi;
        this.anggota = anggota;
        this.buku = buku;
        this.tglPinjam = tlgPinjam;
        this.tglkembali = tlgKembali;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public NodeAnggota getAnggota() {
        return anggota;
    }

    public NodeBuku getBuku() {
        return buku;
    }

    public int getTglPinjam() {
        return tglPinjam;
    }

    public int getTglkembali() {
        return tglkembali;
    }

}
