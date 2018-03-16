package com.jfz.erp.online.purchase.thrifttestng;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hotent.core.util.DateUtil;
import com.jinfuzi.jfz_crm.thrift.service.ThriftJfzOrderRecordService;
import com.jinfuzi.jfz_crm.thrift.struct.TJfzOrderRecord;

public class ThriftjfzorderrecordTest {
	//static 	String IP = "121.40.65.64"; //预发环境的IP地址
	static String IP = "10.1.2.211";    //测试环境的IP地址
  @Test
  public void jfzorderrecord() {
	  TTransport transport = new TSocket(IP, 50108, 20000);
	     try {
	     transport.open();
	     } catch (TTransportException e) {
	     e.printStackTrace();
	     }
	     TProtocol protocol = new TBinaryProtocol(transport);
	     String setOrderTime = DateUtil.getStringNowTime();
	     ThriftJfzOrderRecordService.Client client = new ThriftJfzOrderRecordService.Client(protocol);
	     TJfzOrderRecord tJfzOrderRecord = new TJfzOrderRecord();
	     tJfzOrderRecord.setOrderName("刘德芳");
	     tJfzOrderRecord.setReservePhone("15989576517");
	     tJfzOrderRecord.setPhoneCallAttribution("广东深圳");
	     tJfzOrderRecord.setOrderTime(setOrderTime);
	     tJfzOrderRecord.setOrderType((short) 4);
	     tJfzOrderRecord.setPageSource("http://www.jinfuzi.com/xintuo/list_a1_f3.html#xtlist");
	     tJfzOrderRecord.setCustomerType(2);
	     tJfzOrderRecord.setChannelKey("360sem-gs");
	     tJfzOrderRecord.setLandpage("http://www.jinfuzi.com/xintuo/tg-71?yw\u003dgq\u0026qd\u003ductt\u0026jh\u003dsm\u0026dy\u003dxtcpc\u0026cy\u003d3-11tg71\u0026sign\u003ductt-gq");
	     tJfzOrderRecord.setSrcpage("iflow.uczzd.cn");
	     tJfzOrderRecord.setChannelKey("uctt-gq");
	     tJfzOrderRecord.setCustomerType(2);
	     tJfzOrderRecord.setPrdName("平安信托-淡水泉成长一期");
	     tJfzOrderRecord.setErpPrdCode("222222S0");
	     tJfzOrderRecord.setJfzPrdCode("222222S0");
	     /*tJfzOrderRecord.setPrdName("测试开放日净值");
	     tJfzOrderRecord.setErpPrdCode("P61cwbyi2r");
	     tJfzOrderRecord.setJfzPrdCode("P61cwbyi2r");
	    tJfzOrderRecord.setPrdName("cxx私募成本【高】");  //预发环境的数据
	     tJfzOrderRecord.setErpPrdCode("P61lriohey");
	     tJfzOrderRecord.setJfzPrdCode("P61lriohey");*/
	     tJfzOrderRecord.setRemark("测试加上备注字段问题");
	     tJfzOrderRecord.setOrderSourceType(31);
	     tJfzOrderRecord.setSecurityManagerId("999999999");
	     tJfzOrderRecord.setSecurityManagerName("证券经理周伟鑫");

	     try {
	     Boolean result = client.saveReserve(tJfzOrderRecord);
	     System.out.println("tJfzOrderRecord:" + tJfzOrderRecord);
	     System.out.println("result:" + result);
	     Assert.assertTrue(result);
	    // Assert.assertFalse(result);
	     if (result == true) {
	     System.out.println("save Jfz Order Record Success!");
	     
	     } else {
	     System.out.println("save Jfz Order Record Fail!");
	     }
	     } catch (Exception e) {
	     e.printStackTrace();
	     } finally {
	     transport.close();
	     }

	     }
}
