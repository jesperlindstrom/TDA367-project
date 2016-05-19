package se.chalmers.get_rect.io.strategies.json;

import com.google.gson.Gson;
import se.chalmers.get_rect.io.strategies.IOStrategy;

import java.io.*;
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
        Gson gson = new Gson();
        String s = gson.toJson(data);

        File myFile = new File("savedData/playerSavedData.json");
        try {
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append(s);
            myOutWriter.close();
            fOut.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
