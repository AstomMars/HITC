<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HITC-不如来兮</title>
    <!-- zui -->
    <link href="css/zui.min.css" rel="stylesheet">
  </head>
  <body>
  </br></br>
    
	
	   <header id="header" class="bg-primary">
      <div class="navbar navbar-inverse navbar-fixed-top" id="navbar" role="banner">
        <div class="container">
          <div class="navbar-header">
            
            <a href="${Ccbt}_fillName.action" class="navbar-brand"><span class="path-zui-36"><i class="path-1"></i><i class="path-2"></i></span> <span class="brand-title">${Ccbt}</span> &nbsp;<small class="zui-version"></small> </a>
          </div>
          <nav class="collapse navbar-collapse zui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li id="navDownloadLink"><a href="person_${Ccbt}.action?userName=${Ccbt}"><i class="icon icon-download-alt"></i><span>个人信息</span></a></li>
              <li><a href="${Ccbt}_friend.action" ><i class="icon icon-comments-alt"></i><span>添加好友</span></a></li>
              <li><a href="addedition.action?userName=${Ccbt}"><i class="icon icon-github"></i><span>建立圈子</span></a></li>
            </ul>
          </nav>
        </div>
      </div>
      
        
      </div>
    </header>

<div class="right" style="width:600px;float:left">
<div class="panel panel-primary">
  <div class="panel-heading">
   ${Qwbt}
  </div>
  <div class="panel-body">
    <ul class="list-group">
	  <li class="list-group-item">HITC ${Qwbt}</li>
	  <li class="list-group-item">QQ ${qq}</li>
	</ul>
  </div>
</div>
</div>
</body>
</html>