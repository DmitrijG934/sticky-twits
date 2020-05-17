<#import "parts/common.ftl" as c>
<@c.page>
    <table>
        <thead>
        <tr>
            <th>Username</th>
            <th>Roles</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/edit?id=${user.id}">Edit</a></td>
                <td><a href="/user/remove?id=${user.id}">Delete</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>