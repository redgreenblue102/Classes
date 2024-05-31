package TreeDriver;

import java.util.Scanner;
import java.io.File;
/**
 * Interface for user to create a ternary decision-making tree and use it.
 * @author Carl Liu
 * sec: 06
 */
public class TreeDriver {
    /**
     * the implementation of all available user options.
     * @param args
     * takes inputs in from terminal
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String user = "";
        Tree tree = new Tree();
        do {
            try {
                options();
                user = input.nextLine().toUpperCase().trim();
                switch (user) {
                    case "L":
                        // lets user add a tree off a file
                        System.out.println("Input file name");
                        File file = new File(input.nextLine());
                        Scanner fileInput = new Scanner(file);
                        if(!fileInput.hasNext()){
                            System.out.println("Empty file");
                            break;
                        }
                        tree = new Tree();
                        TreeNode root = new TreeNode();
                        root.setLabel(fileInput.nextLine().trim());
                        root.setPrompt(fileInput.nextLine());
                        root.setMessage(fileInput.nextLine());
                        tree.setRoot(root);
                        while(fileInput.hasNext()){
                            String parentLabel = fileInput.next().trim();
                            int child = fileInput.nextInt();
                            int i = 0;
                            fileInput.nextLine();
                            while(i< child){
                                String label = fileInput.nextLine().trim();
                                String prompt = fileInput.nextLine();
                                String message = fileInput.nextLine();
                                tree.addNode(label, prompt, message, parentLabel);
                                i++;
                            }
                        }
                        System.out.println("TreeDriver.TreeDriver.Tree created");
                        break;
                    case "H":
                        // lets user use the tree as help
                        tree.beginSession();
                        break;
                    case "T":
                        //prints out the full tree
                        if(tree.getRoot() == null) {
                            System.out.println("Empty tree cannot use traversal");
                            break;
                        }
                        tree.preOrder();
                        break;
                    case "Q":
                        //quit and exits normally
                        break;
                    default :
                        System.out.println("Not an option");
                        break;

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }while (!user.equals("Q"));
    }

    /**
     * menu options for user
     */
    public static void options(){
        System.out.println("L - Create a tree");
        System.out.println("H - Initiate Help Session");
        System.out.println("T - Display tree");
        System.out.println("Q - Quit");
    }
}
