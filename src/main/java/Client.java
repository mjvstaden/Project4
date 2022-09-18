import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the Client
 */
public class Client {
    private static JTextField textField;
    public static JFrame frame;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int serverport = 1024;
    private String ServerIp;
    /**
     * Constructor for the Client
     */
    public Client() {

        //socket = new Socket(1024);
    }

    /**
     * Main function that loops and sends information
     */
    private void start() {

    }

    private static void intializeGUI() {
        frame = new JFrame();
        frame.setBounds(100, 100, 825, 485);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBackground(Color.darkGray);

        JList list = new JList();
        list.setBounds(12, 12, 132, 428);
        frame.getContentPane().add(list);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(156, 12, 488, 373);
        frame.getContentPane().add(textArea);

        textField = new JTextField();
        textField.setBounds(156, 392, 488, 48);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Make Call");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(658, 70, 155, 46);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Send");
        btnNewButton_1.setBounds(656, 392, 157, 46);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Start Conference");
        btnNewButton_2.setBounds(658, 12, 155, 46);
        frame.getContentPane().add(btnNewButton_2);

        frame.setVisible(true);
    }

    /**
     * Main function for client
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
        intializeGUI();
    }
}
