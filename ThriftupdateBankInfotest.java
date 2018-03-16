package com.jfz.erp.online.purchase.thrifttestng;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jfz.erp.api.thrift.service.ThriftContractService;
import com.jfz.erp.api.thrift.service.ThriftRemittanceService;
import com.jfz.erp.api.thrift.struct.ConfirmStatus;
import com.jfz.erp.api.thrift.struct.TBankStruct;
import com.jfz.erp.api.thrift.struct.TCommonResultStruct;

import net.sf.json.JSONObject;

public class ThriftupdateBankInfotest {
	 static String IP = "121.40.65.64"; //预发环境的IP地址
		//static String IP = "10.1.2.211";    //测试环境的IP地址
		//static String IP = "10.1.2.88";    //性能测试环境的IP地址
	
	@Test
	public void addBankInfo() {
		ThriftContractService.Client client = null;

		try {

			TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);

			client = new ThriftContractService.Client(protocol);
			TBankStruct bankInfo = new TBankStruct();
			//bankInfo.uid = "2842544148";
			bankInfo.uid = "7736803641";
			
			bankInfo.payerName = "刘德芳";
			bankInfo.idCard = "43122319840326221X";
			bankInfo.bankName = "工商银行";
			bankInfo.bankCard = "6212262011018464458";
			bankInfo.bankAddr = "火炬开发区";
			bankInfo.province = "广东省";
			bankInfo.city = "中山市";
			bankInfo.bankCode = "ICBC";

			TCommonResultStruct Result = client.addBankInfo(bankInfo);
			JSONObject jsonObj = JSONObject.fromObject(Result);
			String message = (String) jsonObj.get("message");
	        System.out.println("ResultStruct" + new Gson().toJson(Result));
	        Assert.assertEquals("新增银行信息成功!", message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			client.getInputProtocol().getTransport().close();

		}

	}
	
	//更新银行卡信息
	/*@Test
	public void updateBankInfo() {
		ThriftContractService.Client client = null;

		try {

			TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);

			client = new ThriftContractService.Client(protocol);
			TBankStruct bankInfo = new TBankStruct();
			bankInfo.uid = "2464978132";
			bankInfo.bankId = 20000098681803L;
			bankInfo.payerName = "测试更新银行卡";
			bankInfo.idCard = "431223198802142213";
			bankInfo.bankName = "招商银行";
			bankInfo.bankCard = "6225887832697508";
			bankInfo.bankAddr = "测试地址";

			TCommonResultStruct Result = client.updateBankInfo(bankInfo);
			JSONObject jsonObj = JSONObject.fromObject(Result);
			String message = (String) jsonObj.get("message");
	        System.out.println("ResultStruct3" + new Gson().toJson(Result));
	        Assert.assertEquals("银行账号已经存在!", message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			client.getInputProtocol().getTransport().close();

		}

	}*/
}
