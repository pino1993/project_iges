import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import Paper from 'material-ui/Paper'
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton'
import FlatButton from 'material-ui/FlatButton';
import Dialog from 'material-ui/Dialog';


class App extends Component {
  render() {
    return (
      <MuiThemeProvider muiTheme={muiTheme}>
      <Box alignItems="center" justifyContent="center" style={style.boxContainer}>
          <Paper style={style.loginPaper} zDepth={3} >
              <Box column alignItems="center" justifyContent="center" style={style.innerBoxContainer}>
                  <img src={KinetonLogo} style={{ width: '30%' }} />
                  <TextField hintText="Inserisci Username" tabIndex={1} floatingLabelText="Username" underlineStyle={style.underline} hintStyle={style.hint} onChange={this.onChangeEmail.bind(this)} style={{ marginTop: '20px' }} errorText={this.loginState.emailError} />
                  <TextField hintText="Inserisci Password" tabIndex={2} floatingLabelText="Password" type="password" underlineStyle={style.underline} hintStyle={style.hint} onChange={this.onChangePassword.bind(this)} errorText={this.loginState.passwordError} onKeyPress=
                      {(ev) => {
                          if (ev.key === 'Enter') {
                              // Do code here
                              this.onSignInClick()
                              ev.preventDefault();
                          }
                      }} />
                  <Box justifyContent="center" alignItems="center" style={{ height: '90px'}}>
                      <ButtonLoader ref="buttonLoader" tabIndex={3} label="Accedi" primary={true} style={{ marginTop: '80px' }} onClickHandler={this.onSignInClick.bind(this)} loaderStyle0/>
                  </Box>
                  <p style={{ color: '#ED5565', marginTop : '20px' }}>{this.loginState.message}</p>
                  <FlatButton label="Password dimenticata" primary={true} onTouchTap={this.openModal.bind(this)} />
              </Box>
          </Paper>
          {this.createForgottenPasswordModal()}
      </Box>
  </MuiThemeProvider>
    );
  }
}

export default App;
