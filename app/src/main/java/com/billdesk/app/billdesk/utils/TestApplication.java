package com.billdesk.app.billdesk.utils;

import com.billdesk.app.billdesk.services.ServiceUtil;

import java.io.IOException;

public class TestApplication {

    public static void main(String args[]) {
        try {
            ServiceUtil.getService().sendCode("484484484").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
