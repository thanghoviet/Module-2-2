package FideClose;

public class Menu {
    public void menu(){
        System.out.println("______________________________________________________________");
        System.out.println("         File            "+"                Close      ");
        System.out.println("Bấm F để sử dụng ứng dụng"+ "     Bấm C để thoát ứng dụng");
        System.out.println("_________________________________");
        System.out.println("Nhập lựa chọn của bạn");

    }
    public void menuFile(){
        System.out.println("Ấn D: Để Show toàn bộ các file và thư mục ở C:\\Baitap\\");
        System.out.println("Ấn S: Để tìm kiếm file or thư mục");
        System.out.println("Ấn N: Để thêm file or thư mục");
        System.out.println("Ấn C: Để sao chép file or thư mục chỉ định");
        System.out.println("Ấn E: Để xóa file or thư mục chỉ định");
        System.out.println("Ấn M: Để di chuyển các file or thư mục chỉ định");
        System.out.println("Ấn O: Để thoát ứng dụng");
        System.out.println("_________________________________");
        System.out.println("Nhập lựa chọn của bạn");

    }
    public void menuClose(){
        System.out.println("Ấn Y: Để thoát");
        System.out.println("Ấn N: Để Quay Lại menu chính");
    }
}
