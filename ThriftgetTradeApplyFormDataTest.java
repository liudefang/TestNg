package cn.erp.testng.testScript;

import com.google.gson.Gson;
import com.test.erp.api.thrift.service.ThriftContractService;
import com.test.erp.api.thrift.struct.*;
import net.sf.json.JSONObject;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mike.liu on 2018/3/14.
 */
public class ThriftgetTradeApplyFormDataTest {

    static String IP = "10.1.2.211";

    //获取交易申请表数据
    @Test(priority = 1)
    public  void getTradeApplyFormData1()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);


            {
                TTradeParam ApplyFormData = new TTradeParam();

                ApplyFormData.uid = "2842544150";
                ApplyFormData.prdtestId = "222222S0";
                ApplyFormData.amount = 1000;
                ApplyFormData.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                ApplyFormData.openDay = "2018-03-15";


                TTradeApply flag = client.getTradeApplyFormData(ApplyFormData);
                JSONObject jsonObj = JSONObject.fromObject(flag);

                System.out.println("ResposeStruct:"+ ApplyFormData);
                String message = (String) jsonObj.get("bankUsername");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResposeStruct1:"+ flag);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【正常用例】:正常获取交易申请表数据");
                String exp_message = "刘德芳";
                Reporter.log("请求报文信息: "+ ApplyFormData);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message,message);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            client.getInputProtocol().getTransport().close();
        }
    }

    //追加金额小于最低追加金额
    @Test(priority = 2)
    public  void getTradeApplyFormData2()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);


            {
                TTradeParam ApplyFormData = new TTradeParam();

                ApplyFormData.uid = "2842544150";
                ApplyFormData.prdtestId = "222222S0";
                ApplyFormData.amount = 5;
                ApplyFormData.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                ApplyFormData.openDay = "2018-04-14";


                TTradeApply flag = client.getTradeApplyFormData(ApplyFormData);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ ApplyFormData);
                String message = (String) jsonObj.get("bankUsername");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "刘德芳";
                Reporter.log("请求报文信息: "+ ApplyFormData);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message,message);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            client.getInputProtocol().getTransport().close();
        }
    }
}
