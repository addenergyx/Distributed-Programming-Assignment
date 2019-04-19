import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.*;

// Because background music is going to be constantly playing throughout decided 
// to use multithreading and have the music in its own thread
public class MusicPlayer implements Runnable {

	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	private Clip clip;
	
	public MusicPlayer (String... files ) {
		musicFiles = new ArrayList<String>();
		for(String file : files) {
			musicFiles.add("./media/music/" + file + ".wav");
		}
		
	}

	private void playSound(String fileName) {
		try {
			
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new  DataLine.Info(Clip.class, format);
			
			
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20); //lower volume
			
			clip.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void run() {

		playSound(musicFiles.get(currentSongIndex));
		
	}
	
	public void play() {
		if (clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	
	public void close() {
		stop();
		clip.close();
	}
	
	
	
	
	
	
	
	
	
	
	
}
