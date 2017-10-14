package com.billdesk.app.billdesk.models;

import android.support.annotation.ColorRes;

import java.util.Date;


public class BillCard {

    private Date dueDate;
    private Provider provider;
    private PaymentStatus status;
    private  float amount;
    private int colorId;

    public enum PaymentStatus {
        PAID,
        PENDING,
        OVERDUE;

    }
    public BillCard(Date dueDate, Provider provider, PaymentStatus status, float amount, @ColorRes int colorId){
        this.dueDate = dueDate;
        this.provider = provider;
        this.status = status;
        this.amount = amount;
        this.colorId = colorId;
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

    public int getColorId() {
        return colorId;
    }
}

