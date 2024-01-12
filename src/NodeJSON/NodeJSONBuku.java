package NodeJSON;

public class NodeJSONBuku {
    private String kodeBuku, judulBuku, pengarang, tahunTerbit, stok;

    public NodeJSONBuku() {
        this.kodeBuku = "kodeBuku";
        this.judulBuku = "judulBuku";
        this.pengarang = "pengarang";
        this.tahunTerbit = "tahunTerbit";
        this.stok = "stok";

    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public String getStok() {
        return stok;
    }
}
