import java.util.*;

public class StudentManager {
    public static void sortByGpa(List<Student> students){
        students.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
    }

    public static Student SearchStudent(List<Student> students,String sid){
        for(Student student: students){
            if(student.getId().equals(sid)){
                return student;
            }
        }
        return null;
    }

    public static Map<String,List<Student>> groupGender(List<Student> students){
        Map<String,List<Student>> genderMap=new HashMap<>();
        for(Student student:students){
            String gender=student.getGender();
            if(!genderMap.containsKey(gender)){
                genderMap.put(gender,new ArrayList<>());
            }
            genderMap.get(gender).add(student);
        }
        return genderMap;
    }
}
