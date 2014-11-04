package com.asu.alliancebank.security.pki.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.security.pki.IPKIManager;

@Service
public class PKIManager implements IPKIManager{

	
	
	private static final Logger logger = LoggerFactory
			.getLogger(PKIManager.class);

	
	@PostConstruct
	public void init(){
		String classPath = null;
		try {
			classPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("issue while getting class path for pki folder",e);
		}
		if(classPath != null){
			String pkiFolder = classPath.substring(0,classPath.indexOf("classes"))+ "classes" + File.separator + IPKIManager.PKI_FOLDER;
			createFolder(pkiFolder);
		}
	}
	
	public String[] getKeyLocs(String loginId){
		
		String publicKeyLoc = getKeyFolder(loginId) + File.separator + PUBLIC_KEY;
		String privateKeyLoc = getKeyFolder(loginId) + File.separator + PRIVATE_KEY;
		
		String keyLocs[] = {publicKeyLoc,privateKeyLoc};
		return keyLocs;
	}
	
	public void deleteKeys(String loginId){
		String publicKeyLoc = getKeyFolder(loginId) + File.separator + PUBLIC_KEY;
		String privateKeyLoc = getKeyFolder(loginId) + File.separator + PRIVATE_KEY;
		
		File deleteFile = new File(publicKeyLoc);
		deleteFile.delete();
		deleteFile = new File(privateKeyLoc);
		deleteFile.delete();
		deleteFile = new File(getKeyFolder(loginId));
		deleteFile.delete();
		
	}
	
	public void createKeyPairs(String loginId){
		KeyPairGenerator keyGen = null;

		try {
			keyGen = KeyPairGenerator
					.getInstance(IPKIManager.ALGORITHM);
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);

			KeyPair pair = keyGen.generateKeyPair();
			writeKeyToDisk(pair, loginId);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Issue while creating key pair");
		} catch (NoSuchProviderException e) {
			logger.error("Issue while creating SecureRandom ");
		}
	}
	
	public boolean isResponseValid(String encrypted, String loginId){
		try{
		String keyFolder = getKeyFolder(loginId);
		
		String publicKeyFile = keyFolder + File.separator + IPKIManager.PUBLIC_KEY;
		
		PublicKey publicKey = getPublicKey(publicKeyFile);

		BigInteger bigEncrypted = new BigInteger(encrypted);
		byte encryptedBytes [] = bigEncrypted.toByteArray();
		
		if(publicKey != null && (encrypted !=null && !encrypted.isEmpty())){
			String decrypt = decrypt(encryptedBytes, publicKey);
			if(decrypt.equals(IPKIManager.DATA_TO_BE_DECRYPTED)){
				return true;
			}
		}else{
			return false;
		}
		}catch(Exception e){
			
		}
		return false;
	}
	
	public boolean isResponseValidWithHashedString(String encrypted, String loginId){
		try{
		String keyFolder = getKeyFolder(loginId);
		
		String publicKeyFile = keyFolder + File.separator + IPKIManager.PUBLIC_KEY;
		
		PublicKey publicKey = getPublicKey(publicKeyFile);

		BigInteger bigEncrypted = new BigInteger(encrypted);
		byte encryptedBytes [] = bigEncrypted.toByteArray();
		
		if(publicKey != null && (encrypted !=null && !encrypted.isEmpty())){
			String decrypt = decrypt(encryptedBytes, publicKey);
			
			if(BCrypt.checkpw(IPKIConstants.PKI_CONTENT, decrypt)){
				return true;
			}
		}else{
			return false;
		}
		}catch(Exception e){
			
		}
		return false;
	}
	
	
	private PublicKey getPublicKey(String fileLoc){
		ObjectInputStream inputStream = null;
		
		try {
			inputStream = new ObjectInputStream(
					new FileInputStream(new File(fileLoc)));
			
			return (PublicKey) inputStream.readObject();
		} catch (FileNotFoundException e) {
			logger.error("File not found",e);
		} catch (IOException e) {
			logger.error("IO Issue ",e);
		} catch (ClassNotFoundException e) {
			logger.error("public key file not correct ",e);
		}finally{
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(IOException e){
					logger.error("IO Issue while closing ",e);
				}
			}
		}

		return null;
	}
	
	
	private void writeKeyToDisk(KeyPair pair,String loginId){
		
		String userPKIFolder = getKeyFolder(loginId);
		createFolder(userPKIFolder);
		String privateKeyFile = userPKIFolder+ File.separator+ PRIVATE_KEY;
		ObjectOutputStream ous = null;
		try {
			ous = new ObjectOutputStream ( new FileOutputStream(new File(privateKeyFile)));
			ous.writeObject(pair.getPrivate());
			ous.flush();
		} catch(IOException e){
			logger.error("IO Issue ",e);
		}finally {
			if(ous != null){
				try{
					ous.close();
				}catch(IOException e){
					logger.error("IO Issue while closing ",e);
				}
			}
		}
		
		String publicKeyFile = userPKIFolder+ File.separator+ PUBLIC_KEY;
		
		try {
			ous = new ObjectOutputStream ( new FileOutputStream(new File(publicKeyFile)));
			ous.writeObject(pair.getPublic());
			ous.flush();
		} catch(IOException e){
			logger.error("IO Issue ",e);
		}finally {
			if(ous != null){
				try{
					ous.close();
				}catch(IOException e){
					logger.error("IO Issue while closing ",e);
				}
			}
		}
	}
	
	private String getKeyFolder(String loginId){
		String classPath = null;
		try {
			classPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("issue while getting class path for pki folder",e);
		}
		if(classPath != null){
			String pkiFolder = classPath.substring(0,classPath.indexOf("classes"))+ "classes" + File.separator + IPKIManager.PKI_FOLDER;
			
			String userPKIFolder = pkiFolder + File.separator + loginId;
			return userPKIFolder;
		}
		return "";
	}
	
	
	private String createFolder(String userPKIFolder){
		File f = new File(userPKIFolder);
		
		if(!f.exists()){
			if(f.mkdir())
				return userPKIFolder;
			
		}
		return "";
	}
	
	private byte[] encrypt(String text, PrivateKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(IPKIManager.ALGORITHM);
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return cipherText;
	}
	
	public static String decrypt(byte[] text, PublicKey key) {
		byte[] dectyptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);
			return new String(dectyptedText);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return "INVALID";
	}
}
