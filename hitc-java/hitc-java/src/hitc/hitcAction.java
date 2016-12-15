package hitc;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.Action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

public class hitcAction {

		public String gotoPage1()
	    {
			String asd = "";
	        ServletActionContext.getRequest()  
	            .setAttribute("AccessBy1","<div>sjl</div>");
	        return Action.SUCCESS;  
	    }
	    
		private String userName ;
	    private String passWord ;
	    private String qqId ;
	    private String content;
	    private String topic;
	    private String edition;
	    private String addfriend;
	    
	    //String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url
	    String url = "jdbc:mysql://mkkqryfbfihy.mysql.sae.sina.com.cn:10597/hit_c";
	    //必须的setter和getter方法 
	    
	    public String getUserName(){
			return userName;
	    }
	    public void setUserName(String userName){
	    	this.userName = userName;
	    }
	    
	    public String getPassWord(){
			return passWord;
	    }
	    public void setPassWord(String passWord){
	    	this.passWord = passWord;
	    }
	    
	    public String getQqId(){
	    	return qqId;
	    }
	    public void setQqId(String qqId){
	    	this.qqId = qqId;
	    }
	    
	    public String getContent(){
			return content;
	    }
	    public void setContent(String content){
	    	this.content = content;
	    }
	    
	    public String getTopic(){
			return topic;
	    }
	    public void setTopic(String topic){
	    	this.topic = topic;
	    }
	    
	    public String getEdition(){
			return edition;
	    }
	   public void setEdition(String edition){
	   	this.edition = edition;
	   }
	    
	    public String getAddfriend(){
			return addfriend;
	    }
	    public void setAddfriend(String addfriend){
	    	this.addfriend = addfriend;
	    }
	    //业务逻辑
	    
	    private String resultEdition(String userName){
	    	String edition ="";
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql= "select * from edition order by name asc" ;
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				while(res.next()){//set不空
					String edit = res.getString("name");
					String edit_id = res.getString("id");
					String temp = "<li><a href=\"edition.action?edition=" + edit_id + "&userName=" + userName +"\">"+edit+"</a></li>";
					edition = edition + temp;
				} 
				edition = "<a href=\"#\"><i class=\"icon-time\"></i>圈子</a><ul class=\"nav\">" + edition + "</ul>" ;	
				conn.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return edition;
	    }
	    
	    public String fillName(){
	    	
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	if(getName.split("_").length==1){
	    	} else {
	    		userName = getName.split("_")[0];
	    	}
	    	ServletActionContext.getRequest()  
        	.setAttribute("Ccbt",userName);
	    	ServletActionContext.getRequest()  
        	.setAttribute("change","最新动态");
	    	String edition = resultEdition(userName);	
			ServletActionContext.getRequest()
			.setAttribute("edition",edition);
			return Action.SUCCESS;
	    }
	    
	    public String zc()
	    {
	    	char[] temp = userName.toCharArray();
	    	
	    	for(int i=0;i<temp.length;i++){
	    		if(temp[i]<48 || (temp[i]>59 && temp[i]<65) || (temp[i]>90 && temp[i]<97) || temp[i]>122){
	    			return Action.ERROR;
	    		}
	    	}
	    	try {
				// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				
				
				System.out.println(userName);

				String selectsql = "select hitc_id from users where hitc_name='"+ userName+"'";
				// 2、生成一条sql语句
				// 生成一条mysql语句
				String sql;
				if(!qqId.equals("")){
					System.out.println(qqId);
					sql = "insert into users(hitc_name,password,qq) values('" +userName +"','"+passWord + "','"+qqId+"')";
				} else {
					sql = "insert into users(hitc_name,password) values('" +userName +"','"+passWord + "')";
				}
				

				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				if(res.next()){//set不空，即hitc_name已存在
					
					conn.close();
					System.out.println("用户名已存在");
					return Action.ERROR;
				} else {
					stmt.executeUpdate(sql);// 执行sql语句
					System.out.println("插入到数据库成功");
					conn.close();
					System.out.println("关闭数据库成功");
					return Action.SUCCESS;
				}
				// 4、关闭数据库连接
			
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.ERROR;
	    	
	    }
	    
	    public String login()
	    {
	    	//System.out.println(userName);
	    	//System.out.println(passWord);
	    	try {
	    	// 1、建立数据库连接
			Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			// 2、生成一条sql语句
			String selectsql = "select password from users where hitc_name='"+ userName+"'";
			
			// 3、执行sql语句
			Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
			ResultSet res = stmt.executeQuery(selectsql);
			if(res.next()){//set不空，即hitc_name已存在
				String psd = res.getString("password");
				if(psd.equals(passWord)){
					System.out.println(userName);
					System.out.println(psd);
					return Action.SUCCESS;
				}
				conn.close();
			} else {
				conn.close();
				System.out.println("关闭数据库成功");
				return Action.ERROR;
			}
			// 4、关闭数据库连接
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return Action.ERROR;
	}  
	    
	    public String resultArtical() throws UnsupportedEncodingException{
	    	//System.out.println("Title is "+title);
	    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	//request.setCharacterEncoding("utf-8");
	    	String getName=(String) request.getParameter("title");
	    	
	    	//getName = new String (getName.getBytes("gbk"),"utf-8");
	    	//getName = new String (getName.getBytes("gbk"),"iso8859-1");
	    	//getName = new String (getName.getBytes("iso8859-1"),"gbk");
	    	//getName = new String (getName.getBytes("iso8859-1"),"utf-8");
	    	//getName = new String (getName.getBytes("utf-8"),"gbk");
	    	//getName = new String (getName.getBytes("utf-8"),"iso8859-1");
	    	//getName = new String (getName.getBytes("unicode"),"gbk");
	    	//getName = new String (getName.getBytes("utf-8"),"gbk");
	    	
	    	//String defaultCharsetName=Charset.defaultCharset().displayName();   
	       // System.out.println("defaultCharsetName:"+defaultCharsetName); 
	    	
	    	System.out.println(getName);
	    	
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	String[] tempList = getName.split("_");
	    	
	    	String hitcName = tempList[0];
	    	
	    	String classify = tempList[1].substring(0,8);
	    	System.out.println(classify);
	    	
	    	String tit = tempList[1].substring(8);
	    	System.out.println(tit);
	    	
	    	String cont = null;
	    	String hot = null;
	    	String branch = null;
	    	String title = null;
	    	try {

				// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				String selectsql;
				if(classify.equals("[topic ]")){
		    		selectsql = "select * from catalog where id='"+ tit +"'";
		    	} else if(classify.equals("[ news ]")){
		    		selectsql = "select * from news where id='"+ tit +"'";
		    	} else {
		    		selectsql = "select * from person where id='" + tit + "'";
		    	}
		    	
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
		    	
				if(res.next()){//set不空
					cont = res.getString("content");
					//hot = res.getString("hot");
					branch = res.getString("xueyuan");
					title = res.getString("title");
					System.out.println(branch);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	ServletActionContext.getRequest()  
        		.setAttribute("Ccbt",hitcName);
	    	ServletActionContext.getRequest()  
        		.setAttribute("title",classify + "[" + branch + "]" + title);
	    	ServletActionContext.getRequest()  
    			.setAttribute("hot",hot);
	    	
	    	ServletActionContext.getRequest()
    			.setAttribute("content",cont);
    		
	    	return Action.SUCCESS;
	    }
	    
	    public String topicHot(){
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(10);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select * from catalog order by hot desc";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				ServletActionContext.getRequest()  
            		.setAttribute("Ccbt",getName);
				
				System.out.println(userName);
				String result = "";
				while(res.next()){//set不空
					String tit = res.getString("title");
					String tit_id = res.getString("id");
					System.out.println(tit);
					tit = "[topic ]" + tit;
					tit_id = "[topic ]" + tit_id;
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"result_" + tit_id+ ".action?title=" + getName + "_" + tit_id + "\">" + tit + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				} 
				String edition = resultEdition(getName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
				
				
				conn.close(); 
				
				
				// 4、关闭数据库连接
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.SUCCESS;
	    }
	    
	    public String topicBranch(){
	    	
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(13);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select * from catalog order by xueyuan asc";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				ServletActionContext.getRequest()  
            		.setAttribute("Ccbt",getName);
				System.out.println(userName);
				String result = "";
				while(res.next()){//set不空
					String tit = res.getString("title");
					String tit_id = res.getString("id");
					System.out.println(tit);
					tit = "[topic ]" + tit;
					tit_id = "[topic ]" + tit_id;
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"result_" + getName + tit_id+ ".action?title=" + getName + "_" + tit_id + "\">" + tit + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				} 
				
				String edition = resultEdition(getName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
				conn.close(); 
				return Action.SUCCESS;
				
				// 4、关闭数据库连接
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.ERROR;
	    }
	    
	    public String newsHot(){
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(9);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select * from news order by hot desc";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				ServletActionContext.getRequest()  
        			.setAttribute("Ccbt",getName);
				
				System.out.println(userName);
				
				String result = "";
				while(res.next()){//set不空
					String tit = res.getString("title");
					String tit_id = res.getString("id");
					System.out.println(tit);
					tit = "[ news ]" + tit;
					tit_id = "[ news ]" + tit_id;
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"result_" + getName + tit_id+ ".action?title=" + getName + "_" + tit_id + "\">" + tit + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				} 
				String edition = resultEdition(getName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
				
				conn.close(); 
				return Action.SUCCESS;
				
				// 4、关闭数据库连接
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.ERROR;
	    }
	    
	    public String newsBranch() throws UnsupportedEncodingException{
	    	
	    	ActionMapping actionMapping = ServletActionContext.getActionMapping();
	    	
	    	String getName = actionMapping.getName();
	    	getName = getName.substring(12);
	    	
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select * from news order by xueyuan asc";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				ServletActionContext.getRequest()  
	        		.setAttribute("Ccbt",getName);
				System.out.println(userName);
				
				
				String result = "";
				while(res.next()){//set不空
					String tit = res.getString("title");
					String tit_id = res.getString("id");
					System.out.println(tit);
					tit = "[ news ]" + tit;
					tit_id = "[ news ]" + tit_id;
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"result_" + getName + tit_id+ ".action?title=" + getName + "_" + tit_id + "\">" + tit + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				} 
				String edition = resultEdition(getName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
				conn.close(); 
				return Action.SUCCESS;
				
				// 4、关闭数据库连接
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.ERROR;
	    }
	    
	    public String people(){
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	userName = (String) request.getParameter("userName");
	    	
	    	System.out.println("-----getName----->>"+userName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select * from person";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				ServletActionContext.getRequest()  
            		.setAttribute("Ccbt",userName);
				
				System.out.println(userName);
				String result = "";
				while(res.next()){//set不空
					String tit = res.getString("title");
					String tit_id = res.getString("id");
					System.out.println(tit);
					tit = "[people]" + tit;
					tit_id = "[people]" + tit_id;
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"result_" + tit_id+ ".action?title=" + userName + "_" + tit_id + "\">" + tit + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				} 
				String edition = resultEdition(userName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
				
				
				conn.close(); 
				
				
				// 4、关闭数据库连接
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.SUCCESS;
	    }
	      
	    public String edition(){
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
	    	String getName=(String) request.getParameter("edition");
	    	userName = (String) request.getParameter("userName");
	    	
	    	System.out.println(getName);
	    	System.out.println(userName);
	    	
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {

				// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				String selectsql;
				
				Statement stmt = (Statement) conn.createStatement();
				selectsql = "select name from edition where id='" + getName +"'";
				
				ResultSet res = stmt.executeQuery(selectsql);
				String editName = null;
				if(res.next()){
					editName = res.getString("name");
				}
		    	
				selectsql = "select * from tie where edition='"+ editName +"'";
		    	
				// 创建一个Statement对象
				res = stmt.executeQuery(selectsql);
		    	String result ="<script>function clit(){document.getElementById(\"jumpto\").click();}</script>";
		    	result += "<h4><a href=\"_addt.action?userName=" + userName + "&edition=" + getName  + "\" id=\"jumpto\"></a></h4>";
	    		result +="<div class=\"item\"><div class=\"item-heading\"><div class=\"col-md-2\"><button class=\"btn btn-block btn-primary\" onclick=\"clit()\"><i class=\"icon icon-plus-sign icon-2x\"></i></button></div></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
		    	String id = "";
		    	if(res.next()){
		    		id = res.getString("id");
		    	
		    		String name = res.getString("name");
		    	
		    		result += "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"tie.action?id=" + id +"&userName=" + userName +"\">" + name + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
		    	}
		    	
				while(res.next()){//set不空
					String name = res.getString("name");
					id = res.getString("id");
					String temp = "<div class=\"item\"><div class=\"item-heading\"><h4><a href=\"tie.action?id=" + id +"&userName=" + userName +"\">" + name + "</a></h4></div><div class=\"item-content\"><div class=\"text\"></div></div></div>";
					result = result + temp;
				}
				
				System.out.println("userName = " + userName);
				System.out.println("result = "+result);
				
				String edition = resultEdition(userName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				ServletActionContext.getRequest()
    			.setAttribute("Ccbt",userName);
				ServletActionContext.getRequest()  
	        	.setAttribute("change",editName);
				conn.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return Action.SUCCESS;
	    }
	    
	    private String returnTie(String id,String userName) throws ClassNotFoundException, SQLException{
	    	// 1、建立数据库连接
			Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			String selectsql;
			
	    	selectsql = "select * from tiezi where id='"+ id +"'order by floor,in_floor " ;
	    	
			Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
			ResultSet res = stmt.executeQuery(selectsql);
			
			String first = "";
			if(res.next()){//一楼，也就是楼主
				
				first = "<div class=\"comment\" id=\"getid\" name=\"" + id + "\"><a href=\"###\" class=\"avatar\"><i class=\"icon-camera-retro icon-2x\"></i></a><div class=\"content\"><div class=\"pull-right text-muted\">" + res.getString("time") + "</div><div><a href=\"person_" + res.getString("author") + ".action?userName=" + userName + "\"><strong>" + res.getString("author") + "</strong></a></div><div class=\"text\">" + res.getString("content") + "</div></div></div>";
				//first = "<h4>" +res.getString("time") +"</br>" +res.getString("author")+"  "+   res.getString("author")+"</br>"+  res.getString("content")+"</h4>";
				
			}
	    	String result = first;
	    	int tempFloor = 0;
	    	int in_floor = 0;
			while(res.next()){//set不空
				
				int floor = res.getInt("floor");
				
				//String temp ="";
				
				if(floor == tempFloor){//层号没变，是楼中楼
					
					in_floor = res.getInt("in_floor");
					
					if(in_floor == 1){//第一个楼中楼，加comments-list
						result += "<div class=\"comments-list\"><div class=\"comment\"><a href=\"###\" class=\"avatar\"><i class=\"icon-camera-retro icon-2x\"></i></a><div class=\"content\"><div class=\"pull-right text-muted\">" + res.getString("time") + "</div><div><a href=\"person_" + res.getString("author") + ".action?userName=" + userName + "\"><strong>" + res.getString("author") + "</strong></a><span class=\"text-muted\">回复</span> <a href=\"person_" + res.getString("to_whom") + ".action?userName="+ userName +"\">" + res.getString("to_whom") + "</a></div><div class=\"text\">" + res.getString("content") + "</div><div class=\"actions\"><a href=\"#replyarea\" onclick=\"init('"+ (floor+1) + "','" + in_floor + "','" + res.getString("author") + "')\">回复</a></div></div></div>";
						//没有comments-list的</div>
					} else {//不是第一个楼中楼
						result += "<div class=\"comment\"><a href=\"###\" class=\"avatar\"><i class=\"icon-camera-retro icon-2x\"></i></a><div class=\"content\"><div class=\"pull-right text-muted\">" + res.getString("time") + "</div><div><a href=\"person_" + res.getString("author") + ".action?userName=" + userName + "\"><strong>" + res.getString("author") + "</strong></a><span class=\"text-muted\">回复</span> <a href=\"person_" + res.getString("to_whom") + ".action?userName=" + userName + "\">" + res.getString("to_whom") + "</a></div><div class=\"text\">" + res.getString("content") + "</div><div class=\"actions\"><a href=\"#replyarea\" onclick=\"init('"+ (floor+1) + "','" + in_floor + "','" + res.getString("author") + "')\">回复</a></div></div></div>";
						
					}
				} else {//层号变了，是层主
					tempFloor = floor;
					if(floor != 1){
						
						if(in_floor == 0){
							result += "</div>";
						} else {
							result += "</div></div>";
						}
					}
					
					in_floor = 0;
					
					result += "<div class=\"comment\"><a href=\"###\" class=\"avatar\"><i class=\"icon-camera-retro icon-2x\"></i></a><div class=\"content\"><div class=\"pull-right text-muted\">" + res.getString("time") + "</div><div><a href=\"person_" + res.getString("author") + ".action?userName=" + userName + "\"><strong>" + res.getString("author") + "</strong></a></div><div class=\"text\">" + res.getString("content") + "</div><div class=\"actions\"><a href=\"#replyarea\" onclick=\"init('"+ (floor+1) + "','" + in_floor + "','" + res.getString("author") + "')\">回复</a></div></div>";
						//没有层主的</div>
				
				}
			}
	    	conn.close();
			return result;
	    	
	    }
	    
	    public String tie() throws SQLException, ClassNotFoundException{	
	    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
	    	String getName=(String) request.getParameter("id");
	    	userName = (String) request.getParameter("userName");
	    		String result = returnTie(getName,userName);
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				String selectsql = "select * from tie where id="+ getName;
				
				Statement stmt = (Statement) conn.createStatement();
				ResultSet res = stmt.executeQuery(selectsql);
				String title = new String();
				if(res.next()){
					title = res.getString("name");
				}
				System.out.println(title);
				
				ServletActionContext.getRequest()
    			.setAttribute("title",title);
				
				System.out.println("userName = " + userName);
				System.out.println("result = "+result);
				
				String edition = resultEdition(userName);
		    	System.out.println(edition);
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
				
				ServletActionContext.getRequest()
    			.setAttribute("result",result);
				ServletActionContext.getRequest()
    			.setAttribute("Ccbt",userName);
				conn.close();
			return Action.SUCCESS;
	    }

	    public String reply() throws ClassNotFoundException, SQLException{
	    	
	    	ActionMapping actionMapping = ServletActionContext.getActionMapping();
	    	
	    	userName = actionMapping.getName().split("_")[0];
	    	
	    	
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url
	    	String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
	    	
	    	System.out.println(content);
	    	String id ="";
	    	String floor = "";
	    	String in_floor = "";
	    	String to_whom = "";
	    	
	    	char[] getcontent = content.toCharArray();
	    	int temp = 0;
	    	for(int i=0;i<content.length();i++){
	    		if(getcontent[i]==':'){
	    			temp = i;
	    		}
	    	}
	    	
	    	if(temp == 0){//没有；是新的一楼
	    		id = content.split("&")[0];
	    		content = content.split("&")[1];
	    		floor = "0";
	    		
	    		String selectsql1 = "select floor from tiezi where id=" + id + " order by floor desc";
				
		    	System.out.println(selectsql1);
		    	
				Statement stmt0 = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res0 = stmt0.executeQuery(selectsql1);
	    		if(res0.next()){
	    			floor = Integer.valueOf(res0.getString("floor")) + 1 + "";
	    		}
	    		
	    		in_floor = "0";
	    		to_whom = null;
	    	} else {
	    		String array = content.substring(0,temp);
		    	content = content.substring(temp+1);
		    	System.out.println(array);
		    	System.out.println(content);
		    	id = array.split("&回复")[0];
		    	System.out.println("id="+id);
		    	to_whom = "'" + array.split("&回复")[1].split(" ")[1] + "'";
		    	floor = array.split("&回复")[1].split(" ")[0].split("-")[0];
		    	floor = Integer.valueOf(floor) - 1 + "";
		    	in_floor = array.split("&回复")[1].split(" ")[0].split("-")[1];
		    	
		    	System.out.println(userName);
		    	System.out.println(floor);
		    	System.out.println(in_floor);
	    	}	
	    	
	    	String selectsql1 = "select in_floor from tiezi where id=" + id + " and floor=" + floor + " order by in_floor desc";
			
	    	System.out.println(selectsql1);
	    	
			Statement stmt0 = (Statement) conn.createStatement();// 创建一个Statement对象
			ResultSet res0 = stmt0.executeQuery(selectsql1);
			
			
			
			if(res0.next()){
				in_floor = Integer.valueOf(res0.getString("in_floor")) + 1 + "";
			}
			
	    	String sql;
			
	    	sql = "insert into tiezi (id,time,author,floor,in_floor,to_whom,content)values('"+ id +"',"+ null + ",'"+ userName + "','" + floor + "','" + in_floor + "'," + to_whom + ",'" + content + "')";
	    	
	    	System.out.println(sql);
	    	
			Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
			stmt.executeUpdate(sql);
	    	
			
			String selectsql = "select * from tie where id="+ id;
			
			Statement stmt1 = (Statement) conn.createStatement();
			ResultSet res = stmt1.executeQuery(selectsql);
			String title = new String();
			if(res.next()){
				title = res.getString("name");
			}
			System.out.println(title);
			
			
			ServletActionContext.getRequest()
			.setAttribute("Ccbt",userName);
			
			ServletActionContext.getRequest()
			.setAttribute("title",title);
	    	
	    	String result = returnTie(id,userName);
	    	ServletActionContext.getRequest()
			.setAttribute("result",result);
			return Action.SUCCESS;
	    }
	    
	    public String addt() throws SQLException, ClassNotFoundException{
	    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
	    	//String getName=(String) request.getParameter("edition");
	    	userName = (String) request.getParameter("userName");
	    	String edition = (String) request.getParameter("edition");
	    	
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url
	    	String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
	    	
	    	String selectsql1 = "select name from edition where id='" + edition +"'";
			
	    	System.out.println(selectsql1);
	    	
			Statement stmt0 = (Statement) conn.createStatement();// 创建一个Statement对象
			ResultSet res0 = stmt0.executeQuery(selectsql1);
			
			
			
			if(res0.next()){
				edition = res0.getString("name");
			}
	    	
	    	
	    	ServletActionContext.getRequest()
			.setAttribute("Ccbt",userName);
	    	ServletActionContext.getRequest()
			.setAttribute("edition",edition);
	    	
			return Action.SUCCESS;
	    	
	    }
	    
	    public String insertTie() throws ClassNotFoundException, SQLException{
	    	
	    	
	    	userName = ServletActionContext.getActionMapping().getName().split("_")[0];
	    	
	    	
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			
			Statement stmt = (Statement) conn.createStatement();
			
			String selectsql = "select name from edition where id='" + edition + "'";
			
			stmt = (Statement) conn.createStatement();
			ResultSet res0 = stmt.executeQuery(selectsql);
			if(res0.next()){
				edition = res0.getString("name");
			}
			
			String sql = "insert into tie(edition,name)values('"+ edition +"','"+ topic + "')";
			
			System.out.println(sql);
			
			
			stmt.executeUpdate(sql);// 执行sql语句
			
			sql = "select id from tie where name='" + topic + "' and edition='" + edition + "'";
			stmt = (Statement) conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			String id = null;
			if(res.next()){
				id = res.getString("id");
			}
			
			
			sql = "insert into tiezi(id,author,floor,in_floor,content)values('" + id +"','" + userName + "','" + 0 + "','"+ 0 + "','"+ content + "')";
			stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
			
			ServletActionContext.getRequest()  
        	.setAttribute("Ccbt",userName);
			
	    	ServletActionContext.getRequest()  
        	.setAttribute("change","最新动态");
	    	
	    	String edition = resultEdition(userName);
	    	System.out.println(edition);	
			ServletActionContext.getRequest()
			.setAttribute("edition",edition);
			return Action.SUCCESS;
	    }
	    
	    public String addEdition(){
	    	HttpServletRequest request = ServletActionContext.getRequest();
	  
	    	userName = (String) request.getParameter("userName");
	    	ServletActionContext.getRequest()
			.setAttribute("Ccbt",userName);
			return Action.SUCCESS;
	    	
	    }
	    
	    public String insertEdition() throws SQLException, ClassNotFoundException, UnsupportedEncodingException{
	    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	userName = ServletActionContext.getActionMapping().getName().split("_")[0];
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			
			//edition = new String (edition.getBytes("iso8859-1"),"gbk");
			//edition = new String (edition.getBytes("utf-8"),"gbk");
			
			String selectsql = "select name from edition where name='" + edition + "'";
			
			System.out.println(selectsql);
			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet res = stmt.executeQuery(selectsql);
			
			if(res.next()){
				
				return Action.ERROR;
			} 
			
			String selectsql1 = "select * from users where hitc_name='" + userName + "'";
			System.out.println(selectsql1);
			Statement stmt1 = (Statement) conn.createStatement();
			ResultSet res1 = stmt1.executeQuery(selectsql1);
			res1 = stmt1.executeQuery(selectsql1);
			String tempUserName = null;
			if(res1.next()){
				
				tempUserName = res1.getString("hitc_id");
				System.out.println(tempUserName);
			}
			String sql = "insert into edition(name,boss)values('"+ edition +"','"+ tempUserName  + "')";
			
			System.out.println(sql);
			
			stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);// 执行sql语句
			
			ServletActionContext.getRequest()
			.setAttribute("Ccbt",userName);
			
			ServletActionContext.getRequest()  
        	.setAttribute("change","最新动态");
	    	
	    	String edition = resultEdition(userName);
	    	System.out.println(edition);	
			ServletActionContext.getRequest()
			.setAttribute("edition",edition);
			
			
			return Action.SUCCESS;
	    }
	    
	    public String person() throws ClassNotFoundException, SQLException{
	    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	  	  
	    	userName = (String) request.getParameter("userName");
	    	System.out.println("userName = " + userName);
	    	String getName = ServletActionContext.getActionMapping().getName().split("_")[1];
	    	
	    	
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			
			String selectsql = "select qq from users where hitc_name='" + getName + "'";
			
			System.out.println(selectsql);
			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet res = stmt.executeQuery(selectsql);
			String qq = null;
			if(res.next()){
				qq = res.getString("qq");
			}
			ServletActionContext.getRequest()
			.setAttribute("Ccbt",userName);
			ServletActionContext.getRequest()
			.setAttribute("qq",qq);
			ServletActionContext.getRequest()
			.setAttribute("Qwbt",getName);
			
			
			
			return Action.SUCCESS;
	    }
	    
	    public String friend(){
	    	
	    	//HttpServletRequest request = ServletActionContext.getRequest();
		  	  
	    	//userName = (String) request.getParameter("userName");
	    	
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.split("_")[0];
	    	System.out.println(getName);
	    	String result = "";
	    	String getId = "";
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				
				String nameSql = "select hitc_id from users where hitc_name='" + getName +"'";
				
				// 3、执行sql语句
				Statement nameStmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet nameRes = nameStmt.executeQuery(nameSql);
				System.out.println("lalala");
				while(nameRes.next()){
					getId = nameRes.getString("hitc_id");
					System.out.println(getId);
				}
				
				// 2、生成一条sql语句
				String selectsql = "select friend_id from friends where hitc_id=" + getId;
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				
				while(res.next()){//set不空
					String friend_id = res.getString("friend_id");
					System.out.println(friend_id);
					String sql = "select hitc_name from users where hitc_id=" + friend_id + " order by hitc_name";
					
					Statement tempStmt = (Statement) conn.createStatement();// 创建一个Statement对象
					
					System.out.println("lalala");
					
					
					ResultSet tempRes = tempStmt.executeQuery(sql);
					
					System.out.println("lalala");
					
					while(tempRes.next()){
						String friend = tempRes.getString("hitc_name");
						
						System.out.println(friend);
						
						String temp = "<li><a href=\"person_" +friend+ ".action?userName=" + getName + "\">"+ friend + "</a></li>";
						result = result + temp;
					}
				}
				ServletActionContext.getRequest()
    			.setAttribute("friend",result);
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	ServletActionContext.getRequest()  
    			.setAttribute("Ccbt",getName);
	    	
			return Action.SUCCESS;
	    }

	    public String addFriend() throws ClassNotFoundException, SQLException{
	    	
	    	userName = ServletActionContext.getActionMapping().getName();
	    	userName = userName.split("_")[0];
	    	
	    	Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			//String url = "jdbc:mysql://localhost:3306/hit_c";// 声明数据库hitc的url

			// 其中hitc为操作的database（数据库）

			String user = "root";// 数据库的用户名
			String password = "414732";// 数据库的密码
			// 建立数据库连接，获得连接对象conn(抛出异常即可)
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功");
			
			String selectsql = "select hitc_id from users where hitc_name='" + addfriend + "'";
			
			System.out.println(selectsql);
			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet res = stmt.executeQuery(selectsql);
			String friend_id = null;
	    	if(res.next()){
	    		friend_id = res.getString("hitc_id");
	    	}
	    	
	    	selectsql = "select hitc_id from users where hitc_name='" + userName+ "'";
	    	System.out.println(selectsql);
	    	
	    	stmt = (Statement) conn.createStatement();
			res = stmt.executeQuery(selectsql);
			String hitc_id = null;
	    	if(res.next()){
	    		hitc_id = res.getString("hitc_id");
	    	}
	    	
	    	selectsql = "select * from friends where hitc_id='" + hitc_id+ "' and friend_id='" + friend_id +"'";
	    	System.out.println(selectsql);
	    	stmt = (Statement) conn.createStatement();
			res = stmt.executeQuery(selectsql);
			
	    	if(res.next()){//已是好友
	    		return Action.ERROR;
	    	} else {
	    		String sql1 = "insert into friends(hitc_id,friend_id)values(" + hitc_id + "," + friend_id + ")";
	    		String sql2 = "insert into friends(hitc_id,friend_id)values(" + friend_id + "," + hitc_id + ")";
	    		stmt = (Statement) conn.createStatement();
	    		stmt.executeUpdate(sql1);
	    		stmt.executeUpdate(sql2);
	    		
	    		ServletActionContext.getRequest()
				.setAttribute("Ccbt",userName);
				
				ServletActionContext.getRequest()  
	        	.setAttribute("change","最新动态");
		    	
		    	String edition = resultEdition(userName);
		    	System.out.println(edition);	
				ServletActionContext.getRequest()
				.setAttribute("edition",edition);
	    		
	    		
	    		
	    		
	    		return Action.SUCCESS;
	    	}
	    	
	    	
	    }
}
