package home.alizhan.alizh;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.connect();

        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("[1] ADD USER");
            System.out.println("[2] LIST USERS");
            System.out.println("[3] UPDATE USER");
            System.out.println("[4] DELETE USER");
            System.out.println("[0] EXIT");

            String choice = scan.next();
            if(choice.equals("1")){
                System.out.println("Insert name: ");
                String name = scan.next();
                System.out.println("Insert surname: ");
                String surname = scan.next();
                System.out.println("Insert username: ");
                String username = scan.next();
                System.out.println("Insert password: ");
                String pass = scan.next();

                User user = new User(null, name, surname, username, pass);
                db.addUser(user);
            }else if(choice.equals("2")){
                ArrayList<User> users = db.getAllUsers();
                for(User u : users){
                    System.out.println(u);
                }
            }else if(choice.equals("3")){
                System.out.println("Insert id: ");
                Long id = scan.nextLong();
                System.out.println("Insert name: ");
                String name = scan.next();
                System.out.println("Insert surname: ");
                String surname = scan.next();
                System.out.println("Insert username: ");
                String username = scan.next();
                System.out.println("Insert password: ");
                String pass = scan.next();

                User u = new User(id, name, surname, username, pass);
                db.update(u);
            }else if(choice.equals("4")){
                System.out.println("Insert id: ");
                Long id = scan.nextLong();
                db.delete(id);
            }else if(choice.equals("0")){
                System.exit(0);
            }else{
                System.out.println("WRONG INPUT!!! \nTRY AGAIN");
            }
        }
    }
}
