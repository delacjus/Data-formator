
/**
 * CPS2231-04
 * @author Justin De la Cruz 
 * delacjus@kean.edu
 */
import java.util.*;

import java.io.*;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class delacjus_Program5 {

    private static Animal[] clients;

    public static void main(String[] args) {
        input();
        sort();
        report();
    }
//Reads the file and puts it into a Array
    public static void input() {
        Scanner input=null;
        try {
            URL url = new URL("http://imc.kean.edu/CPS2231/program5.txt");
            
            input = new Scanner(url.openStream());
            
            clients = new Animal[Integer.parseInt(input.next())];
            String name;
            int bYear;
            int bBalance;
            String species;
            String blood;
            int legs;
            String temp;
            char t1;
            for (int i=0;i<clients.length;i++){
                name=input.next();
                bYear=Integer.parseInt(input.next());
                bBalance=Integer.parseInt(input.next());
                species=input.next();
                temp=input.next();
                t1=temp.charAt(0);
                if (Character.isDigit(t1)){
                    legs=Character.getNumericValue(t1);
                    Mammal m = new Mammal(name,bYear,bBalance,species,legs);
                    clients[i]=m;
                }
                else{
                    blood=temp;
                    nonMammal nM = new nonMammal(name,bYear,bBalance,species,blood);
                    clients[i]=nM;
                }    
            }    
        } catch (MalformedURLException ex) {
            Logger.getLogger(delacjus_Program5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(delacjus_Program5.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            input.close();
        }
    }
// sort the objects by Balance

    public static void sort() {// selection sort
        Animal temp= new Animal();
        int mamloc;
        for (int i = 0; i < clients.length; i++) {
            temp=clients[i];
            mamloc=i;
            for (int j = i + 1; j < clients.length; j++) {
                if (clients[j].compareTo(temp)==1)
                {
                temp=clients[j];
                mamloc=j;
                }
            }
            clients[mamloc]=clients[i];
            clients[i]=temp;
        }
    }

// print out report, 40 Animals per page
    public static void report() {
        int pageNo = 0;
        int lineNo = 0;
        for (int i = 0; i < clients.length; i++) {
            if ( lineNo % 40 == 0){
                pageNo++;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Page #"+pageNo+" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
            lineNo++;
            System.out.println(clients[i].toString());
        }
    }
}

class Animal {
    private String name;
    private int bYear;
    private int bBalance;
    private String species;
    //private String sFeature;
    public Animal(String name,int bYear,int bBalance,String species){
        setName(name);
        setBirthYear(bYear);
        setBillBalance(bBalance);
        setSpecies(species);
    }

    Animal() {
    }
    public String toString() { 
        return ("Name: "+getName()+", Birth Year: "+getBirthYear()+", Bill Balance "+getBillBalance()+", Animal Species: "+getSpecies());
    }

    public void setName(String name){
        this.name=name;
    }    
    public String getName(){
        return name;
    }
    public void setBirthYear(int bYear){
        this.bYear=bYear;
    }
    public int getBirthYear(){
        return bYear;
    }
    public void setBillBalance(int bBalance){
        this.bBalance=bBalance;
    }
    public int getBillBalance(){
        return bBalance;
    }
    public void setSpecies(String species){
        this.species=species;
    }    
    public String getSpecies(){
        return species;
    }
    // TODO: compare by bill balance, return 0 if ==, return 1 is >, otherwise, return -1
    public int compareTo(Animal a) {
        if (this.getBillBalance()==a.getBillBalance()){
            return 0;
        }
        else if(a.getBillBalance()>this.getBillBalance()){
            return 1;
        }
        else 
            return -1;
    }
}

class Mammal extends Animal { 
    private int legs;
    
    public Mammal(String name,int bYear,int bBalance,String species,int legs){
        super(name,bYear,bBalance,species);
        setNumLegs(legs);
    }
    public String toString() {
        return ("Name: "+getName()+", Birth Year: "+getBirthYear()+", Bill Balance "+getBillBalance()+", Animal Species: "+getSpecies()+", Number Of Legs: "+getNumLegs());
    }
    public void setNumLegs(int legs){
        this.legs=legs;
    }
    public int getNumLegs(){
        return legs;
    }
}

class nonMammal extends Animal { 
    private String blood;
    
    public nonMammal(String name,int bYear,int bBalance,String species,String blood){
        super(name,bYear,bBalance,species);
        setBloodType(blood);
    }
    public String toString() { 
        return ("Name: "+getName()+", Birth Year: "+getBirthYear()+", Bill Balance "+getBillBalance()+", Animal Species: "+getSpecies()+", Blood Type: "+getBloodType());
    }
    
    public void setBloodType(String blood){
        this.blood=blood;
    }
    public String getBloodType(){
        return blood;
    }
    
}