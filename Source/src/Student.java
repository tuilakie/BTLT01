import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;

/**
 * PACKAGE_NAME
 * Created by Nguyen Thanh Kien 19127456
 * Date 01/11/2021 - 11:18 CH
 * Description: ...
 */
public class Student implements Serializable {
    private String StuID;
    private String StuName;
    private float StuGPA;
    private String StuImage;
    private String StuAddress;
    private String StuNotes;

    public String getStuID() {
        return StuID;
    }

    public void setStuID(String stuID) {
        StuID = stuID;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public float getStuGPA() {
        return StuGPA;
    }

    public void setStuGPA(float stuGPA) {
        StuGPA = stuGPA;
    }

    public String getStuImage() {
        return StuImage;
    }

    public void setStuImage(String stuImage) {
        StuImage = stuImage;
    }

    public String getStuAddress() {
        return StuAddress;
    }

    public void setStuAddress(String stuAddress) {
        StuAddress = stuAddress;
    }

    public String getStuNotes() {
        return StuNotes;
    }

    public void setStuNotes(String stuNotes) {
        StuNotes = stuNotes;
    }

    public Student(){
        this.StuID = "";
        this.StuAddress = "";
        this.StuGPA = 0.0F;
        this.StuImage = "";
        this.StuName = "";
        this.StuNotes = "";
    }

    public Student(String stuID, String stuName, float stuGPA, String stuImage, String stuAddress, String stuNotes) {
        StuID = stuID;
        StuName = stuName;
        StuGPA = stuGPA;
        StuImage = stuImage;
        StuAddress = stuAddress;
        StuNotes = stuNotes;
    }
    public Student(Student st){
        this.StuID = st.getStuID();
        this.StuAddress = st.getStuAddress();
        this.StuGPA = st.getStuGPA();
        this.StuImage = st.getStuImage();
        this.StuName = st.getStuName();
        this.StuNotes = st.getStuNotes();
    }
    public void StuInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        this.setStuID(scanner.nextLine());
        System.out.print("Enter Student Name: ");
        this.setStuName(scanner.nextLine());
        System.out.print("Enter GPA of Student: ");
        String gpa = scanner.nextLine();
        this.setStuGPA(Float.parseFloat(gpa));
        System.out.print("Enter link address image of student: ");
        this.setStuImage(scanner.nextLine());
        System.out.print("Enter Student address: ");
        this.setStuAddress(scanner.nextLine());
        System.out.print("Enter the Notes: ");
        this.setStuNotes(scanner.nextLine());
    }
    public void StuOutput(){
        System.out.println("Student ID: " + this.StuID);
        System.out.println("Student Name: " + this.StuName);
        System.out.println("GPA: " + this.StuGPA);
        System.out.println("Image address: " + this.StuImage);
        System.out.println("Student address: " + this.StuAddress);
        System.out.println("Notes: " + this.StuNotes);
    }

    public static Student pareStudent(String Stustr){
        Student newStu = new Student();
        String[] Stu = Stustr.split(";");
        newStu.setStuID(Stu[0]);
        newStu.setStuName(Stu[1]);
        newStu.setStuGPA(Float.parseFloat(Stu[2]));
        newStu.setStuImage(Stu[3]);
        newStu.setStuAddress(Stu[4]);
        newStu.setStuNotes(Stu[5]);
        return newStu;
    }

    @Override
    public String toString(){
        String separate = ";";
        return getStuID() + separate + getStuName() + separate + getStuGPA() + separate + getStuImage() + separate + getStuAddress() + separate + getStuNotes();
    }
}