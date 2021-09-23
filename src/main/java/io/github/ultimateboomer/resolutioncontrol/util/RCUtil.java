package io.github.ultimateboomer.resolutioncontrol.util;

import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class RCUtil {
    private static final String[] UNITS = "KMGTPE".split("");
    private static final NumberFormat FORMAT = NumberFormat.getNumberInstance();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

    /**
     * Format number with metric conversion (K, M, G etc)
     */
    public static String formatMetric(long n) {
        if (n < 1000) {
            return n + " ";
        }

        int log10 = (int) Math.log10(n);
        int decimalPlace = (int) Math.pow(10, 2 - log10 % 3);
        int displayDigits = (int) (n / Math.pow(10, log10 - 2));

        float result = (float) displayDigits / decimalPlace;
        return String.format("%s %s", FORMAT.format(result), UNITS[log10 / 3 - 1]);
    }

    public static String getScreenshotFilename(File directory) {
        String string = DATE_FORMAT.format(new Date());
        int i = 1;

        while (true) {
            File screenshotDir = new File(directory, "screenshots");
            screenshotDir.mkdir();

            String fileName = "fb" + string + (i == 1 ? "" : "_" + i) + ".png";
            File imagePath = new File(screenshotDir, fileName);

            if (!imagePath.exists()) {
                return fileName;
            }

            ++i;
        }
    }
}
