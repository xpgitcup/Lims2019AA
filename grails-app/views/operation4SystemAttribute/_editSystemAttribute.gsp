<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'systemAttribute.label', default: 'SystemAttribute')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-systemAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="edit-systemAttribute" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.systemAttribute}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.systemAttribute}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <!--g:form resource="${this.systemAttribute}" method="PUT"-->
            <g:form action="updateSystemAttribute" controller="operation4SystemAttribute">
                <!--这是增加的一行-->
                <g:hiddenField name="id" value="${this.systemAttribute?.id}" />
                <g:hiddenField name="version" value="${this.systemAttribute?.version}" />
                <fieldset class="form">
                    <f:all bean="systemAttribute"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
