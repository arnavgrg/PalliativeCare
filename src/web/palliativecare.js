var myCredentials = new AWS.CognitoIdentityCredentials({IdentityPoolId:'AKIAI2DYCWPH7YJ4FXZQ'});
var myConfig = new AWS.Config({
  credentials: myCredentials, region: 'us-west-2'
});