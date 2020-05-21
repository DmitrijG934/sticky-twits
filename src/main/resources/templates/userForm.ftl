<#import "parts/common.ftl" as c>
<@c.page>
    <#if message??>
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </#if>
    <h5>${username}</h5>
    <form method="post">
        <div class="form-group row">
            <label for="password" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="Password"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-5">
                <input type="email" class="form-control" id="email" value="${email!''}" name="email"
                       placeholder="don.joe@mail.com"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>
