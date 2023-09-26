package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Controller;

public class Executable{

    private Scanner lector;
    private Controller controller;

    public Executable(){
        lector = new Scanner(System.in);
        controller = new Controller();
    }
    public static void main(String[] args) {
        
        Executable executable = new Executable();
        boolean menu = true;
        boolean exception = false;
        while(menu){
            try{
                menu = executable.menu(exception);
            } catch(InputMismatchException e){
                System.out.println("Wrong input");
                exception = true;
            }
        }
        
    }

    /**
     * This method is the menu.
     * @return boolean false if user wants to exit the program.
     */
    private boolean menu(boolean exception) throws InputMismatchException{
        if(exception){
            lector.nextLine();
        }
        System.out.println(controller.showMenu());
        int menu = lector.nextInt();
        boolean flag = true;
        switch(menu){
            case 0:
                System.out.println("Exiting the program...");
                flag = false;
                break;
            case 1:
                addTask();
                break;
            case 4:
                ctrlZ();
            default:
                System.out.println("Type a valid option");
                break;
        }
        return flag;
        
    }

    private void addTask(){
        lector.nextLine();
        System.out.println("Type the title");
        String title = lector.nextLine();
        System.out.println("Type the description");
        String description = lector.nextLine();
        try{
            System.out.println("Type in the deadline day");
            int day = lector.nextInt();
            System.out.println("Type in the deadline month");
            int month = lector.nextInt();
            System.out.println("Type in the deadline year");
            int year = lector.nextInt();
            System.out.println("(1) Priority \n(2) Non-priority");
            int pri = lector.nextInt();
            boolean priority = (pri==1) ? true : false;
            controller.addTask(title, description, day, month, year, priority);
        } catch (NumberFormatException e){
            System.out.println("Invalid");
        }
        
    }

    private void ctrlZ(){

    }


}