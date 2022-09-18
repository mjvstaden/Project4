import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

/**
 * Class for the Client
 */
public class Client {
    private static JTextField textField;
    public static JFrame frame;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int serverport;
    private String ServerIp;
    private String hostname;


    /**
     * Constructor for the Client
     */
    public Client() {
        getUserInfo();
    }

    /**
     * Main function that loops and sends information
     */
    private void start() {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Sets up the GUI for the Client
     */
    public void intializeGUI() {
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
                //output.writeObject("$[CODE:%25849565]$");
                String hostname = JOptionPane.showInputDialog("Enter hostname: ");
                //output.writeObject(hostname);
                //output.flush();
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

    public void getUserInfo() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();

        contentPane.setLayout(null);

        frame.setBounds(100, 100, 380, 250);
        frame.getContentPane().add(contentPane);

        JLabel lblEnterIpAddress = new JLabel("Enter Server IP Address");
        lblEnterIpAddress.setBounds(30, 80, 133, 15);
        contentPane.add(lblEnterIpAddress);

        JTextField textfield_ip_address = new JTextField();
        textfield_ip_address.setBounds(30, 105, 114, 19);
        contentPane.add(textfield_ip_address);
        textfield_ip_address.setColumns(10);

        JLabel lblEnterPortNumber = new JLabel("Enter port number");
        lblEnterPortNumber.setBounds(30, 150, 133, 15);
        contentPane.add(lblEnterPortNumber);

        JTextField textfield_port = new JTextField();
        textfield_port.setBounds(30, 175, 114, 19);
        contentPane.add(textfield_port);
        textfield_port.setColumns(10);
        textfield_port.setText("1024");

        JButton btnConnect = new JButton("Connect");
        btnConnect.setBounds(225, 165, 117, 25);
        contentPane.add(btnConnect);

        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boolean ip_check = false;
                boolean port_check = false;
                InetAddress inetAddress = null;
                String error = "";

                try {
                    inetAddress = InetAddress.getByName(textfield_ip_address.getText());
                    ip_check = true;
                } catch (UnknownHostException e) {
                    //JOptionPane.showMessageDialog(null, "Enter a valid IP address");
                    error += "Enter a valid IP address. ";
                    ip_check = false;
                }

                if (!textfield_port.getText().trim().equals("")) {
                    try {
                        serverport = Integer.parseInt(textfield_port.getText().trim());
                        port_check = true;
                    } catch (NumberFormatException e) {
                        error += "Enter a valid port number. ";
                        port_check = false;
                    }
                } else {
                    error += " Port number can't be empty.";
                }

                if (ip_check && port_check) {
                    try {
                        socket = new Socket(inetAddress.getHostName(), serverport);
                        frame.setVisible(false);
                        intializeGUI();
                        start();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "IP Address or port may be invalid", "Couldn't Connect",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error);
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * Main function for client
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
