package com.company;


import java.util.*;

interface Component
{
    void showList();
    void showDetails();


}

class Leaf implements Component
{

    String name;
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Leaf(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void showList() {

        for(int i=0;i<Composite.getCompCount();i++) {System.out.print("---");}
        System.out.println(name+"."+type);

    }

    @Override
    public void showDetails() {

    }


}

class Composite implements Component {

    String name;
    String type;
    static int compCount;
    int leafcount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getCompCount() {
        return compCount;
    }

    public void setCompCount(int compCount) {
        this.compCount = compCount;
    }

    public Composite(String name, String type) {
        this.name = name;
        this.type = type;
        if(type.equalsIgnoreCase("drive")) drivelist.add(this);
    }


    List<Component> componentList = new ArrayList<>();
    static List<Composite> drivelist = new ArrayList<>();
    static Hashtable<String,Component> componentHashtable = new Hashtable<>();
    static Hashtable<Component,Component> motherHashtable = new Hashtable<>();
    public void addComponent(Component com,String name,String type)
    {
        componentList.add(com);
        componentHashtable.put(name,com);
        motherHashtable.put(com,this);
        if(!type.equalsIgnoreCase("folder"))
        {
            leafcount++;
        }

    }

    public void removeComponent(Component com,String name)
    {
        componentList.remove(com);
        componentHashtable.remove(name,com);
        motherHashtable.remove(com,this);

    }

    @Override
    public void showList() {


        if(type=="Drive") {System.out.println(name + ":\\");}
        else { for(int i=0;i<compCount;i++) System.out.print("---");
            System.out.println(name);}
        for(Component c:componentList)
        {
            compCount++;
            //System.out.println("entered:"+ name + "  " +compCount);
                c.showList();

            //System.out.println("exit"+compCount);
            compCount--;

        }

    }

    @Override
    public void showDetails() {


    }
}

class Example
{
    public static void view() {


    }
}


public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Current Directory: ");
        Component a= new Leaf("rain","mp3");
        Component b= new Leaf("groove","mp3");
        Component c= new Leaf("sun","mp4");

        Composite d= new Composite("mp3","Folder");
        Composite e= new Composite("mp4","Folder");

        d.addComponent(a,"rain","mp3");
        d.addComponent(b,"groove","mp3");
        e.addComponent(c,"sun","mp4");

        Component f=new Leaf("fly","flv");
        Composite g=new Composite("Music","Folder");

        g.addComponent(d,"mp3","Folder");
        g.addComponent(e,"mp4","Folder");
        g.addComponent(f,"fly","flv");

        Composite h=new Composite("C","Drive");

        h.addComponent(g,"Music","Folder");

        h.showList();
     while (true)
     {
         System.out.println("1.Show Current Directory");
         System.out.println("2.Add new folder or drive");
         System.out.println("3.Delete existing folder or drive");
         System.out.println("4.Show Details");
         System.out.println("5.Exit");
         Scanner sc = new Scanner(System.in);
         int choice = sc.nextInt();
         if(choice==1) {
             Iterator iterator = Composite.drivelist.iterator();
             int flag = 0;
             while (iterator.hasNext()) {
                 Composite drive = (Composite) iterator.next();
                 drive.showList();
             }
         }
         if(choice==2)
         {
             System.out.println("Enter the type:");
             Scanner sc1=new Scanner(System.in);
             String newtype=sc1.nextLine();
             if(newtype.equalsIgnoreCase("folder")) {
                 System.out.println("Enter folder name");
                 String newname=sc1.nextLine();
             //    System.out.println(newtype + " " + newname);
                 Composite newcom=new Composite(newname,newtype);
                 System.out.println("Enter the name of folder or drive where you want to add");
                 String place = sc1.nextLine();

                 Iterator iterator = Composite.drivelist.iterator();
                 int flag=0;
                 while (iterator.hasNext()){
                     Composite drive =(Composite) iterator.next();
                //     System.out.println(drive.getName());
                     //System.out.println(drive.componentHashtable.toString());
                    if (drive.getName().equalsIgnoreCase(place)==true) {
                     //System.out.println("found");

                     drive.addComponent(newcom, newname,newtype);
                     //drive.showList();
                     flag=1;
                     break;
                    }
                    else if(drive.componentHashtable.containsKey(place) == true)
                    {
                     //System.out.println("found");
                     Composite placeObj = (Composite) drive.componentHashtable.get(place);
                     placeObj.addComponent(newcom, place,newtype);
                     //drive.showList();
                     flag=1;
                     break;
                    }
                 }
                 if(flag==0) System.out.println("Invalid folder name,please try again");
             }
             else if(newtype.equalsIgnoreCase("drive"))
             {
                 System.out.println("Enter the new Drive letter");
                 char l = sc1.next().charAt(0);
                 String letter = Character.toString(l);
                 Iterator iterator = Composite.drivelist.iterator();
                 int flag=0;
                 while (iterator.hasNext()) {
                     Composite drive = (Composite) iterator.next();
                     if (letter.equalsIgnoreCase(drive.getName()))
                     {System.out.println("Drive already exist.");flag=1;break;}
                 }

                 if(flag==0)
                 {
                     Composite newdrive=new Composite(letter,"Drive");
                 //    h.showList();
                   //  newdrive.showList();
                 }
             }
             else {
                 System.out.println("Invalid option.");
             }
         }
         else if(choice==3)
         {
             System.out.println("Enter the name of folder or drive you want to remove");
             Scanner sc1 = new Scanner(System.in);
             String place = sc1.nextLine();
             //System.out.println(place);

             Iterator iterator = Composite.drivelist.iterator();
             int flag=0;
             int i=0;
             while (iterator.hasNext()) {
                 Composite drive = (Composite) iterator.next();
                 //System.out.println(drive.getName());
                 //System.out.println(drive.componentHashtable.toString());
                 if (drive.getName().equalsIgnoreCase(place) == true) {
                     //System.out.println("found");

                     Composite.drivelist.remove(i);
                     //drive.showList(i);
                     flag = 1;
                     break;
                 }
                 i++;
             }
          if(flag==0) {
              if (Composite.componentHashtable.containsKey(place) == true) {
                  //System.out.println("found");
                  Composite placeObj = (Composite) h.componentHashtable.get(place);
                  Composite first = (Composite) h.motherHashtable.get(placeObj);
                  //  System.out.println(first.getName());
                  first.removeComponent(h.componentHashtable.get(place), place);
                  //   h.showList();
              } else System.out.println("Invalid folder name,please try again");
          }
         }

         else if(choice==4)
         {
             System.out.println("Enter the name of folder or drive");
             Scanner sc1 = new Scanner(System.in);
             String place = sc1.nextLine();
             System.out.println(place);

             if (Composite.componentHashtable.containsKey(place) == true) {
                 System.out.println("Details of "+place);
                 Composite placeObj = (Composite) h.componentHashtable.get(place);
                 System.out.println("Name:"+place);
                 System.out.println("Type:"+placeObj.getType());
                 Composite first = (Composite) h.motherHashtable.get(placeObj);
                 String dir=placeObj.getName();
                 while (first!=null)
                 {
                     if(first.getType().equalsIgnoreCase("drive")) dir=first.getName()+":\\"+dir;
                     else dir=first.getName()+"\\"+dir;
                     first=(Composite) h.motherHashtable.get(first);
                 }
                 System.out.println(dir);
                 int comp=placeObj.leafcount;
                 Iterator iterator = placeObj.componentList.iterator();
                 int flag = 0;
                 while (iterator.hasNext()) {

                     Composite temp;
                     try{
                         temp = (Composite) iterator.next();
                        } catch (Exception ex) {
                         continue;
                     }
                     comp=comp+temp.leafcount;
                 }
                 System.out.println("Component Count: "+ comp);

                 System.out.println();
             } else System.out.println("Invalid folder name,please try again\n");
         }

         if(choice==5) break;


     }


    }
}
