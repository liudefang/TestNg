package cn.erp.testng.testScript;

import com.google.gson.Gson;
import com.jfz.erp.api.thrift.service.ThriftContractService;
import com.jfz.erp.api.thrift.struct.TTradeParam;
import com.jfz.erp.api.thrift.struct.TTradeResult;
import com.jfz.erp.api.thrift.struct.TTradeStatus;
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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mike.liu on 2018/3/13.
 */
public class ThriftisCanAppendTest {
    ThriftContractService.Client client = null;

    static String IP = "10.1.2.211";

    //不存在该产品的持仓
    @Test(priority = 1)
    public  void isCanAppend1()
    {

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
                appendStruct.prdJfzId = "P6e3f2xq0k";
                appendStruct.amount = 200;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "您无法通过线上追加此产品，请联系理财师做线下购买";
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

    //风险测评已过期
    @Test(priority = 2)
    public  void isCanAppend2()
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
                appendStruct.amount = 200;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "您的风险测评已过期，请重新测评";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(flag.status, TTradeStatus.RiskEvaluateOutOfDate);
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

    //风险等级不匹配
    @Test(priority = 3)
    public  void isCanAppend3()
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
                appendStruct.amount = 200;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:风险等级不匹配");
                String exp_message = "您无法通过线上追加此产品，请联系理财师做线下购买";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(flag.getStatus(), TTradeStatus.RiskGradeNotMatch);
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

    //资产证明已过期
    @Test(priority = 4)
    public  void isCanAppend4()
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
                appendStruct.amount = 200;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "您无法通过线上追加此产品，请联系理财师做线下购买";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(flag.getStatus(), TTradeStatus.NoCustomer);
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

    //最低追加金额等于产品最低金额
    @Test(priority = 5)
    public  void isCanAppend5()
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
                appendStruct.amount = 10;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:最低追加金额等于产品最低金额");
                String exp_message = "Succ";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message, message);
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
    //最低追加金额小于产品最低金额
    @Test(priority = 6)
    public  void isCanAppend6()
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
                appendStruct.amount = 5;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:最低追加金额小于产品最低金额");
                String exp_message = "追加时，认/申购金额不能小于10.0万";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message, message);
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

    //该uid不存在
    @Test(priority = 7)
    public  void isCanAppend7()
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
                appendStruct.uid = "3842544150";
                appendStruct.prdJfzId = "222222S0";
                appendStruct.amount = 10;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在线上购买的持仓");
                String exp_message = "无3842544150对应的客户";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message, message);
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

    //不存在该产品的id
    @Test(priority = 8)
    public  void isCanAppend8()
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
                appendStruct.prdJfzId = "1222222S0";
                appendStruct.amount = 10;
                appendStruct.tradeType = TTradeType.Append;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                appendStruct.openDay = ""+date1+"";
                TTradeResult flag = client.isCanAppend(appendStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                System.out.println("ResposeStruct:"+ appendStruct);
                String message = (String) jsonObj.get("message");
                System.out.println("ResultStruct1:" + flag);
                String ResultStruct = new Gson().toJson(flag);

                System.out.println("ResultStruct:" + ResultStruct);

                Reporter.log("【异常用例】:不存在该产品的id");
                String exp_message = "无1222222S0对应的产品";
                Reporter.log("请求报文信息: "+ appendStruct);
                Reporter.log("返回结果: "+ ResultStruct);

                Reporter.log("用例结果: resultCode=>expected: " + exp_message + " ,actual: "+ message);
                Assert.assertEquals(exp_message, message);
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
