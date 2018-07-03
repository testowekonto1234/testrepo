package sieciowka;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Serwer 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
        ArrayList<Student> myList = generateData(); 
        while(true)
        {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Czekam na klienta");
            Socket socket = serverSocket.accept();
            System.out.println("Polaczony z klientem");
            Scanner sc = new Scanner(socket.getInputStream());

            String line = sc.nextLine();
            System.out.println("Odczytano: "+Integer.valueOf(line));
            
            Student sSend = findStudent(Integer.valueOf(line), myList);
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sSend);
            out.flush();
            System.out.println("Wysłano obiekt");
            
            if(socket.isClosed())
            {
                System.out.println("Zerwane polaczenie z klientem");
                serverSocket.close();
                System.out.println("Rozlaczono klienta");
            }
            else
            {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Student sReceive = (Student)in.readObject();
                System.out.println("Odebrano obiekt");
                compareAndUpdateOceny(sReceive, myList);
                System.out.println("Zaktualizowano obiekt");
                serverSocket.close();
                System.out.println("Rozlaczono klienta");
            }
        }
    }
    
    public static ArrayList<Student> generateData()
    {
        ArrayList<Student> myList = new ArrayList<Student>();
        Student s = new Student("Jan", "Kowalski", 202020);
        s.dodajOcene(5.0);
        s.dodajOcene(4.5);
        s.dodajOcene(4.0);
        myList.add(s);
        s = new Student("Stefan", "Nowak", 202021);
        s.dodajOcene(4.0);
        s.dodajOcene(5.0);
        s.dodajOcene(3.5);
        myList.add(s);
        s = new Student("Tomasz", "Gallus", 202022);
        s.dodajOcene(3.0);
        s.dodajOcene(3.5);
        s.dodajOcene(5.0);
        s = new Student("Adam", "Kowal", 202023);
        s.dodajOcene(5.0);
        s.dodajOcene(5.0);
        s.dodajOcene(3.5);
        myList.add(s);
        s = new Student("Bartosz", "Nowacki", 202024);
        s.dodajOcene(4.0);
        s.dodajOcene(3.5);
        s.dodajOcene(5.0);
        s = new Student("Michał", "Anioł", 202025);
        s.dodajOcene(3.5);
        s.dodajOcene(4.0);
        s.dodajOcene(3.5);
        myList.add(s);
        s = new Student("Łukasz", "Niewidamski", 202026);
        s.dodajOcene(4.0);
        s.dodajOcene(4.0);
        s.dodajOcene(4.0);
        s = new Student("Mateusz", "Stefanowski", 202027);
        s.dodajOcene(5.0);
        s.dodajOcene(5.0);
        s.dodajOcene(5.0);
        myList.add(s);
        s = new Student("Wojciech", "Podolski", 202028);
        s.dodajOcene(3.0);
        s.dodajOcene(3.0);
        s.dodajOcene(3.0);
        s = new Student("Antoni", "Kiejkut", 202029);
        s.dodajOcene(3.5);
        s.dodajOcene(3.5);
        s.dodajOcene(3.5);
        myList.add(s);
        s = new Student("Kamil", "Sawicki", 202030);
        s.dodajOcene(4.5);
        s.dodajOcene(4.5);
        s.dodajOcene(4.5);
        
        return myList;
    }

    private static Student findStudent(Integer numIndeksu, ArrayList<Student> myList) 
    {
        Student st = null;
        for(Student s : myList)
        {
            if(s.getNumerIndeksu().equals(numIndeksu))
            {
                st = s;
                break;
            }
        }
        
        return st;
    }

    private static void compareAndUpdateOceny(Student sReceive, ArrayList<Student> myList) 
    {
        for(Student s : myList)
        {
            if(s.getNumerIndeksu().equals(sReceive.getNumerIndeksu()))
            {
                int diff = sReceive.getOceny().size() - s.getOceny().size();
                if(diff == 0)
                {
                    System.out.println("Nie zmieniono ocen");
                    break;
                }
                else
                {
                    int value = s.getOceny().size();
                    for(int i = value; i < value+diff; i++)
                        s.getOceny().add(sReceive.getOceny().get(i));
                    System.out.println("Zmieniono: "+s);
                    break;
                }
            }
        }
    }
}
