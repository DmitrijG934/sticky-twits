<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <h3>Login</h3>
    <@f.form path="/login" buttonName="Login"/>
    <a href="/registration" class="btn btn-primary mt-2">Sign up</a>
</@c.page>
