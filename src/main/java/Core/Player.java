package Core;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private String club;
    private String position;
    private int age;
    private int number;
    private long salary;
    private double height;
    private int sold;

    public Player(){}
    public Player(String name, String country, int age, double height, String club, String position, int number, long salary, int sold) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.salary = salary;
        this.sold = sold;
    }

    public String save(){
        if(number == 0){
            return (name+","+country+","+age+","+height+","+club+","+position+","+","+salary+","+sold+"\n");
        }
        else {
            return (name+","+country+","+age+","+height+","+club+","+position+","+number+","+salary+","+sold+"\n");
        }
        
    }

    public String countryrow(){
        if(number == 0){
            return (country+", "+position);
        }
        else {
            return (country+", "+position+", "+number);
        }
    }

    public String printing(){
        if(number == 0){
            return ("\nName: "+name+"\n Country: "+country+"\n Age: "+age+"\n Height: "+height+"\n Club: "+club+"\n Position: "+position+"\n Weekly Salary: "+salary);
        }
        else{
            return ("\nName: "+name+"\n Country: "+country+"\n Age: "+age+"\n Height: "+height+"\n Club: "+club+"\n Position: "+position+"\n Jersey Number: "+number+"\n Weekly Salary: "+salary);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return this.club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getSalary() {
        return this.salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getSold(){return this.sold;}

    public void setSold(int sold){this.sold = sold;}

}