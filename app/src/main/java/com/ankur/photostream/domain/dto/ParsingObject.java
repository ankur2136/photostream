package com.ankur.photostream.domain.dto;

import org.json.JSONException;
import org.json.JSONObject;

public interface ParsingObject {

    public <T> T fromJsonObject(JSONObject obj) throws JSONException;
}
