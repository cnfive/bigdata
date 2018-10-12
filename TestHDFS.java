import java.io.IOException;
import java.net.URI;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
//
//import org.junit.Test; 

//import looly.github.hutool.FileUtil;
//import com.xiaoleilu.hutool.util.FileUtil;
//import org.mozilla.universalchardet.UniversalDetector;
//import org.apache.commons.httpclient.*; 
//import org.apache.commons.httpclient.methods.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URLDecoder;  
import java.net.URLEncoder;



//import org.apache.commons.
import javax.net.ssl.SSLContext;  

import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;  
import org.apache.http.ParseException;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;  
import org.apache.http.conn.ssl.SSLContexts;  
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;  
import org.apache.http.entity.ContentType;  
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.entity.mime.content.FileBody;  
import org.apache.http.entity.mime.content.StringBody;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
//import org.junit.Test;  

 import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


 
public class   TestHDFS {
	
	
    //initialization
    static Configuration conf = new Configuration();
    static FileSystem hdfs;
        static {
            String path = "";              //     /usr/java/hadoop-1.0.3/conf/";
          conf.addResource(new Path(path + "core-site.xml"));
           conf.addResource(new Path(path + "hdfs-site.xml"));
  //      conf.addResource(new Path(path + "mapred-site.xml"));
 //       path = "/usr/java/hbase-0.90.3/conf/";
  //     conf.addResource(new Path(path + "hbase-site.xml"));
        try {
           hdfs = FileSystem.get(conf);
   //       hdfs= FileSystem.get(URI.create(file), conf);  
         } catch (IOException e) {
              e.printStackTrace();
            }
        }
     
    //create a direction
    public void createDir(String dir) throws IOException {
        Path path = new Path(dir);
        hdfs.mkdirs(path);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }  
     
    //copy from local file to HDFS file
    public void copyFile(String localSrc, String hdfsDst) throws IOException{
        Path src = new Path(localSrc);     
        Path dst = new Path(hdfsDst);
        hdfs.copyFromLocalFile(src, dst);
         
        //list all the files in the current direction
        FileStatus files[] = hdfs.listStatus(dst);
        System.out.println("Upload to \t" + conf.get("fs.default.name") + hdfsDst);
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }
     
    //create a new file
    public void createFile(String fileName, String fileContent) throws IOException {
        Path dst = new Path(fileName);
        byte[] bytes = fileContent.getBytes();
        FSDataOutputStream output = hdfs.create(dst);
        output.write(bytes);
        System.out.println("new file \t" + conf.get("fs.default.name") + fileName);
    }
     
    //list all files
    public void listFiles(String dirName) throws IOException {
        Path f = new Path(dirName);
        FileStatus[] status = hdfs.listStatus(f);
        System.out.println(dirName + " has all files:");
        for (int i = 0; i< status.length; i++) {
            System.out.println(status[i].getPath().toString());
        }
    }
 
    //judge a file existed? and delete it!
    public void deleteFile(String fileName) throws IOException {
        Path f = new Path(fileName);
        boolean isExists = hdfs.exists(f);
        if (isExists) { //if exists, delete
            boolean isDel = hdfs.delete(f,true);
            System.out.println(fileName + "  delete? \t" + isDel);
        } else {
            System.out.println(fileName + "  exist? \t" + isExists);
        }
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
 
 private static void print(String msg, Object... args) {
     System.out.println(String.format(msg, args));
 }

 private static String trim(String s, int width) {
     if (s.length() > width)
         return s.substring(0, width-1) + ".";
     else
         return s;
 }
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	
        TestHDFS ofs = new TestHDFS();
        System.out.println("\n=======create dir=======");
        String dir = "/ifengcom";
     //   ofs.gethtml("http://www.ifeng.com");
        ofs.createDir(dir);

        
        Spider sp=new Spider();
        sp.gethtml("http://www.ifeng.com");
		String encoding=getEncoding(sp.gethtmlbody());
		
		System.out.print(encoding);
		String body=  new String(sp.gethtmlbody().getBytes(encoding),"utf8");
	//	System.out.print(body);
		
		Document doc=Jsoup.parse(body);
		Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        
        
        String title = doc.title();
        System.out.println("---------------"+title+"-----------------");
		
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }   

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            
             sp.gethtml(link.attr("abs:href"));
            
            String encoding2=getEncoding(sp.gethtmlbody());
    		
    		System.out.print(encoding2);
    		String body2=  new String(sp.gethtmlbody().getBytes(encoding2),"utf8");
    		
    	
    		
    		Date dt = new Date(); 
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss"); 
    		String filename=sdf.format(dt)+".htm";
            
    		 System.out.println("\n=======create a file=======");
    	  //      String fileContent = "Hello, world! Just a test.";
    	        ofs.createFile(dir+"/"+filename, body2);
            
            Thread.sleep(5000);
        }
        
        
        
        
        
      //  System.out.println("\n=======create dir=======");
 //       String dir = "/test1999";
       
    //    System.out.println("\n=======copy file=======");
 //       String src = "/media/hadoop/文档/x.txt";
   //     ofs.copyFile(src, dir);
       
    }
}