package Playlist;

/**
 * Contains info about a specific song.
 * @author Carl Liu
 */
public class SongRecord implements Cloneable{
    private String title;
    private String artist;
    private int mins;
    private int secs;

    /**
     * Constructs a default Playlist.SongRecord object
     */
    public SongRecord(){

    }

    /**
     * Constructs Playlist.SongRecord object with user input
     * @param title
     *  is of type string, title of song
     * @param artist
     *  is of type string, artist of song
     * @param mins
     *  is of type int, minute length of song
     * @param secs
     *  is of type int, leftover seconds in song
     */
    public SongRecord(String title, String artist, int mins, int secs){
        setMins(mins);
        setArtist(artist);
        setSecs(secs);
        setTitle(title);

    }

    /**
     * Getter method for minutes
     * @return returns how many minutes long the song is
     */
    public int getMins(){
        return this.mins;
    }

    /**
     * Getter method for seconds
     * @return
     *  returns how many seconds left after the last minute
     */

    public int getSecs(){
        return this.secs;
    }

    /**
     * Getter method for Song title
     * @return
     *  returns the title of the song
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter method for Song artist
     * @return
     *  returns song title
     */
    public String getArtist(){
        return this.artist;
    }

    /**
     * mutator method that allows user to change the minute length of song
     * @param mins
     *  is of type int and will replace member variable mins
     * @throws
     *  IllegalArgumentException if the input is negative
     */

    public void setMins(int mins) throws IllegalArgumentException{
        if(mins<0)
            throw new IllegalArgumentException("Mins Cannot be negative");
        this.mins=mins;
    }

    /**
     * mutator method that allows user to change the second length of song
     * @param secs
     *  is of type int and will replace member variable secs
     * @throws IllegalArgumentException if input is not between 0 and 59
     */
    public void setSecs(int secs) throws IllegalArgumentException{
        if((secs>59) || (secs<0))
            throw new IllegalArgumentException("Seconds has to be between 0 and 59");
        this.secs=secs;
    }

    /**
     * mutator method that allows user to change the artist of the song
     * @param artist
     *  is of type string and replaces member variable artist
     */
    public void setArtist(String artist){
        this.artist=artist;
    }

    /**
     * mutator method that allows user to change the title of the song
     * @param title
     *  is of type string and replaces member variable title
     */
    public void setTitle(String title){
        this.title=title;
    }

    /**
     * to string method that returns information about the song.
     * @return
     *  returns all member information about the song as a string
     */
    public String toString(){
        return String.format("%-25s%-25s%d:%02d", this.title , this.artist ,this.mins , this.secs);
    }

    /**
     * Clones this Playlist.SongRecord object
     * @return
     *  Returns a clone of this Playlist.SongRecord as an object
     */
    public Object clone(){
        try {
            return super.clone();
        }
        catch(Exception ex){
            throw new RuntimeException("Unclonable");
        }
    }

    /**
     * checks if two objects are equal based on the Playlist.SongRecord data fields
     * @param obj
     *  takes in an object to compare with current song
     * @return
     *  returns boolean, true if the two songs are equal in all data fields, false otherwise.
     */
    public boolean equals(Object obj){
        if(obj instanceof SongRecord){
            SongRecord temp = (SongRecord)obj;
            return (temp.getArtist().equals(this.artist)) && (temp.getTitle().equals(this.title))
            && (temp.getMins()==this.mins) && (temp.getSecs()==this.secs);
            }
        return false;
    }
}
