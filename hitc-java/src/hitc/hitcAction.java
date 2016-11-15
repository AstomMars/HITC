package hitc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.Action;
//import com.opensymphony.xwork2.ActionSupport;


import org.apache.struts2.ServletActionContext;

public class hitcAction {

		public String gotoPage1()
	    {
			
			String asd = "";
	        ServletActionContext.getRequest()  
	            .setAttribute("AccessBy1","<div>sjl</div>");
	        return Action.SUCCESS;  
	    }
	    
		
		private String userName ;//= request.getParameter("userName");//页面里面的userName 
	    private String passWord ;//= request.getParameter("passWord");//页面里面的passWord
	    private String qqId ;
	    
	    //private String title;
	    
		//private HttpServletRequest request;
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
	    /*
	    public String getTitle(){
	    	return qqId;
	    }
	    public void setTitle(String title){
	    	this.title = title;
	    }
	    */
	    public String getQqId(){
	    	return qqId;
	    }
	    public void setQqId(String qqId){
	    	this.qqId = qqId;
	    }
	    
	    
	    //业务逻辑 
	     
	    public String fillName(){
	    	ServletActionContext.getRequest()  
            	.setAttribute("Ccbt",userName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				// 2、生成一条sql语句
				String selectsql = "select title from catalog";
				
				// 3、执行sql语句
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
				int i = 1;
				while(res.next()){//set不空
					String psd = res.getString("title");
					String title = "title" + i;
					ServletActionContext.getRequest()
		    			.setAttribute(title,"[topic]" + psd);
					i++;
				} 
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
	    
	    public String zc()
	    {  
	    	
	    	try {

				// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");

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
				
				// insert [into] 表名 [(列名1, 列名2, 列名3, ...)] values (值1, 值2, 值3, ...);
				// select 列名称 from 表名称 [查询条件];例如要查询 students 表中所有学生的名字和年龄, 输入语句
				// select name, age from students;
				// select 列名称 from 表名称 where 条件;select * from students where
				// sex="male";
				// update 表名称 set 列名称=新值 where 更新条件;
				// delete from 表名称 where 删除条件;

				// http://www.cnblogs.com/mr-wid/archive/2013/05/09/3068229.html#d8

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
	    	
	    	
	    	/*
	    	System.out.println(userName);
	    	System.out.println(passWord);
	    	if(null != qqId){
	    		System.out.print(qqId);
	    	} else {
	    		System.out.print("nothing");
	    	}
	    	if (null!= userName && null != passWord  
		            && userName.equals("arne3166") && passWord.equals("111111")) {  
		            return Action.SUCCESS;
		        } else {  
		            return Action.ERROR;
		    } 
	    	
	    	/*
	        if (null!= userName && null != passWord  
	            && userName.equals("arne3166") && passWord.equals("111111")) {  
	            return Action.SUCCESS;
	        } else {  
	            return Action.ERROR;
	        }  
	        */
	    }
	    
	    public String login()
	    {
	    	//System.out.println(userName);
	    	//System.out.println(passWord);
	    	try {
	    	// 1、建立数据库连接
			Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			System.out.println("加载数据库驱动成功");
			String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

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
	    
	    public String resultArtical(){
	    	//System.out.println("Title is "+title);
	    	
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(7);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	String[] tempList = getName.split("_");
	    	
	    	String hitcName = tempList[0];
	    	
	    	String classify = tempList[1].substring(0,7);
	    	System.out.println(classify);
	    	
	    	String tit = tempList[1].substring(7);
	    	System.out.println(tit);
	    	
	    	String cont = null;
	    	String hot = null;
	    	String branch = null;
	    	
	    	try {

				// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

				// 其中hitc为操作的database（数据库）

				String user = "root";// 数据库的用户名
				String password = "414732";// 数据库的密码
				// 建立数据库连接，获得连接对象conn(抛出异常即可)
				Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
				String selectsql;
				if(classify.equals("[topic]")){
		    		selectsql = "select * from catalog where title='"+ tit +"'";
		    	} else {
		    		selectsql = "select * from news where title='"+ tit +"'";
		    	}
		    	
				Statement stmt = (Statement) conn.createStatement();// 创建一个Statement对象
				ResultSet res = stmt.executeQuery(selectsql);
		    	
				if(res.next()){//set不空
					cont = res.getString("content");
					hot = res.getString("hot");
					branch = res.getString("xueyuan");
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
        		.setAttribute("title",classify + "[" + branch + "]" + tit);
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
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

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
				int i = 1;
				while(res.next()){//set不空
					String psd = res.getString("title");
					String title = "title" + i;
					ServletActionContext.getRequest() 
		    			.setAttribute(title,"[topic]" + psd);
					i++;
				} 
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
	    
	    public String topicBranch(){
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(13);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

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
				int i = 1;
				while(res.next()){//set不空
					String psd = res.getString("title");
					String title = "title" + i;
					ServletActionContext.getRequest() 
		    			.setAttribute(title,"[topic]" + psd);
					i++;
				} 
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
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

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
				
				int i = 1;
				while(res.next()){//set不空
					String psd = res.getString("title");
					String title = "title" + i;
					ServletActionContext.getRequest() 
		    			.setAttribute(title,"[news ]" + psd);
					i++;
				} 
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
	    
	    public String newsBranch(){
	    	String getName = ServletActionContext.getActionMapping().getName();
	    	getName = getName.substring(12);
	    	System.out.println("-----getName----->>"+getName);
	    	
	    	try {
		    	// 1、建立数据库连接
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
				System.out.println("加载数据库驱动成功");
				String url = "jdbc:mysql://localhost:3306/hitc";// 声明数据库hitc的url

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
				
				
				int i = 1;
				while(res.next()){//set不空
					String psd = res.getString("title");
					String title = "title" + i;
					ServletActionContext.getRequest() 
		    			.setAttribute(title,"[news ]" + psd);
					i++;
				} 
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
	    
}
