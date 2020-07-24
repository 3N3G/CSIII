// Gene Yang
// Assignment 12 Melody.java
// Creates functions to modify and 
// CSIII
// 7/21/20


import java.util.*;

public class Melody {
	/**
	 * This variable represents the song as a Queue of Notes 
	 */
	private Queue<Note> song = new LinkedList<Note>();
	
	/**
	 * This constructor initializes the song as a Queue
	 *  
	 * @param song
	 * @throws IllegalArgumentException if the Queue given is null
	 */
	public Melody(Queue<Note> song) {
		if (song != null) {
			this.song = song;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Gives the length of the song in seconds, including the time taken in repeat sections
	 * 
	 * @return length of the song in seconds
	 */
	public double getLength() {
		Queue<Note> songWithoutRepeats = removeRepeats(song);
		double len = 0.0;
		for (Note n : songWithoutRepeats) {
			len += (n.getDuration());
		}
		return len;
	}
	
	/**
	 * Returns the toString of the song
	 * Each line includes the toString of a Note
	 * 
	 * @return toString of the song
	 */
	public String toString() {
		String string = "";
		for (Note s : song) {
			string += s.toString() + "\n";
		}
		return string;
	}
	
	/**
	 * Changes the tempo of the song by a certain rate
	 * 
	 * @param tempo amount to change the speed by
	 */
	public void changeTempo(double tempo) {
		for (Note n : song) {
			n.setDuration(n.getDuration() / tempo);
		}
	}
	
	/**
	 * Reverses the song, or the Queue of its notes
	 */
	public void reverse() {
		Stack<Note> newStack = new Stack<>();
		while (!song.isEmpty()) {
			newStack.add(song.poll());
		}
		while (!newStack.isEmpty()) {
			song.add(newStack.pop());
		}
	}
	
	/**
	 * Appends another song to the end of this one
	 * 
	 * @param other Song to append
	 */
	public void append(Melody other) {
		Queue<Note> songToAppend = other.song;
		while (!songToAppend.isEmpty()) {
			song.add(songToAppend.poll());
		}
	}
	
	/**
	 * Plays the song with all the repeated parts included
	 */
	public void play() {
		Queue<Note> songWithoutRepeats = removeRepeats(song);
		for (Note s : songWithoutRepeats) {
			s.play();
		}
	}

	/**
	 * Removes the repeats in a Queue of Notes by creating an additional Queue
	 * by manually including the repeated segments
	 * 
	 * @param song
	 * @return
	 */
	private Queue<Note> removeRepeats(Queue<Note> song) {
		Queue<Note> copyOfSong = copySong(song);
		Queue<Note> repeatsRemoved = new LinkedList<>();
		Queue<Note> repeats = new LinkedList<>();
		boolean duringRepeat = false;
		while (!copyOfSong.isEmpty()) {
			if (copyOfSong.peek().isRepeat()) {
				if (duringRepeat) {	
					Note temp = copyOfSong.poll();
					repeatsRemoved.add(temp);
					repeats.add(temp);
					while (!repeats.isEmpty()) {
						Note repeatNote = repeats.poll();
						repeatsRemoved.add(repeatNote);
					}
					duringRepeat = false;
				} else {
					Note firstRepeatNote = copyOfSong.poll();
					repeats.add(firstRepeatNote);
					repeatsRemoved.add(firstRepeatNote);
					duringRepeat = true;
				}
			} else {
				if (duringRepeat) {
					Note temp = copyOfSong.poll();
					repeatsRemoved.add(temp);
					repeats.add(temp);
					duringRepeat = true;
				} else {
					repeatsRemoved.add(copyOfSong.poll());
				}
			}
		}
		return repeatsRemoved;
	}
	
	/**
	 * This helper method copies the given Queue into another one.
	 * 
	 * @param song the Queue to copy
	 * @return a copy of the given Queue
	 */
	private Queue<Note> copySong(Queue<Note> song) {
		Queue<Note> temp = new LinkedList<>();
		Queue<Note> copy = new LinkedList<>();
		while(!song.isEmpty()) {
			temp.add(song.poll());
		}
		while(!temp.isEmpty()) {
			Note noteToAdd = temp.poll();
			song.add(noteToAdd);
			copy.add(noteToAdd);
		}
		return copy;

	}
	
	
	
}
