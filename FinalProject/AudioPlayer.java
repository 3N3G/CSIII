// Gene Yang
// Final Assignment AudioPlayer.java
// Plays an audio wav file from the filename
// CSIII
// 7/30/20

import java.io.*;
import javax.sound.sampled.*;

public class AudioPlayer {
	/**
	 * Plays a sound based on the filename. 
	 * (By TSCHWAB on https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java)
	 * @param fileName
	 */
	public static void playSound(String fileName) {
		try {
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(new File(fileName));
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
		    System.out.println("Error playing music file: " + fileName);
		}
	}
}