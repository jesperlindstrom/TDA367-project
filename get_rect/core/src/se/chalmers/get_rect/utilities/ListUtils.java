package se.chalmers.get_rect.utilities;

import java.util.List;
import java.util.Random;

public class ListUtils {
    public static <T> T randomItem(List<T> list) {
        Random r = new Random();
        int random = r.nextInt(list.size());
        return list.get(random);
    }
}
