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
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <!--f:table collection="${objectList}"/-->
    <table>
        <thead>
        <th>队长</th>
        <th>任务</th>
        <th>队员</th>
        <th>进度</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0 ? 'even' : 'odd')}">
                <td>
                    ${item.leader}
                    <a href="javascript: listMembers(${item.id})">查看队员</a>
                    <g:if test="${item.leader.id!=session.realId}">
                        <a href="javascript: joinTeam(${item.id})">加入团队</a>
                    </g:if>
                    <g:else>
                        <g:if test="${item?.members?.size()<1}">
                            <a href="javascript: disband(${item.id})">解散队伍</a>
                        </g:if>
                    </g:else>
                </td>
                <td>
                    ${item.thing}
                </td>
                <td>${item.members}</td>
                <td>${item.progresses}</td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>
</body>
</html>