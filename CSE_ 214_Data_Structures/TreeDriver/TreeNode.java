package TreeDriver;

/**
 * Node of a ternary tree that contains info on questions and options
 * @author Carl Liu
 * sec: 06
 */
public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private TreeNode middle;
    private String label;
    private String message;
    private String prompt;

    /**
     * constructor for tree node
     */
    public TreeNode(){
    }

    /**
     * get the left child of node
     * @return
     * returns type TreeDriver.TreeDriver.TreeNode which is the left child of this node
     */
    public TreeNode getLeft(){
        return this.left;
    }

    /**
     * sets left child of this node
     * @param left
     * is of type TreeDriver.TreeDriver.TreeNode, is what the left child of this node will be set to
     */
    public void setLeft(TreeNode left){
        this.left = left;
    }

    /**
     * get the right child of this node
     * @return
     * returns type TreeDriver.TreeDriver.TreeNode which is right child of this node
     */
    public TreeNode getRight(){
        return this.right;
    }

    /**
     * set the right child for this node
     * @param right
     * is of type TreeDriver.TreeDriver.TreeNode, right child of this node will be set to this
     */
    public void setRight(TreeNode right){
        this.right = right;
    }

    /**
     * gets the middle child of this node
     * @return
     * returns type TreeDriver.TreeDriver.TreeNode which is the middle child of this node
     */
    public TreeNode getMiddle(){
        return this.middle;
    }

    /**
     * sets the middle child of this node
     * @param middle
     * is of type TreeDriver.TreeDriver.TreeNode, middle child will be set to this
     */
    public void setMiddle(TreeNode middle){
        this.middle = middle;
    }

    /**
     * gets the label for the node
     * @return
     * returns type String which is the label of the node
     */
    public String getLabel(){
        return this.label;
    }

    /**
     * sets the label of the node
     * @param label
     * is of type String, node label will be set to this
     */
    public void setLabel(String label){
        this.label = label;
    }

    /**
     * gets the message of this node
     * @return
     * returns type String which is the message of this node
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * sets the message of this node
     * @param message
     * is of type String, message of this node will be set to it
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * gets the prompt of this node
     * @return
     * returns type String, the prompt of this node
     */
    public String getPrompt(){
        return this.prompt;
    }

    /**
     * sets the prompt of this node
     * @param prompt
     * is of type String, will be the prompt for this node
     */
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }

    /**
     * checks if node is a leaf
     * @return
     * returns type boolean, true if it is a leaf, false otherwise
     */
    public boolean leaf(){
        return ((this.left == null) && (this.right == null)&&(this.middle ==null));
    }

    /**
     * goes through the tree, with this node as root and checks if any nodes have a label that matches
     * @param label
     * is of type String, the label that the nodes will be checked against
     * @return
     * returns type TreeDriver.TreeDriver.TreeNode which is null if label isn't found else it returns the TreeDriver.TreeDriver.TreeNode of where the label was found
     */
    public TreeNode getNodeReference(String label){
        TreeNode answer = null;
        if(this.label.equals(label))
            return this;
        if(this.left != null)
            answer = this.left.getNodeReference(label);
        if(answer != null)
            return answer;
        if(this.middle != null)
            answer = this.middle.getNodeReference(label);
        if(answer != null)
            return answer;
        if(this.right != null)
            answer = this.right.getNodeReference(label);
        return answer;
    }

    /**
     * prints out in preorder form all the nodes
     */
    public void preOrder(){
        System.out.println("Label: " + this.label);
        System.out.println("Prompt: " + this.prompt);
        System.out.println("Message: " + this.message);
        System.out.println();
        if(this.left != null)
            this.left.preOrder();
        if(this.middle != null)
            this.middle.preOrder();
        if(this.right != null)
            this.right.preOrder();
    }

}
