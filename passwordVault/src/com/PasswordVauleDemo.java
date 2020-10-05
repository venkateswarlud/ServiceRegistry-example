package com;

public class PasswordVauleDemo {

    public static void main(String[] args) {

        final String passwordsafe="HQ-ACCT-JAS-PWV-PROD";
        final String userID="NROFTP";

        //download JavaPasswordSDK.jar
        PSDKPasswordRequest passwordRequest = new PSDKPasswordRequest();
        passwordRequest.setAttribute("AppDescs.AppID","APP_JAS_PRD");
        passwordRequest.setAttribute("Query","safe="+passwordsafe+";folder=rootlobject="+passwordsafe+"."+userID);
        passwordRequest.setAttribute("Reason","Retrive password");
        String password = javapasswordsdk.PasswordSDK.getPassword(passwordRequest).getContent();
    }
}
