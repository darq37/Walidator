package com.umcs;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        List<Pomiar> pomiary = Parser.read("Model.vmg");
           pomiary.forEach(System.out::println);
     //   System.out.println(pomiary.size());
    }
}
