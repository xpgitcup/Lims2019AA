<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
    <g:set var="entityName" value="Person"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os4lims/${entityName}.js"/>
</head>

<body>

<div class="nav">
    <ul id="operation4PersonUl">
        <li>
            <a id="currentTemplate" href="">下载模板</a>
        </li>
        <li><a id="currentImport">导入数据</a></li>
        <li>
            <g:uploadForm method="post" action="importFromFile">
                <ul>
                    <li>
                        <input type="file" name="uploadedFile"/>
                    </li>
                    <li>
                        <input type="hidden" name="key" value="" id="importKey"/>
                    </li>
                    <!--li>
                        <input type="checkbox" name="autoUpdateType" checked="checked"/>
                        <label>自动更新分类</label>
                    </li-->
                    <li>
                        <input type="submit" value="ok">
                    </li>
                </ul>
            </g:uploadForm>
        </li>
        <li><a>||</a></li>
    </ul>
</div>

<div class="nav">
    <ul>
        <li><a id="imputManual"></a></li>
        <li>
            <div id="inputTeacher" class="hidden">
                <g:form action="inputTeacher" method="post">
                    <ul>
                        <li>工号</li>
                        <li><input type="text" name="code"></li>
                        <li>姓名</li>
                        <li><input type="text" name="name"></li>
                        <li>职称</li>
                        <li>
                            <g:select name="personTitle"
                                      from="${cn.edu.cup.lims.PersonTitle.findByName('教师').subTitles}"
                                      optionKey="id"
                                      noSelection="['': '-请选择-']"/>
                        </li>
                        <li>
                            <input type="submit" value="ok">
                        </li>
                    </ul>
                </g:form>
            </div>
        </li>
        <li>
            <div id="inputStudent" class="hidden">
                <g:form action="inputStudent" method="post">
                    <ul>
                        <li>学号</li>
                        <li><input type="text" name="code"></li>
                        <li>姓名</li>
                        <li><input type="text" name="name"></li>
                        <li>类型</li>
                        <li>
                            <g:select name="personTitle"
                                      from="${cn.edu.cup.lims.Student.executeQuery('select DISTINCT student.personTitle from Student student ')}"
                                      noSelection="['': '-请选择-']"/>
                        </li>
                        <li>年级</li>
                        <li>
                            <g:select name="gradeName"
                                      from="${cn.edu.cup.lims.Student.executeQuery('select DISTINCT student.gradeName from Student student ')}"
                                      noSelection="['': '-请选择-']"/>
                        </li>
                        <li>专业</li>
                        <li>
                            <g:select name="major"
                                      from="${cn.edu.cup.lims.Major.list()}"
                                      optionKey="id"
                                      noSelection="['': '-请选择-']"/>
                        </li>
                        <li>
                            <input type="submit" value="ok">
                        </li>
                    </ul>
                </g:form>
            </div>
        </li>
    </ul>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4PersonDiv" class="easyui-tabs">
</div>
</body>
</html>
