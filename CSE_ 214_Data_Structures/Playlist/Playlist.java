package Playlist;

/**
 * Contains many songs in a playlist format.
 * @author Carl Liu
 */
public class Playlist implements Cloneable{
    private final int CAP = 50;
    private SongRecord songs[] = new SongRecord[CAP+1];
    private int size=0;

    /**
     * Default constructor of playlist with no songs
     */
    public Playlist(){

    }

    /**
     * Makes a deep clone of the playlist
     * @return
     * a playlist that has all the information of the original. Should be typecasted to Playlist.Playlist for use
     */
    public Object clone(){
        Playlist playlistCopy= new Playlist();
        for(int i = 1; i <= this.size ; i++){
            playlistCopy.songs[i] = (SongRecord)this.songs[i].clone();
            playlistCopy.size++;
        }
        return playlistCopy;
    }

    /**
     * checks if another playlist has equivalent data fields to the data fields of this instance.
     * @param obj
     * accepts object but should be of type Playlist.Playlist else will automatically return false
     * @return
     * returns boolean which is true if all data fields are equal, false if object isn't an instance of playlist or
     * there is a data field not equivalent
     */
    public boolean equals(Object obj){
        if(obj instanceof Playlist){
            Playlist temp = (Playlist)obj;
            if(this.size == temp.size) {

                for (int i = 1; i <= this.size; i++){
                    if (this.songs[i].equals(temp.songs[i]))
                        continue;
                    return false;
                }
                return true;
            }

        }
        return false;
    }

    /**
     * number of songs in the playlist
     * @return
     * returns int which is the amount of songs in the playlist
     */
    public int size(){
        return this.size;
    }

    /**
     * adds song to the playlist at any position 1 less than or equal to p  and  p less than or equal to size+1
     * @param song
     * Playlist.SongRecord type and is the song that will be added
     * @param position
     * where in the playlist the song will go
     * @throws IllegalArgumentException
     * Will throw if the position isn't  1 less than or equal to p  and  p less than or equal to #ofSongs+1
     * @throws FullPlaylistException
     * will throw when the playlist has 50 songs
     */
    public void addSong(SongRecord song, int position) throws IllegalArgumentException, FullPlaylistException {
        if(this.size >= 50)
            throw new FullPlaylistException();
        if((1 <= position) && (position<= this.size)){
            SongRecord temp[]=new SongRecord[CAP+1];
            System.arraycopy(this.songs, position , temp, 1, this.size-(position-1));
            System.arraycopy(temp, 1, this.songs, position+1, this.size-(position-1));
            this.songs[position] = song;
            this.size++;
        }else if (position == this.size+1) {
            this.songs[position] = song;
            this.size++;
        }
        else
            throw new IllegalArgumentException("Add Song Position not in range, has to be greater than " +
                    "or equal to 1 and less than or equal to " + (this.size+1) );
    }

    /**
     * removes song from the playlist based on given position
     * @param position
     * is of int type, position of the song to be removed
     * @throws IllegalArgumentException
     * will throw if the position doesn't correspond to a song.
     */
    public void removeSong(int position) throws IllegalArgumentException{
        if((1<=position) && (position <= this.size)) {
            SongRecord temp[]=new SongRecord[CAP+1];
            System.arraycopy(this.songs, position+1, temp, 1, this.size-(position));
            System.arraycopy(temp, 1, this.songs, position, this.size-(position));
            this.size--;
        }else
            throw new IllegalArgumentException("Remove Song Position not in range, has to be greater " +
                    "than or equal to 1 and less than or equal to "  + (this.size));

    }

    /**
     * gets a song in the playlist given a position
     * @param position
     * is of type int. The position of where the song to be accessed is
     * @return
     * returns Playlist.SongRecord object that is the song at the given position.
     * @throws IllegalArgumentException
     * will throw if the position doesn't correspond to a song
     */
    public SongRecord getSong(int position) throws IllegalArgumentException{
        if ((1<=position) && (position<=this.size) ){
            return this.songs[position];
        }else
            throw new IllegalArgumentException("Get Song Position is not in range, has to be greater " +
                    "than or equal to 1 and less than or equal to "  + (this.size));
    }

    /**
     * Gives the data fields of the playlist in a list format as a string.
     * @return
     * string that contains information on the playlist
     */
    public String toString(){
        String ret = String.format("%-15s%-25s%-25s%s\n","Position", "Title", "Artist", "Length") +
                "---------------------------------------------------------------------\n";
        for(int i = 1; i <= this.size; i++){
            ret = ret + String.format("%-15d", i) + this.songs[i] + "\n";
        }
        return ret;
    }

    /**
     * Prints to console the playlist in a list format, uses toString, for format. Contains all songs
     */
    public void printAllSongs(){
        System.out.println(this);
    }

    /**
     * Creates a playlist that contains songs from a playlist by a certain artist
     * @param originalList
     * Playlist.Playlist type, contains the songs that will be looked at for the specific artist
     * @param artist
     * the artist for all the songs in the new playlist
     * @return
     * Playlist.Playlist type that contains only songs from the original playlist by the given artist.
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist){
        if((originalList.size == 0) || (artist==null))
            return null;

        Playlist ret = new Playlist();
        for(int i = 1; i <=originalList.size; i++){
            if(originalList.songs[i].getArtist().equals(artist)){
               ret.songs[ret.size+1]= originalList.songs[i];
               ret.size++;
            }
        }
        return ret;
    }

}

/**
 * Test Program
 */
class PlaylistTest{
    public static void main(String[] args){
        try {
            Playlist p1 = new Playlist();
            Playlist p2 = new Playlist();
            SongRecord s1 = new SongRecord("Song 1", " a", 3, 0);
            SongRecord s2 = new SongRecord("Song 2", " a", 3, 5);

            p2.addSong(s1, 1);
            p1.addSong(s2, 1);
            p1.addSong(s1, 1);

            Playlist p3 = (Playlist) p1.clone();

            p3.addSong(s1, 3);
            System.out.println(p1.equals(p3));
            System.out.println(p1.size());
            System.out.println(p3.size());
            p3.printAllSongs();
        }catch (Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
    }
}
