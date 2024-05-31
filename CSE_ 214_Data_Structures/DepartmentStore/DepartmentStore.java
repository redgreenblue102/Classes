package DepartmentStore;

import java.util.Scanner;
/**
 * Interface for user to manage items from a department store
 * @author Carl Liu
 * rec: 07
 */
public class DepartmentStore {
    /**
     * Provides options for the user and allows them to manage items in the department store
     * @param args
     * takes inputs in from terminal
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ItemList l1 = new ItemList();
        String user;
        options();
        do {
            System.out.println("Input Option, Z for menu");
            user = input.nextLine().toUpperCase();
            try {
                switch(user){
                    case "I":
                        System.out.println("Input item name");
                        String name = input.nextLine();
                        System.out.println("Input item price as a double");
                        double price = input.nextDouble();
                        input.nextLine();
                        System.out.println("Input Rfid Number of Item as a 9 digit hexadecimal");
                        String rfid0 = input.nextLine().trim();
                        System.out.println("Input original location");
                        String oLoc = input.nextLine().trim();
                        l1.insertInfo(name, rfid0, price, oLoc);
                        System.out.println("Item added");
                        break;
                    case "M":
                        System.out.println("Input Rfid Number of Item as a 9 digit hexadecimal");
                        String rfid1 = input.nextLine().trim();
                        System.out.println("Input the source location of the item");
                        String source = input.nextLine().trim();
                        System.out.println("Input the destination for the item");
                        String dest = input.nextLine().toLowerCase().trim();
                        if(dest.equals("out")) {
                            System.out.println("Destination cannot be 'out'");
                            break;
                        }
                        if(validLoc(dest)) {
                            if (l1.moveItem(rfid1, source, dest))
                                System.out.println("Item has been moved");
                            else
                                System.out.println("Item not found at location");
                        }else{
                            System.out.println("destination has to be of form sDDDDD or cDDD where D is digits");
                        }

                        break;
                    case "L":
                        System.out.println("Input location of items");
                        String loc = input.nextLine().trim();
                        if(validLoc(loc)){
                            l1.printByLocation(loc);
                        }else{
                            System.out.println("Invalid Input, has to be of form cDDD, sDDDDD, or 'out' where D is digits");
                        }
                        break;
                    case "P":
                        l1.printAll();
                        break;
                    case "O":
                        System.out.println("Input cart");
                        String cart = input.nextLine().toLowerCase().trim();
                        System.out.printf("Total Price: $%.2f\n", l1.checkOut(cart));
                        break;
                    case "C":
                        System.out.println("The below items have been moved back to their original location");
                        l1.cleanStore();
                        break;
                    case "U":
                        System.out.println("The items below have been removed from the list");
                        l1.removeAllPurchased();
                        break;
                    case "Q":
                        break;
                    default :
                        System.out.println("Invalid Option try again");
                        break;
                    case "R":
                        System.out.println(("Input Rfid"));
                        l1.printByRfid(input.nextLine().trim());
                        break;
                    case "Z":
                        options();
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            input = new Scanner(System.in);
        }while(!user.equals("Q"));
    }

    /**
     * Prints the available options to the user
     */
    public static void options(){
        System.out.printf("%-25s%s\n","Insert Item:", "I"  );
        System.out.printf("%-25s%s\n","Move Item:", "M"  );
        System.out.printf("%-25s%s\n","List by Location:", "L"  );
        System.out.printf("%-25s%s\n","Print All Items:", "P"  );
        System.out.printf("%-25s%s\n","Checkout:", "O"  );
        System.out.printf("%-25s%s\n","Clean Store:", "C"  );
        System.out.printf("%-25s%s\n","Update Inventory:", "U"  );
        System.out.printf("%-25s%s\n","List by RFID:", "R"  );
        System.out.printf("%-25s%s\n","Quit:", "Q"  );
    }

    /**
     * checks if the location given is of a valid format
     * @param loc
     * is of type string, location of item
     * @return
     * returns boolean, true if location is of valid format
     * false otherwise
     */
    public static boolean validLoc(String loc){
        loc = loc.toLowerCase();
        if(loc.equals("out")){
            return true;
        }
        if(loc.length() == 6) {
            if (loc.charAt(0) == 's') {
                for (int i = 1; i <= 5; i++) {
                    if ((loc.charAt(i) >= 48) && (loc.charAt(i) <= 57))
                        continue;
                    return false;
                }
                return true;
            }
        }
        if(loc.length() == 4){
            if (loc.charAt(0) == 'c') {
                for (int i = 1; i <= 3; i++) {
                    if ((loc.charAt(i) >= 48) && (loc.charAt(i) <= 57))
                        continue;
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
