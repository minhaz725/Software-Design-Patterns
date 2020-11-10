package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Client
{
    void notification(String notice);
}

interface Server
{

    void sendNotice();
}


class RegularClient implements Client
{
    private String name;

    public String getName() {
        return name;
    }

    public RegularClient(String name) {
        this.name = name;
    }

    @Override
    public void notification(String notice) {
        System.out.println("Hello "+name +",\n" +notice);
    }
}

class PremiumClient implements Client
{

    private String name;
    public String getName() {
        return name;
    }

    public PremiumClient(String name) {
        this.name = name;
    }

    @Override
    public void notification(String notice) {
        System.out.println("Hello "+name +",\n" +notice);
    }
}

class ServerABC implements Server
{

    private int serverStatus;
    private int serverStatusPrev;

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getServerStatusPrev() {
        return serverStatusPrev;
    }

    public void setServerStatusPrev(int serverStatusPrev) {
        this.serverStatusPrev = serverStatusPrev;
    }

    List<PremiumClient> premiumClients = new ArrayList<>();
    List<RegularClient> regularClients = new ArrayList<>();

    public void addPremiumClient(PremiumClient premiumClient)
    {
        premiumClients.add(premiumClient);
    }
    public void addRegularClient(RegularClient regularClient)
    {
        regularClients.add(regularClient);
    }
    public void upgradeUser(RegularClient newClient)
    {
        PremiumClient newPremiumClient= new PremiumClient(newClient.getName());
        premiumClients.add(newPremiumClient);
        regularClients.remove(newClient);
    }


    public String noticeRegular()
    {
        if (serverStatusPrev==0 && serverStatus==1)
        {
            String msg="1.Continue to use limited edition\n2.Upgrade to premium for 20$\n";
            return msg;
        }
        else if (serverStatusPrev==0 && serverStatus==2)
        {
            {
                String msg="1.Upgrade to premium for 20$\n2.Exit\n";
                return msg;
            }
        }
        else if (serverStatusPrev==1 && serverStatus==0)
        {
            {
                String msg="Here is your bill: \nHours:60 Bill:400$ \n";
                return msg;
            }
        }
        else if (serverStatusPrev==1 && serverStatus==2)
        {
            {
                String msg="No new notification \n";
                return msg;
            }
        }

        else if (serverStatusPrev==2 && serverStatus==1)
        {
            {
                String msg="No new notification \n";
                return msg;
            }
        }
        else if (serverStatusPrev==2 && serverStatus==0)
        {
            {
                String msg="Here is your bill: \nHours:60 Bill:400$ \n";
                return msg;
            }
        }
        else return null;


    }

    public String noticePremium()
    {
        if (serverStatusPrev==0 && serverStatus==1)
        {
            String msg="1.Use both ABC & DEF \n2.Move completely to DEF \n";
            return msg;
        }
        else if (serverStatusPrev==0 && serverStatus==2)
        {
            {
                String msg="Service is now provided by DEF\n";
                return msg;
            }
        }
        else if (serverStatusPrev==1 && serverStatus==0)
        {
            {
                String msg="No new notification \n";
                return msg;
            }
        }
        else if (serverStatusPrev==1 && serverStatus==2)
        {
            {
                String msg="Service is now provided by DEF,all data copied to DEF \n";
                return msg;
            }
        }

        else if (serverStatusPrev==2 && serverStatus==1)
        {
            {
                String msg="No new notification \n";
                return msg;
            }
        }
        else if (serverStatusPrev==2 && serverStatus==0)
        {
            {
                String msg="No new notification \n";
                return msg;
            }
        }
        else return null;
    }

    @Override
    public void sendNotice() {
        String notice;
        System.out.println("Premium Client Notice:");
        for(PremiumClient premiumClient:premiumClients )
        {
            notice=noticePremium();
            premiumClient.notification(notice);
        }

        System.out.println("Regular Client Notice:");
        for(RegularClient regularClient:regularClients )
        {
            notice=noticeRegular();
            regularClient.notification(notice);
        }

    }


}

public class Main {

    public static void main(String[] args) {
        PremiumClient premiumClient = new PremiumClient("Pro-noob");
        RegularClient regularClient = new RegularClient("Pee-juice");
        ServerABC server= new ServerABC();
        server.addPremiumClient(premiumClient);
        server.addRegularClient(regularClient);
        server.setServerStatus(1);
        server.setServerStatusPrev(0);
       // server.upgradeUser(regularClient);
       // server.sendNotice();
        while (true)
        {
            System.out.println("Change server Status:\n0.Operational\n1.Partially Down\n2.Fully down");
            Scanner sc = new Scanner(System.in);
            int choice=sc.nextInt();
            if(choice==server.getServerStatus())
            {
                System.out.println("Already in this state");
            }
            else
            {
                server.setServerStatusPrev(server.getServerStatus());
                server.setServerStatus(choice);
                server.sendNotice();
            }
        }

	// write your code here
    }
}
