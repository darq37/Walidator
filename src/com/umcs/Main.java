package com.umcs;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Pomiar[][] pomiary = Parser.read("Model.vmg");
        for (int i = 0; i < 150; i++) {
            System.out.println();
            for (int j = 0; j < 150; j++) {
                System.out.print("\t" + pomiary[i][j].getSURFACE_4() + "\t");
            }
        }
    }
}
