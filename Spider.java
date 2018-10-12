//package spider;
//import java.io.File;  
//import java.io.FileInputStream;  
import java.io.IOException;  
//import java.io.UnsupportedEncodingException;  
//
//import org.junit.Test; 

//import looly.github.hutool.FileUtil;
//import com.xiaoleilu.hutool.util.FileUtil;
//import org.mozilla.universalchardet.UniversalDetector;
//import org.apache.commons.httpclient.*; 
//import org.apache.commons.httpclient.methods.*;





//import org.apache.commons.
//import javax.net.ssl.SSLContext;  

import org.apache.http.HttpEntity;  
//import org.apache.http.NameValuePair;  
import org.apache.http.ParseException;  
import org.apache.http.client.ClientProtocolException;  
//import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;

import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
//import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
//import org.junit.Test;  

 import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class    Spider {

	/**
	 * @param args
	 */
	
private   String  htmlbody;

public String gethtmlbody()
{
	
	return this.htmlbody;
}
    public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   }      
    

    
	
    public   void   gethtml(String url) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
       // CloseableHttpClient httpClient = HttpClients.custom().setHostnameVerifier(AllowAllHostnameVerifier..INSTANCE).build();
      
    //	CloseableHttpClient httpclient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
    	try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(url);  
            System.out.println("executing request " + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
            //    System.out.println("--------------------------------------");  
                // 打印响应状态    
             //   System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    // 打印响应内容长度    
                //    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
                 //   System.out.println("Response content: " + EntityUtils.toString(entity));  
                    
                //      return EntityUtils.toString(entity);
                     htmlbody=EntityUtils.toString(entity);
                }  
              //  System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	

    
    
    
    
	
}
