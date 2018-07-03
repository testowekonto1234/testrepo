package sieciowka;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable
{
    String imie;
    String nazwisko;
    Integer numerIndeksu;
    ArrayList <Double> oceny;

    private Student()
    {
        oceny = new ArrayList<Double>();
    }
    
    public Student(String imie, String nazwisko, Integer numerIndeksu) 
    {
        this();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerIndeksu = numerIndeksu;
    }
    
    public void dodajOcene(Double ocena)
    {
        oceny.add(ocena);
    }
    
    @Override
    public String toString() 
    {
        String s = "Student " + "imie: " + imie + ", nazwisko: " + nazwisko + ", numerIndeksu: " + numerIndeksu+ ", oceny: [ ";
        String ocenny = "";
        for(Double d : oceny)
        {
            ocenny = ocenny+d+" ";
        }
        s = s + ocenny + "]";
        return s;
    }

    public String getImie() 
    {
        return imie;
    }

    public String getNazwisko() 
    {
        return nazwisko;
    }

    public Integer getNumerIndeksu() 
    {
        return numerIndeksu;
    }

    public ArrayList<Double> getOceny() 
    {
        return oceny;
    } 
}
