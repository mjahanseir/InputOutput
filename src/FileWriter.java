import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWriter {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<StudentClass> studentClasses = new ArrayList<>();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/data.studat"))) {
            outputStream.writeObject(new StudentClass(1, "SDC", "Mark", "Albert", "Mark565@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(2, "SDC", "Richard", "belmont", "richard@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(3, "SDC", "Mike", "McMory", "macmory@gmail.com"));
            outputStream.writeObject(new StudentClass(4, "SDC", "William", "Winner", "william.winner@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(5, "SDC", "Alexina", "Zinn", "alexina.zinn@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(6, "SDC", "Kenn", "Hollon", "kenn.hollon@gmail.com"));
            outputStream.writeObject(new StudentClass(7, "SDC", "Roydon", "Mary", "raydon.mary@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(8, "SDC", "Ourania", "Hinds", "ourania.hinds@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(9, "SDC", "Nerissa", "Hinze", "nerissa.hinze@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(10, "SDC", "Delanna", "Nalls", "delanna.nalls@gmail.com"));
            outputStream.writeObject(new StudentClass(11, "SDC", "Bly", "Rascon", "bly.racson@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(12, "SDC", "Caroline", "Mak", "caroline.mak@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(13, "SDC", "Kirstie", "Runyon", "kirstie.runyon@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(14, "SDC", "Giovanni", "Layden", "giovanni.layden@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(15, "SDC", "Deniz", "Simonsen", "deniz.simonsen@gmail.com"));
            outputStream.writeObject(new StudentClass(16, "SDC", "bill", "Mary", "bill.mary@gmail.com"));
            outputStream.writeObject(new StudentClass(17, "SDC", "Paula", "Bown", "paula.bown@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(18, "SDC", "Lynnette", "Stallman", "lynette.stallman@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(19, "SDC", "Fenwick", "Copple", "fenwick.copple@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
            outputStream.writeObject(new StudentClass(20, "SDC", "Frank", "Young", "frank.young@gmail.com", new ArrayList<>(Arrays.asList("COSC", "CWEB"))));
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/data.studat"))) {
           while (true){
               studentClasses.add((StudentClass) objectInputStream.readObject());
           }
        } catch (EOFException ex) {
            System.out.println("File scan completed!");
        }

        for (StudentClass studentClass : studentClasses
        ) {
            System.out.println(studentClass.getFirstName());
        }
    }

}
