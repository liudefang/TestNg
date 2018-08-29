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
import com.hotent.core.util.DateUtil;
import com.test.erp.api.thrift.service.ThriftContractService;
import com.test.erp.api.thrift.service.ThriftOnlineSaleProductService;
import com.test.erp.api.thrift.struct.TContractResultStruct;
import com.test.erp.api.thrift.struct.TContractSignType;
import com.test.erp.api.thrift.struct.TContractStruct;
import com.test.erp.api.thrift.struct.TOnlineSaleProduct;
import com.test.erp.api.thrift.struct.TOnlineSaleProductAttribute;
import com.test.erp.api.thrift.struct.TOnlineSaleProductType;

import net.sf.json.JSONObject;

public class ThriftOnlineSaleProductServiceTest {
	
	static String IP = "10.1.2.211";    //测试环境的IP地址
		//static String IP = "10.1.2.88";    //性能测试环境的IP地址
  @Test
  public void OnlineSaleProduct() {
	  ThriftOnlineSaleProductService.Client client = null;
      try
      {
          TTransport transport = new TFramedTransport(new TSocket(IP, 33202, 20000));
          transport.open();
          TProtocol protocol = new TCompactProtocol(transport);
          client = new ThriftOnlineSaleProductService.Client(protocol);
        
          List<TOnlineSaleProduct> result = client.getOnlineSaleProductList(TOnlineSaleProductType.Simu, 0, (short)100);
          System.out.println("result" + new Gson().toJson(result));
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

