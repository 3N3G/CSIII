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
	 * @return
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
	 * 
	 * @param tempo amount to change the speed by
	 */
	public void changeTempo(double tempo) {
		for (Note n : song) {
			n.setDuration(n.getDuration() / tempo);
		}
	}
	
	public void reverse() {
		Stack<Note> newStack = new Stack<>();
		while (!song.isEmpty()) {
			newStack.add(song.poll());
		}
		while (!newStack.isEmpty()) {
			song.add(newStack.pop());
		}
	}
	
	public void append(Melody other) {
		Queue<Note> songToAppend = other.song;
		while (!songToAppend.isEmpty()) {
			song.add(songToAppend.poll());
		}
	}
	
	public void play() {
		Queue<Note> songWithoutRepeats = removeRepeats(song);
		for (Note s : songWithoutRepeats) {
			s.play();
		}
	}

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
						repeatNote.setRepeat(false);
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
	
	private Queue<Note> copySong(Queue<Note> song) {
		Stack<Note> temp = new Stack<>();
		Stack<Note> tempReversed = new Stack<>();
		Queue<Note> copy = new LinkedList<>();
		
		while (!song.isEmpty()) {
			temp.add(song.poll());
		}
		while (!temp.isEmpty()) {
			tempReversed.add(temp.pop());
		}
		while (!tempReversed.isEmpty()) {
			Note n = tempReversed.pop();
			song.add(n);
			copy.add(n);
		}
		return copy;
	}
	
	
	
}
