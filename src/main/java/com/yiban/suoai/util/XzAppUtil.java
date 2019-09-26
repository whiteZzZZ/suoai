package com.yiban.suoai.util;



import com.yiban.suoai.exception.XZException;
import com.yiban.suoai.weixin.UnionidAndOpenId;
import net.sf.json.JSONObject;


public class XzAppUtil {

    private static final String appid = "wxa89746c53ec389f1";

    private static final String secret = "69d38a05923fa00f02d214f5ce1682cd";

    private static final String grant_type = "authorization_code";


    public static String requestUrl(String JSCODE) {

        return "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code="
                + JSCODE + "&grant_type=" + grant_type;
    }

    /**
     * @return java.lang.String
     * @Description 通过openId获取用户的UnionId
     * @parameters [openId, accessToken]
     */
    public static String getOpenUnionId(String openId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", accessToken);
        url = url.replace("OPENID", openId);

        JSONObject jsonObject = JSONObject
                .fromObject(HttpUtil
                        .get(url));

        System.out.println(jsonObject);
        return (String) jsonObject.get("unionid");
    }

    public static UnionidAndOpenId getUnionId(String JSCODE, String encryptedData, String iv) throws Exception {


        String requestUrl = requestUrl(JSCODE);

        JSONObject jsonObject = JSONObject
                .fromObject(HttpUtil
                        .get(requestUrl));//此处用于获取微信接口的返回数据包含openID和session_key

        String openId = String.valueOf(jsonObject.get("openid"));//解析json数据，获取openid
        if (openId.equals("null") || openId.equals("")) {// 如果openID为null，则可得出JSCODE已经过期，或者出错
            throw new XZException("005", "requestUrl:" + requestUrl + "获取openID出错 :: " + jsonObject.toString());
        }

        String session_key = String.valueOf(jsonObject.get("session_key"));
        //解密算法，根据encryptedData、session_key和偏移量
        String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
        return new UnionidAndOpenId(getOpenUnionId(openId, getAccessToken()), openId);//解析json数据，获取openid
    }

    public static String getAccessToken() {

        String requestUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+
                "&secret="+secret;

        JSONObject jsonObject=JSONObject
                .fromObject(HttpUtil
                        .get(requestUrl));//此处用于获取微信接口的返回数据包含openID和session_key
        System.out.println(jsonObject);
        return (String) jsonObject.get("access_token");//解析json数据，获取openid
    }
}