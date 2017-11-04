import getMuiTheme from 'material-ui/styles/getMuiTheme';
import {green100, green500, green700, grey800, grey700} from 'material-ui/styles/colors';

const muiTheme = getMuiTheme({
  fontFamily: 'Roboto',
  palette: {
    primary1Color: green500,
    primary2Color: green700,
    primary3Color: green100,
    textColor: grey800,
    pickerHeaderColor : green500,
    disabledColor: grey700,
  },
}, {
  avatar: {
    borderColor: null,
  },
  snackbar: { actionColor: 'rgb(76, 175, 80)' }
});

export default muiTheme;