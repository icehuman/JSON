package com.json.demo;

import com.json.demo.exeCommand;

public class command {
    public static void main(String[] args) throws Exception {
        exeCommand exe=new exeCommand();
        int i = exe.exeCommand();
        System.out.println(i);
    }
}
