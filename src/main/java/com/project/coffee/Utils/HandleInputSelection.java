package com.project.coffee.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandleInputSelection {
    public static void printPrompt(String prompt) {
        System.out.print(prompt + " ");
        System.out.flush();
    }

    public static void inputFlush() {
        int dummy;
        int bAvail;

        try {
            while ((System.in.available()) != 0)
                dummy = System.in.read();
        } catch (java.io.IOException e) {
            System.out.println("Input error");
        }
    }

    public static String inString(String prompt) {
        inputFlush();
        printPrompt(prompt);
        return inString();
    }

    public static String inString() {
        int aChar;
        String s = "";
        boolean finished = false;

        while (!finished) {
            try {
                aChar = System.in.read();
                if (aChar < 0 || (char) aChar == '\n')
                    finished = true;
                else if ((char) aChar != '\r')
                    s = s + (char) aChar; // Enter into string
            } catch (java.io.IOException e) {
                System.out.println("Input error");
                finished = true;
            }
        }
        return s;
    }

    public static int inInt(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);
            try {
                return Integer.valueOf(inString().trim()).intValue();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer");
            }
        }
    }

    public static String inDate(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);

            String strDate = inString();
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            dateTimeFormat.setLenient(false);
            try {
                Date javaDate = dateTimeFormat.parse(strDate);
                return dateTimeFormat.format(javaDate);
            } catch (ParseException e) {
                System.out.println(strDate + " is invalid Date format " + Constants.DATE_TIME_FORMAT);
            }
        }
    }

    public static String inGender(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);

            String genderString = inString();
            if (genderString.equals("Nam") || genderString.equals("nu")) {
                return genderString;
            }
            System.out.println(genderString + " is invalid gender, only (Nam/Nu)");
        }
    }

    public static String inPrice(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);
            String priceString = inString();
            try {
                String[] priceRange = priceString.split("-");
                Integer.valueOf(priceRange[0]).intValue();
                Integer.valueOf(priceRange[1]).intValue();
                return priceString;
            } catch (Exception e) {
                System.out.println(" Invalid price range format");
            }

        }
    }
}
