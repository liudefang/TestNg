package com.test.erp.online.purchase.thrifttestng;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.test.erp.api.thrift.service.ThriftContractService;
import com.test.erp.api.thrift.struct.TContractStruct;
import com.test.erp.crm.model.contract.Contract;

public class ThriftgetContractListTest {
	
	//static String IP = "121.40.65.64"; //预发环境的IP地址
		//static String IP = "10.1.2.211";    //测试环境的IP地址
		static String IP = "10.1.2.88";    //性能测试环境的IP地址

  @Test
  public void f() {
	  ThriftContractService.Client client = null;
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          client = new ThriftContractService.Client(protocol);
          
         // List<TContractStruct> getContractListByUid(String uid, short invalidDays)
         // List<TContractStruct> contracts = new ArrayList<TContractStruct>();
          List<TContractStruct> contractService = client.getContractListByUid("2842540000", (short)60);  //获取合同列表
          //List<TContractStruct> contracts = new ArrayList<TContractStruct>();
          //TContractStruct contract = client.getContractByCode("HT20170620005", (short)60);    //获取合同详情
          System.out.println("contractService" + new Gson().toJson(contractService));
      
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

