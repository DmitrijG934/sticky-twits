<#macro form path buttonName isRegister>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-1 col-form-label">Username</label>
            <div class="col-sm-5">
                <input type="text" class="form-control ${(usernameError??)?string("is-invalid", "")}" id="username"
                       name="username" value="<#if user??>${user.username}</#if>" placeholder="Username"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-5">
                <input type="password" class="form-control ${(passwordError??)?string("is-invalid", "")}" id="password"
                       name="password" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegister == "true">
            <div class="form-group row">
                <label for="passwordConfirm" class="col-sm-1 col-form-label">Confirm password</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control ${(passwordConfirmError??)?string("is-invalid", "")}"
                           id="passwordConfirm" name="passwordConfirm" placeholder="Confirm password"/>
                    <#if passwordConfirmError??>
                        <div class="invalid-feedback">
                            ${passwordConfirmError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-1 col-form-label">Email</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control ${(emailError??)?string("is-invalid", "")}" id="email"
                           name="email" value="<#if user??>${user.email}</#if>" placeholder="don.joe@mail.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
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