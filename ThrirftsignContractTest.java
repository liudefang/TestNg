package cn.erp.testng.testScript;

import cn.erp.testng.configuration.Constants;
import cn.erp.testng.configuration.SignContract;
import cn.erp.testng.util.ExcelUtil;
import cn.erp.testng.util.Log;
import com.google.gson.Gson;
import com.hotent.core.util.DateUtil;
import com.test.erp.api.thrift.service.ThriftContractService;
import com.test.erp.api.thrift.struct.*;
import net.sf.json.JSONObject;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;


public class ThrirftsignContractTest {
	
	 /*
		 * 添加合同成功
		 */
		//static String IP = "121.40.65.64"; //预发环境的IP地址
		static String IP = "10.1.2.211";    //测试环境的IP地址
		//static String IP = "10.1.2.88";    //性能测试环境的IP地址

		//static String uid = "7736803641";  //预发环境的uid地址

		static String  contractCode = null;


	//@Test(groups = {"合同管理"})
	  @Test (priority = 1)
	  public   void   generateContractadd()
	  {
	      ThriftContractService.Client client = null;
	      
	      try
	      {
	          TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
	          transport.open();
	          TProtocol protocol = new TCompactProtocol(transport);
	          client = new ThriftContractService.Client(protocol);

			  //定义Excel关键文件的路径
			  String excelFilePath = Constants.Path_ExcelFile;
			  //设定读取Excel文件中的“提交合同”Sheet为操作目标
			  ExcelUtil.setExcelFile( excelFilePath );
			  // 创建一个dataformat对象
			  SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Date date = new Date();//获取当前时间
			  Calendar calendar = Calendar.getInstance();
			  calendar.add(Calendar.MONTH, -1);
			  date = calendar.getTime();
			  String date1 = dataFormat.format(date) ;
			  System.out.println(date1);


			  {
	              TContractStruct addStruct = new TContractStruct();
	             /*addStruct.uid = "2842544171";     //预发测试环境的uid
	              addStruct.prdtestId = "P61pupidcd";   //预发测试环境的prdid
	              addStruct.prdName = "测试开放日净值同步持仓";    //预发测试环境的产品名称*/

				  //在日志文件中打印关键字信息
				  Log.info("从Excel文件读取到的关键字是:"+SignContract.keyword);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.uid);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.prdtestId);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.prdName);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.riskLevel);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.realCertifyCard);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.realCertifyName);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.bankId);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.payerName);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.idCard);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.bankName);
				  Log.info("从Excel文件中读取的操作值是："+SignContract.bankCard);

				  addStruct.uid = ""+SignContract.uid+"";
	              addStruct.prdtestId = ""+SignContract.prdtestId+"";
	              addStruct.prdName = ""+SignContract.prdName+"";
	              //addStruct.prdtestId = "P61c7iwcge";
	              //addStruct.prdName = "ERP-DC没有持仓";
	              addStruct.money = 200;
	              addStruct.signType = TContractSignType.AssetProofSign;
	              TRiskEvaluate Risk = new TRiskEvaluate();
	              Risk.uid = ""+SignContract.uid+"";
	              Risk.riskId = "20000145586156";

				  Risk.riskLevel = ""+SignContract.riskLevel+"";
				  Risk.riskGrade = 90;
				  Risk.evaluteTime = ""+date1+"";
	              addStruct.applyTime = DateUtil.getStringNowTime();
	              addStruct.realCertifyCard = ""+SignContract.realCertifyCard+"";
	              addStruct.realCertifyName = ""+SignContract.realCertifyName+"";
	              TBankStruct bank = new TBankStruct();
	              bank.uid = ""+SignContract.uid+"";
	              bank.bankId = ""+SignContract.bankId+"";
	              bank.payerName = ""+SignContract.payerName+"";
	              bank.idCard = ""+SignContract.idCard+"";
	              bank.bankName = ""+SignContract.bankName+"";
	              bank.bankCard = ""+SignContract.bankCard+"";
	              addStruct.bankInfo = bank;
	              addStruct.riskEvalute = Risk;
	              TContractResultStruct flag = client.signContract(addStruct);
	              JSONObject jsonObj = JSONObject.fromObject(flag);
	              String message = (String) jsonObj.get("message");
	              contractCode = (String) jsonObj.get("contractCode");
	              System.out.println("ResultStruct" + new Gson().toJson(flag));
				  System.out.println("Respst" + addStruct);
	              Assert.assertEquals("新增合同成功!", message);
	              System.out.println("data:" + DateUtil.getStringNowTime());


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
	  //更新合同
	  @Test (priority = 2)
	  //@Test(groups = {"合同管理"})
	  public  void generateContractupdate()
	  {
	      ThriftContractService.Client client = null;
	      try
	      {
	          TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
	          transport.open();
	          TProtocol protocol = new TCompactProtocol(transport);
	          client = new ThriftContractService.Client(protocol);
	        
	          {
	              TContractStruct addStruct = new TContractStruct();
	             addStruct.contractCode = contractCode;
	             addStruct.uid = ""+SignContract.uid+"";
	             addStruct.prdtestId = ""+SignContract.prdtestId+"";
	             addStruct.prdName = ""+SignContract.prdName+"";
	             addStruct.money = 300;
	             addStruct.signType = TContractSignType.OnlineSign;
	            /* TRiskEvaluate Risk = new TRiskEvaluate();
	             Risk.uid = ""+SignContract.uid+"";
	             //Risk.riskId = 220000102333411L;
	            
	             Risk.riskLevel = ""+SignContract.riskLevel+"";
	             Risk.riskGrade = 86;
	             Risk.evaluteTime = DateUtil.getStringNowTime();*/
	             addStruct.applyTime = DateUtil.getStringNowTime();
				  addStruct.realCertifyCard = ""+SignContract.realCertifyCard+"";
				  addStruct.realCertifyName = ""+SignContract.realCertifyName+"";
	             TBankStruct bank = new TBankStruct();
				  bank.uid = ""+SignContract.uid+"";
				  bank.bankId = ""+SignContract.bankId+"";
				  bank.payerName = ""+SignContract.payerName+"";
				  bank.idCard = ""+SignContract.idCard+"";
				  bank.bankName = ""+SignContract.bankName+"";
				  bank.bankCard = ""+SignContract.bankCard+"";
	             addStruct.bankInfo = bank;
	             //addStruct.riskEvalute = Risk;
	             addStruct.applyTime = DateUtil.getStringNowTime();
	             TContractResultStruct flag = client.signContract(addStruct);
	             JSONObject jsonObj = JSONObject.fromObject(flag);
	             String message = (String) jsonObj.get("message");
	             System.out.println("ResultStruct7" + new Gson().toJson(flag));
	             Assert.assertEquals("更新合同成功!", message);
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


	//上传资产证明
	 @Test (priority = 3)
	  //@Test(groups = {"附件管理"})
	  public void AssetProof() {
		  ThriftContractService.Client client = null;
		    try
		    {
		        TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
	            transport.open();
	            TProtocol protocol = new TCompactProtocol(transport);
	            client = new ThriftContractService.Client(protocol);
	            
	            File file = new File("D:/图片/资产证明.jpg");
	            InputStream is = new FileInputStream(file);
	            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	            byte[] buff = new byte[1024];
	            int rc = 0;
	            while ((rc = is.read(buff, 0, 1024)) > 0)
	            {
	                swapStream.write(buff, 0, rc);
	            }
	             
	            TUploadFile uploadFile = new TUploadFile();
	            uploadFile.contractCode = contractCode;
	            uploadFile.uid = ""+SignContract.uid+"";
	            uploadFile.fileName = "资产证明.jpg";
	            uploadFile.fileType = TFileType.AssetProof;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
	            uploadFile.fileSize = file.length();
	            ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
	            uploadFile.inputStream = buffer;
	            List<TUploadFile> files = new ArrayList<TUploadFile>();
	            files.add(uploadFile);
	            List<TCommonResultStruct> result = client.addContractFiles(files);
	             
	            JSONArray getJsonArray=JSONArray.fromObject(result);
	            //JSONArray getJsonArray1=JSONArray.fromObject(getJsonArray);
	            //JSONObject jUser = getJsonArray1.getJSONObject(0).getJSONObject("message");  
	            System.out.println("AssetProof" + new Gson().toJson(result));
	           // String message = (String) getJsonArray.get("message");
	          System.out.println("getJsonArray" +getJsonArray);
	          System.out.println("uploadFile" +uploadFile);
	           // System.out.println("getJsonArray1" +getJsonArray1);
	            swapStream.close();
	            is.close();
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

	//上传风险揭示书
	  @Test (priority = 4)
	  //@Test(groups = {"附件管理"})
	  
	  public void RiskStatement() {
		  ThriftContractService.Client client = null;
		    try
		    {
		        TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
	            transport.open();
	            TProtocol protocol = new TCompactProtocol(transport);
	            client = new ThriftContractService.Client(protocol);
	            
	            File file = new File("D:/图片/风险揭示书.jpg");
	            InputStream is = new FileInputStream(file);
	            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	            byte[] buff = new byte[1024];
	            int rc = 0;
	            while ((rc = is.read(buff, 0, 1024)) > 0)
	            {
	                swapStream.write(buff, 0, rc);
	            }
	            TUploadFile uploadFile = new TUploadFile();
	            uploadFile.contractCode = contractCode;
	            uploadFile.uid = ""+SignContract.uid+"";
	            uploadFile.fileName = "风险揭示书.jpg";
	            uploadFile.fileType = TFileType.RiskStatement;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
	            uploadFile.fileSize = file.length();
	            ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
	            uploadFile.inputStream = buffer;
	            List<TUploadFile> files = new ArrayList<TUploadFile>();
	            files.add(uploadFile);
	            List<TCommonResultStruct> result = client.addContractFiles(files);
	            System.out.println("RiskStatement" + new Gson().toJson(result));
	            swapStream.close();
	            is.close();
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

     @Test(priority = 5)
	//上传合同签字页
	public void Contract() {
		ThriftContractService.Client client = null;
		try
		{
			TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);
			client = new ThriftContractService.Client(protocol);

			File file = new File("D:/图片/合同签字页.jpg");
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int rc = 0;
			while ((rc = is.read(buff, 0, 1024)) > 0)
			{
				swapStream.write(buff, 0, rc);
			}
			TUploadFile uploadFile = new TUploadFile();
			uploadFile.contractCode = contractCode;
			uploadFile.uid = ""+SignContract.uid+"";
			uploadFile.fileName = "合同签字页.jpg";
			uploadFile.fileType = TFileType.Contract;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
			uploadFile.fileSize = file.length();
			ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
			uploadFile.inputStream = buffer;
			List<TUploadFile> files = new ArrayList<TUploadFile>();
			files.add(uploadFile);
			List<TCommonResultStruct> result = client.addContractFiles(files);
			System.out.println("payReceipt" + new Gson().toJson(result));
			swapStream.close();
			is.close();

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
	  
	//上传风险测评书
	@Test (priority = 6)
	 // @Test(groups = {"附件管理"})
	  
	public void RiskEvaluate() {
		  ThriftContractService.Client client = null;
		    try
		    {
		        TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
	            transport.open();
	            TProtocol protocol = new TCompactProtocol(transport);
	            client = new ThriftContractService.Client(protocol);
	            
	            File file = new File("D:/图片/风险测评.jpg");
	            InputStream is = new FileInputStream(file);
	            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	            byte[] buff = new byte[1024];
	            int rc = 0;
	            while ((rc = is.read(buff, 0, 1024)) > 0)
	            {
	                swapStream.write(buff, 0, rc);
	            }
	            TUploadFile uploadFile = new TUploadFile();
	            uploadFile.contractCode = contractCode;
	            uploadFile.uid = ""+SignContract.uid+"";
	            uploadFile.fileName = "风险测评.jpg";
	            uploadFile.fileType = TFileType.RiskEvaluate;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
	            uploadFile.fileSize = file.length();
	            ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
	            uploadFile.inputStream = buffer;
	            List<TUploadFile> files = new ArrayList<TUploadFile>();
	            files.add(uploadFile);
	            List<TCommonResultStruct> result = client.addContractFiles(files);
	            System.out.println("RiskEvaluate" + new Gson().toJson(result));
	            swapStream.close();
	            is.close();
	            
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


	//上传身份证附件
	@Test (priority = 7)
	//@Test(groups = {"附件管理"})

	public void IdCard() {
		ThriftContractService.Client client = null;
		try
		{
			TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);
			client = new ThriftContractService.Client(protocol);

			File file = new File("D:/图片/身份证.jpg");
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int rc = 0;
			while ((rc = is.read(buff, 0, 1024)) > 0)
			{
				swapStream.write(buff, 0, rc);
			}
			TUploadFile uploadFile = new TUploadFile();
			uploadFile.contractCode = contractCode;
			uploadFile.uid = ""+SignContract.uid+"";
			uploadFile.fileName = "身份证.jpg";
			uploadFile.fileType = TFileType.IdCard;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
			uploadFile.fileSize = file.length();
			ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
			uploadFile.inputStream = buffer;
			List<TUploadFile> files = new ArrayList<TUploadFile>();
			files.add(uploadFile);
			List<TCommonResultStruct> result = client.addContractFiles(files);
			System.out.println("files：" + files);
			System.out.println("IdCard" + new Gson().toJson(result));
			swapStream.close();
			is.close();
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
	//上传身份证附件
	@Test (priority = 8)
	//@Test(groups = {"附件管理"})

	public void BankCard() {
		ThriftContractService.Client client = null;
		try
		{
			TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);
			client = new ThriftContractService.Client(protocol);

			File file = new File("D:/图片/银行卡.jpg");
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int rc = 0;
			while ((rc = is.read(buff, 0, 1024)) > 0)
			{
				swapStream.write(buff, 0, rc);
			}
			TUploadFile uploadFile = new TUploadFile();
			uploadFile.contractCode = contractCode;
			uploadFile.uid = ""+SignContract.uid+"";
			uploadFile.fileName = "银行卡.jpg";
			uploadFile.fileType = TFileType.BankCard;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
			uploadFile.fileSize = file.length();
			ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
			uploadFile.inputStream = buffer;
			List<TUploadFile> files = new ArrayList<TUploadFile>();
			files.add(uploadFile);
			List<TCommonResultStruct> result = client.addContractFiles(files);
			System.out.println("BankCard：" + new Gson().toJson(result));
			swapStream.close();
			is.close();
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

