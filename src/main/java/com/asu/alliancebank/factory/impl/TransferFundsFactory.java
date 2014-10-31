package com.asu.alliancebank.factory.impl;

/**
 * @author Sravya
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.TransferFundsBackingBean;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.factory.ITransferFundsFactory;

@Service
public class TransferFundsFactory implements ITransferFundsFactory{
	private static final Logger logger = LoggerFactory
			.getLogger(UserFactory.class);
	
	@Override
	public TransferFunds createEmptyTransferFundsObject(){
		return new TransferFunds();
	}

	@Override
	public TransferFunds createTransferFundsInstance(TransferFundsBackingBean transferFundsBackingBean) {					
				
		return new TransferFunds(transferFundsBackingBean.getFromAccountId(),			
				transferFundsBackingBean.getFromAccountId(),
				transferFundsBackingBean.getAmount());				
	}

	@Override
	public TransferFunds createTransferFundsInstance(String fromAccountId,String toAccountId,Long amount) {
		return new TransferFunds(fromAccountId,toAccountId ,amount);
	}
}
