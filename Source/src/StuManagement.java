import java.io.*;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by Nguyen Thanh Kien 19127456
 * Date 02/11/2021 - 12:29 SA
 * Description: ...
 */
public class StuManagement {
    private ArrayList<Student> StuList;
    private String fileAddress;
    StuManagement(){
        StuList = new ArrayList<Student>();
        fileAddress = "default.bin";
    }
    StuManagement(String fileAddr){
        StuList = new ArrayList<Student>();
        fileAddress = fileAddr;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public ArrayList<Student> getStuList() {
        return StuList;
    }

    public void setStuList(ArrayList<Student> stuList) {
        StuList = stuList;
    }
    private void showOption(){
        System.out.println("1.Add a student\n2.Update student information\n3.Delete a student\n4.View student list ascending order by ID\n5.View student list ascending order by GPA\n6.Save Student List\n7.Export Student List (CSV)\n0.Exit");
        System.out.print("Your choice: ");
    }

    public void addStudent(){
        Student stu = new Student();
        stu.StuInput();
        this.StuList.add(stu);
        this.SaveStuList();
    }
    public void updateStuInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID you wanna update: ");
        String id = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < StuList.size(); i++)
            if (StuList.get(i).getStuID().equals(id)){
                index = i;
                break;
            }
        if (index == -1){
            System.out.println("Can't find student ID" + id +" in data");
            return;
        }
        boolean stop = false;
        while (!stop){
            System.out.println("we have FIELDS information of student: ID/Name/GPA/Image/Address/Note/Exit");
            System.out.println("Please enter the following to syntax: FIELDS-INFO (Example: ID-19127456 or Exit-0 to back): ");
            String[] newInfo = scanner.nextLine().split("-");
            if (newInfo.length != 2) continue;
            switch (newInfo[0])
            {
                case "ID":
                    StuList.get(index).setStuID(newInfo[1]);
                    break;
                case "Name":
                    StuList.get(index).setStuName(newInfo[1]);
                    break;
                case "GPA":
                    StuList.get(index).setStuGPA(Float.parseFloat(newInfo[1]));
                    break;
                case "Image":
                    StuList.get(index).setStuImage(newInfo[1]);
                    break;
                case "Address":
                    StuList.get(index).setStuAddress(newInfo[1]);
                    break;
                case "Note":
                    StuList.get(index).setStuNotes(newInfo[1]);
                    break;
                case "Exit":
                    stop = true;
                    break;
                default:
                    System.out.println("Syntax error");

            }
        }
        this.SaveStuList();
    }
    public void deleteStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID you wanna delete: ");
        String id = scanner.nextLine();
        for (Student item : StuList)
            if (item.getStuID().equals(id)){
                StuList.remove(item);
                this.SaveStuList();
                return;
            }
        System.out.println("Can't find student ID" + id +" in data");
    }
    public void viewStuList(int option){
        if(option == 1){
            StuList.sort(Comparator.comparing(Student::getStuID));
            System.out.println("view student list with StudentID in ascending order:");
            for (Student item: StuList){
                item.StuOutput();
                System.out.println("<---------------------------------------------->");
            }
        }
        if (option == 2){
            StuList.sort(Comparator.comparing(Student::getStuGPA));
            System.out.println("view student list with GPA of Student in ascending order:");
            for (Student item: StuList){
                item.StuOutput();
                System.out.println("<---------------------------------------------->");
            }
        }
    }
    /*https://stackoverflow.com/questions/65875514/how-to-write-and-read-student-object-from-a-binary-files-in-java*/
    public void SaveStuList(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileAddress));
            oos.writeObject(StuList);
            oos.close();
            System.out.println("Done write file!");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveStuList(String fileName){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(StuList);
            oos.close();
            System.out.println("Done write file!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*https://stackoverflow.com/questions/65875514/how-to-write-and-read-student-object-from-a-binary-files-in-java*/
    public void LoadStuList(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileAddress));
            ArrayList<Student> LoadStu = (ArrayList<Student>)ois.readObject();
            this.setStuList(LoadStu);
            System.out.println("Done read file!");
            ois.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found -> open empty file with your filename");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*https://stackabuse.com/reading-and-writing-csvs-in-java/*/
    public void ImportStuListCSV(String fileName){
        try {
            String line;
            ArrayList<Student> temp = new ArrayList<Student>();
            BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
            while ((line = csvReader.readLine()) != null)
                temp.add(Student.pareStudent(line));
            this.setStuList(temp);
        } catch (FileNotFoundException e) {
            System.out.println("File not found -> open empty file with your filename");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*https://stackabuse.com/reading-and-writing-csvs-in-java/*/
    public void ExportStuListCSV(String fileName){
        try {
            FileWriter csvWriter = new FileWriter(fileName);
            for (Student item : this.StuList)
                csvWriter.write(item.toString() + '\n');
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Navigation(){
        Scanner scanner = new Scanner(System.in);
        String filename;
        int choice;
        while (true){
            this.showOption();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    this.addStudent();
                    break;
                case 2:
                    this.updateStuInfo();
                    break;
                case 3:
                    this.deleteStudent();
                    break;
                case 4:
                    this.viewStuList(1);
                    break;
                case 5:
                    this.viewStuList(2);
                    break;
                case 6:
                    System.out.print("Please enter filename you wanna to Export: ");
                    filename = scanner.nextLine();
                    this.SaveStuList(filename);
                    break;
                case 7:
                    System.out.print("Please enter filename you wanna to Export: ");
                    filename = scanner.nextLine();
                    this.ExportStuListCSV(filename);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("syntax error, please enter again!");
            }
        }
    }
}
