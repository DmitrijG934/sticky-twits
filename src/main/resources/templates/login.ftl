<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <h3>Login</h3>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </#if>
    <#if messageSuccess??>
        <div class="alert alert-success" role="alert">
            ${messageSuccess}
        </div>
    </#if>
    <#if messageFail??>
        <div class="alert alert-warning" role="alert">
            ${messageFail}
        </div>
    </#if>
    <@f.form path="/login" buttonName="Login" isRegister="false"/>
    <a href="/registration" class="btn btn-primary mt-2">Sign up</a>
</@c.page>
