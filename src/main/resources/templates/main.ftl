<#import "parts/common.ftl" as c>
<#import "parts/form.ftl" as f>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control" placeholder="Search by tag" value="${search!}"
                       name="search"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <p>
        <a class="btn btn-primary" data-toggle="collapse" href="#createMessage" role="button" aria-expanded="false"
           aria-controls="createMessage">Create message</a>
    </p>
    <div class="collapse" id="createMessage">
        <div class="form-group">
            <@f.createMessage path="/main" />
        </div>
    </div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.file??>
                    <#if !message.file.fileType?contains("image")>
                        <img class="card-img-top" src="https://img.icons8.com/carbon-copy/2x/file.png" alt="">
                    <#else >
                        <img class="card-img-top" src="file/${message.file.fileName}" alt=""><br>
                    </#if>
                </#if>
                <#if !message.text?matches("") || !message.tag?matches("")>
                    <div class="card-body">
                        <div class="m-2">
                            <#if !message.text?matches("")>
                                <p class="card-text">${ message.text }</p>
                            </#if>
                            <#if !message.tag?matches("")>
                                <p class="card-text"><a href="?search=${message.tag}">${message.tag}</a></p>
                            </#if>
                        </div>
                    </div>
                </#if>
                <div class="card-footer text-muted">${message.authorName}</div>
            </div>
            <#if reportMessage??>
                <p class="text-muted">${reportMessage}</p></#if>
        <#else >
            <p class="text-muted">${reportMessage}</p>
        </#list>
    </div>
</@c.page>