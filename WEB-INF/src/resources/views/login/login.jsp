<form action="<%= response.encodeURL("j_security_check") %>" method="post">
    <fieldset>
        <legend>Login</legend>
        <p>
            <label for="name">Username</label>
            <input type="text" name="j_username">
        </p>
        <p>
            <label for="e-mail">Passsword</label>
            <input type="password" name="j_password">
        </p>

        <p class="submit">
            <input type="submit" value="Submit">
        </p>
    </fieldset>
</form>