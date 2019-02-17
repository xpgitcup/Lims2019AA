<!DOCTYPE html>

<div id="list-progress" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${objectList}"/>
    <table>
        <thead>
        <th>状态</th>
        <th>贡献者</th>
        <th>问题</th>
        <th>支撑文件</th>
        <th>日期</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0 ? 'even' : 'odd')}">
                <td>
                    ${item.currentStatus}
                    <a href="javascript: checkEvaluate(${item.id})">查看反馈</a>
                </td>
                <td>${item.contributor}</td>
                <td>${item.problemEncounter}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
