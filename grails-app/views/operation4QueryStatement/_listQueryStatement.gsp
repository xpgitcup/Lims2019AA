<!DOCTYPE html>
<html>
<head>
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
<!--meta name="layout" content="main" /-->
    <g:set var="entityName" value="${message(code: 'queryStatement.label', default: 'QueryStatement')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-queryStatement" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
<!--f:table collection="${objectList}"/-->
    <table>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td>${item.id}.说明：${item.discription}</td>
                <td>属性：${item.isSQL}</td>
                <td>视图：${item.viewName}</td>
                <td>参数：${item.paramsList}</td>
            </tr>
            <tr class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td>
                    关键字：${item.keyString}
                    <a href="operation4QueryStatement/edit/${item.id}">编辑</a>
                </td>
                <td colspan="4">${item.hql}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>