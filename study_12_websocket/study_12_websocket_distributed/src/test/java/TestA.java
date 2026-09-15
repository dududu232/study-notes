import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/01/28/16:57
 * @Description:
 */
public class TestA {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        JSONObject a = new JSONObject();
        a.put("1",1);
        jsonObject.put("a",a);
        jsonObject = JSONObject.parseObject(jsonObject.toJSONString());
        JSONObject a1 = jsonObject.getJSONObject("a");
        a1.put("1",2);
        System.out.println(jsonObject.toJSONString());
    }
}
