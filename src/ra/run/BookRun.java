package ra.run;

import ra.imp.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookRun {
    public static List<Book> listBook = new ArrayList<>();
    public static void writeDataToFile(List<Book> listBook) {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listBook);
            oos.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static List<Book> readDataFromFile() {
        List<Book> listBookRead = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBookRead = (List<Book>) ois.readObject();
            return listBookRead;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        listBook.add(book);
        writeDataToFile(listBook);
        List<Book> listBookRead = readDataFromFile();
        if (listBookRead == null) {
            System.err.println("Có lỗi trong quá trình đọc file");
        } else {
            System.out.println("Thông tin sach: ");
            listBookRead.forEach(Book -> System.out.println(book));
        }
        do {

            System.out.println("*********************************MENU********************************\n" +
                    "1. Nhập thông tin sách\n" +
                    "2. Hiển thị thông tin sách\n" +
                    "3. Cập nhật thông tin sách theo mã sách\n" +
                    "4. Xóa sách theo mã sách\n" +
                    "5. Sắp xếp sách theo giá bán tăng dần\n" +
                    "6. Thống kê sách theo khoảng giá (a-b nhập từ bàn phím)\n" +
                    "7. Tìm kiếm sách theo tên tác giả\n" +
                    "8. Thoát");
            System.out.println("Nhập lựa chọn:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    book.inputData(sc);
                    break;
                case 2:
                    book.displayData();
                    break;
                case 3:
                    book.updateBookById(sc, listBook);
                    break;
                case 4:
                    book.deleteBookById(sc);
                    break;
                case 5:
                    book.sortBookByPrice();
                    break;
                case 6:
                    book.statisticalBookByPrice(sc);
                    break;
                case 7:
                    book.searchBookByAuthor(sc);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Mời nhập từ 1 đến 8");
                    break;
            }
        } while (true);
    }
}
