package org.example.CommonValues;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ClassTypeAdapter extends TypeAdapter<Class<?>> {

    @Override
    public void write(JsonWriter out, Class<?> value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getName()); // Serialize Class<?> as its fully qualified name
        }
    }

    @Override
    public Class<?> read(JsonReader in) throws IOException {
        String className = in.nextString();
        try {
            return Class.forName(className); // Deserialize fully qualified name back to Class<?>
        } catch (ClassNotFoundException e) {
            throw new IOException("Class not found: " + className, e);
        }
    }
}