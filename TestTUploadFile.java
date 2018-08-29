package com.test.erp.online.purchase.thrifttestng;

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
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.test.erp.api.thrift.service.ThriftContractService;
import com.test.erp.api.thrift.struct.TCommonResultStruct;
import com.test.erp.api.thrift.struct.TContractStruct;
import com.test.erp.api.thrift.struct.TFileType;
import com.test.erp.api.thrift.struct.TUploadFile;

public class TestTUploadFile {
 /*@Test
  //上传资产证明
  public void PayReceipt() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/2.jpg");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170610007";
            uploadFile.uid = "2464978132";
            uploadFile.fileName = "2.jpg";
            uploadFile.fileType = TFileType.AssetProof;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
            uploadFile.fileSize = file.length();
            ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
            uploadFile.inputStream = buffer;
            List<TUploadFile> files = new ArrayList<TUploadFile>();
            files.add(uploadFile);
            List<TCommonResultStruct> result = client.addContractFiles(files);
            System.out.println("PayReceipt" + new Gson().toJson(result));
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
  /*
  @Test
  //上传风险揭示书
  public void RiskStatement() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/3.jpg");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170610011";
            uploadFile.uid = "2464978132";
            uploadFile.fileName = "3.jpg";
            uploadFile.fileType = TFileType.RiskStatement;    // BankCard,银行卡 PayReceipt,打款凭条,Contract, 合同(签字后的合同), RiskStatement,风险揭示书,AssetProof,资产证明,RiskEvaluate,风险测评书
            uploadFile.fileSize = file.length();
            ByteBuffer buffer = ByteBuffer.wrap(swapStream.toByteArray());
            uploadFile.inputStream = buffer;
            List<TUploadFile> files = new ArrayList<TUploadFile>();
            files.add(uploadFile);
            List<TCommonResultStruct> result = client.addContractFiles(files);
            System.out.println("RiskStatement" + new Gson().toJson(files));
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
  
 /* @Test
  //上传打款凭条
  public void payReceipt() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/3.jpg");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170610007";
            uploadFile.uid = "2464978132";
            uploadFile.fileName = "3.jpg";
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
  }*/
  
  @Test
  //上传风险测评书
public void RiskEvaluate() {
	  ThriftContractService.Client client = null;
	    try
	    {
	        TTransport transport = new TFramedTransport(new TSocket("10.1.2.211", 33201, 20000));
            transport.open();
            TProtocol protocol = new TCompactProtocol(transport);
            client = new ThriftContractService.Client(protocol);
            
            File file = new File("D:/图片/3.jpg");
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buff, 0, 1024)) > 0)
            {
                swapStream.write(buff, 0, rc);
            }
            TUploadFile uploadFile = new TUploadFile();
            uploadFile.contractCode = "HT20170610009";
            uploadFile.uid = "2464978132";
            uploadFile.fileName = "3.jpg";
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
}
