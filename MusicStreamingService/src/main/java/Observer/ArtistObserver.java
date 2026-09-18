package Observer;

import Entities.Album;
import Entities.Artist;

public interface ArtistObserver {
    void update(Artist artist, Album newAlbum);
}