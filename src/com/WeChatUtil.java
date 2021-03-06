package com;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class WeChatUtil {
       
        public String agent_id = "";  //企业初始值
        public String code = "";    //企业code初始值
        public String state = "";           //企业初始值,固定，如果需要修改，必须在网页跳转那边跳转

        public String error = "";//后台代码错误
        //步骤获取人物信息

       
        //生成签名所需的jsapi_ticket，最长为512字节
        public String jsapi_ticket = "";
        //当前网页的URL，不包含#及其后面部分
        public String url = "";
        public String appId = "ww58dfe8dfd432a0bf";
        //生成签名的时间戳
        public String timestamp = "";
        //生成签名的随机串
        public String nonceStr = "";
       public String signature = "";
    
        public  String getSha1(String str){
            if (null == str || 0 == str.length()){
                return null;
            }
            char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                    'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
                mdTemp.update(str.getBytes("UTF-8"));
                 
                byte[] md = mdTemp.digest();
                int j = md.length;
                char[] buf = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    buf[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(buf);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return new String("");
        }

        public String doStep2(String jsapi_ticket,String url) 
        {            
        	nonceStr = "qwertyuiop";
            long startTime = System.currentTimeMillis();   
             timestamp = startTime+"";
             String time_stamp = timestamp.substring(0,9);
   
            signature = getSha1("jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + time_stamp + "&url=" + url);
            return "{\"timestamp\":\""+time_stamp+"\",\"nonceStr\":\""+nonceStr+"\",\"signature\":\""+signature+"\",\"corpid\":\""+appId+"\"}";
        }
        public  String getRandomString(int length) { 
            String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
            Random random = new Random();   
            StringBuffer sb = new StringBuffer();   
            for (int i = 0; i < length; i++) {   
                int number = random.nextInt(base.length());   
                sb.append(base.charAt(number));   
            }   
            return sb.toString();   
         }   

}
