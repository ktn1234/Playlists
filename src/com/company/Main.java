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

import java.util.*;

// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove()
public class Main {

    private static ArrayList<Album> albumsDirectory = new ArrayList<Album>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to K-Music");
        preAddedAlbumsDirectory();
        LinkedList<Song> playlist = new LinkedList<Song>();
        System.out.println("Check out the pre-added albums directory");
        System.out.println("Then add those songs to your playlist to play!\n");
        printOptions();

        boolean quit = false;
        while (!quit) {
            System.out.println("\nPlease enter a choice");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    quit = true;
                    System.out.println("exiting...");
                    break;
                case "1":
                    printOptions();
                    break;
                case "2":
                    addSongToPlaylist(playlist);
                    break;
                case "3":
                    printPlaylist(playlist);
                    break;
                case "4":
                    play(playlist);
                    break;
                case "5":
                    printAllAlbumDirectory();
                    break;
                case "6":
                    printAlbumSongs();
                    break;
                case "7":
                    printAllAlbumDirectoryAndSongs();
                    break;
                case "8":
                    removeSongFromPlaylist(playlist);
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please try again");
                    break;
            }
        }
        //albumsDirectory.get(0).addToPlaylist("Helix", playlist);
    }

    //TODO: Finish this.
    public static boolean removeSongFromPlaylist(LinkedList<Song> playlist) {
        System.out.println("Please enter the song you want to remove from your playlist:");
        String name = scanner.nextLine();
        for (int i = 0; i < albumsDirectory.size(); i++) {
            if (albumsDirectory.get(i).removeFromPlaylist(name, playlist)) {
                System.out.println("Song: \"" + name + "\" was successfully removed from playlist");
                return true;
            }
        }
        System.out.println("Error: Failed to remove song from playlist. Song does not exist in any albums in album directory.");
        return false;
    }

    public static boolean addSongToPlaylist(LinkedList<Song> playlist) {
        System.out.println("Please enter song to add to playlist:");
        String name = scanner.nextLine();
        for (int i = 0; i < albumsDirectory.size(); i++) {
            if (albumsDirectory.get(i).addToPlaylist(name, playlist)) {
                System.out.println("Song: \"" + name + "\" was successfully added to playlist at index " + (playlist.size()));
                return true;
            }
        }
        System.out.println("Error: Failed to add song to playlist. Song does not exist in any albums in album directory.");
        return false;
    }

    public static void printPlaylist(LinkedList<Song> playlist) {
        Iterator<Song> i = playlist.iterator();
        System.out.println("Playlist Songs:");
        System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
        int count = 0;
        while (i.hasNext()) {
            Song song = i.next();
            System.out.print((++count) + "\t\t\t" + song.getTitle() + "\t\t" + findArtist(song) + "\t\t" + song.getDuration() + "\n");
        }
    }

    public static String findArtist(Song song) {
        for (int i = 0; i < albumsDirectory.size(); i++) {
            Song existingSong = albumsDirectory.get(i).findSong(song.getTitle());
            if (existingSong != null) {
                return albumsDirectory.get(i).getArist();
            }
        }
        return null;
    }

    private static void preAddedAlbumsDirectory() {
        Album Awake = new Album("Awake", "Alison Wonderland");
        Awake.addSong("Good Enough", 138);
        Awake.addSong("No", 241);
        Awake.addSong("Okay", 234);
        Awake.addSong("High", 175);
        Awake.addSong("Here 4 U", 165);
        Awake.addSong("Happy Place", 201);
        Awake.addSong("Sometimes Love", 202);
        Awake.addSong("Awake", 223);
        albumsDirectory.add(Awake);

        Album Skin = new Album("Skin", "Flume");
        Skin.addSong("Helix", 210);
        Skin.addSong("Never Be Like You", 235);
        Skin.addSong("Say It", 263);
        Skin.addSong("Wall Fuck", 189);
        Skin.addSong("You Know", 202);
        Skin.addSong("Take A Chance", 329);
        Skin.addSong("Innocence", 378);
        albumsDirectory.add(Skin);
    }

    public static void printOptions() {
        System.out.println("0 - Exit program" +
                "\n1 - Print choice options" +
                "\n2 - Add song to playlist" +
                "\n3 - List songs from playlist" +
                "\n4 - Start playing songs in playlist" +
                "\n5 - List all albums in Album Directory" +
                "\n6 - List all songs in an album in Album Directory" +
                "\n7 - List all albums and songs in Album Directory" +
                "\n8 - Remove song from playlist");
    }

    public static void printPlayingOptions() {
        System.out.println("0 - Exit playing playlist" +
                "\n1 - Play next song" +
                "\n2 - Play previous song" +
                "\n3 - Replay song" +
                "\n4 - List current song playing" +
                "\n5 - List playlist songs" +
                "\n6 - Remove current song from playlist" +
                "\n\tMust be playing current song");
    }

    public static void play(LinkedList<Song> playlist) {
        boolean forward = true;
        if (playlist.size() == 0) {
            System.out.println("Error: There are no songs in the playlist");
            printOptions();
            return;
        }
        ListIterator<Song> i = playlist.listIterator();

        boolean quit = false;
        while (!quit) {
            System.out.println();
            printPlayingOptions();
            System.out.println("Please enter a choice:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    System.out.println("Quitting playing your playlist...");
                    quit = true;
                    printOptions();
                    break;
                case "1":
                    if (!forward) {
                        if (i.hasNext()) {
                            i.next();
                        }
                        forward = true;
                    }
                    if (i.hasNext()) {
                        Song song = i.next();
                        System.out.println("Next song playing now...");
                        System.out.println("Currently playing:");
                        System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                        System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                findArtist(song) + "\t\t" + song.getDuration());
                    } else {
                        System.out.println("Currently at the end of playlist");
                        forward = false;
                    }
                    break;
                case "2":
                    if (forward) {
                        if (i.hasPrevious()) {
                            i.previous();
                        }
                        forward = false;
                    }
                    if (i.hasPrevious()) {
                        Song song = i.previous();
                        System.out.println("Previous song playing now...");
                        System.out.println("Currently playing:");
                        System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                        System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                findArtist(song) + "\t\t" + song.getDuration());
                    } else {
                        System.out.println("Currently at the beginning of playlist");
                        forward = true;
                    }
                    break;
                case "3":
                    System.out.println("Replaying Song...");
                    if (forward) {
                        Song song = i.previous();
                        System.out.println("Currently playing:");
                        System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                        System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                findArtist(song) + "\t\t" + song.getDuration());
                        i.next();
                    }
                    if (!forward) {
                        Song song = i.next();
                        i.previous();
                        System.out.println("Currently playing:");
                        System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                        System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                findArtist(song) + "\t\t" + song.getDuration());
                    }
                    break;
                case "4":
                    if (forward) {
                        if (i.hasPrevious()) {
                            Song song = i.previous();
                            System.out.println("Currently playing:");
                            System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                            System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                    findArtist(song) + "\t\t" + song.getDuration());
                            i.next();
                        } else {
                            System.out.println("Currently at the beginning of the playlist");
                        }
                    }
                    if (!forward) {
                        if (i.hasNext()) {
                            Song song = i.next();
                            System.out.println("Currently playing:");
                            System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                            System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                    findArtist(song) + "\t\t" + song.getDuration());
                            i.previous();
                        } else {
                            System.out.println("Currently at the end of the playlist");
                        }
                    }
                    break;
                case "5":
                    printPlaylist(playlist);
                    break;

                /*TODO: Bug
                    For the remove method to work, you must be playing a current song
                    In other words, the cursor must be between element(s).
                    This will throw an exception if the linked list cursor pointer is at the beginning
                    or at the end of the list.
                */
                case "6":
                    if(playlist.size() == 0){
                        System.out.println("There are no songs in the playlist after that remove.");
                    }
                    if (playlist.size() > 0) {
                        i.remove();
                        if (i.hasNext()) {
                            Song song = i.next();
                            System.out.println("Playing next song...");
                            System.out.println("Currently playing:");
                            System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                            System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                    findArtist(song) + "\t\t" + song.getDuration());
                            forward = true;
                        } else if(i.hasPrevious()){
                            Song song = i.previous();
                            System.out.println("Playing previous song...");
                            System.out.println("Currently playing:");
                            System.out.println("Track:\t\tSong:\t\tArtist:\t\tDuration:");
                            System.out.println((playlist.indexOf(song) + 1) + "\t\t\t" + song.getTitle() + "\t\t" +
                                    findArtist(song) + "\t\t" + song.getDuration());
                            forward = false;
                        } else {
                            System.out.println("There are no songs in the playlist after that remove.");
                        }
                    }
                    break;
            }
        }
    }

    public static void printAlbumSongs() {
        System.out.println("Function: Printing Album Songs");
        System.out.println("Enter an album:");
        String name = scanner.nextLine();
        for (int i = 0; i < albumsDirectory.size(); i++) {
            if (albumsDirectory.get(i).getName().equals(name)) {
                System.out.println("Album: " + albumsDirectory.get(i).getName() + "\nArtist: " + albumsDirectory.get(i).getArist());
                albumsDirectory.get(i).printSongs();
                System.out.println();
                return;
            }
        }

        System.out.println("Error: Album cannot be found. Album does not exist");

    }

    public static void printAllAlbumDirectory() {
        for (int i = 0; i < albumsDirectory.size(); i++) {
            System.out.println("Album: " + albumsDirectory.get(i).getName() + "\nArtist: " + albumsDirectory.get(i).getArist());
            System.out.println();
        }
    }

    public static void printAllAlbumDirectoryAndSongs() {
        for (int i = 0; i < albumsDirectory.size(); i++) {
            System.out.println("Album: " + albumsDirectory.get(i).getName() + "\nArtist: " + albumsDirectory.get(i).getArist());
            albumsDirectory.get(i).printSongs();
            System.out.println();
        }
    }
}
