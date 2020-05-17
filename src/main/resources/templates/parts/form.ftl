<#macro form path buttonName>
    <div>
        <form action="${path}" method="post">
            <label>Username:
                <input type="text" name="username"/>
            </label>
            <label>Password:
                <input type="password" name="password"/>
            </label>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">${buttonName}</button>
        </form>
    </div>
</#macro>

<#macro createMessage path>
    <div>
        <form method="post" action="${path}">
            <div>
                <label for="text">Enter your message: </label><br>
                <input type="text" id="text" name="text"/><br>
                <label for="tag">Enter tag for message: </label><br>
                <input type="text" id="tag" name="tag"/><br>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </div>
            <br>
            <button type="submit">Create message!</button>
        </form>
    </div>
</#macro>

<#macro logout path>
    <div>
        <form action="${path}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Logout</button>
        </form>
    </div>
</#macro>