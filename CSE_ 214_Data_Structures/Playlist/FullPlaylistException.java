package Playlist;

/**
 * Exception that is thrown when playlist is full, aka 50 songs.
 * @author Carl Liu
 */
public class FullPlaylistException extends Exception{
    /**
     * Default constructor for exception, with text given
     */
    public FullPlaylistException(){
        super("Playlist.Playlist is full");
    }
}
