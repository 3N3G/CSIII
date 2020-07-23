import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MelodyFail {
	private Queue<Note> song = new LinkedList<Note>();
	
	public MelodyFail(Queue<Note> song) {
		if (song != null) {
			this.song = song;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public double getLength() {
		double len = 0.0;
		for (Note n : song) {
			len += (n.getDuration());
		}
		return len;
	}
	
	public String toString() {
		String string = "";
		for (Note s : song) {
			string += s.toString() + "\n";
		}
		return string;
	}
	
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
	
	public void append(MelodyFail other) {
		Queue<Note> songToAppend = other.song;
		while (!songToAppend.isEmpty()) {
			song.add(songToAppend.poll());
		}
	}
	
	public void play() {
		Queue<Note> songWithoutRepeats = new LinkedList<>();
		songWithoutRepeats = removeRepeats(song);
		for (Note s : songWithoutRepeats) {
			s.play();
		}
	}

	private Queue<Note> removeRepeats(Queue<Note> song) {
		Queue<Note> copy = copySong(song);
		Stack<Note> s1 = new Stack<>();
		Stack<Note> s2 = new Stack<>();
		while (!copy.isEmpty()) {
			s1.add(copy.poll());
		}
		while (!s1.isEmpty()) {
			s2.add(s1.pop());
		}
		
		boolean inRepeat = false;
		while (!s2.isEmpty()) {
			boolean isNextRepeat = s2.peek().isRepeat();
			
			if (!inRepeat) {
				if (isNextRepeat) {
					copy.add(s2.pop());
					inRepeat = true;
				} else {
					copy.add(s2.pop());
				}
			} else {
				if (isNextRepeat) {
					
				} else {
					Note temp = s2.pop();
					copy.add(temp);
					s1.add(temp);
					inRepeat = false;
					s1 = reverse(s1);
					while (!s1.isEmpty()) {
						copy.add(s1.pop());
					}
				}
			}
		}
		return copy;
		
	}
	
	private Stack<Note> reverse(Stack<Note> stack) {
		Stack<Note> tempStack = new Stack<>();
		Stack<Note> newStack = new Stack<>();
		while (!stack.isEmpty()) {
			Note temp = stack.pop();
			newStack.add(temp);
			tempStack.add(temp);
		}
		while (!tempStack.isEmpty()) {
			stack.add(tempStack.pop());
		}
		return newStack;
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
