package org.example.bank.JsonBuilderRef;

import com.google.gson.*;
import java.lang.reflect.*;
import java.util.*;

public class BlankJsonGenerator {

    public static JsonElement generateBlankJson(Class<?> clazz) {
        return build(clazz, new HashSet<>());
    }

    private static JsonElement build(Type type, Set<Type> visited) {

        // Prevent cycles
        if (visited.contains(type)) {
            return JsonNull.INSTANCE; // stop recursion
        }

        if (type instanceof Class<?> cls) {

            if (isSimple(cls)) return JsonNull.INSTANCE;

            // Arrays
            if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                JsonArray arr = new JsonArray();
                if (!isSimple(componentType)) {
                    visited.add(componentType);
                    arr.add(buildObject(componentType, visited));
                    visited.remove(componentType);
                }
                return arr;
            }

            // Collections
            if (Collection.class.isAssignableFrom(cls)) {
                return new JsonArray();
            }

            // Maps
            if (Map.class.isAssignableFrom(cls)) {
                return new JsonObject();
            }

            // Custom object
            visited.add(cls);
            JsonObject obj = buildObject(cls, visited);
            visited.remove(cls);
            return obj;
        }

        // ParameterizedType
        if (type instanceof ParameterizedType pType) {
            Type raw = pType.getRawType();
            Type[] args = pType.getActualTypeArguments();

            if (raw instanceof Class<?> rawCls && Collection.class.isAssignableFrom(rawCls)) {
                JsonArray arr = new JsonArray();
                Type inner = args[0];
                if (!isSimple(inner)) {
                    arr.add(build(inner, visited));
                }
                return arr;
            }

            if (raw instanceof Class<?> rawCls2 && Map.class.isAssignableFrom(rawCls2)) {
                return new JsonObject();
            }
        }

        return JsonNull.INSTANCE;
    }

    private static JsonObject buildObject(Class<?> clazz, Set<Type> visited) {
        JsonObject obj = new JsonObject();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Type fieldType = field.getGenericType();
            obj.add(name, build(fieldType, visited));
        }
        return obj;
    }

    private static boolean isSimple(Type type) {
        if (type instanceof Class<?> cls) {
            return cls.isPrimitive()
                    || Number.class.isAssignableFrom(cls)
                    || cls.equals(String.class)
                    || cls.equals(Boolean.class);
        }
        return false;
    }
}
