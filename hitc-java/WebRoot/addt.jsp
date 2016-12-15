<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HITC-不如来兮</title>
    <!-- zui -->
    <link href="css/zui.min.css" rel="stylesheet">
  </head>
  
 <body> 
  </br></br>
   
	<a id= "edortit" name="${edition}"></a>
	   <header id="header" class="bg-primary">
      <div class="navbar navbar-inverse navbar-fixed-top" id="navbar" role="banner">
        <div class="container">
          <div class="navbar-header">
            
            <a href="${Ccbt}_fillName.action" class="navbar-brand"><span class="path-zui-36"><i class="path-1"></i><i class="path-2"></i></span> <span class="brand-title">${Ccbt}</span> &nbsp;<small class="zui-version"></small> </a>
          </div>
          <nav class="collapse navbar-collapse zui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li id="navDownloadLink"><a href="person_${Ccbt}.action?userName=${Ccbt}"><i class="icon icon-download-alt"></i><span> 个人信息</span></a></li>
              <li><a href="${Ccbt}_friend.action" ><i class="icon icon-comments-alt"></i><span> 添加好友</span></a></li>
              <li><a href="addedition.action?userName=${Ccbt}"><i class="icon icon-github"></i><span> 建立圈子</span></a></li>
            </ul>
          </nav>
        </div>
      </div>
      </div>
    </header>


<div class="left" style="width:1000px;height:600px;float:left">
<form method="post" action=${Ccbt}_insertTie.action>
	<h4 id="qz">圈子</h4>
	<input type="text" class="form-control" id="" name="edition" value="${edition}">
	
	<h4>主题</h4>
	<input type="text" class="form-control" id="edortitk" name="topic" placeholder="写下你的主题">
	<div id="hide">
	<h4 >内容</h4>
	<textarea class="form-control new-comment-text" name="content" rows="6" id="commenth" placeholder="写下你的内容"></textarea>
	</div>
	<div class="col-md-2"><button type="submit" class="btn btn-block btn-primary">ok</button></div>
</form>
</div>


</body>
</html>