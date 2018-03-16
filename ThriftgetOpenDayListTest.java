package cn.erp.testng.testScript;

import com.google.gson.Gson;
import com.jfz.erp.api.thrift.service.ThriftContractService;
import com.jfz.erp.api.thrift.struct.TOpenDay;
import com.jfz.erp.api.thrift.struct.TTradeParam;
import com.jfz.erp.api.thrift.struct.TTradeResult;
import com.jfz.erp.api.thrift.struct.TTradeType;
import net.sf.json.JSONObject;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mike.liu on 2018/3/14.
 */
public class ThriftgetOpenDayListTest {
    static String IP = "10.1.2.211";

    //有产品的开放日列表
    @Test(priority = 1)
    public  void getOpenDayList1()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);


            {

                String uid = "2842544150";
                String prdJfzId = "222222S0";
                TTradeType tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                List<TOpenDay> list = client.getOpenDayList(uid, prdJfzId, tradeType);
                System.out.println("list:" + list);
                int size = list.size();


                String ResultStruct = new Gson().toJson(list);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                int exp_siz = 2;
                Reporter.log("请求报文信息: "+ list);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_siz + " ,actual: "+ size);
                Assert.assertEquals(exp_siz,size);
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

    //没有产品的开放日列表，验证发邮件
    @Test(priority = 2)
    public  void getOpenDayList2()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);


            {

                String uid = "2842544150";
                String prdJfzId = "P62c9rrqc2";
                TTradeType tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                List<TOpenDay> list = client.getOpenDayList(uid, prdJfzId, tradeType);
                System.out.println("list:" + list);
                int size = list.size();


                String ResultStruct = new Gson().toJson(list);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:没有产品的开放日列表");
                int exp_siz = 0;
                Reporter.log("请求报文信息: "+ list);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_siz + " ,actual: "+ size);
                Assert.assertEquals(exp_siz,size);
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

    //产品的id为空，验证发邮件
    @Test(priority = 3)
    public  void getOpenDayList3()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);


            {

                String uid = "2842544150";
                String prdJfzId = "P62c9rrqc2";
                TTradeType tradeType = TTradeType.Redemption;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                List<TOpenDay> list = client.getOpenDayList(uid, prdJfzId, tradeType);
                System.out.println("list:" + list);
                int size = list.size();


                String ResultStruct = new Gson().toJson(list);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:产品的id不存在");
                int exp_siz = 0;
                Reporter.log("请求报文信息: "+ list);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_siz + " ,actual: "+ size);
                Assert.assertEquals(exp_siz,size);
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
