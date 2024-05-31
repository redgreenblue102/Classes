package Playlist;

import Playlist.Playlist;

import java.util.Scanner;
/**
 * Allows the user to interact with a playlist through the console
 * @author Carl Liu
 */
public class PlaylistOperations {
    /**
     * allows a user to interact to the playlist through a menu,
     * they can add song, print the playlist, get songs, print by artist, remove songs, and get the size of playlist.
     * @param args
     * to receive command line arguments
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Playlist p1 = new Playlist();
        String user;
        options();
        do {
            System.out.println("Enter Options, M for menu");
            user = input.nextLine();
            try {
                switch(user){
                    case "A":
                        SongRecord temp = A();
                        System.out.println("Input position as int");
                        p1.addSong(temp, input.nextInt());
                        System.out.println("Song added: " + temp.getTitle());

                        break;
                    case "G":
                        if(p1.size()==0){
                            System.out.println("No songs in playlist");
                        }else {
                            System.out.println("Input position");
                            int pos = input.nextInt();
                            System.out.println(String.format("%-15s%-25s%-25s%s\n", "Position", "Title", "Artist", "Length") +
                                    "---------------------------------------------------------------------\n"
                                    + String.format("%-15d", pos) + p1.getSong(pos));
                        }
                        break;
                    case "R":
                        if(p1.size()==0){
                            System.out.println("No songs in playlist");
                        }else {
                            System.out.println("Input position");
                            int pos = input.nextInt();
                            String title = p1.getSong(pos).getTitle();
                            p1.removeSong(pos);
                            System.out.println("Song removed: " + title);
                        }
                        break;
                    case "P":
                        p1.printAllSongs();
                        break;
                    case "B":
                        if(p1.size()==0){
                            System.out.println("No songs in playlist");
                        }else {
                            System.out.println("Input artist");
                            Playlist partist = Playlist.getSongsByArtist(p1, input.nextLine());
                            partist.printAllSongs();
                        }
                        break;
                    case "S":
                        System.out.println("The playlist has " + p1.size() + " songs");
                        break;
                    case "Q":
                        break;
                    case "M":
                        options();
                        break;
                    default :
                        System.out.println("Invalid Option try again");
                        }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            input = new Scanner(System.in);
        }while(!user.equals("Q"));
        }

    /**
     * Prints all the options available to user to console
     */
    public static void options(){
        System.out.printf("%-25s%s\n","Add Song:", "A"  );
        System.out.printf("%-25s%s\n","Get Song:", "G"  );
        System.out.printf("%-25s%s\n","Remove Song:", "R"  );
        System.out.printf("%-25s%s\n","Print All Songs:", "P"  );
        System.out.printf("%-25s%s\n","Print Songs by Artist:", "B"  );
        System.out.printf("%-25s%s\n","Size:", "S"  );
        System.out.printf("%-25s%s\n","Quit:", "Q"  );
    }

    /**
     * goes through the user inputs for adding a song when input A is put in
     * @return
     * returns Playlist.SongRecord type which has its fields as the user inputs.
     */
    public static SongRecord A(){
        Scanner input = new Scanner(System.in);
        SongRecord temp= new SongRecord();
        System.out.println("Input title");
        temp.setTitle(input.nextLine());
        System.out.println("Input artist");
        temp.setArtist(input.nextLine());
        System.out.println("Input minutes as an int");
        temp.setMins(input.nextInt());
        System.out.println("Input seconds as an int");
        temp.setSecs(input.nextInt());
        return temp;

    }
}
