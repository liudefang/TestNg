
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
import com.jfz.erp.api.thrift.struct.TAddressStruct;
import com.jfz.erp.api.thrift.struct.TBankStruct;
import com.jfz.erp.api.thrift.struct.TCommonResultStruct;

import net.sf.json.JSONObject;

public class ThriftContractAddressTest {
	/*
	 * 新增地址成功
	 */
	@Test
	public void addAddress() {
		ThriftContractService.Client client = null;

		try {

			TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);

			client = new ThriftContractService.Client(protocol);
			TAddressStruct Address = new TAddressStruct();
			Address.uid = "2464978132";
			//Address.addressId = 20000100108688L;
			Address.receiverName = "测试新增默认地址";
			Address.province = "广东省";
			Address.city = "台山市";
			Address.area = "开发区";
			Address.detailAddr = "人民路中路88号默认的地址";
			Address.receiverPhone = "15812345678";
            Address.isDefault = 1;
			TCommonResultStruct Result = client.addAddress(Address);
			JSONObject jsonObj = JSONObject.fromObject(Result);
			String message = (String) jsonObj.get("message");
	        System.out.println("ResultStruct" + new Gson().toJson(Result));
	        Assert.assertEquals("新增客户地址信息成功!", message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			client.getInputProtocol().getTransport().close();

		}

	}
	/*
	 * 更新地址成功
	 */
	/*@Test
	public void updateAddress() {
		ThriftContractService.Client client = null;

		try {

			TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);

			client = new ThriftContractService.Client(protocol);
			TAddressStruct Address = new TAddressStruct();
			Address.uid = "2464978132";
			Address.addressId = 20000100108688L;
			Address.receiverName = "测试更新地址";
			Address.province = "广东省";
			Address.city = "台山市";
			Address.area = "开发区";
			Address.detailAddr = "人民路8号";
			Address.receiverPhone = "15812345678";

			TCommonResultStruct Result = client.updateAddress(Address);
			JSONObject jsonObj = JSONObject.fromObject(Result);
			String message = (String) jsonObj.get("message");
	        System.out.println("ResultStruct3" + new Gson().toJson(Result));
	        Assert.assertEquals("更新客户地址信息成功!", message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			client.getInputProtocol().getTransport().close();

		}

	}*/
}
