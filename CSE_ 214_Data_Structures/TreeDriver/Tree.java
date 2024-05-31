package TreeDriver;

import java.util.Scanner;
/**
 * Ternary tree for decision-making system,  contains methods to interact with it
 * @author Carl Liu
 * sec: 06
 */
public class Tree {
    private TreeNode root;

    /**
     * constructor for TreeDriver.TreeDriver.Tree
     */
    public Tree() {
    }

    /**
     * sets the root of the TreeDriver.TreeDriver.Tree
     * @param root
     * is of type TreeDriver.TreeDriver.TreeNode, sets the root of this tree
     */
    public void setRoot(TreeNode root){
        this.root = root;
    }

    /**
     * get the root of this tree
     * @return
     * returns TreeDriver.TreeDriver.TreeNode which is the root of this tree
     */
    public TreeNode getRoot(){
        return this.root;
    }

    /**
     * adds a node to the tree, is left justified
     * @param label
     * is of type String, the label for the new node
     * @param prompt
     * is of type String, the prompt for the new node
     * @param message
     * is of type String, the message for the new node
     * @param parentLabel
     * is of type String, the label of the parent that the new node will have
     * @return
     * returns type boolean, true if node is added, false otherwise. Node will not be added if the given parent already has 3 child
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel){
        TreeNode node = new TreeNode();
        node.setLabel(label);
        node.setPrompt(prompt);
        node.setMessage(message);
        TreeNode parent = this.root.getNodeReference(parentLabel);
        if(parent == null)
            return false;
        if(parent.getLeft() == null){
            parent.setLeft(node);
            return true;
        }
        if(parent.getMiddle() == null){
            parent.setMiddle(node);
            return true;
        }
        if(parent.getRight() == null){
            parent.setRight(node);
            return true;
        }
        return false;
    }

    /**
     * prints out the entire tree
     */
    public void preOrder(){
        this.root.preOrder();
    }

    /**
     * begins the help session for user interaction
     */
    public void beginSession(){
        if(this.root == null) {
            System.out.println("Empty tree cannot use help");
            return;
        }
        Scanner input = new Scanner(System.in);
        TreeNode cursor = this.root;
        while(!cursor.leaf()){
            System.out.println(cursor.getMessage());
            if(cursor.getLeft() != null)
                System.out.println("1-" + cursor.getLeft().getPrompt());
            if(cursor.getMiddle() != null)
                System.out.println("2-" + cursor.getMiddle().getPrompt());
            if(cursor.getRight() != null)
                System.out.println("3-" + cursor.getRight().getPrompt());
            System.out.println("0-Exit to menu");
            int choice = input.nextInt();
            if(choice == 1){
                if(cursor.getLeft() != null) {
                    cursor = cursor.getLeft();
                    continue;
                }
            }
            if(choice == 2){
                if(cursor.getMiddle() != null) {
                    cursor = cursor.getMiddle();
                    continue;
                }
            }
            if(choice == 3){
                if(cursor.getRight() != null) {
                    cursor = cursor.getRight();
                    continue;
                }
            }
            if(choice == 0){
                return;
            }
            System.out.println("Not an option");
        }
        System.out.println(cursor.getMessage());
        System.out.println("Thanks for using this system");
    }
}
