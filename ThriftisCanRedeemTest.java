package cn.erp.testng.testScript;

import com.google.gson.Gson;
import com.jfz.erp.api.thrift.service.ThriftContractService;
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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mike.liu on 2018/3/13.
 */
public class ThriftisCanRedeemTest {

    static String IP = "10.1.2.211";    //测试环境的IP地址

    //正常的赎回
    @Test(priority = 1)
    public  void isCanRedeem()
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
                TTradeParam redeemStruct = new TTradeParam();
                redeemStruct.uid = "3426991543";
                redeemStruct.prdJfzId = "P62c9rrqc2";
                redeemStruct.amount = 2000;
                redeemStruct.tradeType = TTradeType.Redemption;  //Purchase(1):申购,Subscription(2)：认购,Append(3)：追加,Redemption(4)：赎回;
                redeemStruct.openDay = "2018-04-07";
                TTradeResult flag = client.isCanRedeem(redeemStruct);
                JSONObject jsonObj = JSONObject.fromObject(flag);
                String message = (String) jsonObj.get("status");
                String ResultStruct = new Gson().toJson(flag);
                System.out.println("ResultStruct:" + new Gson().toJson(flag));

                Reporter.log("【正常用例】:追加购买记录成功");
                String exp_message = "Succ";
                Reporter.log("请求报文信息: "+ redeemStruct);
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
