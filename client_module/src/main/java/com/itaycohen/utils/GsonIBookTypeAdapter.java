package com.itaycohen.utils;

import com.google.gson.*;
import com.itaycohen.data_layer.dm.Book;
import com.itaycohen.data_layer.dm.BookWithSearch;
import com.itaycohen.data_layer.dm.IBook;

import java.lang.reflect.Type;
import java.util.Objects;


public class GsonIBookTypeAdapter implements JsonSerializer<IBook>, JsonDeserializer<IBook> {

    private final static String TYPE = "type";

    @Override
    public IBook deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String objType = jsonObject.get(TYPE).getAsString();
        Class<?> clazz = getClassForType(objType);
        return jsonDeserializationContext.deserialize(jsonObject, clazz);
    }

    @Override
    public JsonElement serialize(IBook iBook, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = jsonSerializationContext.serialize(iBook).getAsJsonObject();
        jsonObject.addProperty(TYPE, iBook.getClass().getSimpleName());
        return jsonObject;
    }

    private Class<?> getClassForType(String type) {
        Objects.requireNonNull(type);
        if (type.equals(Book.class.getSimpleName()))
            return Book.class;
        else if (type.equals(BookWithSearch.class.getSimpleName()))
            return BookWithSearch.class;
        else
            throw new JsonParseException(String.format("Unknown type for %s", IBook.class.getSimpleName()));
    }
}
