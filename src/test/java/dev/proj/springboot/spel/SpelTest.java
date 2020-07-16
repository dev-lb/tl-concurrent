package dev.proj.springboot.spel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SpelTest {

    public static void main(String[] args) {
        parserAnd();
    }

    /**
     * 解析and条件
     */
    public static void parserAnd(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("alarmKpiName", "v1");
        jsonObject.put("alarmKpiValue", 1);

        String expression = "#{#map[alarmKpiName] eq 'v1' && #map[alarmKpiValue] == 1}";
        Object value = ExpressionBuilder.getDefault().calculate(jsonObject, expression);
        System.out.println(value);
    }

    /**
     * 解析map中的list参数
     */
    public static void parserList(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("p1", "v1");

        JSONArray jsonArray = new JSONArray();
        JSONObject jao = new JSONObject();
        jao.put("op1", "ov1");
        jsonArray.add(jao);
        jsonObject.put("plist", jsonArray);

        String expression = "#{#map[plist][0][op1]}";
        Object value = ExpressionBuilder.getDefault().calculate(jsonObject, expression);
        System.out.println(value);
    }

}
