<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <!-- 在此处编码你的创意 -->
	
	   <header id="header" class="bg-primary">
      <div class="navbar navbar-inverse navbar-fixed-top" id="navbar" role="banner">
        <div class="container">
          <div class="navbar-header">
            
            <a href="${Ccbt}_fillName.action" class="navbar-brand"><span class="path-zui-36"><i class="path-1"></i><i class="path-2"></i></span> <span class="brand-title">${Ccbt}</span> &nbsp;<small class="zui-version"></small> </a>
          </div>
          <nav class="collapse navbar-collapse zui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li id="navDownloadLink"><a title="个人信息" href="person_${Ccbt}.action?userName=${Ccbt}"><i class="icon icon-download-alt"></i><span> 个人信息</span></a></li>
              <li><a title="添加好友" href="${Ccbt}_friend.action" ><i class="icon icon-comments-alt"></i><span> 添加好友</span></a></li>
              <li><a title="建立圈子" href="addedition.action?userName=${Ccbt}"><i class="icon icon-github"></i><span> 建立圈子</span></a></li>
            </ul>
          </nav>
        </div>
      </div>
      
        
      </div>
    </header>

<script>
function init(x1,x2,x3)
{
	document.getElementById("replyarea").value="回复"+x1 + "-" + x2 + " " +x3 + ":";
}
function addid(){
	var x=document.getElementById("getid").getAttribute("name");
	document.getElementById("replyarea").value=x + "&" +document.getElementById("replyarea").value;
}

</script>	
<div class="comments">
  <header>
    <div class="pull-right"><a href="#commentReplyForm2" class="btn btn-primary"><i class="icon-comment-alt"></i> 发表评论</a></div>
    <h2>${title}</h2>
  </header>
  <section class="comments-list">
	${result}
  </section>
  <footer>
    <div class="reply-form" id="commentReplyForm2">
      <a href="###" class="avatar"><i class="icon-user icon-2x"></i></a>
      <form method="post" class="form" action="${Ccbt}_reply.action">
        <div class="form-group">
          <textarea class="form-control new-comment-text" name="content" id="replyarea" rows="4" placeholder="撰写评论..."></textarea>
        </div>
        <div class="form-group comment-user">
          <div class="row">
            
            
            <div class="col-md-2"><button type="submit" onclick="addid()" class="btn btn-block btn-primary"><i class="icon-ok"></i></button></div>
          </div>
        </div>
      </form>
    </div>
  </footer>
</div>

    <!-- jQuery (ZUI中的Javascript组件依赖于jQuery) -->
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <!-- ZUI Javascript组件 -->
    <script src="js/zui.min.js"></script>