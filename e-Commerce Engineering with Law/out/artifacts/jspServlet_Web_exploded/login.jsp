<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="./style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<script  src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" ></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<div class="body">
    <% System.out.println("*********");%>
    <div class="veen">
        <div class="login-btn splits">
            <p>Already an user?</p>
            <button class="active">Login</button>
        </div>
        <div class="rgstr-btn splits">
            <p>Don't have an account?</p>
            <button>Register</button>
        </div>
        <div class="wrapper">
            <form id="login" tabindex="500"action="./login"method="post">
                <h3>Login</h3>
                <div class="name">
                    <input type="text" name="name">
                    <label>Member Name</label>
                </div>
                <div class="passwd">
                    <input type="password" name="pwd">
                    <label>Password</label>
                </div>
                <div class="submit">
                    <button class="dark">Login</button>
                </div>
            </form>
            <form id="register" tabindex="502"action="./login" method="post">
                <h3>Register</h3>
                <div class="name">
                    <input type="text" name="rname">
                    <label>Member Name</label>
                </div>
                <div class="phone">
                    <input type="text" name="phone">
                    <label>Phone Number</label>
                </div>

                <div class="passwd">
                    <input type="password" name="rpwd">
                    <label>Password</label>
                </div>
                <div class="passwd1">
                    <input type="password" name="rpwd1">
                    <label>Confirm Your Password</label>
                </div>
                <div class="gender">
                    <input type="text" name="gender">
                    <label>Gender</label>
                </div>

                <div class="submit">
                    <button class="dark">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>


<style type="text/css">
    .site-link{
        padding: 5px 15px;
        position: fixed;
        z-index: 99999;
        background: #fff;
        box-shadow: 0 0 4px rgba(0,0,0,.14), 0 4px 8px rgba(0,0,0,.28);
        right: 30px;
        bottom: 30px;
        border-radius: 10px;
    }
    .site-link img{
        width: 30px;
        height: 30px;
    }
</style>
<!-- partial -->
<script  src="./script.js"></script>

</body>
</html>
