package tsn_java_preferences;

import java.util.prefs.Preferences;

public class TSN_JAVA_Preferences {

    public static void main(String[] args) {
        Preferences node = Preferences.userRoot().node("tsn_sp_lab3");

        System.out.println("Task: Yi = (Sum(Ki)*i)/Kii, ii=i-1");

        final int r = 10;
        final int c = 2;
        float m[][] = new float[r][c];
        float sum = 0, Ki, Kii, Yi;
        System.out.println("Matrix:");
        for (int i = 0; i < r; i++) {
            Ki = (int) Math.round(Math.random() * 9);
            m[i][0] = Ki;
            m[i][1] = 0;
            System.out.println(String.format("%.0f", Ki) + " 0");
        }

        for (int i = 0; i < r; i++) {
            Ki = m[i][0];
            try {
                sum = sum + Ki;
                Kii = m[i - 1][0];
                Yi = (sum * (i + 1)) / Kii;
                if ((Double.isNaN(Yi) == true) || Double.isInfinite(Yi) == true) {
                    throw new Exception();
                }
                m[i][1] = (float) Yi;
            } catch (Exception e) {
                m[i][1] = 0f;
            }
        }

        for (int i = 0; i < r; i++) {
            node.putFloat("K" + i, m[i][0]);
            node.putFloat("Y" + i, m[i][1]);
        }

        System.out.println("New matrix:");
        for (int i = 0; i < r; i++) {
            Ki = node.getFloat("K" + i, 0);
            Yi = node.getFloat("Y" + i, 0);
            System.out.println(String.format("%.0f", Ki) + " " + String.format("%.2f", Yi));
        }
    }
}
