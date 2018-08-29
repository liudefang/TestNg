package com.test.erp.online.purchase.thrifttestng;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hotent.core.util.DateUtil;
import com.test.test_crm.thrift.service.ThrifttestOrderRecordService;
import com.test.test_crm.thrift.struct.TtestOrderRecord;

public class ThrifttestorderrecordTest {
	//static 	String IP = "121.40.65.64"; //预发环境的IP地址
	static String IP = "10.1.2.211";    //测试环境的IP地址
  @Test
  public void testorderrecord() {
	  TTransport transport = new TSocket(IP, 50108, 20000);
	     try {
	     transport.open();
	     } catch (TTransportException e) {
	     e.printStackTrace();
	     }
	     TProtocol protocol = new TBinaryProtocol(transport);
	     String setOrderTime = DateUtil.getStringNowTime();
	     ThrifttestOrderRecordService.Client client = new ThrifttestOrderRecordService.Client(protocol);
	     TtestOrderRecord ttestOrderRecord = new TtestOrderRecord();
	     ttestOrderRecord.setOrderName("刘德芳");
	     ttestOrderRecord.setReservePhone("15989576517");
	     ttestOrderRecord.setPhoneCallAttribution("广东深圳");
	     ttestOrderRecord.setOrderTime(setOrderTime);
	     ttestOrderRecord.setOrderType((short) 4);
	     ttestOrderRecord.setPageSource("http://www.test.com/xintuo/list_a1_f3.html#xtlist");
	     ttestOrderRecord.setCustomerType(2);
	     ttestOrderRecord.setChannelKey("360sem-gs");
	     ttestOrderRecord.setLandpage("http://www.test.com/xintuo/tg-71?yw\u003dgq\u0026qd\u003ductt\u0026jh\u003dsm\u0026dy\u003dxtcpc\u0026cy\u003d3-11tg71\u0026sign\u003ductt-gq");
	     ttestOrderRecord.setSrcpage("iflow.uczzd.cn");
	     ttestOrderRecord.setChannelKey("uctt-gq");
	     ttestOrderRecord.setCustomerType(2);
	     ttestOrderRecord.setPrdName("平安信托-淡水泉成长一期");
	     ttestOrderRecord.setErpPrdCode("222222S0");
	     ttestOrderRecord.settestPrdCode("222222S0");
	     /*ttestOrderRecord.setPrdName("测试开放日净值");
	     ttestOrderRecord.setErpPrdCode("P61cwbyi2r");
	     ttestOrderRecord.settestPrdCode("P61cwbyi2r");
	    ttestOrderRecord.setPrdName("cxx私募成本【高】");  //预发环境的数据
	     ttestOrderRecord.setErpPrdCode("P61lriohey");
	     ttestOrderRecord.settestPrdCode("P61lriohey");*/
	     ttestOrderRecord.setRemark("测试加上备注字段问题");
	     ttestOrderRecord.setOrderSourceType(31);
	     ttestOrderRecord.setSecurityManagerId("999999999");
	     ttestOrderRecord.setSecurityManagerName("证券经理周伟鑫");

	     try {
	     Boolean result = client.saveReserve(ttestOrderRecord);
	     System.out.println("ttestOrderRecord:" + ttestOrderRecord);
	     System.out.println("result:" + result);
	     Assert.assertTrue(result);
	    // Assert.assertFalse(result);
	     if (result == true) {
	     System.out.println("save test Order Record Success!");
	     
	     } else {
	     System.out.println("save test Order Record Fail!");
	     }
	     } catch (Exception e) {
	     e.printStackTrace();
	     } finally {
	     transport.close();
	     }

	     }
}
