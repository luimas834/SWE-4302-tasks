import java.util.*;

public class Main {
    public static void main(String[] args) {
        Student s1 =new Student("1", "alice", 3.5, "male");
        Student s2 =new Student("2", "karim", 3.7, "male");
        Student s3 =new Student("3", "browny", 3.9, "female");

        DataStore<Student> slist =new DataStore<>();
        slist.addItem(s1);
        slist.addItem(s2);
        slist.addItem(s3);
        System.out.println(slist.getItem());
        List<Student> theList =slist.getItem();

        Student foundStudent = StudentManager.SearchStudent(theList, "1");
        if (foundStudent != null) {
            System.out.println("Found them: " + foundStudent.getName());
        } else {
            System.out.println("Student not found.");
        }
        System.out.println("before sorting: ");
        for(Student s:theList){
            System.out.println("name: "+s.getName()+" cgpa: "+s.getCgpa());
        }
        StudentManager.sortByGpa(theList);
        System.out.println("after sorting: ");
        for(Student s:theList){
            System.out.println("name: "+s.getName()+" cgpa: "+s.getCgpa());
        }
        Map<String,List<Student>> genderlist=new HashMap<>();

        genderlist=StudentManager.groupGender(theList);

        System.out.println(genderlist);
    }
}
