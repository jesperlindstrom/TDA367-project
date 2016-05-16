package se.chalmers.get_rect.utilities;

public class StringWrapper {


    public StringWrapper(){
    }


    public String[] wrap(String string){
        StringBuilder sb = new StringBuilder(string);
        int i = 0;
        while (i + 25 < sb.length() && (i = sb.lastIndexOf(" ", i + 25)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return trimString(sb);
    }


    private String[] trimString(StringBuilder sb) {
        String[] trimmedString = sb.toString().split("\n");
        String[] returnMe = new String[trimmedString.length/4 + 1];
        int index = 0;
        int count = 0;


        for (int i = 0; i < returnMe.length; i ++){
            returnMe[i] = "";
        }

        for (String s : trimmedString){
            returnMe[index] += trimmedString[count] + "\n";
            if (count % 4 == 0){
                index++;
            }
            count++;
        }
        return returnMe;
    }

}
