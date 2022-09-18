import javax.sound.sampled.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class VOIPSender {
    private static int port;
    private static boolean endCall;
    private static String IP;

    public VOIPSender(int port, String IPAddress) {
        this.port = port;
        this.IP = IPAddress;
        this.endCall = false;
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
     * Sets endCll to input parameter
     * @param endCall false if call should end.
     */
    public static void setEndCall(boolean endCall) {
        VOIPSender.endCall = endCall;
    }

    public static void main(String[] args) throws Exception {
        AudioFormat format = buildAudioFormat();

        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Not Supported");
            System.exit(1);
        }
        DatagramSocket socket = new DatagramSocket(port);
        InetAddress IPAddress = InetAddress.getByName(IP);

        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);

            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];
            int totalBytesRead = 0;

            line.start();
            while (!endCall) {
                numBytesRead = line.read(data, 0, data.length);
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, port);
                socket.send(sendPacket);
            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
