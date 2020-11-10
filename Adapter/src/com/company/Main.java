package com.company;


import java.util.*;
import java.util.ArrayList;


interface MediaPlayer
{
    public void play(Files files);
}



 class Files  {
    private String name,type;
    private float size,duration; // size in MB,duration in min

    public Files(String name,float size, float duration) {
        String [] temp = name.split("[.]+");
        String [] arrOfString=new String[2];

        int i=0;
        for (String a : temp){
            arrOfString[i]=a;
            i++;
        }
        this.name = arrOfString[0];
        this.type = arrOfString[1];
        this.size = size;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Files setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Files setType(String type) {
        this.type = type;
        return this;
    }

    public float getSize() {
        return size;
    }

    public Files setSize(int size) {
        this.size = size;
        return this;
    }

    public float getDuration() {
        return duration;
    }

    public Files setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}



class Mp4
{
    public void playMp4(Files file) {
        System.out.print("Playing mp4 file.     ");
        System.out.println("Name : "+file.getName()+"   |   Type : "+file.getType()+"   |   Size : "+file.getSize()+"MB   |   Duration : "+file.getDuration()+"min");
    }

}

class Flv
{
    public void playFlv(Files file) {
        System.out.print("Playing flv file.     ");
        System.out.println("Name : "+file.getName()+"   |   Type : "+file.getType()+"   |   Size : "+file.getSize()+"MB   |   Duration : "+file.getDuration()+"min");
    }

}

class Vlc
{
    public void playVlc(Files file) {
        System.out.print("Playing vlc file.     ");
        System.out.println("Name : "+file.getName()+"   |   Type : "+file.getType()+"   |   Size : "+file.getSize()+"MB   |   Duration : "+file.getDuration()+"min");
    }

}


class Adapter implements MediaPlayer {


    @Override
    public void play(Files files) {
        if(files.getType().equalsIgnoreCase("vlc"))
        {
            Vlc vlcfile=new Vlc();
            vlcfile.playVlc(files);
        }
        else if(files.getType().equalsIgnoreCase("flv"))
        {
            Flv flvfile=new Flv();
            flvfile.playFlv(files);
        }
        else if(files.getType().equalsIgnoreCase("mp4"))
        {
            Mp4 mp4file=new Mp4();
            mp4file.playMp4(files);
        }
    }
}


 class AudioPlayer implements MediaPlayer {
    Adapter adapter=new Adapter();

    @Override
    public void play(Files file) {

        if(file.getType().equalsIgnoreCase("mp3")){
            System.out.print("Playing mp3 file.     ");
            System.out.println("Name : "+file.getName()+"   |   Type : "+file.getType()+"   |   Size : "+file.getSize()+"MB   |   Duration : "+file.getDuration()+"min");
        }

        else if(file.getType().equalsIgnoreCase("vlc") || file.getType().equalsIgnoreCase("flv") || file.getType().equalsIgnoreCase("mp4")){
            adapter.play(file);
        }

        else{
            System.out.println("Invalid media. " + file.getType() + " format not supported");
        }
    }
}



 class PlayList {
    List<Files> playList = new ArrayList();

    public List<Files> getPlayList() {
        return playList;
    }

    void addFile(Files file){
        playList.add(file);
    }
    void printList(){
        Iterator<Files> iterator = playList.iterator();
        while (iterator.hasNext()){
//            System.out.print("\t\t------");
            Files tempFile = iterator.next();
            System.out.println("Name : "+tempFile.getName()+"   |   Type : "+tempFile.getType()+"   |   Size : "+tempFile.getSize()+"MB   |   Duration : "+tempFile.getDuration()+"min");
        }
    }

    float totalDuration(){
        float time=0;
        Iterator<Files> iterator = playList.iterator();
        while (iterator.hasNext()){
//            System.out.print("\t\t------");
            Files tempFile = iterator.next();
            time=time+tempFile.getDuration();
        }
        return time;
    }

}






public class Main {

    public static void main(String[] args) {

        AudioPlayer audioPlayer = new AudioPlayer();
        Files file1 = new Files("All of me.mp3",4,3);
        Files file2 = new Files("Perfect.mp4",6,5);
        Files file3 = new Files("Senorita.vlc",6,4);
        Files file4 = new Files("can't help falling in love.avi",5,5);
        List<PlayList> arrayOfPlayList = new ArrayList();
        int start,playListIndex=-1;
        float elapsedTime;
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.print("1. Create PlayList\n2. Add files\n3. View PlayList\n>>>");
            start = myObj.nextInt();
            if (start == 1) {
                if (playListIndex == -1) {
                    playListIndex++;
                    PlayList tempPlayList = new PlayList();
                    arrayOfPlayList.add(tempPlayList);
                    System.out.println("PlayList Created");
                } else {
                    System.out.print("Elapsed Time: ");
                    elapsedTime = myObj.nextFloat();
                    if (arrayOfPlayList.get(playListIndex).totalDuration() >= elapsedTime) {
                        int i = 1;
                        System.out.println("Play List : ");
                        List<Files> tempFileList = arrayOfPlayList.get(playListIndex).getPlayList();
                        Iterator<Files> iterator = tempFileList.iterator();
                        while (iterator.hasNext()) {
                            Files tempFile = iterator.next();
                            System.out.print("#" + i + " ");
                            audioPlayer.play(tempFile);
                            i++;
                        }

                        System.out.println("Current playList Duration : " + arrayOfPlayList.get(playListIndex).totalDuration() + "  and   Elapsed Time : " + elapsedTime);
                        System.out.println("New PlayList can not be added");
                    } else {
                        playListIndex++;
                        PlayList tempPlayList = new PlayList();
                        arrayOfPlayList. add(tempPlayList);
                        System.out.println("PlayList Created");
                    }
                }
            }

            else if(start==2){
                if(playListIndex>-1){
                    String name;
                    float size,duration;
                    System.out.print("Name: ");
                    name = myObj.next();
                    System.out.print("Size: ");
                    size = myObj.nextFloat();
                    System.out.print("Duration: ");
                    duration = myObj.nextFloat();
                    Files tempFile = new Files(name,size,duration);
                    arrayOfPlayList.get(playListIndex).addFile(tempFile);
                }
                else{
                    System.out.println("No PlayList");
                }
            }
            else if(start==3){
                int i=1;
                if(playListIndex>-1){
                    System.out.println("Play List : ");
                    List<Files> tempFileList = arrayOfPlayList.get(playListIndex).getPlayList();
                    Iterator<Files> iterator = tempFileList.iterator();
                    while (iterator.hasNext()){
//            System.out.print("\t\t------");
                        Files tempFile = iterator.next();
                        System.out.print("#"+i+" ");
                        audioPlayer.play(tempFile);
                        i++;
                    }
                }
                else{
                    System.out.println("No PlayList");
                }
            }




        }

    }
}
