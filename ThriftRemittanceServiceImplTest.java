package com.test.erp.online.purchase.thrifttestng;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.test.erp.api.thrift.service.ThriftRemittanceService;
import com.test.erp.api.thrift.struct.ConfirmStatus;
import com.test.erp.api.thrift.struct.RemittancePicInfo;
import com.test.erp.api.thrift.struct.RemittanceStatus;

import net.sf.json.JSONObject;

public class ThriftRemittanceServiceImplTest {
	static String IP = "121.40.65.64"; //预发环境的IP地址
	//String IP = IP;    //测试环境的IP地址
	/*
	 * 根据传入合同编号获取合同是否有打款记录
	 */
 
  /*
   * 有打款记录
   */
  @Test
  public static void getContractStatusByCrmCode()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          RemittanceStatus remittanceStatus = client.getContractStatusByCrmCode("HT20170615004");
          
          JSONObject jsonObj = JSONObject.fromObject(remittanceStatus);
          //boolean ret = (Boolean) jsonObj.get("contractStatus");
          System.out.println("remittanceStatus " + new Gson().toJson(remittanceStatus));
          String message = (String) jsonObj.get("message");
	       
	        Assert.assertEquals("查询成功！", message);
          
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }

  /*
   * 没有打款记录
   */
/*@Test
  public static void getContractStatusByCrmCode1()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          RemittanceStatus remittanceStatus = client.getContractStatusByCrmCode("HT20170613005");
          
          JSONObject jsonObj = JSONObject.fromObject(remittanceStatus);
          
          System.out.println("remittanceStatus1 " + new Gson().toJson(remittanceStatus));
          String message = (String) jsonObj.get("message");
	       
	        Assert.assertEquals("打款记录不存在！", message);
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }
 
  /*
   *获取打款记录：合同编号为空
   */
 /*@Test
  public static void getContractStatusByCrmCode2()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          RemittanceStatus remittanceStatus = client.getContractStatusByCrmCode("");
          
          JSONObject jsonObj = JSONObject.fromObject(remittanceStatus);
         
          System.out.println("remittanceStatus2 " + new Gson().toJson(remittanceStatus));
          String message = (String) jsonObj.get("message");
	       
	        Assert.assertEquals("合同编号缺失", message);
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }
 
  /*
   *获取打款记录：合同编号不存在
   */
 /*@Test
  public static void getContractStatusByCrmCode3()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          RemittanceStatus remittanceStatus = client.getContractStatusByCrmCode("HT20170603001");
          
          JSONObject jsonObj = JSONObject.fromObject(remittanceStatus);
          
          System.out.println("remittanceStatus3 " + new Gson().toJson(remittanceStatus));
          String message = (String) jsonObj.get("message");
	       
	        Assert.assertEquals("合同编号不存在", message);
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }*/

  /*
   *获取合同的签约状态：合同编号正确,存在持仓-确认中
   */
   @Test
  public static void getByContractCrmCode()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
    	            
    	  TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          ConfirmStatus confirmStatus = client.getByContractCrmCode("HT20170615004");// 2.7加入时间单位净值录入
          
          JSONObject jsonObj = JSONObject.fromObject(confirmStatus);
          //boolean ret = (Boolean) jsonObj.get("confirmStatus");
          System.out.println("remittanceStatus4 " + new Gson().toJson(confirmStatus));
          String message = (String) jsonObj.get("message");
	       
	        Assert.assertEquals("确认成功!", message);
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }
  
  
  /*
   *获取合同的签约状态：合同编号不存在
   */
  /*@Test
  public static void getByContractCrmCode1()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
    	            
    	  TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          ConfirmStatus confirmStatus = client.getByContractCrmCode("HT20170612000");// 2.7加入时间单位净值录入
          
          JSONObject jsonObj = JSONObject.fromObject(confirmStatus);
          //boolean ret = (Boolean) jsonObj.get("confirmStatus");
          System.out.println("remittanceStatus5 " + new Gson().toJson(confirmStatus));
         // System.out.println("ret:" + ret);
         // Assert.assertFalse(ret,"合同编号为空");
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }
  

  /*
   *获取合同的签约状态：合同编号为空
   */
  /*@Test
  public static void getByContractCrmCode2()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
    	            
    	  TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          ConfirmStatus confirmStatus = client.getByContractCrmCode("");// 2.7加入时间单位净值录入
          
          JSONObject jsonObj = JSONObject.fromObject(confirmStatus);
          //boolean ret = (Boolean) jsonObj.get("confirmStatus");
          System.out.println("remittanceStatus5 " + new Gson().toJson(confirmStatus));
         // System.out.println("ret:" + ret);
         // Assert.assertFalse(ret,"合同编号为空");
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }

  /*
   *获取合同的签约状态：确认成功
   */
  /*@Test
  public static void getByContractCrmCode3()
  {
      ThriftRemittanceService.Client client = null;
      
      try
      {
    	            
    	  TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          
          client = new ThriftRemittanceService.Client(protocol);
          ConfirmStatus confirmStatus = client.getByContractCrmCode("");// 2.7加入时间单位净值录入
          
          JSONObject jsonObj = JSONObject.fromObject(confirmStatus);
          //boolean ret = (Boolean) jsonObj.get("confirmStatus");
          System.out.println("remittanceStatus6 " + new Gson().toJson(confirmStatus));
         // System.out.println("ret:" + ret);
         // Assert.assertFalse(ret,"合同编号为空");
         
      }
      catch (Exception e)
      {
          // TODO: handle exception
          e.printStackTrace();
      }
      finally
      {
          client.getInputProtocol().getTransport().close();
          
      }
  }*/
   
   //购买记录没有通过审批
   @Test
   public static void getByContractCrmCode3()
   {
       ThriftRemittanceService.Client client = null;
       
       try
       {
     	            
     	  TTransport transport = new TFramedTransport(new TSocket(IP, 33203, 20000));
           transport.open();
           TProtocol protocol = new TCompactProtocol(transport);
           
           client = new ThriftRemittanceService.Client(protocol);
           ConfirmStatus confirmStatus = client.getByContractCrmCode("HT20170615004");// 2.7加入时间单位净值录入
           
           JSONObject jsonObj = JSONObject.fromObject(confirmStatus);
           //boolean ret = (Boolean) jsonObj.get("confirmStatus");
           System.out.println("remittanceStatus7 " + new Gson().toJson(confirmStatus));
           String message = (String) jsonObj.get("message");
           Assert.assertEquals("购买记录没有通过审批", message);
          
       }
       catch (Exception e)
       {
           // TODO: handle exception
           e.printStackTrace();
       }
       finally
       {
           client.getInputProtocol().getTransport().close();
           
       }
   }
}
