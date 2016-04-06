package se.chalmers.get_rect.io.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonListWrapper<T> implements ParameterizedType
{
    private Class<?> data;

    public JsonListWrapper(Class<T> data) {
        this.data = data;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { data };
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
