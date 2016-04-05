package se.chalmers.get_rect.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import se.chalmers.get_rect.io.IOStrategy;

import java.io.*;
import java.util.List;

public class JsonIOStrategy<T> implements IOStrategy<T> {
    @Override
    public List<T> read(String file) throws FileNotFoundException {
        InputStream stream = new FileInputStream(file);
        Reader reader = new InputStreamReader(stream);
        Gson gson = new Gson();

        return gson.fromJson(reader, new TypeToken<List<T>>(){}.getType());
    }

    @Override
    public void write(String file, List data) {

    }
}
