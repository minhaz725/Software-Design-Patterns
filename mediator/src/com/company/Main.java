package com.company;

import java.util.*;

interface Mediator {
     void sendMessage(String message, Tsc tsc);
     void addParticipant(Tsc tsc);
}

interface Tsc {
     void send(String message);

     void receive(String message);
}


class Student implements Tsc {

private ExamController controller;
private int id;

public Student(ExamController controller, int id){
        this.controller = controller;
        this.id = id;
        this.controller.addParticipant(this);
        }

@Override
public void send(String message) {
        System.out.println("Student"+this.getId() + " is requesting for a recheck ");
        controller.sendMessage(message, this);
        }

@Override
public void receive(String message) {
        System.out.println("Student"+this.id + "'s marks in CSE316: " + message);
        }

public int getId() {
        return id;
        }

public void setName(int id) {
        this.id = id;
        }


}


class Teacher implements Tsc {

private ExamController controller;
private int id;

public Teacher(ExamController controller, int id){
        this.controller = controller;
        this.id = id;
        this.controller.addParticipant(this);
        }

@Override
public void send(String message) {

        System.out.println("Teacher" + this.getId() + " sent marks of student " + controller.getStudents(message) + "to Exam Controller");
        controller.sendMessage(message, this);
        }
public void sendRechecked(String message) {

        System.out.println("Teacher" + this.getId() + " sent rechecked marks of student " + controller.getStudents(message) + "to Exam Controller");
        controller.sendRecheck(message,this);
        }

@Override
public void receive(String message) {
        System.out.println("Teacher"+this.id + " got recheck request from student id " + message);
        }

public int getId() {
        return id;
        }

public void setName(int id) {
        this.id = id;
        }


}

class ExamController implements Mediator {

    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();

    public String getStudents(String marks)
    {
        StringTokenizer str=new StringTokenizer(marks,",");
        int stm;
        Iterator iterator = studentList.iterator();
        String msg="";
        while (iterator.hasNext())
        {
            stm=Integer.parseInt(str.nextToken());
            Student temp=(Student) iterator.next();
            String st=Integer.toString(temp.getId());
            if(stm >0) {
                if (!iterator.hasNext()) msg = msg + st + " ";
                else msg = msg + st + ",";
            }

        }

        return msg;
    }
    @Override
    public void sendMessage(String message, Tsc tsc) {

        StringTokenizer st= new StringTokenizer(message,",");

        if(tsc instanceof Teacher) {
            for (Tsc p : studentList) {
                if (p != tsc) {

                     p.receive(st.nextToken());

                    }
            }
        }
        else for (Tsc p : teacherList) {
            if (p != tsc) {
                p.receive(message);
                }

        }

    }

    public void sendRecheck(String message, Tsc tsc) {

        StringTokenizer st = new StringTokenizer(message, ",");

        if (tsc instanceof Teacher) {
            for (Tsc p : studentList) {

                    Random random = new Random();
                    int result = random.nextInt(2);
                    String marks=st.nextToken();
                    int tempmarks=Integer.parseInt(marks);

                    if(Integer.parseInt(marks)<0) {p.receive("Didn't requested for recheck");continue;}
                    if (result == 0) p.receive(marks+",marks didn't changed");
                    else {

                        int tempint = tempmarks;
                        tempint = tempint + 5;
                        marks = Integer.toString(tempint);
                        p.receive("was corrected.Previous marks:"+Integer.toString(tempmarks) +".Corrected marks: " + marks);
                    }

            }
        }
    }





    @Override
    public void addParticipant(Tsc  tsc) {
        if (tsc instanceof Student) studentList.add((Student) tsc);
        else teacherList.add((Teacher)tsc);
    }

}


public class Main {

    public static void main(String[] args) {
        ExamController controller=new ExamController();
        Student s1=new Student(controller,1);
        Student s2=new Student(controller,2);
        Student s3=new Student(controller,3);
        Student s4=new Student(controller,4);
        Student s5=new Student(controller,5);
        Teacher t1=new Teacher(controller,1);
        String marks= "70,80,90,50,60";
        t1.send(marks);
        Scanner sc= new Scanner(System.in);
        int arr[]=new int[5];
        Student s[]=new Student[5];
        s[0]=s1;s[1]=s2;s[2]=s3;s[3]=s4;s[4]=s5;
        StringTokenizer st= new StringTokenizer(marks,",");
        for(int i=1;i<=5;i++) {
            int choice;
            System.out.println("Student" + i + ",do you wanna recheck?If yes press 0,if no press 1.");
            choice = sc.nextInt();
            if(choice==0) {arr[i-1]=Integer.parseInt(st.nextToken());s[i-1].send(Integer.toString(s[i-1].getId()));}
            else if(choice==1) {arr[i-1]=-1;st.nextToken();}
            else {System.out.println("Invalid choice,counted as no.");arr[i-1]=-1;}
        }

        marks="";
        for(int i=0;i<arr.length;i++)
        {
            if(i==arr.length-1) marks=marks+Integer.toString(arr[i]);
            else marks=marks+Integer.toString(arr[i])+",";
        }
        //System.out.println(marks);
        t1.sendRechecked(marks);
	// write your code here
    }
}
