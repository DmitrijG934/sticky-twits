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
            <#if message.file??>
                <div>
                    <#if !message.file.fileType?contains("image")>
                        <img src="https://img.icons8.com/carbon-copy/2x/file.png" alt="">
                    <#else >
                        <img src="file/${message.file.fileName}" alt=""><br>
                    </#if>
                    <hr>
                    <a href="file/${message.file.fileName}">Download</a>
                </div>
            </#if>
        </div>
        <hr>
    <#else>
        <p><#if reportMessage??>${reportMessage}</#if></p>
    </#list>
</@c.page>