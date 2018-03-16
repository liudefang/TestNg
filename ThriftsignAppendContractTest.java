package cn.erp.testng.testScript;

import cn.erp.testng.configuration.SignContract;
import com.google.gson.Gson;
import com.hotent.core.util.DateUtil;
import com.jfz.erp.api.thrift.service.ThriftContractService;
import com.jfz.erp.api.thrift.struct.*;
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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mike.liu on 2018/3/12.
 */



public class ThriftsignAppendContractTest {
    static String IP = "10.1.2.211";    //测试环境的IP地址

    //正常的追加
    @Test(priority = 1)
    public  void signAppendContract()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);

            // 创建一个dataformat对象
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();

            date = calendar.getTime();
            String date1 = dataFormat.format(date) ;
            System.out.println(date1);

            {
                TTradeParam appendStruct = new TTradeParam();
                appendStruct.uid = "2842544150";
                appendStruct.prdJfzId = "222222S0";
                appendStruct.amount = 600;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = "2018-03-18";
                TTradeResult flag = client.signAppendContract(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                String message = (String) jsonObj.get("status");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + new Gson().toJson(flag));

                Reporter.log("【正常用例】:追加购买记录成功");
                String exp_message = "Succ";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(message,exp_message);
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



    //传入的开放日大于产品的开放日列表的开放日日期
    @Test(priority = 2)
    public  void signAppendContract2()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);

            // 创建一个dataformat对象
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();

            date = calendar.getTime();
            String date1 = dataFormat.format(date) ;
            System.out.println(date1);

            {
                TTradeParam appendStruct = new TTradeParam();
                appendStruct.uid = "2842544150";
                appendStruct.prdJfzId = "222222S0";
                appendStruct.amount = 50;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = "2018-03-20";
                TTradeResult flag = client.signAppendContract(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                String message = (String) jsonObj.get("message");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + new Gson().toJson(flag));

                Reporter.log("【异常用例】:无某某对应的开放日");
                String exp_message = "无2018-03-20对应的开放日";
                Reporter.log("请求报文信息: "+ appendStruct);
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

    //用户名uid不存在
    @Test (priority = 3)
    public  void signAppendContract3()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);

            // 创建一个dataformat对象
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();

            date = calendar.getTime();
            String date1 = dataFormat.format(date) ;
            System.out.println(date1);

            {
                TTradeParam appendStruct = new TTradeParam();
                appendStruct.uid = "3426991545";
                appendStruct.prdJfzId = "P6e3f2xq0k";
                appendStruct.amount = 300;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.signAppendContract(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "无3426991545对应的客户";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(message,exp_message);
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

    //产品id不存在
    @Test (priority = 4)
    public  void signAppendContract4()
    {
        ThriftContractService.Client client = null;
        try
        {
            TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);

            // 创建一个dataformat对象
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();

            date = calendar.getTime();
            String date1 = dataFormat.format(date) ;
            System.out.println(date1);

            {
                TTradeParam appendStruct = new TTradeParam();
                appendStruct.uid = "3426991543";
                appendStruct.prdJfzId = "P62c9rrqc3";
                appendStruct.amount = 300;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.signAppendContract(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:无某某对应的产品");
                String exp_message = "无P62c9rrqc3对应的产品";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(message,exp_message);
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
