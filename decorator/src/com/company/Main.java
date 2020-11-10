package com.company;


import java.awt.*;

interface Cake
{
    public String getCost();
    public String Desciption();
}


abstract class Topping implements Cake
{
    protected Cake cakewithtop;
    public Topping(Cake cakewithtop)
    {
        this.cakewithtop=cakewithtop;
    }
    @Override
    public String getCost()
    {
        return cakewithtop.getCost();
    }

}

class Vanilla implements Cake
{

    @Override
    public String getCost() {
        return "60$";

    }

    @Override
    public String Desciption()
    {
        return "Vanilla Cake";
    }
}



class Chocolate implements Cake
{

    @Override
    public String getCost() {
        return "70";

    }
    @Override
    public String Desciption()
    {
        return "Chocolate Cake";
    }


}

class RedVelvet implements Cake
{

    @Override
    public String getCost() {
        return "80$";
    }
    @Override
    public String Desciption()
    {
        return "Redvelvet Cake";
    }
}

class Strawberry implements Cake
{

    @Override
    public String getCost() {

        return "90$";
    }
    @Override
    public String Desciption()
    {

        return "Strawberry Cake";
    }
}


class MoltenChocolate extends Topping
{


    public MoltenChocolate(Cake cakewithtop) {
        super(cakewithtop);

    }

    @Override
    public String getCost()
    {
        return cakewithtop.getCost()+"+10$";
    }

    @Override
    public String Desciption() {
        String temp=cakewithtop.Desciption();
        return temp+" with Moltenchocolate";
    }
}

class StrawberryPuree extends Topping
{


    public StrawberryPuree(Cake cakewithtop) {
        super(cakewithtop);
    }

    @Override
    public String getCost()
    {
        return cakewithtop.getCost()+"+15$";
    }

    @Override
    public String Desciption() {
        String temp=cakewithtop.Desciption();
        return temp+" with StrawberryPuree";
    }
}

class ButterCream extends Topping
{

    public ButterCream(Cake cakewithtop) {
        super(cakewithtop);
    }

    @Override
    public String getCost()
    {
        return cakewithtop.getCost()+"+20$";
    }

    @Override
    public String Desciption() {
        String temp=cakewithtop.Desciption();
        return temp+" with ButterCream";
    }

}

class Fondant extends Topping
{

    public Fondant(Cake cakewithtop) {
        super(cakewithtop);
    }

    @Override
    public String getCost()
    {
        return cakewithtop.getCost()+"+25$";
    }

    @Override
    public String Desciption() {
        String temp=cakewithtop.Desciption();
        return temp+" with Fondant";
    }

}






public class Main {

    public static void main(String[] args) {
        Cake cake1=new Vanilla();
        Cake cake2=new MoltenChocolate(cake1);
        Cake cake3=new Fondant(cake2);
        System.out.println(cake2.Desciption());
        System.out.println(cake2.getCost());
        System.out.println(cake3.Desciption());
        System.out.println(cake3.getCost());

	// write your code here
    }
}
