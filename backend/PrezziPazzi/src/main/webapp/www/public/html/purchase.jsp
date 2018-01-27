<%-- 
    Document   : purchase
    Created on : 16-dic-2017, 12.47.54
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="head.html"%>
        <title>PrezziPazzi-Riepilogo acquisto</title>
    </head>
    <body style="text-align: center;">
        <div id="hello" style="margin-top:50px;"></div>
        <%
            final String type = (String) request.getAttribute("type");
        %>
        <% if (type.equals("success")) { %>
        <h1 style="color: green;margin-top: 50px;"><c:out>${requestScope.message}</c:out></h1>
                <% } else { %>
        <h1 style="color: red;margin-top: 50px;"><c:out>${requestScope.message}</c:out></h1>
                <% }%>
        <form name="purchaseProduct" action="Login" method="POST">
            <input id="valueToPassEmail" type="hidden" name="email" value="" />
            <input id="valueToPassPassword" type="hidden" name="password" value="" />
        </form>

        <button style="margin-top: 50px;" type="button" name="back" onclick="returnTOhome()">Ritorna alla pagina precedente</button>





        <script type="text/babel">

            var PageHeader = ReactBootstrap.PageHeader;

            const formInstance = (
                    <PageHeader style={{color: "blue", marginTop: "1px"}}>Pagina di riepilogo</PageHeader>

                                );

                        ReactDOM.render(formInstance, document.getElementById("hello"));





        </script>
        <script>
                        function returnTOhome() {
                            var cUs = getCookie("usernamePrezziPazzi")
                            var cPsw = getCookie("passwordPrezziPazzi")

                            if (cUs !== "" && cPsw !== "") {

                                document.getElementById("valueToPassEmail").value = cUs;
                                document.getElementById("valueToPassPassword").value = cPsw;
                                document.forms[0].submit();

                            }
                        }

                        function getCookie(cname) {
                            var name = cname + "=";
                            var decodedCookie = decodeURIComponent(document.cookie);
                            var ca = decodedCookie.split(';');
                            for (var i = 0; i < ca.length; i++) {
                                var c = ca[i];
                                while (c.charAt(0) == ' ') {
                                    c = c.substring(1);
                                }
                                if (c.indexOf(name) == 0) {
                                    return c.substring(name.length, c.length);
                                }
                            }
                            return "";
                        }

        </script>
    </body>
</html>
