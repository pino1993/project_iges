<%-- 
    Document   : home
    Created on : 11-dic-2017, 11.56.26
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="head.html"%>
        <title>JSP Page</title>
    </head>
    <body>    
        <div id="hello"></div>
        <script type="text/babel">
            var HelloWorld = React.createClass({
                render: function () {
                    return <p>Hello <strong>React</strong>!</p>;
                }
            });

            ReactDOM.render(<HelloWorld />, document.getElementById("hello"));
        </script>
    </body>
</html>
