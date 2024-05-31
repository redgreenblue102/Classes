import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Folder object holds emails in a folder
 * @author Carl Liu
 * sec: 06
 */
public class Folder implements Serializable {
    private ArrayList<Email> emails = new ArrayList<Email>();
    private String name;
    private String currentSortingMethod  = "dd";

    /**
     * default constructor for folder
     */
    public Folder(){

    }

    /**
     * gets the list of emails in the folder
     * @return
     * returns type ArrayList which contains all the email in the folder
     */

    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * sets the emails in the folder
     * @param emails
     * is of Type ArrayList, will replace all the emails in the folder with this new list
     */

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * gets the name of the folder
     * @return
     * returns type String which is the name of the folder
     */

    public String getName() {
        return name;
    }

    /**
     * sets the name of the folder
     * @param name
     * is of type String, the name to be set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the sorting method of the folder
     * @return
     * returns type String, which is "dd" for date descending, "da" for date ascending, "sa" for subject ascending, and "sd" for subject descending
     */

    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * sets the current sorting method of the folder
     * @param currentSortingMethod
     * is of type String should be either "dd","da","sa", or "sd"
     */

    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * adds an email to this folder
     * @param email
     * is of type Email, adds in the email according to current sorting method
     */
    public void addEmail(Email email){
        if(this.currentSortingMethod.equals("dd")) {
            for (int i = 0; i < this.emails.size(); i++)
                if (email.getTimeStamp().compareTo(this.emails.get(i).getTimeStamp()) >= 0) {
                    this.emails.add(i, email);
                    return;
                }
            this.emails.add(email);
            return;
        }
        if(this.currentSortingMethod.equals("da")) {
            for (int i = 0; i < this.emails.size(); i++)
                if (email.getTimeStamp().compareTo(this.emails.get(i).getTimeStamp()) <= 0) {
                    this.emails.add(i, email);
                    return;
                }
            this.emails.add(email);
            return;
        }
        if(this.currentSortingMethod.equals("sd")){
            for (int i = 0; i < this.emails.size(); i++)
                if (email.getSubject().compareTo(this.emails.get(i).getSubject()) >= 0) {
                    this.emails.add(i, email);
                    return;
                }
            this.emails.add(email);
            return;
        }
        if(this.currentSortingMethod.equals("sa")){
            for (int i = 0; i < this.emails.size(); i++)
                if (email.getSubject().compareTo(this.emails.get(i).getSubject()) <= 0) {
                    this.emails.add(i, email);
                    return;
                }
            this.emails.add(email);
        }
    }

    /**
     * removes an email from this folder
     * @param index
     * is of type int, index of email to be removed
     * @return
     * returns the email removed, null if email isn't found
     */
    public Email removeEmail(int index){
        if((index>= emails.size()) || (index< 0))
            return null;
        return this.emails.remove(index);
    }

    /**
     * sorts the folder by subject ascending
     */
    public void sortBySubjectAscending(){
        Collections.sort(this.emails, new subjectComparator());
    }

    /**
     * sorts the folder by subject descending
     */
    public void sortBySubjectDescending(){
        Collections.sort(this.emails,Collections.reverseOrder(new subjectComparator()));
    }

    /**
     * sorts the folder by date ascending
     */
    public void sortByDateAscending(){
        Collections.sort(this.emails, new dateComparator());
    }

    /**
     * sorts the folder by date descending
     */
    public void sortByDateDescending(){
        Collections.sort(this.emails,Collections.reverseOrder(new dateComparator()));

    }

}
