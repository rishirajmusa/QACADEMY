package com.crio.jukebox.entities;

public class Artist extends BaseEntity {

    public Artist(Artist artist){
        this(artist.id, artist.name);
    }

    public Artist(String id,String name) {
        this(name);
        this.id=id;
    }

    public Artist(String name) {
        this.name=name;
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
        Artist other = (Artist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name ;
    }

    

}
