package com.company;
// Create a program that implements a playlist for songs

// Create a Song class having Title and Duration for a song.

// The program will have an Album class containing a list of songs.

// The albums will be stored in an ArrayList

// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.

// Once the songs have been added to the playlist, create a menu of options to:-

// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.

// List the songs in the playlist

// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).

// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.

import java.util.ArrayList;
import java.util.LinkedList;

// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove())

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getArist() {
        return artist;
    }

    public boolean addSong(String title, double duration){
        Song existingSong = findSong(title);
        if(existingSong == null) {
            Song song = Song.createSong(title, duration);
            songs.add(song);
            return true;
        } else {
            System.out.println("Error: Create song failed. This song already exists on the album");
            return false;
        }
    }

    public void removeSong(String title){
        Song existingSong = findSong(title);
        if(existingSong != null) {
            songs.remove(existingSong);
        } else {
            System.out.println("Error: Remove failed. This song does not exist on the album");
        }
    }

    public Song findSong(String title){

        //Alternative way FOR EACH
        /*
        for(Song checkedSong: this.songs){
            if(checkSong.getTitle().equals(title)){
                return checkedSong;
            }
        }
        return null;
     }
         */
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getTitle().equals(title)){
                return songs.get(i);
            }
        }
        return null;
    }

    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playlist){
        int index = trackNumber - 1;
        if((index >= 0) && (index <= this.songs.size())){
            playlist.add(this.songs.get(index));
            return true;
        } else {
            System.out.println("This album does not have a track " + index);
            return false;
        }
    }

    public boolean addToPlaylist(String title, LinkedList<Song> playlist){
        Song checkedSong = findSong(title);
        if(checkedSong != null){
            playlist.add(checkedSong);
            return true;
        }
        return false;
    }

    public boolean removeFromPlaylist(String title, LinkedList<Song> playlist){
        Song checkedSong = findSong(title);
        if(checkedSong != null){
            playlist.remove(checkedSong);
            return true;
        }
        return false;
    }

    public void printSongs(){
        if(songs.size() == 0){
            System.out.println("No songs are on this album, please add it");
        } else {
            System.out.println("Songs:");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println("Track " + (i + 1) + "  Title: \t" + songs.get(i).getTitle()
                        + "\n\t\t Duration:  " + songs.get(i).getDuration()
                        + " seconds\n");
            }
        }
    }
}
