package cn.erp.testng.testScript;


import cn.erp.testng.configuration.HttpRequest;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import net.sf.saxon.exslt.Common;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike.liu on 2017/8/30.
 */
public class ProductfeeListTest {

    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httppost;
    private HttpGet httpget;
    private HttpResponse response;
    private HttpEntity entity;
    private String postResult = null;
    private String getResult = null;
    public static String URL = "http://qa1-erp.jfz.com:8080/erp/scm/productfee";
    public static String productfeeurl = "";
    public int status,exp_status ;
    public String exp_errorMessage;

    @Test
    public void Login(){

        String loginurl = "http://qa1-erp.jfz.com:8080/login.ht";
        httppost = new HttpPost( loginurl );

        List<NameValuePair> formTabe = new ArrayList<NameValuePair>();
        formTabe.add(new BasicNameValuePair("username","defang3"));
        formTabe.add(new BasicNameValuePair("password","123"));

        try {
            httppost.setEntity( new UrlEncodedFormEntity( formTabe,"UTF-8" ) );
            response = httpClient.execute( httppost );
            entity = response.getEntity();
            postResult = EntityUtils.toString( entity,"UTF-8" );

            System.out.println( "查看登录接口请求的参数：" + formTabe );
            System.out.println( "查看登录接口请求返回的结果：" + postResult );
        }catch (Exception e) {
            e.printStackTrace();
        }
        httppost.releaseConnection();
    }

    //获取费率列表
    @Test
    public void productfee_Succ() throws IOException{

        Long Request_productId = 20000081811908L;
         productfeeurl= ""+URL+"/list.ht?productId="+Request_productId+"";
       // exp_errorMessage = "成功";
        exp_status = 0;
        resultCheck(exp_status);


    }
    @Test
    public void productfee_Nmess() throws IOException{

        Long Request_productId = 30000000186454L;
        productfeeurl= ""+URL+"/list.ht?productId="+Request_productId+"";
        //exp_errorMessage = "产品ID不能为空。";
        exp_status = 0;
        resultCheck(exp_status);

    }



    public void resultCheck(int exp_status) throws IOException {
        httpget = new HttpGet( productfeeurl );

        try {
            response = httpClient.execute( httpget );

            entity = response.getEntity();

            getResult = EntityUtils.toString( entity,"UTF-8" );

            JSONObject jsonObj = JSONObject.fromObject(getResult);
            int status = (int) jsonObj.get("status");
           // String errorMessage = (String) jsonObj.get( "errorMessage" );


           /* List<JSONObject> list = (List<JSONObject>) jsonObj.getJSONObject("data").get("productFeeList");

            Long productId = (Long) list.get(0).get( "productId" );

            System.out.println( "product id in product fee list: " + productId );*/

            Reporter.log("获取产品费率信息!");

            Reporter.log("请求地址: "+httpget);
            Reporter.log("返回结果: "+getResult);

            Reporter.log("用例结果: resultCode=>expected: " + exp_status + " ,actual: "+ status);
            Assert.assertEquals(status,exp_status);

          //  Assert.assertEquals(errorMessage, exp_errorMessage);
            System.out.println( "查看获取费率列表的结果：" + jsonObj );
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpget.releaseConnection();
    }
}
