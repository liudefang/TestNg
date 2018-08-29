package com.test.erp.online.purchase.thrifttestng;

import java.util.List;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.test.erp.api.thrift.service.ThriftBuydetailService;
import com.test.erp.api.thrift.service.ThriftRemittanceService;
import com.test.erp.api.thrift.struct.TBuydetailStruct;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ThriftBuydetailServiceImplTest {
	//static String IP = "121.40.65.64"; //预发环境的IP地址
	String IP = "10.1.2.211";    //测试环境的IP地址
  @Test
  /*
   * 根据传入合同编号获取对应的购买记录的相关信息
   * 线下
   */
  public void getByContractCode() {
	  try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

          List<TBuydetailStruct> buydetails = client.getByContractCode("HT20170911009");
         //JSONArray getJsonArray=JSONArray.fromObject(buydetails);
         // JSONObject getJsonObj = getJsonArray.getJSONObject(0);
         // JSONObject result= getJsonObj.getJSONObject("status");
          System.out.println("remittanceStatus " + new Gson().toJson(buydetails));
          
          
          //refund:(1，退款 2，未退款)
          //DataSource：1，线下。2.线上
          //Assert.assertTrue(getJsonObj,"有打款记录");
          //System.out.println("getJsonArray:" + getJsonArray);
         
          transport.close();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
  
	// @Test
	  /*
	   * 根据传入合同编号获取对应的购买记录的相关信息
	   * 线上
	   */
	/*  public void getByContractCode1() {
		  try
	      {
	          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
	          transport.open();
	          TProtocol protocol = new TCompactProtocol(transport);
	          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

	          List<TBuydetailStruct> buydetails = client.getByContractCode("HT20170606062");
	         
	          System.out.println("remittanceStatus " + new Gson().toJson(buydetails));
	          
	          
	          //refund:(1，退款 2，未退款)
	          //DataSource：1，线下。2.线上
	          //Assert.assertTrue(getJsonObj,"有打款记录");
	          //System.out.println("getJsonArray:" + getJsonArray);
	         
	          transport.close();
	      }
	      catch (Exception e)
	      {
	          e.printStackTrace();
	      }
	  }
  
 // @Test
  /*
   * 根据传入合同编号获取对应的购买记录的相关信息
   * 没有购买记录
   */
  /*public void getByContractCode2() {
	  try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

          List<TBuydetailStruct> buydetails = client.getByContractCode("HT20170606004");
          //int ret = int jsonObj.getInt("refund");
          System.out.println("remittanceStatus1 " + new Gson().toJson(buydetails));
          TBuydetailStruct tb=new TBuydetailStruct();
          if(buydetails.size()>0){
        	  tb=buydetails.get(0);
          }
          
          //refund:(1，退款 2，未退款)
          //DataSource：1，线下。2.线上
          //Assert.assertTrue(ret,"有打款记录");
          /*System.out.println("============================");
          System.out.println("buydetail size:" + buydetails.size());
          System.out.println("============================");*/
    /*      transport.close();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }*/
  
 // @Test
  /*
   * 根据传入合同编号获取对应的购买记录的相关信息
   * 合同编号不存在
   */
 /* public void getByContractCode3() {
	  try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

          List<TBuydetailStruct> buydetails = client.getByContractCode("HT20170503001");
          //int ret = int jsonObj.getInt("refund");
          System.out.println("remittanceStatus2 " + new Gson().toJson(buydetails));
          
         
          
          //refund:(1，退款 2，未退款)
          //DataSource：1，线下。2.线上
          //Assert.assertTrue(ret,"有打款记录");
          
          transport.close();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
 // @Test
  /*
   * 根据传入合同编号获取对应的购买记录的相关信息
   * 合同编号为空
   */
  /*public void getByContractCode4() {
	  try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

          List<TBuydetailStruct> buydetails = client.getByContractCode("");
          //int ret = int jsonObj.getInt("refund");
          System.out.println("remittanceStatus3 " + new Gson().toJson(buydetails));
          TBuydetailStruct tb=new TBuydetailStruct();
          if(buydetails.size()>0){
        	  tb=buydetails.get(0);
          }
          
          //refund:(1，退款 2，未退款)
          //DataSource：1，线下。2.线上
          //Assert.assertTrue(ret,"有打款记录");
          
          transport.close();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
  
  //@Test
  /*
   * 根据传入合同编号获取对应的购买记录的相关信息
   * 购买记录没有通过审批
   */
 /* public void getByContractCode5() {
	  try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33204, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          ThriftBuydetailService.Client client = new ThriftBuydetailService.Client(protocol);

          List<TBuydetailStruct> buydetails = client.getByContractCode("HT20170613006");
          //int ret = int jsonObj.getInt("refund");
          System.out.println("remittanceStatus4 " + new Gson().toJson(buydetails));
          
          
          //refund:(1，退款 2，未退款)
          //DataSource：1，线下。2.线上
          //Assert.assertTrue(ret,"有打款记录");
          
          transport.close();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }*/
}
