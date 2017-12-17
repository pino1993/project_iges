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
            var Button = ReactBootstrap.Button;
            var PageHeader = ReactBootstrap.PageHeader;
            var ButtonGroup = ReactBootstrap.ButtonGroup;
            var DropdownButton = ReactBootstrap.DropdownButton;
            var MenuItem = ReactBootstrap.MenuItem;
            var Thumbnail = ReactBootstrap.Thumbnail;
            
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
                <code style={{padding: "0px"}}>
                    <Grid>
                    <form name="purchaseProduct" action="#" method="POST">
                    <input id="valueToPass" type="hidden" name="idProduct" value="" />
                        <Row>
                            <Col xs={6} md={5}>
                                <Thumbnail src="https://sslcom.github.io/bootsharp/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        
                                        <Button bsStyle="primary" value="prod1" name="bt1" onClick={() => purchase(0)}>Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                       
                                    </p>
                                </Thumbnail>
                            </Col>
                            <Col xs={6} md={5}>
                                <Thumbnail src="https://sslcom.github.io/bootsharp/assets/thumbnaildiv.png" alt="242x200">
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
                                <Thumbnail src="https://sslcom.github.io/bootsharp/assets/thumbnaildiv.png" alt="242x200">
                                    <h3>Thumbnail label</h3>
                                    <p>Description</p>
                                    <p>
                                        <Button bsStyle="primary" onClick={() => purchase(3)}>Button</Button>&nbsp;
                                        <Button bsStyle="default">Button</Button>
                                    </p>
                                </Thumbnail>
                            </Col>
                            <Col xs={6} md={5}>
                                <Thumbnail src="https://sslcom.github.io/bootsharp/assets/thumbnaildiv.png" alt="242x200">
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
        </script>
    </body>
</html>
