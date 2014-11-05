package com.asu.alliancebank.security.pki.impl;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashingForPKI {

	
	
	public String getHashedData(){
		return BCrypt.hashpw(IPKIConstants.PKI_CONTENT, BCrypt.gensalt());
	}
	
	public static void main(String args [] ){
		for(int i=0; i< 10;i++){
			String hash = BCrypt.hashpw(IPKIConstants.PKI_CONTENT, BCrypt.gensalt());
			System.out.println(hash);
			
			System.out.println(BCrypt.checkpw(IPKIConstants.PKI_CONTENT, hash));
		}
	}
}
