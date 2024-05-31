package DepartmentStore;

import DepartmentStore.ItemInfo;
import DepartmentStore.ItemInfoNode;

/**
 * Uses doubly linked list to hold item info
 * @author Carl Liu
 * rec: 07
 */
public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;

    /**
     * default constructor for DepartmentStore.ItemList
     */
    public ItemList() {

    }

    /**
     * Allows the insertion of item into the list.
     * @param name
     * is of type string, name of the item
     * @param rfidNum
     * is of type string, rfid number of item, has to be of form HHHHHHHHH where H is a hexadecimal
     * @param price
     * is of type double, the price of the item, cannot be negative
     * @param oLoc
     * is of type string, the original location of the item, has to be of form sDDDDD where D is a digit
     * @throws IllegalFormatException
     * Throws if any of the above formatting is incorrect
     * @throws IllegalArgumentException
     * Throws if the inputted price is negative.
     */
    public void insertInfo(String name, String rfidNum, double price, String oLoc) throws IllegalFormatException,
            IllegalArgumentException {
        /* Is of complexity O(n). There is 3 statements at the top, when the cursor starts at null then 2 more
        statements will occur then exit the method for a total of 5 statements, whereas when the method continues on to
        the while loop we can have a max of 2n statements where n is the size of the list and after the while loop theres
        an additional statement. so we have a case of 5 operations, or n+4 operations, both which have an upper bound of
        O(n)
         */
        rfidNum = rfidNum.toUpperCase();
        ItemInfo info = new ItemInfo(name, price, rfidNum, oLoc, oLoc);
        ItemInfoNode cursor = this.head;
        if (cursor == null) {
            this.head = new ItemInfoNode(info, null, null);
            this.tail = this.head;
            return;
        }
        while (cursor != null) {
            if (cursor.getInfo().getRfidNum().compareTo(rfidNum) >= 0) {
                cursor.addBefore(info);
                if (cursor == this.head) {
                    this.head = this.head.getPrev();
                }
                return;
            }
            cursor = cursor.getNext();
        }
        this.tail.addAfter(info);
    }

    /**
     * removes all items that are listed as out from the system and prints a list of all removed items.
     */

    public void removeAllPurchased() {
        /*
        is of complexity O(n) because we have 2 initializing statements and then the while loop has a max complexity of
        3n as well when the if statement is always true, and n is the list size. So we have a total complexity of 3n+2
        and so an upper bound of O(n)
         */
        printHeading();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getCLoc().equals("out")) {
                remove(cursor);
                System.out.println(cursor.getInfo());
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * removes a specified item node
     * @param item
     * is of type DepartmentStore.ItemInfoNode, will be removed from list
     */

    public void remove(ItemInfoNode item) {
        item.getPrev().setNext(item.getNext());
        item.getNext().setPrev(item.getPrev());
    }

    /**
     * moves an item in the list to another location
     * @param rfidNum
     * is of type string, rfid number of the item
     * @param source
     * is of type string, the current location of the item, cannot be 'out'
     * @param dest
     * is of type string, where the item will go, can't be 'out'
     * @return
     * returns type boolean, true when item is moved, false otherwise.
     * @throws IllegalFormatException
     * Throws if the format for any of paramaters are not correct
     */
    public boolean moveItem(String rfidNum, String source, String dest) throws IllegalFormatException {
        /*
        is of O(n) complexity since the while loop can have a max complexity of 3n and all other statements are constant
        complexity
         */
        source = source.toLowerCase();
        dest = dest.toLowerCase();
        if (source.equals("out"))
            throw new IllegalFormatException("source cannot be 'out'");
        rfidNum = rfidNum.toUpperCase();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getRfidNum().equals(rfidNum)) {
                if (source.equals(cursor.getInfo().getCLoc())) {
                    cursor.getInfo().setCLoc(dest);
                    return true;
                }

            }
            cursor = cursor.getNext();
        }
        return false;
    }

    /**
     * prints the entire list of items
     */

    public void printAll() {
        /*
        is of O(n) complexity since the while loop can have a max complexity of 2n and all other statements are constant
        complexity
         */
        printHeading();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            System.out.println(cursor.getInfo());
            cursor = cursor.getNext();
        }

    }

    /**
     * prints all items at a given location
     * @param cLoc
     * is of type string, location of items to be printed out has to be form sDDDDD, cDDD, or 'out' where D is a digit
     */
    public void printByLocation(String cLoc) {
        /*
        is of O(n) complexity since the while loop can have a max complexity of 3n and all other statements are constant
        complexity
         */
        cLoc = cLoc.toLowerCase();
        printHeading();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getCLoc().equals(cLoc)) {
                System.out.println(cursor.getInfo());
            }
            cursor = cursor.getNext();
        }

    }

    /**
     * prints all items with a given rfid
     * @param Rfid
     * is of type string has to be of form HHHHHHHHH where H is a hexadecimal
     */
    public void printByRfid(String Rfid) {
        printHeading();
        Rfid = Rfid.toUpperCase();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getRfidNum().equals(Rfid)) {
                System.out.println(cursor.getInfo());
            }
            cursor = cursor.getNext();
        }

    }

    /**
     * prints the heading for a list
     */
    public void printHeading() {
        System.out.printf("%-25s%-15s%-15s%-15s\n", "", "", "Original", "Current");
        System.out.printf("%-25s%-15s%-15s%-15s%s\n", "Item Name", "RFID", "Location", "Location", "Price");
        System.out.println("-------------------------------------------------------------------------------------");
    }

    /**
     * puts all the items back to their original locations, except for items that are out
     */
    public void cleanStore() {
        /*
        is of O(n) complexity since the while loop can have max complexity of 3n and all other statements are constant
        complexity
         */
        printHeading();
        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (!cursor.getInfo().getCLoc().equals(cursor.getInfo().getOLoc())) {
                if (!cursor.getInfo().getCLoc().equals("out") && !(cursor.getInfo().getCLoc().charAt(0) == 'c')) {
                    System.out.println(cursor.getInfo());
                    try {
                        cursor.getInfo().setCLoc(cursor.getInfo().getOLoc());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

            }
            cursor = cursor.getNext();
        }

    }

    /**
     * checks out a given cart by setting location to out.
     * @param cartNum
     * is of type String, should be of form cDDD where D is a digit
     * @return
     * returns a double which is the total price of all the items in the cart
     * @throws IllegalFormatException
     * Throws if the cart isn't of the correct format
     */
    public double checkOut(String cartNum) throws IllegalFormatException {
        /*
        is of O(n) complexity since the while loop can have max complexity of 4n and all other statements are constant
        complexity
         */
        cartNum = cartNum.toLowerCase();
        if (cartNum.length() == 4) {
            if (cartNum.charAt(0) == 'c') {
                for (int i = 1; i <= 3; i++) {
                    if ((cartNum.charAt(i) >= 48) && (cartNum.charAt(i) <= 57))
                        continue;
                    throw new IllegalFormatException("Has to be of the form cDDD, where D is a digit");
                }
                printHeading();
                ItemInfoNode cursor = head;
                double price = 0;
                while (cursor != null) {
                    if (cursor.getInfo().getCLoc().equals(cartNum)) {
                        cursor.getInfo().setCLoc("out");
                        System.out.println(cursor.getInfo());
                        price += cursor.getInfo().getPrice();

                    }
                    cursor = cursor.getNext();
                }
                return price;
            }
        }
        throw new IllegalFormatException("Has to be of the form cDDD, where D is a digit");
    }
}
