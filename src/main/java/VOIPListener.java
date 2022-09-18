import javax.sound.sampled.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;

public class VOIPListener {
    private static int port;
    private static boolean endCall;

    public VOIPListener(int port) {
        this.port = port;
        endCall = false;
    }

    /**
     * Builds up the AudioFormat with specified details.
     * @return AudioFormat
     */
    private static AudioFormat buildAudioFormat() {
        final AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
        final float RATE = 44100.0f;
        final int CHANNELS = 1;
        final int SAMPLE_SIZE = 16;
        final boolean ENDIAN = false;

        return new AudioFormat(ENCODING, RATE, SAMPLE_SIZE, CHANNELS, ((SAMPLE_SIZE / 8) * CHANNELS), RATE, ENDIAN);
    }

    /**
     * Sets EndCall to input parameter
     * @param status false if call should end.
     */
    public static void setEndCall(boolean status) {
        VOIPListener.endCall = status;
    }
    /**
     * Main function that runs the Listener
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        AudioFormat format = buildAudioFormat();
        SourceDataLine speakers;
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);

        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        speakers.open(format);

        DatagramSocket socket = new DatagramSocket(port);
        byte[] data = new byte[speakers.getBufferSize() / 5];
        speakers.start();
        while (!endCall) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            speakers.write(data, 0, data.length);
        }
    }

}
