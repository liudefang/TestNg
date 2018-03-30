package cn.erp.testng.testScript;
import org.json.JSONObject;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.http.entity.StringEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike.liu on 2017/8/31.
 */
public class ProductfeeSaveTest {
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httppost;
    private HttpGet httpget;
    private HttpResponse response;
    private HttpResponse response2;
    private HttpEntity entity;
    private String postResult = null;
    private String getResult = null;
    public static String URL = "http://qa1-erp.jfz.com:8080/erp/scm/productfee";
    public static String productfeeurl = "";
    public int status,exp_status ;
    public String exp_errorMessage;
    static Long  productFeeId = null;



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
    //新增费率成功
    @DataProvider (name = "Productfee")
    public static Object[][] productfee(){
        String URL = "http://qa1-erp.jfz.com:8080/erp/scm/productfee/save.ht";
        String data = "{\"agreementOrgId\":20000112446294,\"agreementOrgName\":\"夏道山测试数据\",\"agreementType\":\"2\",\"validityFrom\":\"2017-09-04\",\"validityTo\":\"2017-09-10\",\"incomeCalculationSource\":\"2\",\"isInstallment\":\"1\",\"incomeCalculationBenchmark\":\"1\",\"isUpdatingFee\":0,\"subscriptionForms\":[{\"period\":1,\"subscriptionRatio\":\"0.5\",\"subscriptionTime\":\"2017-09-10\"}],\"productId\":\"20000059181072\"}";
        return new Object[][] {{URL,data}};
    }
    @Test(dataProvider = "Productfee")
    public Long ProductfeeSave_Succ(String URL, String data) throws Exception{

        int exp_status = 0;

        try {
            HttpPost httpPost = new HttpPost( URL );
            JSONObject jsonParam = new JSONObject(data);
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity( entity );
            HttpResponse response2 = httpClient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                postResult = EntityUtils.toString(entity2,"UTF-8");
                System.out.println("Response content: " + postResult);
                EntityUtils.consume(entity2);


                JSONObject jsonObj = new JSONObject(postResult);
                int status = (int) jsonObj.get("status");
                 productFeeId = (Long) jsonObj.getJSONObject("data").get("id");


                Reporter.log("新建产品费率_成功");

                Reporter.log("请求地址: "+httpPost);
                Reporter.log("返回结果: "+postResult);

                Reporter.log("用例结果: resultCode=>expected: " + exp_status + " ,actual: "+ status);
                Assert.assertEquals(status,exp_status);

                //Assert.assertEquals(errorMessage, exp_errorMessage);
                System.out.println( "查看获取费率列表的结果：" + jsonObj );
                System.out.println( "生成费率的id：" + productFeeId );
            } catch (Exception e) {
                e.printStackTrace();
            }





        } catch (Exception e) {
            e.printStackTrace();
        }


        return productFeeId;
    }

    //新增认购费成功
    @DataProvider (name = "productSubscriptionFee")
    public static Object[][] productSubscriptionFee(){
        System.out.println("productFeeId:" + productFeeId);
        String URL = "http://qa1-erp.jfz.com:8080/erp/scm/productSubscriptionFee/saveList.ht";
        String data = "[{\"subscriptionAmountFrom\":\"100.00\",\"subscriptionAmountTo\":\"500\",\"subscriptionRate\":\"9\",\"commissionRatio\":\"10\",\"isPreTax\":\"1\",\"taxRate\":\"10\",\"settlementYield\":\"0.81\",\"productId\":\"20000059181072\",\"productFeeId\":productFeeId]";
        return new Object[][] {{URL,data}};
    }
    @Test(dataProvider = "productSubscriptionFee")
    public void ProductSubscriptionFee(String URL, String data) throws Exception{

        int exp_status = 0;
        try {
            HttpPost httpPost = new HttpPost( URL );
            JSONObject jsonParam = new JSONObject(data);
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity( entity );
            HttpResponse response2 = httpClient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                postResult = EntityUtils.toString(entity2,"UTF-8");
                System.out.println("Response content: " + postResult);
                EntityUtils.consume(entity2);

                JSONObject jsonObj = new JSONObject(postResult);
                int status = (int) jsonObj.get("status");


                Reporter.log("新建产品费率_成功");

                Reporter.log("请求地址: "+httpPost);
                Reporter.log("返回结果: "+postResult);

                Reporter.log("用例结果: resultCode=>expected: " + exp_status + " ,actual: "+ status);
                Assert.assertEquals(status,exp_status);

                //Assert.assertEquals(errorMessage, exp_errorMessage);
                System.out.println( "查看获取费率列表的结果：" + jsonObj );

            } catch (Exception e) {
                e.printStackTrace();
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DataProvider (name = "Productfeeexist")
    public static Object[][] productfeeexit(){
        String URL = "http://qa1-erp.jfz.com:8080/erp/scm/productfee/save.ht";
        String data = "{\"agreementOrgId\":20000112446294,\"agreementOrgName\":\"夏道山测试数据\",\"agreementType\":\"2\",\"validityFrom\":\"2017-09-04\",\"validityTo\":\"2017-09-10\",\"incomeCalculationSource\":\"1\",\"isInstallment\":\"2\",\"incomeCalculationBenchmark\":\"2\",\"isUpdatingFee\":0,\"subscriptionForms\":[{\"period\":1,\"subscriptionRatio\":\"1\",\"subscriptionTime\":\"2017-08-31\"}],\"productId\":\"20000059181072\"}";
        return new Object[][] {{URL,data}};
    }
    @Test(dataProvider = "Productfeeexist")
    public void ProductfeeSave_exist(String URL,String data) throws Exception{
        //CloseableHttpClient httpclient = HttpClients.createDefault();
        String exp_errorMessage = "该协议主体已创建了一条费率！您可以前往更新或者编辑该费率。";
        try {
            HttpPost httpPost = new HttpPost( URL );
            JSONObject jsonParam = new JSONObject(data);
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity( entity );
            HttpResponse response2 = httpClient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                postResult = EntityUtils.toString(entity2,"UTF-8");
                System.out.println("Response content: " + postResult);
                EntityUtils.consume(entity2);


                JSONObject jsonObj = new JSONObject(postResult);
                int status = (int) jsonObj.get("status");
                 String errorMessage = (String) jsonObj.get( "errorMessage" );


                Reporter.log("新建产品费率_产品费率已经存在");

                Reporter.log("请求地址: "+httpPost);
                Reporter.log("返回结果: "+postResult);

                Reporter.log("用例结果: resultCode=>expected: " + exp_errorMessage + " ,actual: "+ errorMessage);
                Assert.assertEquals(1,status);

                Assert.assertEquals(errorMessage, exp_errorMessage);
                System.out.println( "查看获取费率列表的结果：" + jsonObj );
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
