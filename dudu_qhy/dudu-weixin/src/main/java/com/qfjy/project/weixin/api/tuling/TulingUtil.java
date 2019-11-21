package com.qfjy.project.weixin.api.tuling;


import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Classname TulingUtil
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/21 9:49
 * @Created by Administrator
 */
@Service
public class TulingUtil {
    private static final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";


    /** ######### TODO 发送消息和响应消息
     * @InputParam msg 用户发送的文本
     * @OutputParam result 响应的文本
     */
    public String sendMessage(String msg){
        //生成入参的JSON对象
        JSONObject jsonObject= sendJSONObject(msg,"9a073d07a9304de08fd3169876810278");
        //2步  对指定的API 地址 发送POST请求 （传入入参JSON对象）
        JSONObject object= WeixinUtil.httpRequest(TULING_API_URL,"POST",jsonObject.toString());

       // System.out.println("响应的消息："+object.toString());
        JSONArray  array= (JSONArray) object.get("results");

        JSONObject object1= (JSONObject) array.get(0);
        JSONObject object2= (JSONObject) object1.get("values");
        String result= (String) object2.get("text");
        return result;
    }

    /** ###########TODO 生成入参JSON对象
     * 按规则生成入参的JSON对象
     * @param msg 用户发送的文本
     * @param apiKey 机器人的apiKey
     * @return
     */
    public JSONObject  sendJSONObject(String msg,String apiKey){
        //JSON格式数据（入参）
        InputText inputText=new InputText();
        inputText.setText(msg);
        Perception perception=new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey(apiKey);
        userInfo.setUserId("java1903");

        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject=JSONObject.fromObject(tulingBean);
        return jsonObject;
    }






    public static void main(String[] args) {
        String api="http://openapi.tuling123.com/openapi/api/v2";

        String msg="今天天气怎么样"; //用户输入的文本
        //JSON格式数据（入参）
        InputText inputText=new InputText();
        inputText.setText(msg);
        Perception perception=new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey("acc513be8b5e4b26929247e83132f116");
        userInfo.setUserId("java1903");

        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject=JSONObject.fromObject(tulingBean);
        System.out.println("请求数据："+jsonObject.toString());

        //2步  对指定的API 地址 发送POST请求 （传入入参JSON对象）
        JSONObject object= WeixinUtil.httpRequest(api,"POST",jsonObject.toString());
        System.out.println("响应数据："+object.toString());

        JSONArray  array= (JSONArray) object.get("results");

        JSONObject object1= (JSONObject) array.get(0);
        JSONObject object2= (JSONObject) object1.get("values");
        String result= (String) object2.get("text");
        System.out.println("返回的结果："+result);
    }

}
