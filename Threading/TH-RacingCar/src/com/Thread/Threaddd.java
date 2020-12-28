package com.Thread;

public class Threaddd extends Thread{
    String name;
    public Threaddd(String name) {
        this.name=name;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("chay " +name+" i="+ i);
        }
    }
}
