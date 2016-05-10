package se.chalmers.get_rect.utilities;


public class StringWrapper {


    public StringWrapper(){
    }


    public String[] wrap(String string){
        StringBuilder sb = new StringBuilder(string);
        int i = 0;
        int count = 0;
        while (i + 25 < sb.length() && (i = sb.lastIndexOf(" ", i + 25)) != -1) {
            sb.replace(i, i + 1, "\n");
            count++;
        }
        return trimString(sb, count);
    }

    private String[] trimString(StringBuilder sb, int count){
        String[] trimmedString = sb.toString().split("\n");
        String[] tmp = new String[1 + (trimmedString.length/4)];
        int index = 0;
        for (int i = 0; i < tmp.length;i++){
            tmp[i] = "";
        }
        for (int i = 0; i < trimmedString.length;i++){
            tmp[index] += trimmedString[i] + "\n";
            if (i % 4 == 0){
                index++;
            }
        }
        return tmp;
    }
}
