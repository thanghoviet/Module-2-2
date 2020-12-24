import java.io.Serializable;

public class SanPham implements Serializable {
    private int masanpham;
    private String name;
    private String hangsx;
    private int price;
    private String motakhac;

    public SanPham(int masanpham, String name, String hangsx, int price, String motakhac) {
        this.masanpham = masanpham;
        this.name = name;
        this.hangsx = hangsx;
        this.price = price;
        this.motakhac = motakhac;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) { this.masanpham = masanpham; }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getHangsx() {
        return hangsx;
    }

    public void setHangsx(String hangsx) {
        this.hangsx = hangsx;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMotakhac() {
        return motakhac;
    }

    public void setMotakhac(String motakhac) {
        this.motakhac = motakhac;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "masanpham=" + masanpham +
                ", name='" + name + '\'' +
                ", hangsx='" + hangsx + '\'' +
                ", price=" + price +
                ", motakhac='" + motakhac + '\'' +
                '}';
    }
}
