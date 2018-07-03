package sieciowka;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Klient 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
        int a = 0;
        Scanner sc = new Scanner(System.in);
        int numerIndeksu;
        for(;;)
        {
            System.out.print("Podaj numer indeksu studenta: "); 
            try
            {
                numerIndeksu = sc.nextInt();
            }
            catch(InputMismatchException exc)
            {
                System.out.println("Blad danych! Nie podano cyfry.");
                sc.next();
                continue;
            }
            break;
        }
        
        System.out.println("Czekam na polaczenie");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Polaczony z serwerem");
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println(numerIndeksu);
        pw.flush();
        System.out.println("Wysłano numer");
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Student sReceive = (Student)in.readObject();
        System.out.println("Odebrano obiekt");
        
        System.out.println(sReceive);
        
        System.out.print("\nCzy chcesz dodać ocenę ? (T/N): ");
        String odp = sc.next();
        double ocenaDoDodania;
        if(odp.equals("T") || odp.equals("t"))
        {
            for(;;)
            {
                System.out.print("Podaj ocene jaka chcesz dodac: "); 
                try
                {
                    ocenaDoDodania = sc.nextDouble();
                }
                catch(InputMismatchException exc)
                {
                    System.out.println("Blad danych! Nie podano cyfry.");
                    sc.next();
                    continue;
                }
                break;
            }
            
            sReceive.dodajOcene(ocenaDoDodania);
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sReceive);
            out.flush();
            System.out.println("Wysłano obiekt");
        }
        
        socket.close();
        System.out.println("Rozlaczono");
    }
}
