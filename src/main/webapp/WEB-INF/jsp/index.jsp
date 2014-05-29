<html>
<body>
<h1>Execute code in runtime</h1>

<form id="myForm" action="sendCode" method="post">
    <h2>Import statments</h2>
    <textarea name="importCode" form="myForm" rows="10" cols="50"></textarea>
    <h2>Java Code (ApplicationContext under "ctx" variable)</h2>
    <textarea name="javaCode" form="myForm" rows="10" cols="50"></textarea>
    <input type="submit"/>
</form>
</body>
</html>