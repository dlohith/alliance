

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dwaraka
 */
public class ClientPKIHelperUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INVALID = "INVALID";
	private String selectedPath;
	
    /**
     * Creates new form ClientPKIHelperUI
     */
    public ClientPKIHelperUI() {
        initComponents();
    }

    public ClientPKIHelperUI(String heading){
    	super(heading);
    	initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hashedInputTextArea = new TextAreaMenu();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        encryptedTextArea = new TextAreaMenu();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        errorCodeLabel = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select Private Key : ");

        jButton1.setText("Select file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Enter Hashed string : ");

        hashedInputTextArea.setColumns(20);
        hashedInputTextArea.setRows(5);
        jScrollPane1.setViewportView(hashedInputTextArea);
        errorCodeLabel.setSize(20, 10);
        errorCodeLabel.setText("                                                  ");
        jButton2.setText("Encrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        encryptedTextArea.setColumns(20);
        encryptedTextArea.setRows(5);
        encryptedTextArea.setLineWrap(true);
        jScrollPane2.setViewportView(encryptedTextArea);

        jLabel3.setText("Encrypted String below");

        jLabel4.setText("Error code : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(errorCodeLabel)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(errorCodeLabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	try {
			
			selectedPath = createFileChooser(this);
			
			if(getPrivateKeyObject(selectedPath) == null){
				errorCodeLabel.setText("Invalid private key file");
				Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
				errorCodeLabel.setFont(font);
				errorCodeLabel.setForeground(Color.red);
			}else{
				errorCodeLabel.setText("");
			}
		} catch (ClassNotFoundException e2){

		}catch( IOException e1) {

		}
    }       
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) { 
    	String hashedString = hashedInputTextArea.getText();
		hashedString = hashedString.trim();
		
		if(hashedString.isEmpty()){
			errorCodeLabel.setText("Please enter the String ");
			Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
			errorCodeLabel.setFont(font);
			errorCodeLabel.setForeground(Color.red);
		}else{
			if(selectedPath != null || !selectedPath.isEmpty()){
				PrivateKey privateKey = getPrivateKeyObject(selectedPath);
				if(privateKey != null){
					String cipherText = encrypt(hashedString, privateKey);
					if(!cipherText.equals(INVALID)){
						errorCodeLabel.setText("");
						encryptedTextArea.setText(cipherText);
					}
					
				}else{
					errorCodeLabel.setText("Invalid private key file");
					Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
					errorCodeLabel.setFont(font);
					errorCodeLabel.setForeground(Color.red);
				}
			}
			
		}
    }

	private String createFileChooser(final JFrame frame)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		String filename = File.separator + "tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));
		fileChooser.showOpenDialog(frame);
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
	
	private PrivateKey getPrivateKeyObject(String selectedPath) {
		ObjectInputStream inputStream = null;
		try{
			inputStream = new ObjectInputStream(new FileInputStream(new File(selectedPath)));
			Object obj = inputStream.readObject();
			if(obj instanceof PrivateKey){
				PrivateKey privateKey = (PrivateKey) obj;
				return privateKey;				
			}
		}catch(Exception e){
			
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
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
			errorCodeLabel.setText("Invalid Hashed string");
			Font font =  new Font(Font.SANS_SERIF, Font.BOLD, 13);
			errorCodeLabel.setFont(font);
			errorCodeLabel.setForeground(Color.red);
		}
		return INVALID;
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientPKIHelperUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientPKIHelperUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientPKIHelperUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientPKIHelperUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientPKIHelperUI("Cipher Generator").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel errorCodeLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private TextAreaMenu hashedInputTextArea;
    private TextAreaMenu encryptedTextArea;
    // End of variables declaration                   
    
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
