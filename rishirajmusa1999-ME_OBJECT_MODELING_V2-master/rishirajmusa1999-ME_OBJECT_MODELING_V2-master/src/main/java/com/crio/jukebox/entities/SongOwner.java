package com.crio.jukebox.entities;


public class SongOwner {
    private final String id;
    private final Artist artist;
    private final ArtistGroup artistGroup;

  
    public SongOwner(ArtistGroup artistGroup) {
        this(null, artistGroup);
    }
    public SongOwner(Artist artist) {
        this(null, artist);
    }
    public SongOwner(String id, ArtistGroup artistGroup) {
        this(id,null, artistGroup);
    }
    public SongOwner(String id, Artist artist) {
        this(id, artist,null);
    }
    public SongOwner(String id, Artist artist, ArtistGroup artistGroup) {
        this.id = id;
        this.artist = artist;
        this.artistGroup = artistGroup;
    }


    public String getId(){
        return id;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SongOwner other = (SongOwner) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        if(artistGroup == null){
            return artist.name;
        }
        if(artist == null){
            return artistGroup.toString();
        }
        return "[artists=" + artist + ", " +artistGroup +  "]";
    }
 


}
