package DepartmentStore;

/**
 * Node used in a doubly linked list which contains item info
 * @author Carl Liu
 * rec: 07
 */
public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    /**
     * default constructor for a node
     */
    public ItemInfoNode(){

    }

    /**
     * Constructs a node with fields given by user
     * @param info
     * Is of type DepartmentStore.ItemInfo and contains information on the item
     * @param prev
     * what object of DepartmentStore.ItemInfoNode type is before this
     * @param next
     * what object of DepartmentStore.ItemInfoNode type comes after this.
     */
    public ItemInfoNode(ItemInfo info, ItemInfoNode prev, ItemInfoNode next){
        this.info = info;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Sets the item info for the node
     * @param info
     * Is of type DepartmentStore.ItemInfo and will set the nodes item info to such
     */

    public void setInfo(ItemInfo info){
        this.info = info;
    }

    /**
     * gets the item info of the node
     * @return
     * returns a DepartmentStore.ItemInfo which contains info about an item
     */
    public ItemInfo getInfo(){
        return this.info;
    }

    /**
     * sets what node is linked after this node
     * @param node
     * takes in type DepartmentStore.ItemInfoNode and links this node to the inputted node as its next node
     */
    public void setNext(ItemInfoNode node){
        this.next = node;

    }

    /**
     * sets what node is linked before this node
     * @param node
     * takes in type DepartmentStore.ItemInfoNode and links this node to the inputted node as its previous node
     */
    public void setPrev(ItemInfoNode node){
        this.prev = node;
    }

    /**
     * Gets the node linked to this node as next
     * @return
     * returns the node linked to this node as next as type DepartmentStore.ItemInfoNode
     */
    public ItemInfoNode getNext(){
        return next;
    }

    /**
     * Gets the node linked to this node as prev
     * @return
     * returns the node linked to this node as prev as type DepartmentStore.ItemInfoNode
     */
    public ItemInfoNode getPrev(){
        return prev;
    }

    /**
     * adds a node after this node and links it to this node
     * @param add
     * Takes in type DepartmentStore.ItemInfo, the node added after this node will contain this information
     */
    public void addAfter(ItemInfo add){
        ItemInfoNode temp = this.next;
        this.next = new ItemInfoNode(add, this, this.next);
        if(temp!= null)
            temp.prev = this.next;
    }
    /**
     * adds a node before this node and links it to this node
     * @param add
     * Takes in type DepartmentStore.ItemInfo, the node added before this node will contain this information
     */
    public void addBefore(ItemInfo add){
        ItemInfoNode temp = this.prev;
        this.prev = new ItemInfoNode(add, this.prev, this);
        if(temp != null)
            temp.next = this.prev;
    }
}
