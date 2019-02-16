<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div id="list-team" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--h1><g:message code="default.list.label" args="[entityName]"/></h1-->
    <!--f:table collection="${objectList}"/-->
    <table>
        <thead>
        <th>队长</th>
        <th>任务</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0 ? 'even' : 'odd')}">
                <td>
                    ${item.leader}
                </td>
                <td>
                    ${item.thing}
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table>
                        <thead>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>属性</th>
                        </thead>
                        <g:each in="${item.members}" var="itemA" status="ii">
                            <tr class="${(ii % 2 == 0 ? 'even' : 'odd')}">
                                <td>
                                    ${itemA.code}
                                    <g:if test="${item.leader.id==session.realId}">
                                        <a href="javascript: dismiss(${itemA.id})">解聘</a>
                                    </g:if>
                                    <g:if test="${itemA.id==session.realId}">
                                        <a href="javascript: quitTeam(${item.id})">退出</a>
                                    </g:if>
                                </td>
                                <td>${itemA.name}</td>
                                <td>${itemA.personTitle}</td>
                            </tr>
                        </g:each>
                    </table>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>