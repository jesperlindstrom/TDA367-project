package se.chalmers.get_rect.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import se.chalmers.get_rect.io.IOStrategy;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonIOStrategy<T> implements IOStrategy<T> {
    private Class<T> className;

    public JsonIOStrategy(Class<T> className) {
        this.className = className;
    }

    @Override
    public List<T> read(String file) throws FileNotFoundException {
        InputStream stream = new FileInputStream(file);
        Reader reader = new InputStreamReader(stream);
        Gson gson = new Gson();

        return gson.fromJson(reader, new JsonListWrapper<T>(className));
    }

    @Override
    public void write(String file, List data) {

    }
}
