package com.itaycohen.utils;

import com.google.gson.*;
import com.itaycohen.dm.IBook;

import java.lang.reflect.Type;



public class GsonIBookTypeAdapter implements JsonSerializer<IBook>, JsonDeserializer<IBook> {

    private final static String CLASSNAME = "CLASSNAME";
    private final static String DATA = "DATA";

    @Override
    public IBook deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String className = jsonObject.get(CLASSNAME).toString();
        Class<?> clazz = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), clazz);
    }

    @Override
    public JsonElement serialize(IBook iBook, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, iBook.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(iBook));
        return jsonObject;
    }

    private Class<?> getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
