package com.tongji.ems.coursemanage.util;

public class GenerateIdTenth {
    private static final long LIMIT = 10000000000L;
    private static long next = 0;

    public static long get10UniqueId() {

        long ID = System.currentTimeMillis() % LIMIT;
        if (ID <= next) {
            ID = (next + 1) % LIMIT;
        }
        next = ID;
        return ID;
    }
}
