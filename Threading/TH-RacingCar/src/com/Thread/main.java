package com.Thread;

public class main {
    public static void main(String[] args) {
        Threaddd thread1=new Threaddd("aaa");
        Thread thread01=new Thread(thread1);
        thread01.start();
        Threaddd thread2=new Threaddd("bbb");
        Thread thread02=new Thread(thread2);
        thread02.start();
        Threaddd thread3=new Threaddd("ccc");
        Thread thread03=new Thread(thread3);
        thread03.start();

    }
}
