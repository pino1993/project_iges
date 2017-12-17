<%-- 
    Document   : home
    Created on : 11-dic-2017, 11.56.26
    Author     : andrea
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.prezzipazzi.bean.Utente"%>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="head.html"%>
        <title>PrezziPazzi-Home</title>
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
                        <Col sm={2}>
                            <Nav bsStyle="pills" stacked>
                                <NavItem eventKey="first">
                                    Catalogo
                                </NavItem>
                                <NavItem eventKey="second">
                                    Profilo Utente
                                </NavItem>
                             </Nav>
                        </Col>
                        <Col sm={10}>
                            <Tab.Content animation>
                                <Tab.Pane eventKey="first">
        <Grid>
            <Row className="show-grid">
            <Col md={9}>
                <PageHeader style={{color: "blue",marginTop: "-96px"}}>Benvenuto in PrezziPazzi.com <small><%=utente.getNome()%> <%=utente.getCognome()%> </small></PageHeader>
            </Col>
            <Col md={1}>
                <a title="Logout <%=utente.getEmail()%>"><Image onClick={() => logout()} style={{height: "60px",marginTop: "-77px"}} src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Circle-icons-profle.svg/512px-Circle-icons-profle.svg.png" responsive circle /></a>
            </Col>
            </Row>
            <Row className="show-grid">
            <Col md={8}>
                <code style={{padding: "0px"}}>
                    <Grid>
                    <form name="purchaseProduct" action="#" method="POST">
                    <input id="valueToPass" type="hidden" name="idProduct" value="" />
                        <Row>
                            <Col xs={6} md={5}>
                                <Thumbnail src="/PrezziPazzi/www/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        
                                        <Button bsStyle="primary" value="prod1" name="bt1" onClick={() => purchase(0)}>Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                       
                                    </p>
                                </Thumbnail>
                            </Col>
                            <Col xs={6} md={5}>
                                <Thumbnail src="/PrezziPazzi/www/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        <Button bsStyle="primary" value="prod2" name="bt2" onClick={() => purchase(0)} >Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                    </p>
                                </Thumbnail>
                            </Col>
                        </Row>
                         <Row>
                            <Col xs={6} md={5}>
                                <Thumbnail src="/PrezziPazzi/www/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        <Button bsStyle="primary" onClick={() => purchase(3)}>Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                    </p>
                                </Thumbnail>
                            </Col>
                            <Col xs={6} md={5}>
                                <Thumbnail src="/PrezziPazzi/www/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        <Button bsStyle="primary" onClick={() => purchase(2)}>Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                    </p>
                                </Thumbnail>
                            </Col>
                        </Row>
                    </form>    
                    </Grid>
                </code>
            </Col>
            <Col md={1}>
            </Col>
            </Row>
        </Grid>
    </Tab.Pane>
    <Tab.Pane eventKey="second">
        <Grid>
            <Row className="show-grid">
            <Col md={9}>
                <PageHeader style={{color: "blue",marginTop: "-96px"}}>Benvenuto in PrezziPazzi.com <small><%=utente.getNome()%> <%=utente.getCognome()%> </small></PageHeader>
            </Col>
            <Col md={1}>
                <a title="Logout <%=utente.getEmail()%>"><Image onClick={() => logout()} style={{height: "60px",marginTop: "-77px"}} src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Circle-icons-profle.svg/512px-Circle-icons-profle.svg.png" responsive circle /></a>
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
        <script>
                    function purchase(num) {
                        console.log("Purchase ", document.getElementById("valueToPass").value)
                        document.forms[0].action = 'Purchase';
                        document.getElementById("valueToPass").value = num;
                        document.forms[0].submit();
                        return true;
                    }
                    
                     function logout() {
                        document.cookie = "usernamePrezziPazzi" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                        document.cookie = "passwordPrezziPazzi" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                        document.forms[0].action = 'Logout';
                        document.getElementById("valueToPass").value = "";
                        document.forms[0].submit();
                        return true;
                    }
        </script>
    </body>
</html>
