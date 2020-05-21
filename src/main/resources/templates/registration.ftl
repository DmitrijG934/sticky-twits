<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <#if message??>
        <p>${message}</p>
        <hr>
    </#if>
    <h3>Registration</h3>
    <div>
        <@f.form path="/registration" buttonName="Sign up" isRegister="true"/>
        <a href="/login" class="btn btn-primary mt-2">Sign in</a>
    </div>
</@c.page>