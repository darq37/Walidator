package com.umcs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static final Float OFFSET = 2.5f;
    public static final String OUTPUT_FILE_LAYER_1 = "s1.txt";
    public static final String OUTPUT_FILE_LAYER_2 = "s2.txt";
    public static final String OUTPUT_FILE_LAYER_3 = "s3.txt";
    public static final String OUTPUT_FILE_LAYER_4 = "s4.txt";
    public static final String MODEL = "Model.txt";

    public static void main(String[] args) throws IOException {
        Measurement[][] measurements = Parser.parseModel();
        Set<Measurement> disconnectedElements = getDisconnectedElements(measurements);

        System.out.println("\nDisconnected elements #: " + disconnectedElements.size());

        for (Measurement bad : disconnectedElements) {
            bad.setThirdSurface(bad.getThirdSurface() - OFFSET);
            bad.setSecondSurface(bad.getSecondSurface() - OFFSET);
        }

        Set<Measurement> disconnectedElementsAfterFix = getDisconnectedElements(measurements);

        System.out.println("\nDisconnected elements after fix #: " + disconnectedElementsAfterFix.size());

        System.out.println("Saving output to files...");
        PrintWriter pw1 = new PrintWriter(MODEL);
        try {
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    Measurement m = measurements[i][j];
                    pw1.println(m.getX() + "," + m.getY() + "," + m.getFirstSurface()+ "," + m.getSecondSurface()+ "," + m.getThirdSurface()+ "," + m.getFourthSurface()+ "," + m.getDisconnected());
                }
            }
        } finally {
            pw1.close();
        }

        pw1 = new PrintWriter(OUTPUT_FILE_LAYER_3);
        try {
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    Measurement m = measurements[i][j];
                    pw1.println(m.getX() + "," + m.getY() + "," + m.getThirdSurface());
                }
            }
        } finally {
            pw1.close();
        }
        pw1 = new PrintWriter(OUTPUT_FILE_LAYER_2);
        try {
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    Measurement m = measurements[i][j];
                    pw1.println(m.getX() + "," + m.getY() + "," + m.getSecondSurface());
                }
            }
        } finally {
            pw1.close();
        }

        System.out.println("Done");
    }

    private static boolean surfacesOverlap(Float x1, Float x2, Float y1, Float y2) {
        return x1 <= y2 && y1 <= x2;
    }

    private static boolean activeLayerOverlaps(Measurement current, Measurement other) {
        boolean overlaps = false;
        if (!current.isActive() || !other.isActive()) {
            return true;
        }
        if (surfacesOverlap(current.getSecondSurface(), current.getFirstSurface(),
                other.getSecondSurface(), other.getFirstSurface())) {
            overlaps = true;
        }
        return overlaps;
    }


    private static Set<Measurement> getDisconnectedElements(Measurement[][] measurements) {
        Set<Measurement> disconnected = new HashSet<>();

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                Measurement current = measurements[i][j];
                boolean isLeftDisconnected = false;
                boolean isTopDisconnected = false;
                boolean isRightDisconnected = false;
                boolean isBottomDisconnected = false;
                if (i - 1 >= 0) {
                    Measurement leftElement = measurements[i - 1][j];
                    isLeftDisconnected = activeLayerOverlaps(current, leftElement);
                }
                if (j - 1 >= 0) {
                    Measurement topElement = measurements[i][j - 1];
                    isTopDisconnected = activeLayerOverlaps(current, topElement);
                }
                if (i + 1 < 150) {
                    Measurement rightElement = measurements[i + 1][j];
                    isRightDisconnected = activeLayerOverlaps(current, rightElement);
                }
                if (j + 1 < 150) {
                    Measurement bottomElement = measurements[i][j + 1];
                    isBottomDisconnected = activeLayerOverlaps(current, bottomElement);
                }
                if (!isLeftDisconnected && !isTopDisconnected && !isRightDisconnected && !isBottomDisconnected) {
                    current.setDisconnected(true);
                    disconnected.add(current);
                }
            }
        }
        return disconnected;
    }
}
