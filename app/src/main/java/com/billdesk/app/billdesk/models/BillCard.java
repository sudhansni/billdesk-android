package com.billdesk.app.billdesk.models;

import com.billdesk.app.billdesk.interfaces.PaymentStatus;

import java.util.Date;


public class BillCard {

    private Date dueDate;
    private Provider provider;
    private PaymentStatus status;
    private  float amount;
    private int colorId;

    public BillCard(Date dueDate, Provider provider, PaymentStatus status, float amount){
        this.dueDate = dueDate;
        this.provider = provider;
        this.status = status;
        this.amount = amount;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public Provider getProvider() {
        return provider;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public float getAmount() {
        return amount;
    }

}

