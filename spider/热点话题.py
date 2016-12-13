# -*- coding: utf-8 -*-
"""
Created on Thu Sep 08 21:19:19 2016

@author: Marsm
"""

import urllib2
from bs4 import BeautifulSoup

#import MySQLdb
#
#conn=MySQLdb.connect(host='127.0.0.1',port=3306,user='root',passwd='root',db='hitc')
#cursor=conn.cursor()








try:
    url = "http://today.hit.edu.cn/classList/2.html"
    #构建请求的request
    request = urllib2.Request(url)
    #利用urlopen获取页面代码
    response = urllib2.urlopen(request)
    #将页面转化为UTF-8编码
    pageCode = response.read()
    
 
except urllib2.URLError, e:
    if hasattr(e,"reason"):
        print u"连接网页失败,错误原因",e.reason

 
if not pageCode:
    print u"页面加载失败...."
    
    
soup = BeautifulSoup(pageCode)
data = soup.findAll('a',target='_blank')
data1=data[2:]


num=len(data1)



oc=[]
doc1=open("2doc1.txt","a")
doc2=open("2doc2.txt","a")
doc3=open("2doc3.txt","a")
cat="2doc4"

urllist=[]

for i in range (0,num,2):
    doc1.write(data1[i].get_text().encode("gbk",'ignore'))
    doc1.write("\n")
    oc.append(data1[i].get_text())
    doc2.write(data1[i+1].get_text().encode("gbk",'ignore'))
    doc2.write("\n")
    oc.append(data1[i+1].get_text())
    doc3.write(data1[i+1].get('href'))
    doc3.write("\n")
    oc.append(data1[i+1].get('href'))
    ur="http://today.hit.edu.cn"
    ur+=data1[i+1].get('href')
    doc4=open(cat+"{0}.txt".format(i/2+1),"a")
    try:
        urlin = ur
        #构建请求的request
        requestin = urllib2.Request(urlin)
        #利用urlopen获取页面代码
        responsein = urllib2.urlopen(requestin)
        #将页面转化为UTF-8编码
        pageCodein = responsein.read()
    
 
    except urllib2.URLError, e:
        if hasattr(e,"reason"):
            print u"连接网页失败,错误原因",e.reason
    
     
    if not pageCodein:
        print u"页面加载失败...."
        
        
    soupin = BeautifulSoup(pageCodein)
    data1in = soupin.findAll('div',id="text")
    
    
    datein=soupin.find('div',id="date")
    
    try:
        a=datein.get_text().encode("gbk",'ignore')
    
        s=""
        for c in a:
            if c != " " and c!="\n":
                s+=c
        s=s[6:16]
    #    print s
        doc4.write(s)
        doc4.write("\n")
    
    
    
        data1in=data1in[0].encode("gbk")
        #for data in data1:
        #    data+=">"
    #    print data1in
        doc4.write(data1in)
        doc4.close()
    except:
        pass
    
#    sq1="insert into qwtester values('{0}','{1}')".format(data1[i].get_text().encode("gbk","ignore"),data1[i+1].get_text().encode("gbk","ignore"))
#    cursor.execute(sq1)

#conn.commit()
doc1.close()
doc2.close()
doc3.close()
#limit = len(oc)
#for i in range (0,limit,3):    
#    try:
#        print u"{0} {1} {2} {3}".format(i/3+1,oc[i],oc[i+1],oc[i+2])
#    except:
#        print "\nerror"

# 关闭数据库连接
#conn.close()
#print oc

