package com.umcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Pomiar[][] pomiary = Parser.read("Model.vmg");
        List<String> lines = Files.lines(Paths.get("Active.vmg")).filter(Stringz::isNotNullOrEmpty).collect(Collectors.toList());
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            for (int j = 0; j < split.length; j++) {
                pomiary[i][j].setActive(Integer.parseInt(split[j]) == 1);
            }
        }

        Set<Pomiar> bads = new HashSet<>();
        zwrocZleDlaPierwszej(bads, pomiary);
        zwrocZleDlaDrugiej(bads, pomiary);
        bads.forEach(System.out::println);
        System.out.println("BAD BEFORE CHANGE: " + bads.size());
     /*   for (Pomiar p : bads) {
            p.setBad(true);
        }
        zwrocZleDlaPierwszej(bads, pomiary);
        zwrocZleDlaDrugiej(bads, pomiary);
        System.out.println("BAD AFTER CHANGE: " + bads.size());*/
    }

    private static boolean checkIfOverlap(Float x1, Float x2, Float y1, Float y2) {
        return x1 <= y2 && y1 <= x2; //zwraca True, gdy przedziały się zazębiają
    }

    private static boolean ifFirstLayerOverlaps(Pomiar sprawdzany, Pomiar obok) {
        boolean overlaps = false;
        if (!sprawdzany.getActive() || !obok.getActive()) {
            return true;
        }
        if (checkIfOverlap(sprawdzany.getSURFACE_2(), sprawdzany.getSURFACE_1(), obok.getSURFACE_2(), obok.getSURFACE_1())) { // top
            overlaps = true;
        }
        return overlaps;
    }

    private static boolean ifSecondLayerOverlaps(Pomiar sprawdzany, Pomiar obok) {
        boolean overlaps = false;
        if (!sprawdzany.getActive() || !obok.getActive()) {
            return true;
        }
        if (checkIfOverlap(sprawdzany.getSURFACE_3(), sprawdzany.getSURFACE_2(), obok.getSURFACE_3(), obok.getSURFACE_2())) { // middle
            overlaps = true;
        }
        return overlaps;
    }

    private static void zwrocZleDlaDrugiej(Set<Pomiar> bads, Pomiar[][] pomiary) {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                Pomiar aktualny = pomiary[i][j];
                boolean overlaps = false;
                if (i - 1 >= 0) {
                    Pomiar poLewej = pomiary[i - 1][j];
                    overlaps = ifSecondLayerOverlaps(aktualny, poLewej);
                }
                if (j - 1 >= 0) {
                    Pomiar naGorze = pomiary[i][j - 1];
                    overlaps = ifSecondLayerOverlaps(aktualny, naGorze);
                }
                if (i + 1 < 150) {
                    Pomiar poPrawej = pomiary[i + 1][j];
                    overlaps = ifSecondLayerOverlaps(aktualny, poPrawej);
                }
                if (j + 1 < 150) {
                    Pomiar naDole = pomiary[i][j + 1];
                    overlaps = ifSecondLayerOverlaps(aktualny, naDole);
                }
                if (!overlaps) {
                    bads.add(aktualny); // jezeli 4 komorki wokół zwrocily false => warstwa nie zazębia się z warstwą sąsiedniej komórki
                }
            }
        }

    }

    private static void zwrocZleDlaPierwszej(Set<Pomiar> bads, Pomiar[][] pomiary) {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                Pomiar aktualny = pomiary[i][j];
                boolean overlaps = false;
                if (i - 1 >= 0) {
                    Pomiar poLewej = pomiary[i - 1][j];
                    overlaps = ifFirstLayerOverlaps(aktualny, poLewej);
                }
                if (j - 1 >= 0) {
                    Pomiar naGorze = pomiary[i][j - 1];
                    overlaps = ifFirstLayerOverlaps(aktualny, naGorze);
                }
                if (i + 1 < 150) {
                    Pomiar poPrawej = pomiary[i + 1][j];
                    overlaps = ifFirstLayerOverlaps(aktualny, poPrawej);
                }
                if (j + 1 < 150) {
                    Pomiar naDole = pomiary[i][j + 1];
                    overlaps = ifFirstLayerOverlaps(aktualny, naDole);
                }
                if (!overlaps) {
                    bads.add(aktualny); // jezeli 4 komorki wokół zwrocily false => warstwa nie zazębia się z warstwą sąsiedniej komórki
                }
            }
        }
    }
}
