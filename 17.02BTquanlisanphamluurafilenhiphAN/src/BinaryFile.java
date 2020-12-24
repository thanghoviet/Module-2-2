import java.io.*;
import java.util.ArrayList;

public class BinaryFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        ArrayList<SanPham> dssanpham= new ArrayList<>();
//        SanPham sanpham = new SanPham(12150, "khoca",
//                "canashop", 25, "bảo quản noi kho ráo thoáng mát");
//        dssanpham.add(sanpham);
//
//        SanPham sanpham2 = new SanPham(12151, "khobo",
//                "canashop", 30, "bảo quản noi kho ráo thoáng mát");
//        dssanpham.add(sanpham2);
//
//        ObjectOutputStream luusp = new ObjectOutputStream(
//                new FileOutputStream("sanpham1.dat"));
//        luusp.writeObject(dssanpham);

        InputStream in;
        ObjectInputStream laysp = new ObjectInputStream(new FileInputStream("sanpham1.dat"));
        ArrayList<SanPham> showsanpham = (ArrayList<SanPham>) laysp.readObject();
        for (int i = 0; i <showsanpham.size() ; i++) {
            System.out.println(showsanpham.get(i));
        }
    }
}
