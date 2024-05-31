package DepartmentStore;

/**
 * Exception that is thrown when formatting is incorrect
 * @author Carl Liu
 * rec: 07
 */
public class IllegalFormatException extends Exception{
    /**
     * constructor for exception
     * @param text
     * text that will be used when exception occurs
     */
    public IllegalFormatException(String text){
        super(text);
    }
}
