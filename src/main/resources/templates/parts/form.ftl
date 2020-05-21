<#macro form path buttonName isRegister>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-1 col-form-label">Username</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
            </div>
        </div>
        <#if isRegister == "true">
            <div class="form-group row">
                <label for="email" class="col-sm-1 col-form-label">Email</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" id="email" name="email" placeholder="don.joe@mail.com"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">${buttonName}</button>
    </form>
</#macro>

<#macro createMessage path>
    <form method="post" action="${path}" enctype="multipart/form-data">
        <div>
            <div class="form-group mt-3">
                <input type="text" id="text" name="text" class="form-control" placeholder="Message"/>
            </div>
            <div class="form-group">
                <input type="text" id="tag" name="tag" class="form-control" placeholder="Tag"/>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" id="addFile" name="file" class="form-control" placeholder="Choose file"/>
                    <label for="addFile" class="custom-file-label">Pin file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </div>
    </form>
</#macro>

<#macro logout path>
    <div>
        <form action="${path}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </div>
</#macro>