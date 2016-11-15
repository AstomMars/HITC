<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="gbk">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HITC-不如来兮</title>
    <!-- zui -->
    <link href="css/zui.min.css" rel="stylesheet">
  </head>
  <body>
  </br></br>
    <!-- 在此处编码你的创意 -->
	
	   <header id="header" class="bg-primary">
      <div class="navbar navbar-inverse navbar-fixed-top" id="navbar" role="banner">
        <div class="container">
          <div class="navbar-header">
            
            <a href="#" class="navbar-brand"><span class="path-zui-36"><i class="path-1"></i><i class="path-2"></i></span> <span class="brand-title">${Ccbt}</span> &nbsp;<small class="zui-version"></small> </a>
          </div>
          <nav class="collapse navbar-collapse zui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li id="navDownloadLink"><a title="个人信息" href="docs/download/zui-1.5.0-dist.zip" target="_blank"><i class="icon icon-download-alt"></i><span> 个人信息</span></a></li>
              <li><a title="添加好友" href="http://forum.zui.sexy/forum/" target="_blank"><i class="icon icon-comments-alt"></i><span> 添加好友</span></a></li>
              <li><a title="联系我们" href="https://github.com/easysoft/zui" target="_blank"><i class="icon icon-github"></i><span> 联系我们</span></a></li>
            </ul>
          </nav>
        </div>
      </div>
      
        
      </div>
    </header>
<div class="container">

<div class="welcome">
    <h2>亲爱的${Ccbt}</h2>
    <h1>欢迎来到HITC！</h1>
</div>


<div class="body">

<div class="left" style="width:200px;float:left">
	
	<nav class="menu" data-toggle="menu" style="width: 200px">
  <button class="btn btn-primary"><i class="icon-edit"></i> Baidu</button>
  <button class="btn"><i class="icon-cloud-upload"></i> UPLOAD</button>
  <ul class="nav nav-primary">
    <li><a href="#"><i class="icon-th"></i> 热点新闻</a>
	  <ul class="nav">
        <li><a href="news_branch_${Ccbt}.action">按学院分类</a></li>
        <li><a href="news_hot_${Ccbt}.action">按热度排序</a></li>
      </ul>
	</li>
    <li><a href="http://www.baidu.com"><i class="icon-user"></i> 热点人物</a>
	</li>
    <li>
      <a href="#"><i class="icon-time"></i> 热点话题</a>
      <ul class="nav">
        <li><a href="topic_branch_${Ccbt}.action">按学院分类</a></li>
        <li><a href="topic_hot_${Ccbt}.action">按热度排序</a></li>
      </ul>
    </li>
    <li><a href="#"><i class="icon-list-ul"></i> All</a></li>
    <li class="active show">
      <a href="#"><i class="icon-tasks"></i> 留言板</a>
      
    </li>
  </ul>
</nav>

</div>


<div class="right"  style="width:600px;float:left">

<div class="list list-condensed">
  <header>
    <h3><i class="icon-list-ul"></i> 最新动态 </h3>
  </header>
  <div class="items items-hover">
    <div class="item">
      <div class="item-heading">
        <h4><a href="result_${Ccbt}_${title1}.action">${title1}</a></h4>
      </div>
    
      <div class="item-content">
        <div class="text"></div>
      </div>
    </div>
    <div class="item">
      <div class="item-heading">
      	<h4><a href="result_${Ccbt}_${title2}.action">${title2}</a></h4>
      </div>
      <div class="item-content">
        <div class="text"></div>
      </div>
    </div>
    <div class="item">
      <div class="item-heading">
        <h4><a href="result_${Ccbt}_${title3}.action">${title3}</a></h4>
      </div>
      <div class="item-content">
        <div class="media pull-right"></div>
      </div>
    </div>
</div>

    <!-- jQuery (ZUI中的Javascript组件依赖于jQuery) -->
    <script src="jquery-3.1.1.min.js"></script>
    <!-- ZUI Javascript组件 -->
    <script src="js/zui.min.js"></script>
  </body>
</html>