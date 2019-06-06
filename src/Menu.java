import java.util.Scanner;

public class Menu{

    private String menu;
    private int choice;

    public Menu(){
        System.out.println("|=== Botmir v1.2 ===|");
        var sb = new StringBuilder();
        sb.append("\n0. Quit");
        sb.append("\n1. Post a tweet");
        sb.append("\n2. Fetch tweets");
        sb.append("\n3. Fetch news");
        this.menu = sb.toString();
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void printMenu(){
        System.out.println(this.menu);
    }

    public void askChoice(){
        do{
            System.out.print("\nEnter your choice: ");
            var reader = new Scanner(System.in);
            while(!reader.hasNextInt()){
                System.out.println("That's not a number!");
                System.out.print("\nEnter your choice: ");
                reader.next();
            }
            this.choice = reader.nextInt();
        }while(!isValidChoice());
    }

    public boolean isValidChoice(){
        if(this.choice > 3 || this.choice < 0){
            System.out.println("Please select between 0 - 3!");
            return false;
        }
        return true;
    }
}
