package Node;

public class NodeBuku {
    private int tahunTerbit, stok;
    private String kodeBuku, judulBuku, pengarang;

    public NodeBuku(String kodeBuku, String judulBuku, String pengarang, int tahunTerbit, int stok) {
        this.kodeBuku = kodeBuku;
        this.judulBuku = judulBuku;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.stok = stok;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public int getStok() {
        return stok;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public String getPengarang() {
        return pengarang;
    }

}