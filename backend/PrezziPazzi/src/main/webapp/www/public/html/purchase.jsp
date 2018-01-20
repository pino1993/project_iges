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
            final String type = (String) request.getAttribute ("type");
        %>
        <% if (type.equals("success")){ %>
            <h1 style="color: green;margin-top: 50px;"><c:out>${requestScope.message}</c:out></h1>
        <% } else { %>
            <h1 style="color: red;margin-top: 50px;"><c:out>${requestScope.message}</c:out></h1>
        <% } %>
        
          
            <button style="margin-top: 50px;" type="button" name="back" onclick="history.back()">Ritorna alla pagina precedente</button>
            
    
        
        
        
        <script type="text/babel">
            
            var PageHeader = ReactBootstrap.PageHeader;
            
const formInstance = (
        <PageHeader style={{color: "blue",marginTop: "1px"}}>Pagina di riepilogo</PageHeader>
            
);

ReactDOM.render(formInstance,document.getElementById("hello"));
            
            
            
  

         </script>
    </body>
</html>
