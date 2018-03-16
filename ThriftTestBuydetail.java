package com.jfz.erp.online.purchase.thrifttestng;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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
import com.jfz.erp.api.thrift.service.ThriftContractService;
import com.jfz.erp.api.thrift.struct.TAddressStruct;
import com.jfz.erp.api.thrift.struct.TBankStruct;
import com.jfz.erp.api.thrift.struct.TCommonResultStruct;
import com.jfz.erp.api.thrift.struct.TContractResultStruct;
import com.jfz.erp.api.thrift.struct.TContractSignType;
import com.jfz.erp.api.thrift.struct.TContractStruct;
import com.jfz.erp.api.thrift.struct.TFileType;
import com.jfz.erp.api.thrift.struct.TRiskEvaluate;
import com.jfz.erp.api.thrift.struct.TUploadFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ThriftTestBuydetail {
	 /*
	 * 添加合同成功
	 */
	static String IP = "121.40.65.64"; //预发环境的IP地址
	//static String IP = "10.1.2.211";    //测试环境的IP地址
	//static String IP = "10.1.2.88";    //性能测试环境的IP地址
 /* @Test
  public static void generateContractadd()
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
             /*addStruct.uid = "2842544171";     //预发测试环境的uid
              addStruct.prdJfzId = "P61pupidcd";   //预发测试环境的prdid
              addStruct.prdName = "测试开放日净值同步持仓";    //预发测试环境的产品名称*/
            /*  addStruct.uid = "7736803641";      
              addStruct.prdJfzId = "P61po3czd1";
              addStruct.prdName = "私募产品1768003";
              addStruct.money = 100;
              addStruct.signType = TContractSignType.AssetProofSign;
              TRiskEvaluate Risk = new TRiskEvaluate();
              Risk.uid = "7736803641";
              //Risk.riskId = 220000102333411L;
             
              Risk.riskLevel = "1";
              Risk.riskGrade = 16;
              Risk.evaluteTime = DateUtil.getStringNowTime();
              addStruct.applyTime = DateUtil.getStringNowTime();
              addStruct.realCertifyCard = "43122319840326221X";
              addStruct.realCertifyName = "刘德芳";
              TBankStruct bank = new TBankStruct();
              bank.uid = "7736803641";
              bank.bankId = "20000006483553";
              bank.payerName = "刘德芳";
              bank.idCard = "43122319840326221X";
              bank.bankName = "工商银行";
              bank.bankCard = "6212262011018464458";
              addStruct.bankInfo = bank;
              addStruct.riskEvalute = Risk;
              TContractResultStruct flag = client.signContract(addStruct);
              JSONObject jsonObj = JSONObject.fromObject(flag);
              String message = (String) jsonObj.get("message");
              String contractCode = (String) jsonObj.get("contractCode");
              System.out.println("ResultStruct7" + new Gson().toJson(flag));
              Assert.assertEquals("新增合同成功!", message);
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
	
	/*
	 * 更新合同成功，为线下，生成payer_id
	 */
 @Test
  public static void generateContractupdate()
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
             addStruct.contractCode = "HT20170707006";
             addStruct.uid = "7736803641";      
             addStruct.prdJfzId = "P61po3czd1";
             addStruct.prdName = "私募产品1768003";
             addStruct.money = 100;
             addStruct.signType = TContractSignType.OfflineSign;
             TRiskEvaluate Risk = new TRiskEvaluate();
             Risk.uid = "7736803641";
             //Risk.riskId = 220000102333411L;
            
             Risk.riskLevel = "1";
             Risk.riskGrade = 16;
             Risk.evaluteTime = DateUtil.getStringNowTime();
             addStruct.applyTime = DateUtil.getStringNowTime();
             addStruct.realCertifyCard = "43122319840326221X";
             addStruct.realCertifyName = "刘德芳";
             TBankStruct bank = new TBankStruct();
             bank.uid = "7736803641";
             bank.bankId = "20000006483553";
             bank.payerName = "刘德芳";
             bank.idCard = "43122319840326221X";
             bank.bankName = "工商银行";
             bank.bankCard = "6212262011018464458";
             addStruct.bankInfo = bank;
             addStruct.riskEvalute = Risk;
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
 
  @Test
  //上传资产证明
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
            uploadFile.contractCode = "HT20170707006";
            uploadFile.uid = "7736803641";
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
          //System.out.println("result" +result);
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
  
  @Test
  //上传风险揭示书
  public void RiskStatement() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/不是干货我叫你爸爸.pdf");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170707006";
            uploadFile.uid = "7736803641";
            uploadFile.fileName = "不是干货我叫你爸爸.pdf";
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
  @Test
  //上传风险测评书
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
            uploadFile.contractCode = "HT20170707006";
            uploadFile.uid = "7736803641";
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
  
  @Test
  //上传打款凭条
public void payReceipt() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket(IP, 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/打款凭条.jpg");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170707006";
            uploadFile.uid = "7736803641";
            uploadFile.fileName = "打款凭条.jpg";
            uploadFile.fileType = TFileType.PayReceipt;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
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
 
 /*@Test
 //上传身份证
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
           uploadFile.contractCode = "HT20170615006";
           uploadFile.uid = "7736803641";
           uploadFile.fileName = "身份证.jpg";
           uploadFile.fileType = TFileType.IdCard;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书，IdCard：身份证
           uploadFile.fileSize = file.length();
           ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
           uploadFile.inputStream = buffer;
           List<TUploadFile> files = new ArrayList<TUploadFile>();
           files.add(uploadFile);
           List<TCommonResultStruct> result = client.addContractFiles(files);
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
	
@Test
	//上传银行卡
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
	           uploadFile.contractCode = "HT20170615006";
	           uploadFile.uid = "7736803641";
	           uploadFile.fileName = "合同签字页.jpg";
	           uploadFile.fileType = TFileType.Contract;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书，IdCard：身份证
	           uploadFile.fileSize = file.length();
	           ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
	           uploadFile.inputStream = buffer;
	           List<TUploadFile> files = new ArrayList<TUploadFile>();
	           files.add(uploadFile);
	           List<TCommonResultStruct> result = client.addContractFiles(files);
	           System.out.println("Contract" + new Gson().toJson(result));
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

@Test
//上传身份证
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
           uploadFile.contractCode = "HT20170615006";
           uploadFile.uid = "7736803641";
           uploadFile.fileName = "银行卡.jpg";
           uploadFile.fileType = TFileType.BankCard;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书，IdCard：身份证
           uploadFile.fileSize = file.length();
           ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
           uploadFile.inputStream = buffer;
           List<TUploadFile> files = new ArrayList<TUploadFile>();
           files.add(uploadFile);
           List<TCommonResultStruct> result = client.addContractFiles(files);
           System.out.println("BankCard" + new Gson().toJson(result));
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
 }*/
}
