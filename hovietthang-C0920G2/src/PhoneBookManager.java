public class PhoneBookManager {
    PhoneBookOntion phoneBookOntion = new PhoneBookOntion();
    PhoneBookScanner contactScanner = new PhoneBookScanner();

    String totalMenu = "1. Show toàn bộ Danh Bạ\n" +
            "2. Thêm\n" +
            "3. Sửa\n" +
            "4. Xóa\n" +
            "5. Tìm\n" +
            "6. Đọc danh bạ\n" +
            "7. Lưu danh bạ\n" +
            "0. Thoát\n";

    String findMenu = "1. Tìm theo Số Điện Thoại\n" +
            "2. Tìm theo Tên\n" +
            "0. Thoát\n";

    String warning = "1. Xác Nhận\n" +
            "0. Quay lại\n";

    private boolean isRunningTotal = true;
    private boolean isRunningFind = false;
    private boolean isRunningWarning = false;

    public void printTotalMenu() {
        while (isRunningTotal) {
            int optionTotal = phoneBookOntion.getOptionMenu(totalMenu, 7, "---Program Management---");
            getOptionTotalMenu(optionTotal);
        }
    }

    public void getOptionTotalMenu(int optionTotal) {
        switch (optionTotal) {
            case 1:
                contactScanner.handleShowAllContactList();
                break;
            case 2:
                contactScanner.handleAddContactToList();
                break;
            case 3:
                contactScanner.handleEditContactFromList();
                break;
            case 4:
                if (printWarningMenu()) contactScanner.handleRemoveContactFromList();
                break;
            case 5:
                isRunningFind = true;
                printFindMenu();
                break;
            case 6:
                isRunningWarning = true;
                if (printWarningMenu()) contactScanner.handleReadFile();
                break;
            case 7:
                isRunningWarning = true;
                if (printWarningMenu()) contactScanner.handleWriteFile();
                break;
            case 0:
                isRunningTotal = false;
        }
    }

    public void printFindMenu() {
        while (isRunningFind) {
            int optionFind = phoneBookOntion.getOptionMenu(findMenu, 2, "---Danh sách được tìm thấy---");
            getOptionFindMenu(optionFind);
        }
    }

    public void getOptionFindMenu(int optionFind) {
        switch (optionFind) {
            case 1:
                contactScanner.handleFindListWithPhoneNumber();
                break;
            case 2:
                contactScanner.handleFindListWithName();
            case 0:
                isRunningFind = false;
        }
    }

    public boolean printWarningMenu() {
        while (isRunningWarning) {
            int optionWarning = phoneBookOntion.getOptionMenu(warning, 1, "--Cảnh Báo--");
            return getOptionWarningMenu(optionWarning);
        }
        return false;
    }

    public boolean getOptionWarningMenu(int optionWarning) {
        switch (optionWarning) {
            case 1 -> {
                isRunningWarning = false;
                return true;
            }
            case 0 -> {
                isRunningWarning = false;
                return false;
            }
        }
        return false;
    }
}

