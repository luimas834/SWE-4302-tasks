public class Student{
    public String id;
    public String name;
    public double cgpa;
    public String gender;

    public Student(String id,String name,double cgpa,String gender){
        this.id=id;
        this.name=name;
        this.cgpa=cgpa;
        this.gender=gender;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getCgpa(){
        return this.cgpa;
    }
    public String getGender(){
        return this.gender;
    }

    @Override
    public String toString(){
        return name +" ("+id+") -cgpa: "+cgpa;
    }

}