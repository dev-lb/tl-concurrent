package dev.proj.springboot.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {

    public static void main(String[] args) {
        parseJSONObject();
    }

    /**
     * 解析字节数组 -> JSONObject
     */
    public static void parseJSONObject() {
        JSONObject jsonObject = new JSONObject();
        byte[] bytes = jsonObject.toJSONString().getBytes();
        Object parseValue = JSON.parse(bytes);
        System.out.println(parseValue instanceof JSONObject);
    }

    /**
     * 解析字节数组 -> JSONArray
     */
    public static void parseJSONArray() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonArray.add(jsonObject);
        byte[] bytes = jsonArray.toJSONString().getBytes();
        Object parseValue = JSON.parse(bytes);
        System.out.println(parseValue instanceof JSONArray);
    }
}
