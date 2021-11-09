import java.util.Scanner;

/**
 * PACKAGE_NAME
 * Created by Nguyen Thanh Kien 19127456
 * Date 03/11/2021 - 12:02 SA
 * Description: ...
 */
public class main {
    public static void main(String[] args) {
        String filename;
        String filetype;
        StuManagement Stu;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Load student list from binary file.\n2. Import student list to CSV file.\n0. Exit");
        System.out.print("Please select an optional above: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 1:
                System.out.print("Please enter the filename: ");
                filename = scanner.nextLine();
                if(filename.length() < 4) filename ="errorFile";
                filetype = filename.substring(filename.length() - 3);
                if (filename == "" || (!filetype.equals("bin") && !filetype.equals("BIN"))){
                    filename = "student.bin";
                    System.out.println("filename error -> open default file student.bin");
                }
                Stu = new StuManagement(filename);
                Stu.LoadStuList();
                break;
            case 2:
                System.out.print("Please enter the filename: ");
                filename = scanner.nextLine();
                if(filename.length() < 4) filename ="errorFile";
                filetype = filename.substring(filename.length() - 3);
                if (filename == "" || (!filetype.equals("csv") && !filetype.equals("CSV"))){
                    filename = "data.csv";
                    System.out.println("filename error -> open default file data.csv");
                }
                Stu = new StuManagement("student.bin");
                Stu.ImportStuListCSV(filename);
                break;
            case 0:
                return;
            default:
                System.out.println("syntax error");
                return;
        }
        Stu.Navigation();
    }
}