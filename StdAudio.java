package Csc402;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public final class StdAudio {

	public static final int SAMPLE_RATE = 44100;

	private static final int BYTES_PER_SAMPLE = 2;                // 16-bit audio
	private static final int BITS_PER_SAMPLE = 16;                // 16-bit audio
	private static final double MAX_16_BIT = Short.MAX_VALUE;     // 32,767
	private static final int SAMPLE_BUFFER_SIZE = 4096;


	private static SourceDataLine line;   // to play the sound
	private static byte[] buffer;         // our internal buffer
	private static int bufferSize = 0;    // number of samples currently in internal buffer

	private StdAudio() {
		// can not instantiate
	}

	// static initializer
	static {
		init();
	}

	// open up an audio stream
	private static void init() {
		try {
			// 44,100 samples per second, 16-bit audio, mono, signed PCM, little Endian
			AudioFormat format = new AudioFormat(SAMPLE_RATE, BITS_PER_SAMPLE, 1, true, false);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE);

			// the internal buffer is a fraction of the actual buffer size, this choice is arbitrary
			// it gets divided because we can't expect the buffered data to line up exactly with when
			// the sound card decides to push out its samples.
			buffer = new byte[SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE/3];
		}
		catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}

		// no sound gets made before this call
		line.start();
	}

	public static void close() {
		line.drain();
		line.stop();
	}

	public static void play(double sample) {

		// clip if outside [-1, +1]
		if (Double.isNaN(sample)) throw new IllegalArgumentException("sample is NaN");
		if (sample < -1.0) sample = -1.0;
		if (sample > +1.0) sample = +1.0;

		// convert to bytes
		short s = (short) (MAX_16_BIT * sample);
		buffer[bufferSize++] = (byte) s;
		buffer[bufferSize++] = (byte) (s >> 8);   // little Endian

		// send to sound card if buffer is full
		if (bufferSize >= buffer.length) {
			line.write(buffer, 0, buffer.length);
			bufferSize = 0;
		}
	}

	public static void play(double[] samples) {
		if (samples == null) throw new IllegalArgumentException("argument to play() is null");
		for (int i = 0; i < samples.length; i++) {
			play(samples[i]);
		}
	}
}
