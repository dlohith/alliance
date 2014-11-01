
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;


public class ClientPKIHelper {
	private String INVALID = "INVALID";
	private String SERVER_CIPHER_TEXT = "3hdk27ao28h93h5jd6hj5kr9";

	private void createAndShowGUI() throws FileNotFoundException,
	ClassNotFoundException, IOException {

		// Create and set up the window.
		final JFrame frame = new JFrame("Cipher Generator");

		// Display the window.
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set flow layout for the frame
		frame.getContentPane().setLayout(new BorderLayout());

		// Frame f = new JFrame("Text Area Examples");
		final JPanel upperPanel = new JPanel();
		final JPanel errorPanel = new JPanel();
		final JPanel lowerPanel = new JPanel();
		frame.getContentPane().add(upperPanel, "North");
		frame.getContentPane().add(errorPanel, "Center");
		frame.getContentPane().add(lowerPanel, "South");

		JButton button = new JButton("Choose Your Private Key file");
		upperPanel.add(button);

		final TextAreaMenu encryptedTextArea = new TextAreaMenu(20, 20);
		encryptedTextArea.setLineWrap(true);
		final JLabel errorLabel = new JLabel();
		lowerPanel.add(encryptedTextArea);
		errorPanel.add(errorLabel);
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
					String cipherText = encrypt(SERVER_CIPHER_TEXT, privateKey);
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

	class TextAreaMenu extends JTextArea implements MouseListener {

		private static final long serialVersionUID = 1L;
		private JPopupMenu pm=null;
		private JMenuItem copy=null;
		private JMenuItem delete=null;
		private JMenuItem cut=null;
		private JMenuItem undo=null;
		private JMenuItem paste=null;
		private JMenuItem redo=null;
		private JMenuItem selectAll=null;
		//UndoManager
		private UndoManager um=new UndoManager();
		public TextAreaMenu()
		{
			super();
			init();
		}

		public TextAreaMenu(int rows, int columns)
		{
			super(rows, columns);
			init();
		}

		public TextAreaMenu(String str)
		{
			super(str);
			init();
		}
		private void init()
		{
			this.addMouseListener(this);
			this.getDocument().addUndoableEditListener(um);
			pm=new JPopupMenu();

			//select all
			selectAll=new JMenuItem("Select All");
			selectAll.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});
			//paste
			paste=new JMenuItem("Paste");
			paste.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});

			//copy
			copy=new JMenuItem("Copy");
			copy.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});

			//delete
			delete=new JMenuItem("Delete");
			delete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});
			//cut
			cut=new JMenuItem("Cut");
			cut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});

			//undo
			undo=new JMenuItem("Undo");
			undo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});
			//redo
			redo=new JMenuItem("Redo");
			redo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					action(e);
				}
			});
			pm.add(selectAll);
			//pm.add(delete);
			pm.add(new JSeparator());
			pm.add(copy);
			//pm.add(cut);
			//pm.add(new JSeparator());
			//pm.add(paste);
			//pm.add(new JSeparator());
			//pm.add(undo);
			//pm.add(redo);
			this.add(pm);
		}
		//
		public boolean isClipboardString()
		{
			boolean b=false;
			Clipboard clipboard = this.getToolkit().getSystemClipboard();
			Transferable content = clipboard.getContents(this);
			try 
			{
				if(content.getTransferData(DataFlavor.stringFlavor) instanceof String) 
					b=true;
			} 
			catch (Exception e) 
			{}
			return b;
		}
		//
		public boolean isCanCopy() 
		{
			boolean b = false;
			int start = this.getSelectionStart();
			int end = this.getSelectionEnd();
			if (start != end)
				b = true;
			return b;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				delete.setEnabled(isCanCopy());
				copy.setEnabled(isCanCopy());
				paste.setEnabled(isClipboardString());
				cut.setEnabled(isCanCopy());
				undo.setEnabled(um.canUndo());
				redo.setEnabled(um.canRedo());
				pm.show(this, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

		public void action(ActionEvent e)
		{
			String str = e.getActionCommand();
			if(str.equals(copy.getText())) 
			{ 
				this.copy();
			} 
			else if(str.equals(paste.getText())) 
			{ 
				this.paste();
			} 
			else if(str.equals(cut.getText())) 
			{ 
				this.cut();
			}
			else if(str.equals(undo.getText()))
			{
				um.undo();
			}
			else if(str.equals(redo.getText()))
			{
				um.redo();
			}
			else if(str.equals(delete.getText()))
			{
				this.replaceSelection("");   
			}
			else if(str.equals(selectAll.getText()))
			{
				this.selectAll();
			}
		}

	}

}
