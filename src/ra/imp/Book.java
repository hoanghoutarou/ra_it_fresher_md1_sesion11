package ra.imp;

import ra.IBook;
import ra.run.BookRun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ra.run.BookRun.listBook;


public class Book implements IBook {
    private int bookId;
    private String bookName, descriptions, author;
    private Float importPrice, exportPrice;
    private Date created;

    public Book() {
    }

    public Book(int bookId, String bookName, String descriptions, String author, Float importPrice, Float exportPrice, Date created) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.descriptions = descriptions;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.created = created;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Float importPrice) {
        this.importPrice = importPrice;
    }

    public Float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(Float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int generateIdentityValue(List<Book> listBook) {
        try {
            if (listBook.isEmpty()) {
                return 1;
            } else {
                int max = listBook.get(0).getBookId();
                for (int i = 1; i < listBook.size(); i++) {
                    if (max < listBook.get(i).getBookId()) {
                        max = listBook.get(i).getBookId();
                    }
                }
                return max + 1;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
        return this.bookId;
    }

    public String inputBookName(Scanner sc, List<Book> listBook) {
        do {
            System.out.println("Nhập tên sách:");
            this.bookName = sc.nextLine();
            if (bookName.length() == 4) {
                if (bookName.charAt(0) == 'B') {
                    boolean isDuplication = false;
                    for (int i = 0; i < listBook.size(); i++) {
                        if (this.bookName.equals(listBook.get(i).getBookName())) {
                            isDuplication = true;
                            break;
                        }
                    }
                    if (isDuplication) {
                        System.out.println("Tên sách bị trùng vui long nhập lại");
                    } else {
                        return this.bookName;
                    }
                } else {
                    System.err.println("Tên sach bắt đầu là B, vui lòng nhập lại!");
                }
            } else {
                System.err.println("Tên sach phải có 4 ký tự, vui lòng nhập lại!");
            }

        } while (true);
    }

    public String inputAuthor(Scanner sc) {
        do {
            System.out.println("Nhập tên tác giả:");
            this.author = sc.nextLine();
            if (this.author.length() < 6 || this.author.length() > 50) {
                System.err.println("Ten tac gia khoang 6 - 50 ky tu");
            } else {
                return this.author;
            }
        } while (true);
    }

    public float inputImportPrice(Scanner sc) {
        try {
            do {
                System.out.print("Nhập giá nhập sach:");
                this.importPrice = Float.parseFloat(sc.nextLine());
                if (this.importPrice > 0) {
                    return this.importPrice;
                } else {
                    System.out.println("Giá nhap sach phải lớn hơn 0");
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
        return this.importPrice;
    }

    public float inputExportPrice(Scanner sc) {
        try {
            do {
                System.out.print("Nhập giá xuất sản phẩm:");
                this.exportPrice = Float.parseFloat(sc.nextLine());
                if (this.exportPrice > this.importPrice) {
                    return this.exportPrice;
                } else {
                    System.out.println("Giá xuat sach phải lớn hơn gia nhap");
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("Có lỗi: " + e);
        }
        return this.importPrice;

    }

    public Date inputCreated(Scanner sc) {
        do {
            System.out.println("Nhập vào ngày phat hanh:");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                this.created = sdf.parse(sc.nextLine());
            } catch (ParseException e) {
                System.err.println("Không đúng định dạng ngày tháng, vui lòng nhập lại");
                throw new RuntimeException(e);
            }
            return this.created;
        } while (true);
    }

    public String inputDescriptions(Scanner sc) {
        do {
            System.out.println("Nhập mô tả sách:");
            this.descriptions = sc.nextLine();
            if (this.descriptions.length() > 500) {
                System.err.println("Mo tả tối đa 500 kí tự");
            } else {
                return this.author;
            }
        } while (true);
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.print("Nhập số sach cần nhập thông tin:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Sach %d: \n",i);
            this.bookId = generateIdentityValue(listBook);
            this.bookName = inputBookName(sc, listBook);
            this.importPrice = inputImportPrice(sc);
            this.exportPrice = inputExportPrice(sc);
            this.author = inputAuthor(sc);
            this.created = inputCreated(sc);
            this.descriptions = inputDescriptions(sc);
            Book book = new Book(this.bookId, this.bookName, this.descriptions, this.author, this.importPrice, this.exportPrice, this.created);
            listBook.add(book);
        }

    }

    @Override
    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tác giả: %s - Mô tả sách: %s\n" +
                        "Gía nhập: %s - Gía xuất: %s - Ngày xuất bản: %s\n",
                this.bookId, this.bookName, this.author, this.descriptions, this.importPrice, this.exportPrice, this.created);
    }

    public void updateBookById(Scanner sc,List<Book> listBook) {
        try {
            System.out.println("Mời bạn nhập mã sách cần cập nhật: ");
            int updateBookId = Integer.parseInt(sc.nextLine());
            boolean check = false;//Kiểm tra mã sách cần cập nhật có tồn tại hay không
            for (int i = 0; i < listBook.size(); i++) {
                if (listBook.get(i).getBookId() == updateBookId) {
                    check = true;
                    listBook.get(i).bookName = inputBookName(sc, BookRun.listBook);
                    listBook.get(i).importPrice = inputImportPrice(sc);
                    listBook.get(i).exportPrice = inputExportPrice(sc);
                    listBook.get(i).author = inputAuthor(sc);
                    listBook.get(i).created = inputCreated(sc);
                    listBook.get(i).descriptions = inputDescriptions(sc);
                    System.out.println("Thông tin sách đã được cập nhật");
                    break;
                }
            }
            if (!check) {
                System.err.println("Không tìm thấy mã sách của bạn, vui lòng nhập lại");
            }
        } catch (Exception e) {
            System.err.println("Có lỗi " + e);
        }
    }

    public void deleteBookById(Scanner sc) {
        try {
            System.out.println("Mời bạn nhập mã sách cần xóa: ");
            int deleteBookId = Integer.parseInt(sc.nextLine());
            Iterator<Book> iterator = listBook.iterator();
            while (iterator.hasNext()){
                Book book = iterator.next();
                if (book.getBookId() == deleteBookId){
                    iterator.remove();
                    System.out.println("Thông tin sách đã xóa");
                    return;
                }
            }
            System.err.println("Không tìm thấy mã sách của bạn, vui lòng nhập lại");
        } catch (Exception e) {
            System.err.println("Có lỗi " + e);
        }
    }
    public void searchBookByAuthor(Scanner sc){
        System.out.println("Mời bạn nhập tên tác giả: ");
        try {
            String searchAuthor = sc.nextLine();
            boolean found = false;
            for (int i = 0; i < listBook.size(); i++) {
                if (listBook.get(i).getAuthor().equalsIgnoreCase(searchAuthor)){//Không phân biệt chữ hoa với chữ thường
                    listBook.get(i).displayData();
                }
            }
            if (!found){
                System.err.println("Không tìm thấy tên tác giả, vui lòng nhập lại!");
            }
        }catch (Exception e){
            System.err.println("Có lỗi: " +e);
        }
    }
    public void sortBookByPrice(){
        Collections.sort(listBook, Comparator.comparing(Book::getExportPrice));
    }
    public void statisticalBookByPrice(Scanner scanner){
        try {
            System.out.println("Mời bạn nhập khoảng giá đầu: ");
            Float a = Float.parseFloat(scanner.nextLine());
            System.out.println("Mời bạn nhập khoảng giá cuối: ");
            Float b = Float.parseFloat(scanner.nextLine());
            if (a > b){
                System.err.println("Gía cuối không được nhỏ hơn giá đầu, vui lòng nhập lại!");
                return;
            }
            int count = 0;
            for (int i = 0; i < listBook.size(); i++) {
                Book book = listBook.get(i);
                float priceBook = book.getExportPrice();
                if (priceBook >= a && priceBook <= b){
                    System.out.println(book);
                    count++;
                }
            }
            if (count == 0){
                System.out.println("Không có sách nào trong khoảng giá từ " +a + "-" +b);
            }else {
                System.out.println("Tổng số sách trong khoảng giá từ " +a + "-"+b+" là: " +count);
            }
        }catch (Exception e){
            System.err.println("Gía không hợp lệ, vui lòng nhập lại");
            System.err.println("Có lỗi: " +e);
        }
    }
}
