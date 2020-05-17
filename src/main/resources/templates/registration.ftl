<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <#if message??>
        <p style="color: red">${message}</p>
        <hr>
    </#if>
    <h3>Registration</h3>
    <@f.form path="/registration" buttonName="Sign up"/>
    <a href="/login">>> Sign in</a>
</@c.page>