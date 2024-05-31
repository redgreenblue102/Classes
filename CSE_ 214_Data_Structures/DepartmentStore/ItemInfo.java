package DepartmentStore;

/**
 * Contains information on a specific item of a store
 * @author Carl Liu
 * rec: 07
 */
public class ItemInfo {
    private String name;
    private double price;

    private String rfidNum;
    private String oLoc;
    private String cLoc;

    /**
     * default constructor for DepartmentStore.ItemInfo
     */
    public ItemInfo(){

    }

    /**
     * Constructor for DepartmentStore.ItemInfo that allows for data fields to be filled
     * @param name
     * is of type String, Name of the item
     * @param price
     * is of type double, should be positive, price of item
     * @param rfidNum
     * is of type String, should be hexadecimal and of length 9. Encodes radio freq of Item
     * @param oLoc
     * is of type String, should start with s and have 5 digits following, Is the original shelf position of item
     * @param cLoc
     * is of type String, should be a shelf location, cart location which starts with c and has 3 digits, or out, Is
     * the current location of the item.
     * @throws IllegalFormatException
     * will throw if format is improper for any of its parameters
     * @throws IllegalArgumentException
     * will throw if price is negative
     */
    public ItemInfo(String name, double price, String rfidNum, String oLoc, String cLoc) throws IllegalFormatException,
            IllegalArgumentException{
        this.name=name;
        this.setPrice(price);
        this.setRfidNum(rfidNum);
        this.setOLoc(oLoc);
        this.setCLoc(cLoc);


    }

    /**
     * Sets the name of the item
     * @param name
     * is of type string, will be what the item is named
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the price of the item
     * @param price
     * is of type double, should not be negative, will be the price of item.
     * @throws IllegalArgumentException
     * Throws if the price inputted is negative.
     */
    public void setPrice(double price) throws IllegalArgumentException{
        if(price <0)
            throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
    }

    /**
     * sets the rfid of the item
     * @param rfidNum
     * is of type string, should be of form HHHHHHHHH where H is a hexadecimal
     * @throws IllegalFormatException
     * Throws if input is not of correct format
     */
    public void setRfidNum(String rfidNum) throws IllegalFormatException {
        rfidNum = rfidNum.toUpperCase();
        if(rfidNum.length() == 9){
                for(int i = 0; i <=8 ; i++){
                    if(((rfidNum.charAt(i)>=48)&&(rfidNum.charAt(i) <= 57)) || ((rfidNum.charAt(i) >=65)&&
                            (rfidNum.charAt(i) <=70)) )
                        continue;
                    throw new IllegalFormatException("rfid has to be of the form HHHHHHHHH where H is a digit");
                }
                this.rfidNum = rfidNum;
                return;
        }
        throw new IllegalFormatException("rfid has to be of the form HHHHHHHHH where H is a Hexadecimal");
    }

    /**
     * sets the original location of the item
     * @param oLoc
     * is of type string and should be of form sDDDDD where C is a digit
     * @throws IllegalFormatException
     * Throws if input format isn't as specified
     */
    public void setOLoc(String oLoc) throws IllegalFormatException {
        oLoc = oLoc.toLowerCase();
        if(oLoc.length() == 6){
            if(oLoc.charAt(0) == 's' ){
                for(int i = 1; i <=5 ; i++){
                    if((oLoc.charAt(i)>=48)&&(oLoc.charAt(i) <= 57))
                        continue;
                    throw new IllegalFormatException("original location has to be of the form sDDDDD where D is a digit");
                }
                this.oLoc = oLoc;
                return;
            }
        }
        throw new IllegalFormatException("original location has to be of the form sDDDDD where D is a digit");
    }

    /**
     * sets the current location for the item
     * @param cLoc
     * is of type String and should be of form cDDD, sDDDDD, or out
     * @throws IllegalFormatException
     * Throws if input format isn't as specified
     */
    public void setCLoc(String cLoc) throws IllegalFormatException {
        cLoc = cLoc.toLowerCase();
        if(cLoc.equals("out")){
            this.cLoc = cLoc;
            return;
        }
        if(cLoc.length() == 6) {
            if (cLoc.charAt(0) == 's') {
                for (int i = 1; i <= 5; i++) {
                    if ((cLoc.charAt(i) >= 48) && (cLoc.charAt(i) <= 57))
                        continue;
                    throw new IllegalFormatException("current location has to be of the form sDDDDD, cDDD, or 'out' where D is a digit");
                }
                this.cLoc = cLoc;
                return;
            }
        }
        if(cLoc.length() == 4) {
            if (cLoc.charAt(0) == 'c') {
                for (int i = 1; i <= 3; i++) {
                    if ((cLoc.charAt(i) >= 48) && (cLoc.charAt(i) <= 57))
                        continue;
                    throw new IllegalFormatException("current location has to be of the form sDDDDD, cDDD, or 'out' where D is a digit");
                }
                this.cLoc = cLoc;
                return;
            }
        }
        throw new IllegalFormatException("current location has to be of the form sDDDDD, cDDD, or 'out' where D is a digit");

    }

    /**
     * gets the name of the item
     * @return
     * returns the name of the item as a string
     */
    public String getName(){
        return this.name;
    }

    /**
     * gets the price of the item
     * @return
     * returns the price of the item as a double
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * gets the rfid number of the item
     * @return
     * returns the rfid number of the item as a string
     */
    public String getRfidNum(){
        return this.rfidNum;
    }

    /**
     * get the original location of the item
     * @return
     * returns the items original location as a string
     */
    public String getOLoc(){
        return this.oLoc;
    }

    /**
     * gets the current location of the item
     * @return
     * returns the items current location as a string
     */
    public String getCLoc(){
        return this.cLoc;
    }

    /**
     * will convert all information into a formatted string
     * @return
     * returns a formatted string containing all the information on the item
     */
    public String toString(){
        return String.format("%-25s%-15s%-15s%-15s%.2f", this.name , this.rfidNum ,this.oLoc , this.cLoc, this.price);
    }
}
