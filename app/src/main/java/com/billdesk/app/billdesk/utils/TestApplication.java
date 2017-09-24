package com.billdesk.app.billdesk.utils;

import com.billdesk.app.billdesk.services.ServiceUtil;

import java.io.IOException;

/**
 * Created by sudha on 23-09-2017.
 */

public class TestApplication {

    public static void main(String args[]) {
        try {
            ServiceUtil.getService().sendCode("484484484").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
