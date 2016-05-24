package se.chalmers.get_rect.io.strategies.json;

import com.google.gson.Gson;
import se.chalmers.get_rect.io.strategies.IOStrategy;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class JsonIOStrategy<T> implements IOStrategy<T> {
    private Class<T> className;
    private static final Charset charset = Charset.forName("UTF-8");

    public JsonIOStrategy(Class<T> className) {
        this.className = className;
    }

    @Override
    public List<T> read(String file) throws FileNotFoundException {
        InputStream stream = new FileInputStream(file);
        Reader reader = new InputStreamReader(stream, charset);
        Gson gson = new Gson();

        return gson.fromJson(reader, new JsonListWrapper<>(className));
    }

    @Override
    public void write(String file, List data) throws IOException {
        Gson gson = new Gson();
        String s = gson.toJson(data);

        File myFile = new File(file);

        boolean tmp = myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut, charset);
        myOutWriter.append(s);
        myOutWriter.close();
        fOut.close();
    }
}
