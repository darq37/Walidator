package com.umcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    static Pomiar[][] read(String fileName) throws IOException {
        Float x[] = new Float[150];
        Float y[] = new Float[150];
        Pomiar[][] pomiary1 = new Pomiar[150][150];
        List<String> lines = Files.lines(Paths.get(fileName)).filter(Stringz::isNotNullOrEmpty).collect(Collectors.toList());
        for (int i = 0; i < 150; i++) {
            x[i] = Float.parseFloat(lines.get(i));
        }
        for (int i = 150; i < 300; i++) {
            y[i - 150] = Float.parseFloat(lines.get(i));
        }

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                pomiary1[i][j] = new Pomiar(x[i], y[j], 0f, 0f, 0f, 0f);
            }
        }

        for (int i = 1; i <= 4; i++) { // do 4
            for (int j = 1; j <= 150; j++) { // do 150
                List<Float> odczyty = new ArrayList<>();
                for (int k = 1; k <= 15; k++) {
                    String[] split = lines.get(299 + ((((i - 1) * 150) * 15) + (j - 1) * 15) + k).split(" ");
                    for (int l = 1; l <= 10; l++) {
                        odczyty.add(Float.parseFloat(split[l - 1]));
                    }

                }
                for (int k = 0; k < odczyty.size(); k++) {
                    switch (i) {
                        case 1:
                            pomiary1[j-1][k].setSURFACE_4(odczyty.get(k));
                            break;
                        case 2:
                            pomiary1[j-1][k].setSURFACE_3(odczyty.get(k));
                            break;
                        case 3:
                            pomiary1[j-1][k].setSURFACE_2(odczyty.get(k));
                            break;
                        case 4:
                            pomiary1[j-1][k].setSURFACE_1(odczyty.get(k));
                            break;
                    }
                }
            }
        }
        return pomiary1;
    }
}
