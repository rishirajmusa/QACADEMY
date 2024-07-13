package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistGroup extends BaseEntity{
    private final List<Artist> artists;

    public ArtistGroup(ArtistGroup artistGroup){
        this(artistGroup.id, artistGroup.name, artistGroup.artists);
    }
    public ArtistGroup(String id, String name, List<Artist> artists) {
        this( artists);
        this.name =name;
        this.id=id;
    }
    public ArtistGroup(List<Artist> artists){
        this.artists=artists;
    }


    public StringBuilder getArtists() {
        List<Artist> aList = artists.stream().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<aList.size();i++){
            sb.append(aList.get(i));
            if(i<aList.size()-1){
                sb.append(",");
            }
        }
        return sb;
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
        ArtistGroup other = (ArtistGroup) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return getArtists().toString();
    }
    



}
