package com.yiban.suoai;

import org.junit.Test;

public class CommonTest {
    @Test
    public void test1(){
        String str = "heartbeat:13";
        int id = Integer.valueOf(str.substring(str.lastIndexOf(":")+1));
        System.out.println(id);
    }
}
