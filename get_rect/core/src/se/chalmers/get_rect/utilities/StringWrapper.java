package se.chalmers.get_rect.utilities;


public class StringWrapper {

    public StringWrapper(){}


    public String wrap(String string, int wrapIndex){
        String tmp = "";
        int index = 0;

        while (string.length() - index > wrapIndex){
            tmp = string.substring(index, index + wrapIndex) + "\n";
            index += wrapIndex;
        }
        tmp += string.substring(index);
        return tmp;
    }
}
