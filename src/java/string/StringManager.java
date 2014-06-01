/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

/**
 *
 * @author user
 */
public class StringManager {

    public static String firstAlphabetUpper(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        result += str.charAt(0);
        result = result.toUpperCase();

        if (str.length() != 1) {
            result += str.substring(1, str.length());
        }
        return result;
    }

    public static String firstAlphabetLower(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        result += str.charAt(0);
        result = result.toLowerCase();
        if (str.length() != 1) {
            result += str.substring(1, str.length());
        }
        return result;
    }

    public static String wordsFirstAlphabetUpper(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        boolean isSpace = false;
        char c = str.charAt(0);
        result += (c + "").toUpperCase();
        for (int i = 1; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == ' ') {
                isSpace = true;
                result += c;
            } else if (isSpace) {
                result += (c + "").toUpperCase();
                isSpace = false;
            } else {
                result += c;
            }
        }
        return result;
    }

    public static String wordsFirstAlphabetLower(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        boolean isSpace = false;
        char c = str.charAt(0);
        result += (c + "").toLowerCase();
        for (int i = 1; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == ' ') {
                isSpace = true;
                result += c;
            } else if (isSpace) {
                result += (c + "").toLowerCase();
                isSpace = false;
            } else {
                result += c;
            }
        }
        return result;
    }

    public static String wordsFirstAlphabetUpperWithoutSpace(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        boolean isSpace = false;
        char c = str.charAt(0);
        result += (c + "").toUpperCase();
        for (int i = 1; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == ' ') {
                isSpace = true;
                continue;
            }
            if (isSpace) {
                result += (c + "").toUpperCase();
                isSpace = false;
            } else {
                result += c;
            }
        }
        return result;
    }

    public static String wordsFirstAlphabetLowerWithoutSpace(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        boolean isSpace = false;
        char c = str.charAt(0);
        result += (c + "").toLowerCase();
        for (int i = 1; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == ' ') {
                isSpace = true;
                continue;
            }
            if (isSpace) {
                result += (c + "").toLowerCase();
                isSpace = false;
            } else {
                result += c;
            }
        }
        return result;
    }

    public static String concatWithoutSpace(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String result = "";
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            result += c;
        }
        return result;
    }

    public static void main(String[] ars) {
        System.out.println(StringManager.firstAlphabetUpper("sss"));
        System.out.println(StringManager.concatWithoutSpace("sad asd   asd"));
        System.out.println(StringManager.wordsFirstAlphabetUpper("sad sd  sad"));
        System.out.println(StringManager.wordsFirstAlphabetUpperWithoutSpace("sad sd  sad"));
        System.out.println(StringManager.wordsFirstAlphabetLower("SASD d"));
        System.out.println(StringManager.wordsFirstAlphabetLowerWithoutSpace("SASD ASsd Sad"));
    }
}
