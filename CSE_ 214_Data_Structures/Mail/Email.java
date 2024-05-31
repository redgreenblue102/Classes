import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Comparator;
/**
 * email object represents an email
 * @author Carl Liu
 * sec: 06
 */
public class Email implements Serializable{
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timeStamp = new GregorianCalendar();

    /**
     * Email constructor
     */
    public Email(){

    }

    /**
     * get who the email is to
     * @return
     * returns type String that is this email's to
     */

    public String getTo() {
        return to;
    }

    /**
     * set who this email is to
     * @param to
     * is of type String, represents who this email is to
     */

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * get carbon copy for this email
     * @return
     * returns type String which is the carbon copy of this email
     */

    public String getCc() {
        return cc;
    }

    /**
     * sets the carbon copy of this email
     * @param cc
     * is of type String, represents carbon copy of this email
     */

    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * gets the blind carbon copy of this email
     * @return
     * returns type String which is the blind carbon copy of this email
     */

    public String getBcc() {
        return bcc;
    }

    /**
     * sets the blind carbon copy of this email
     * @param bcc
     * is of type String, which is representation of this emails blind carbon copy
     */

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * gets the subject of this email
     * @return
     * returns type String which represents this emails subject
     */

    public String getSubject() {
        return subject;
    }

    /**
     * sets the subject of this email
     * @param subject
     * is of type String represents the subject of this email
     */

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * gets the body of this email
     * @return
     * returns type String which represents the body of this email
     */

    public String getBody() {
        return body;
    }

    /**
     * sets the body of this email
     * @param body
     * is of type String which represents the body of this email
     */

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * gets the time this email was made
     * @return
     * returns type GregorianCalendar which has the time the email was made
     */

    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * sets the time that this email was made
     * @param timeStamp
     * is of type GregorianCalendar which has information on time
     */

    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * converts all fields to Strings
     * @return
     * returns the info in all fields as String
     */
    public String toString(){
        return "To: " + this.to + "\nCc: " + this.cc + "\nBcc: " + this.bcc + "\nSubject: " + this.subject + "\n" + this.body;
    }
}

/**
 * used in Collections.sort() to compare subject of email
 */
class subjectComparator implements Comparator{
    /**
     * compares two email object's subjects lexicographically
     * @param e1 the first object to be compared.
     * @param e2 the second object to be compared.
     * @return
     * returns 1 if first object is greater lexicographically in the subject than second, 0 if the same, and -1 if less
     */
    public int compare(Object e1, Object e2){
        Email em1 = (Email)e1;
        Email em2 = (Email)e2;
        String sub1 = em1.getSubject();
        String sub2 = em2.getSubject();
        if(sub1 == null)
            return -1;
        if(sub2 == null)
            return 1;
        return sub1.compareToIgnoreCase(sub2);
    }
}

/**
 * used in Collections.sort() to compare date of email
 */
class dateComparator implements Comparator {
    /**
     * compares two email object's date
     * @param e1 the first object to be compared.
     * @param e2 the second object to be compared.
     * @return
     * returns 1 if first object date is greater than second object, 0 if the same, -1 if less
     */
    public int compare(Object e1, Object e2) {
        Email em1 = (Email) e1;
        Email em2 = (Email) e2;
        GregorianCalendar t1 = em1.getTimeStamp();
        GregorianCalendar t2 = em2.getTimeStamp();
        return t1.compareTo(t2);
    }
}
