package com.yurakoreyba.jarproject;

import org.apache.commons.lang3.StringUtils;

public class ProjectMain {
    public static final String s = "Hello World";


    public static void main(String args[]) {
        if (!StringUtils.isBlank(s)){
            System.out.println("Hello world");
        }
    }
}
