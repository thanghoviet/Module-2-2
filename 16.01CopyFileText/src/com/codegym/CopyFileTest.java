package com.codegym;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class CopyFileTest {
    public static void main(String[] args) {
        File originalFile= new File("D:\\JAVA\\[BC-JAVA-APJ]AdvancedProgrammingWithJava2.0\\16.01CopyFileText\\data.txt");
        File newFile = new File("D:\\JAVA\\[BC-JAVA-APJ]AdvancedProgrammingWithJava2.0\\16.01CopyFileText\\backup.txt");

        System.out.println(originalFile.exists());
        try{
            Files.copy(originalFile.toPath(),newFile.toPath());
        }catch(Exception e){
            System.out.println("Error");
        }

    }
}
