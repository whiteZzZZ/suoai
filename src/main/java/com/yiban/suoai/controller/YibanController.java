package com.yiban.suoai.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;

import cn.yiban.util.HTTPSimple;
import com.alibaba.fastjson.JSON;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.pojo.School;
import com.yiban.suoai.service.ImageService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.SchoolService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.util.*;
import com.yiban.suoai.weixin.UnionidAndOpenId;
import com.yiban.suoai.yiban.AppContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class YibanController {

    private static Logger logger = Logger.getLogger(YibanController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    SchoolService schoolService;

    private final String YIBAN_OPEN_URL		= "https://openapi.yiban.cn/";

    private final String YIBAN_USER_ME_INFO	= "user/me";
    private final String YIBAN_USER_OTHER	= "user/other";
    private final String YIBAN_USER_REALME	= "user/real_me";






    @ApiOperation(value = "微信用户登录", notes = "用户登录")
    @RequestMapping(value = "forelogin", method = RequestMethod.GET)
    @ResponseBody
    public Map login(
            @RequestParam("JSCODE") @ApiParam(value = "JSCODE") String JSCODE,
            @RequestParam("encryptedData") @ApiParam(value = "微信加密内容") String encryptedData,
            @RequestParam("iv") @ApiParam(value = "微信解密信息") String iv, HttpServletResponse resp) throws Exception {

        UnionidAndOpenId unionidAndOpenId = XzAppUtil.getUnionId(JSCODE, encryptedData, iv);
//        String unionId = unionidAndOpenId.getUnionID();
        String openId = unionidAndOpenId.getOpenID();
        com.yiban.suoai.pojo.User user = userService.getByOperId(openId);
        // User user = userServices.get(openId);//检查openId是否存在数据库中，如果时该用户第一次登陆，则存入该用户

        if (null == user) {

//            user = UserFactory.getUser("", openId, "https://zhouzhi-gz.oss-cn-shenzhen.aliyuncs.com/xinzhi/Data/headImg/default.jpg");
//            userServices.add(user);
            user = new com.yiban.suoai.pojo.User();
            user.setOpenid(openId);
            user.setBgImg("https://zhouzhi-gz.oss-cn-shenzhen.aliyuncs.com/xinzhi/Data/ads/me2-background.png");
            userService.add(user);
            String token = DigestUtils.md5Hex(openId + AppUtil.getDate());// 根据openid和当前时间生成随机token
            redisService.addTokenToRedis(user.getId(),token);
            //redisServices.addTokenToRedis(user.getUserId(), token);// 将该随机token存入Redis中，用作登录态的维持
            Map map = MapHelper.success();
            map.put("token", token);
            map.put("userId", user.getId());
            map.put("isNew", 1);
            return map;
        } else {
            String token = DigestUtils.md5Hex(openId + AppUtil.getTime());
            redisService.addTokenToRedis(user.getId(),token);
            //  redisServices.addTokenToRedis(user.getUserId(), token);// 将该随机token存入Redis中
            Map map = MapHelper.success();
            map.put("token", token);
            map.put("userId", user.getId());
            map.put("isNew", 0);
            return map;
        }

    }

    @ApiOperation(value = "用户初始化 上传头像和name", notes = "头像图片上传")
    @RequestMapping(value ="initialize" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> initialize(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                          @RequestParam(value = "name")  @ApiParam(value = "用户name  ") String name,
                                          @RequestParam(value = "sex")  @ApiParam(value = "性别 true 男 false 女  ") boolean sex,
                                          @RequestParam(value = "image")  @ApiParam(value = "图片") String image,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        //System.out.println("进入get方法！");
        //获取从前台传过来得图片
        int userId=redisService.getUserId(token);


        com.yiban.suoai.pojo.User user = userService.get(userId);
        user.setHeadImg(image);
        user.setName(name);
        user.setSex(sex);
        userService.update(user);

        Map map = MapHelper.success();
        return map;

    }










    @RequestMapping(value = "/init")
    @ApiOperation(value = "易班登录入口",notes = "易班登录入口")
    @ResponseBody
    public void init(HttpServletRequest req, HttpServletResponse res) throws IOException {

        logger.error("进入init方法");
        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String url = authorize.forwardurl(AppContext.BACK_URL, "QUERY", Authorize.DISPLAY_TAG_T.WEB);
        System.out.println("url="+url);
        logger.error("url="+url);
        res.sendRedirect(url);
    }

    /**
     * 易班回调
     * @param req
     * @param
     * @return
     */
    @RequestMapping("/back")
    @ApiOperation(value = "易班回调地址",notes = "易班回调地址")
    public  void back(HttpServletRequest req, HttpServletResponse res,RedirectAttributes attr, ModelAndView model) throws IOException {
        logger.error("调用成功2");
        //return  MapHelper.success();
        Map<String,Object>  map=null;

//        System.out.println(AppContext.KEY_CODE);
        String code = req.getParameter(AppContext.KEY_CODE);
//        System.out.println(code);
        if (code == null || code.equals("")) {
            //resp.sendRedirect("/yiban_demo/index.html");
            System.out.println("登录失败");
            //return null;
        }
        //System.out.println(code);

        //Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
       // String text = authorize.querytoken(code, AppContext.BACK_URL);

        //自己实现


        String text = doPost(code);

       // System.out.println(text);
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(text);
        AppContext.ACCESS_TOKEN = json.getString(AppContext.KEY_TOKEN);
        User yibanUser = new User(AppContext.ACCESS_TOKEN);
        JSONObject userInfo = JSONObject.fromObject(me(AppContext.ACCESS_TOKEN)).getJSONObject("info");


        int yibanId =userInfo.getInt("yb_userid");//获取用户id
        com.yiban.suoai.pojo.User user = userService.getByYibanId(yibanId);



        if(user == null){
            String name = userInfo.getString("yb_usernick");//获取用户昵称
            String sex = userInfo.getString("yb_sex");//获取用户性别
            String school = userInfo.getString("yb_schoolname");
            String trueName = userInfo.getString("yb_username");//获取用户名
            String headImg = userInfo.getString("yb_userhead");//获取用户易班头像

            com.yiban.suoai.pojo.User user1 = new com.yiban.suoai.pojo.User();
            user1.setYibanid(yibanId);
            user1.setName(name);
            user1.setTurename(trueName);
            user1.setHeadImg(headImg);
            user1.setBgImg("https://zhouzhi-gz.oss-cn-shenzhen.aliyuncs.com/xinzhi/Data/ads/me2-background.png");
            if("M".equals(sex)) {
                user1.setSex(true);
            }else {
                user1.setSex(false);
            }
            List<School> schools = schoolService.selectByName(school);
            if(schools!=null) {
                for (School o : schools) {
                    if (o.getName().equals(school)) {
                        user1.setSchoolId(o.getId());
                        break;
                    }
                }
            }else {
                School school1 = new School();
                school1.setName(school);
                schoolService.add(school1);
            }
            userService.add(user1);
            redisService.addTokenToRedis(user1.getId(),AppContext.ACCESS_TOKEN);

            map=MapHelper.success();
            map.put("userId",user1.getId());
            map.put("token",AppContext.ACCESS_TOKEN);
         /*  // resp.sendRedirect("/index?userId="+yibanId+"&token="+AppContext.ACCESS_TOKEN);
            attr.addAttribute("userId",yibanId);*/
           /* model.addObject("userId", yibanId);
            model.addObject("token", AppContext.ACCESS_TOKEN);
            model.setViewName("redirect:/index.html");
            return model;*/
            res.sendRedirect("http://47.107.74.195:8080/h5/index.html#/pages/tabbar/home/home?userId="+yibanId+"&token="+AppContext.ACCESS_TOKEN);

        }else {
            com.yiban.suoai.pojo.User user2=userService.getByYibanId(yibanId);
            redisService.addTokenToRedis(user2.getId(),AppContext.ACCESS_TOKEN);
            map=MapHelper.success();
            map.put("userId",user2.getId());
            map.put("token",AppContext.ACCESS_TOKEN);
           /* model.addObject("userId", yibanId);
            model.addObject("token", AppContext.ACCESS_TOKEN);
            model.setViewName("redirect:/index.html");
            return model;*/
            res.sendRedirect("http://47.107.74.195:8080/h5/index.html#/pages/tabbar/home/home?userId="+yibanId+"&token="+AppContext.ACCESS_TOKEN);
        }

//        byte file = (byte) userInfo.get("yb_userhead");//获取头像
//        String uuid= UUIDUtil.getUUID();//使用uuid作为图片的名称
//        String path= FileHelper.FileSave(file,uuid,FileHelper.headImg);//头像路径



       /* User user = new User(AppContext.ACCESS_TOKEN);
        HttpSession session = req.getSession();
        session.setAttribute(AppContext.KEY_USER, user);*/

      //  resp.sendRedirect("http://127.0.0.1:8080/yiban_demo/index.html");

    }

    @RequestMapping("testBaidu")
    public void testBaidu(HttpServletResponse resp){
        try {
            System.out.println("123456798");
            resp.sendRedirect("https://www.baidu.com/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String doPost(String code){
        String result = "";
        List <NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("client_id", AppContext.APP_ID));
        param.add(new BasicNameValuePair("client_secret", AppContext.APP_SEC));
        param.add(new BasicNameValuePair("code", code));
        param.add(new BasicNameValuePair("redirect_uri", AppContext.BACK_URL));

        String url = "https://openapi.yiban.cn/oauth/access_token";
        String responseContext = "";
        int found = url.indexOf('?');
        if (found > 0)
        {
            url = url.substring(0, found);
        }
        try
        {
            CloseableHttpClient httpclient = getClientInstance(url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(param));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if( status > 300 && status < 310)
            {
                Header[] h = response.getHeaders("Location");
                if(h.length > 0)
                {
                    httpclient.close();
                    return HTTPSimple.POST(h[0].toString().substring(10), param);
                }
            }
            HttpEntity entity = response.getEntity();
            responseContext = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            httpclient.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        result = responseContext;
        System.out.println("result="+result);
        return result;
    }

    static CloseableHttpClient getClientInstance(String url) throws Exception
    {
        CloseableHttpClient httpclient = null;
        if (isSecurity(url))
        {
            KeyStore myTrustKeyStore = KeyStore.getInstance(
                    KeyStore.getDefaultType()
            );
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(myTrustKeyStore, new TrustStrategy() {
                @Override
                public boolean isTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[] { "TLSv1" },
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier()
            );
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }
        else
        {
            httpclient = HttpClients.createDefault();
        }
        return httpclient;
    }

    private static boolean isSecurity(String url) throws Exception
    {
        URL u = new URL(url);
        return u.getProtocol().contentEquals("https");
    }

    public static String GET(String url)
    {
        String responseContext = "";
        try
        {
            CloseableHttpClient httpclient = getClientInstance(url);
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if( status > 300 && status < 310)
            {
                Header[] h = response.getHeaders("Location");
                if(h.length > 0)
                {
                    httpclient.close();
                    return GET(h[0].toString().substring(10));
                }
            }
            HttpEntity entity = response.getEntity();
            responseContext = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            httpclient.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return responseContext;
    }

    private String me(String token){
        String query = YIBAN_OPEN_URL;
        query += YIBAN_USER_ME_INFO;
        query += "?access_token=";
        query += token;
        return GET(query);
    }
}