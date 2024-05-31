import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Mailbox object and also interface for user to use mailbox, Mailbox has folders with emails in the folders
 * @author Carl Liu
 * sec: 06
 */
public class Mailbox implements Serializable{
    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders  = new ArrayList<Folder>();
    public static Mailbox mailbox = new Mailbox();
    public static Scanner input = new Scanner(System.in);

    /**
     * default mailbox constructor, initiates some fields and names inbox and trash
     */
    public Mailbox(){
        this.inbox = new Folder();
        this.trash = new Folder();
        this.inbox.setName("inbox");
        this.trash.setName("trash");
    }

    /**
     * adds a folder to the Mailbox
     * @param folder
     * is of type Folder, adds folder if the name of the folder doesn't already exist in mailbox
     */

    public void addFolder(Folder folder) {
        if (folder.getName().equals("inbox") || folder.getName().equals("trash")) {
            System.out.println("Folder with the same name exists already");
            return;
        }
        for (int i = 0; i < this.folders.size(); i++) {
            if (folder.getName().equals(this.folders.get(i).getName())) {
                System.out.println("Folder with the same name exists already");
                return;
            }
        }
        this.folders.add(folder);
    }

    /**
     * delete folder based on name
     * @param name
     * is of type String, name of the folder to be deleted
     */

    public void deleteFolder(String name) {
        for (int i = 0; i < this.folders.size(); i++) {
            if (name.equals(this.folders.get(i).getName())) {
                this.folders.remove(i);
                return;
            }
        }
    }

    /**
     * lets the user compose an email and fill out all fields, will add to inbox after
     */
    public void composeEmail(){
        Email email = new Email();
        System.out.println("to:");
        email.setTo(input.nextLine());
        System.out.println("cc:");
        email.setCc(input.nextLine());
        System.out.println("bcc:");
        email.setBcc(input.nextLine());
        System.out.println("subject:");
        email.setSubject(input.nextLine());
        System.out.println("body:");
        email.setBody(input.nextLine());
        this.inbox.addEmail(email);
    }

    /**
     * deletes an email by moving it to trash
     * @param email
     * is of type Email, will move the given email to trash
     */
    public void deleteEmail(Email email){
        this.trash.addEmail(email);
    }

    /**
     * clears the trash can of the mailbox
     */
    public void clearTrash(){
        this.trash.getEmails().clear();
    }

    /**
     * moves an email to another folder
     * @param email
     * is of type Email, The email to be moved
     * @param target
     * is of type Folder, where the email will be moved to
     */
    public void moveEmail(Email email, Folder target){
        int index = this.folders.indexOf(target);
        if(index == -1){
            inbox.addEmail(email);
            return;
        }
        this.folders.get(index).addEmail(email);
    }

    /**
     * gets the folder by name
     * @param name
     * is of type String, the name of the folder to get
     * @return
     * returns type Folder, the folder with the name given, returns null if not found
     */
    public Folder getFolder(String name){
        if(name.equals("inbox"))
            return this.inbox;
        if(name.equals("trash"))
            return this.trash;
        for(int i = 0; i< this.folders.size(); i++){
            if(this.folders.get(i).getName().equals(name)){
                return this.folders.get(i);
            }
        }
        return null;
    }

    /**
     * prints all folder names in the mailbox
     */
    public void printAllFolders(){
        System.out.println("inbox");
        System.out.println("trash");
        for(int i= 0; i <this.folders.size(); i++){
            System.out.println(this.folders.get(i).getName());
        }
    }

    /**
     * main implementation of user interface to interact with mailbox
     * @param args
     * is of type String[] takes in input from terminal
     */

    public static void main(String[] args){
        // checks for mailbox.obj file to read
        try{
            FileInputStream file = new FileInputStream("mailbox.obj");
            ObjectInputStream in = new ObjectInputStream(file);
            mailbox = (Mailbox)in.readObject();
            System.out.println("Mailbox read");

        }catch(IOException ex){
            System.out.println("File Not found, mailbox will be empty");
        }catch(ClassNotFoundException ex){
            System.out.println("Class Not found");
        }
        String user = "";
        // implements all available option in main screen of mailbox
        do {

            try {
                System.out.println("Mailbox");
                System.out.println("-------------------");
                mailbox.printAllFolders();
                mOptions();
                user = input.nextLine();
                switch (user.toUpperCase().trim()) {
                    case "A":
                        Folder folder = new Folder();
                        System.out.println("Input name of folder to be added");
                        folder.setName(input.nextLine());
                        mailbox.addFolder(folder);
                        break;
                    case "R":
                        System.out.println("Folders");
                        System.out.println("--------------------------");
                        for(int i= 0; i <mailbox.folders.size(); i++){
                            System.out.println(mailbox.folders.get(i).getName());
                        }
                        System.out.println("Input name of folder to be deleted");
                        mailbox.deleteFolder(input.nextLine());
                        break;
                    case "C":
                        mailbox.composeEmail();
                        break;
                    case "F":
                        System.out.println("Input name of folder");
                        String name = input.nextLine();
                        Folder folder1 = mailbox.getFolder(name);
                        if(folder1 != null){
                            user = "";
                            while(!user.trim().equalsIgnoreCase("R")) {
                                printFolder(folder1);
                                fOptions();
                                user = input.nextLine();
                                fApplied(user, folder1);
                            }
                            break;
                        }
                        System.out.println("Folder doesn't exist");
                        break;
                    case "I":
                        user = "";
                        while(!user.trim().equalsIgnoreCase("R")) {
                            printFolder(mailbox.inbox);
                            fOptions();
                            user = input.nextLine();
                            fApplied(user, mailbox.inbox);
                        }
                        break;
                    case "T":
                        while(!user.trim().equalsIgnoreCase("R")) {
                            printFolder(mailbox.trash);
                            fOptions();
                            user = input.nextLine();
                            fApplied(user, mailbox.trash);
                        }
                        break;
                    case "E":
                        mailbox.clearTrash();
                        break;
                    case "Q":
                        // saves mailbox info to mailbox.obj file after quitting
                        try{
                            FileOutputStream file = new FileOutputStream("mailbox.obj");
                            ObjectOutputStream out = new ObjectOutputStream(file);
                            out.writeObject(mailbox);
                            out.close();

                        } catch(IOException ex){
                            System.out.println(ex);
                            System.out.println("Unsuccessful save");
                            user = "";
                        }
                        break;
                    default:
                        System.out.println("Not an option");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
                input = new Scanner(System.in);
            }
        }while(!user.toUpperCase().trim().equals("Q"));

        System.out.println("Successfully closed and saved to mailbox.obj");
    }

    /**
     * menu option in main menu
     */
    public static void mOptions(){
        System.out.println("A: add a folder");
        System.out.println("R: remove a folder");
        System.out.println("C: compose an email");
        System.out.println("F: view a folder");
        System.out.println("I: view the inbox");
        System.out.println("T: view the trash");
        System.out.println("E: empty the trash");
        System.out.println("Q: quit the program ");
    }

    /**
     * menu option in folder menu
     */
    public static void fOptions(){
        System.out.println("M: move an email");
        System.out.println("D: delete an email");
        System.out.println("V: view contents of email");
        System.out.println("SA: sort email by ascending subject order");
        System.out.println("SD: sort email by descending subject order");
        System.out.println("DA: sort email by ascending date order");
        System.out.println("DD: sort email by descending date order");
        System.out.println("R: return back to main menu");
    }

    /**
     * implementation of folder options
     * @param user
     * is of type String, the option that the user chose
     * @param folder
     * is of type Folder, The folder in which options will be implemented
     */
    public static void fApplied(String user, Folder folder){

        switch(user.toUpperCase().trim()){
            case "M":
                System.out.println("Input the index of email");
                int index =input.nextInt();
                input.nextLine();
                if((index <= 0) || (index> folder.getEmails().size())){
                    System.out.println("index isn't available");
                    break;
                }
                mailbox.printAllFolders();
                System.out.println("Input name of folder to move to ");
                Folder f = mailbox.getFolder(input.nextLine());
                if(f != null){
                    mailbox.moveEmail(folder.removeEmail(index-1), f);
                    System.out.println("Email Moved");
                    break;
                }
                System.out.println("Folder Not found");
                break;
            case "D":
                System.out.println("Input the index of email");
                index =input.nextInt();
                input.nextLine();
                if((index <= 0) || (index> folder.getEmails().size())){
                    System.out.println("index isn't available");
                    break;
                }
                mailbox.deleteEmail(folder.removeEmail(index -1));
                System.out.println("Successfully deleted");
                break;
            case "V":
                System.out.println("Input the index of email");
                index = input.nextInt();
                input.nextLine();
                if((index <= 0) || (index> folder.getEmails().size())){
                    System.out.println("index isn't available");
                    break;
                }
                Email email = folder.getEmails().get(index-1);
                System.out.println(email);
                break;
            case "SA":
                folder.sortBySubjectAscending();
                folder.setCurrentSortingMethod("sa");
                break;
            case "SD":
                folder.sortBySubjectDescending();
                folder.setCurrentSortingMethod("sd");
                break;
            case"DA":
                folder.sortByDateAscending();
                folder.setCurrentSortingMethod("da");
                break;
            case "DD":
                folder.sortByDateDescending();
                folder.setCurrentSortingMethod("dd");
            case "R":
                break;
            default:
                System.out.println("not an option");
                break;
        }

    }

    /**
     * prints the emails in the folders
     * @param folder
     * is of type Folder, will print the emails in the folder given.
     */
    public static void printFolder(Folder folder){
        System.out.println(folder.getName());
        System.out.printf("%-10s%-40s%-25s\n", "index", "Time Created", "Subject");
        System.out.println("-------------------------------------------------------------------------");
        for(int i = 0; i <folder.getEmails().size(); i++){
            System.out.printf("%-10d%-40s%-25s\n", i+1, folder.getEmails().get(i).getTimeStamp().getTime(), folder.getEmails().get(i).getSubject());
        }
    }



}
