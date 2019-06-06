package Adrenaline.Server.model;

public class Student {
    String name;
    int age;
    public Student(String n, int a ){
        name=n;
        age=a;
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }

    public boolean equals(Student x){
        if(this.name.equals(x.name) && this.age==x.age)
            return true;
        return false;
    }
}
