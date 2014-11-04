
import java.math.BigInteger;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientPKIHelper {

	private String INVALID = "INVALID";
	private String TEXT = "ALLIANCEBANK";

	private void createAndShowGUI() throws FileNotFoundException,
	ClassNotFoundException, IOException {

		// Create and set up the window.
		final JFrame frame = new JFrame("Cipher Generator");

		// Display the window.
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set flow layout for the frame
		frame.getContentPane().setLayout(new FlowLayout());

		// Frame f = new JFrame("Text Area Examples");
		final JPanel upperPanel = new JPanel();
		final JPanel lowerPanel = new JPanel();
		frame.getContentPane().add(upperPanel, "North");
		frame.getContentPane().add(lowerPanel, "South");

		JButton button = new JButton("Choose Your Private Key file");
		upperPanel.add(button);
		
		final JTextArea encryptedTextArea = new JTextArea("", 20, 20);
		encryptedTextArea.setLineWrap(true);
		final JLabel errorLabel = new JLabel();
		lowerPanel.add(encryptedTextArea);
		lowerPanel.add(errorLabel);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String path = createFileChooser(frame);
					if (path != null) {
						File privateKeyFile = new File(path);
						if (privateKeyFile.exists()|| privateKeyFile.length() < 2000) {
							String encryptedText = callencrypt(path);
							if(!encryptedText.equals(INVALID)){
								errorLabel.setText("");
								encryptedTextArea.setText(encryptedText);
								
							}else{
								errorLabel.setText("Invalid private key file");
								Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
								errorLabel.setFont(font);
								errorLabel.setForeground(Color.red);
							}
							
						}else{
							errorLabel.setText("Invalid private key file");
							Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
							errorLabel.setFont(font);
							errorLabel.setForeground(Color.red);
						}
					}
				} catch (ClassNotFoundException e2){
					
				}catch( IOException e1) {

				}
			}
		});

		frame.pack();
	}

	private String createFileChooser(final JFrame frame)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		String filename = File.separator + "tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));
		fileChooser.showOpenDialog(frame);
		return fileChooser.getSelectedFile().getAbsolutePath();
	}

	private String encrypt(String text, PrivateKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
			return new BigInteger(cipherText).toString();
		} catch (Exception e) {
		}
		return INVALID;
	}

	private String callencrypt(String path) {
		if (path != null) {
			ObjectInputStream inputStream = null;

			try{
				inputStream = new ObjectInputStream(new FileInputStream(new File(path)));
				Object obj = inputStream.readObject();
				if(obj instanceof PrivateKey){
					final PrivateKey privateKey = (PrivateKey) obj ;
					String cipherText = encrypt(TEXT, privateKey);
					if(!cipherText.equals(INVALID)){
						return cipherText;
					}
				}
			}catch(IOException e){

			} catch (ClassNotFoundException e) {

			}finally{
				try{
					if(inputStream != null){
						inputStream.close();
					}
				}catch(IOException e){

				}
			}
		}
		return INVALID;
	}

	public static void main(String[] args) {

		final ClientPKIHelper clientPKIHelper = new ClientPKIHelper();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					clientPKIHelper.createAndShowGUI();
				} catch (ClassNotFoundException e){
					
				}catch( IOException e) {

				}
			}

		});

	}

}
