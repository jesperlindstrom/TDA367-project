package se.chalmers.get_rect.utilities;

import java.util.ArrayList;
import java.util.List;

public class StringWrapper {
    public static String[] wrap(String string){
        StringBuilder sb = new StringBuilder(string);
        int i = 0;
        while (i + 25 < sb.length() && (i = sb.lastIndexOf(" ", i + 25)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return trimString(sb);
    }


    private static String[] trimString(StringBuilder sb) {
        String[] trimmedString = sb.toString().split("\n");
        List<String> tmp = new ArrayList<>();
        String parseString = "";
        int count = 0;
        for (String s : trimmedString){
            parseString += s + "\n";
            count++;
            if (count % 4 == 0){
                tmp.add(parseString);
                parseString = "";
            }
        }
        if (!parseString.equals("")){
            tmp.add(parseString);
        }


        String[] returnMe = new String[tmp.size()];

        for (int i = 0; i < tmp.size(); i++) {
            returnMe[i] = tmp.get(i);
        }

        return returnMe;
    }
}
