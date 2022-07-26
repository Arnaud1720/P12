package com.arnaud.p12.security;

public class SecParams {
    public static final long EXP_TIME =  10 * 25 * 60 * 60 * 1000;
    public static final String SECRET ="mySecretKey05030122°";
    public static final String PREFIX="Bearer ";
    public static final String[] urlAdmin = {"/user/all","/asso/association/all"};
    public static final String[] allAccess = {"/**/user/save","/**/**/**/delete"};


}
