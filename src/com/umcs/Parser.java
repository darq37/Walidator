package com.umcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    public static final String ACTIVE_VMG = "akt.vmg";
    public static final String MODEL_VMG = "Model.vmg";

    static Measurement[][] markActiveElements(Measurement[][] measurements) throws IOException {
        List<String> activeElementsLinens = Files.lines(Paths.get(ACTIVE_VMG)).filter(StringUtils::isNotNullOrEmpty).collect(Collectors.toList());
        for (int i = 0; i < activeElementsLinens.size(); i++) {
            String[] splitLine = activeElementsLinens.get(i).split(" ");
            for (int j = 0; j < splitLine.length; j++) {
                measurements[i][j].setActive(Integer.parseInt(splitLine[j]) == 1);
            }
        }
        return measurements;
    }

    static Measurement[][] parseModel() throws IOException {
        Float x[] = new Float[150];
        Float y[] = new Float[150];
        Measurement[][] measurements = new Measurement[150][150];
        List<String> lines = Files.lines(Paths.get(MODEL_VMG)).filter(StringUtils::isNotNullOrEmpty).collect(Collectors.toList());
        for (int i = 0; i < 150; i++) {
            x[i] = Float.parseFloat(lines.get(i));
        }
        for (int i = 150; i < 300; i++) {
            y[i - 150] = Float.parseFloat(lines.get(i));
        }

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                measurements[149 - j][i] = new Measurement(x[i], y[j], 0f, 0f, 0f, 0f, false);
            }
        }

        for (int i = 1; i <= 4; i++) { // do 4
            for (int j = 1; j <= 150; j++) { // do 150
                List<Float> values = new ArrayList<>();
                for (int k = 1; k <= 15; k++) {
                    String[] split = lines.get(getIndex(i, j, k)).split(" ");
                    for (int l = 1; l <= 10; l++) {
                        values.add(Float.parseFloat(split[l - 1]));
                    }
                }
                for (int k = 0; k < values.size(); k++) {
                    switch (i) {
                        case 1:
                            measurements[149 - k][j - 1].setFourthSurface(values.get(k));
                            break;
                        case 2:
                            measurements[149 - k][j - 1].setThirdSurface(values.get(k));
                            break;
                        case 3:
                            measurements[149 - k][j - 1].setSecondSurface(values.get(k));
                            break;
                        case 4:
                            measurements[149 - k][j - 1].setFirstSurface(values.get(k));
                            break;
                    }
                }
            }
        }
        return markActiveElements(measurements);
    }

    private static int getIndex(int i, int j, int k) {
        return 299 + ((((i - 1) * 150) * 15) + (j - 1) * 15) + k;
    }
}
