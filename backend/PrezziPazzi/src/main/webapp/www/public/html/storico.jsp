<%-- 
    Document   : storico
    Created on : 24-gen-2018, 20.25.10
    Author     : giuse
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	 import="com.prezzipazzi.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.prezzipazzi.bean.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	 import="com.prezzipazzi.bean.Catalogo"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
        User user = (User) request.getSession().getAttribute("user");
	Utente utente = (Utente) request.getSession().getAttribute("utente");
        Catalogo cats = (Catalogo) request.getSession().getAttribute("catalogo");
         Catalogo cat = (Catalogo) request.getSession().getAttribute("cat");
%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="head.html"%>
        <title>Eccolaaaa</title>
    </head>
    <body>
       
            <div id="hello"></div>
        <script type="text/babel">
            var Grid = ReactBootstrap.Grid;
            var Row = ReactBootstrap.Row;
            var Col = ReactBootstrap.Col;
            var Button = ReactBootstrap.Button;
            var PageHeader = ReactBootstrap.PageHeader;
            var ButtonGroup = ReactBootstrap.ButtonGroup;
            var DropdownButton = ReactBootstrap.DropdownButton;
            var MenuItem = ReactBootstrap.MenuItem;
            var Thumbnail = ReactBootstrap.Thumbnail;
            var Image = ReactBootstrap.Image;
            var Tab = ReactBootstrap.Tab
            var Nav = ReactBootstrap.Nav
            var NavItem  = ReactBootstrap.NavItem 
            function onSelectAlert(eventKey) {
             alert(`Alert from menu item.\neventKey: ${eventKey}`);
            }
           
            const gridInstance = (
                    
                <Tab.Container id="left-tabs-example" defaultActiveKey="first" style={{marginTop : "100px"}}>
                 
    <Row className="clearfix">


                        <Col sm={10}>
                            <Tab.Content animation>
                                <Tab.Pane eventKey="first">
        <Grid>
            <PageHeader style={{color: "blue",marginTop: "-96px"}}>Elenco prodotti acquistati :  </PageHeader>   
            <Row className="show-grid">
            <Col md={8}>
                <code style={{padding: "0px"}}>
                    <Grid>
                    <form name="purchaseProduct" action="#" method="POST">
                    <input id="valueToPass" type="hidden" name="idProduct" value="" />
                        <% if (cat.size()==0){ %>
                         <h3>Nussun prodotto è stato acquistato</h3>
     
                        <%}%>
                        <% for(int i = 0; i < cat.size(); i+=1) { %>
                         
                                <% if (i < (cat.size()-1)){ %>
                                    <Row>
                                        <Col xs={6} md={5}>
                                            <h3><%=cat.getItem(i).getDescrizione()%></h3>
                                           
                                           
                                        </Col>
                                        <Col xs={6} md={5}>
                                            <% i = i+1;%>
                                            <h3><%=cat.getItem(i).getDescrizione()%></h3>
                                           
                                        
                                        </Col>
                                    </Row>
                                <% } else { %>
                                    
                                    <Row>
                                        <Col xs={6} md={5}>
                                            <h3><%=cat.getItem(i).getDescrizione()%></h3>
                                            
                                        </Col>
                                    </Row>
                                <% } %>
                            
                        <% } %>
                    </form>    
                    </Grid>
                </code>
            </Col>
            <Col md={1}>
            </Col>
            </Row>
        </Grid>
    </Tab.Pane>
    
    
    </Tab.Content>
    </Col>
    </Row>
    </Tab.Container>
            );
    
            ReactDOM.render(gridInstance, document.getElementById("hello"));
        </script>
        
        
    
    
      
    </body>
</html>
