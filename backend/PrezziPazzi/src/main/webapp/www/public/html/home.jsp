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
        <title>PrezziPazzi-Home</title>
    </head>
    <body>    
        <div id="hello"></div>
        <script type="text/babel">
            var Grid = ReactBootstrap.Grid;
            var Row = ReactBootstrap.Row;
            var Col = ReactBootstrap.Col;
            var Jumbotron = ReactBootstrap.Jumbotron;
            var Button = ReactBootstrap.Button;
            var PageHeader = ReactBootstrap.PageHeader;
            var ButtonGroup = ReactBootstrap.ButtonGroup;
            var DropdownButton = ReactBootstrap.DropdownButton;
            var MenuItem = ReactBootstrap.MenuItem;
            
            function onSelectAlert(eventKey) {
             alert(`Alert from menu item.\neventKey: ${eventKey}`);
            }
            
            const gridInstance = (
   
        <Grid>
            <Row className="show-grid">
            <Col md={12}>
                <PageHeader>Example page header <small>Subtext for header</small></PageHeader>
            </Col>    
            </Row>
            <Row className="show-grid">
            <Col md={2}>
                <ul className="dropdown-menu open" style={{marginRight: '5px',position: 'static',display: "block",clear: "left"}}>
                    <MenuItem header>Header</MenuItem>
                    <MenuItem>link</MenuItem>
                    <MenuItem divider />
                    <MenuItem header>Header</MenuItem>
                    <MenuItem>link</MenuItem>
                    <MenuItem disabled>disabled</MenuItem>
                    <MenuItem title="See? I have a title.">
                        link with title
                    </MenuItem>
                    <MenuItem eventKey={1} href="#someHref" onSelect={onSelectAlert}>
                        link that alerts
                    </MenuItem>
                </ul>
            </Col>
            <Col md={8}>
                <code>
                    <Jumbotron>
                        <h1>Hello, world!</h1>
                        <p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
                        <p><Button bsStyle="primary">Learn more</Button></p>
                    </Jumbotron>
                </code>
            </Col>
            <Col md={1}>
            </Col>
            </Row>
        </Grid>
                    );

            ReactDOM.render(gridInstance, document.getElementById("hello"));
        </script>
    </body>
</html>
