package com.crio.jukebox.entities;

public class Song extends BaseEntity {
    private final SongOwner owner;
    private final Album album;

    public Song(Song song){
        this(song.id, song.name, song.owner, song.album);
    }
    //  public Song(String id, String name, SongOwner owner) {
    // //     this(name,owner);
    // //     this.id = id;
    // // }
    public Song(String id, String name, SongOwner owner, Album album) {
        this(name,owner,album);
        this.id = id;
    }
    public Song(String name, SongOwner owner , Album album ){
        this.name=name;
        this.owner=owner;
        this.album=album;
    }

    public SongOwner getOwner() {
        return owner;
    }
    public Album getAlbum(){
        return album;
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
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Song [id=" + id + ", name=" + name + ", owner=" +owner.toString() + "]";
    }
  
    
}
