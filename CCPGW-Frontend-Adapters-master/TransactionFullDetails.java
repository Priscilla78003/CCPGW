/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.payment.gateway.api.json;

import com.truteq.ccpgw.transaction.manager.model.TransactionDetails;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TransactionFullDetails extends TransactionDetails {

    private PaymentDetails paymentDetails;

    
    public TransactionFullDetails(){
        super();
    }
    
    public TransactionFullDetails(String threeDSServerTransID,
                              String order_id,
                              String transaction_id,
                              String secretKey,
                              String payload){
      super(threeDSServerTransID,
            order_id,
            transaction_id,
            secretKey,
            payload);    
    }   
    

    /**
     * @return the paymentDetails
     */
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * @param paymentDetails the paymentDetails to set
     */
    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
      

}