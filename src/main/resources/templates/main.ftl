<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <p><a href="/user">>> Users</a></p>
    <@f.logout path="/logout" />
    <h1>Sticky Twits</h1>
    <@f.createMessage path="/main" />
    <br>
    <div>
        <form method="get" action="/main">
            <div>
                <label for="search">Search message: </label><br>
                <input type="text" id="search" value="${search!}" name="search"/><br>
            </div>
            <br>
            <button type="submit">Search</button>
        </form>
    </div>
    <h3>Messages: </h3>
    <#list messages as message>
        <div>
            <p>${ message.text }</p>
            <p>${ message.tag }</p>
            <p>${ message.createdAt }</p>
            <p>${ message.authorName }</p>
            <#if message.filename??>
                <div>
                    <img src="img/${message.filename}">
                </div>
            </#if>
        </div>
        <hr>
    <#else>
        <p><#if reportMessage??>${reportMessage}</#if></p>
    </#list>
</@c.page>