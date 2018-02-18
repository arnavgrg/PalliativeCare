var AWS = require('aws-sdk');

var myConfig = new AWS.Config({
	accessKeyId: 'AKIAIKAYQ5SLD5WJYL2A',
	secretAccessKey: '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', 
	region: 'us-west-2',
	dynamodb: '2011-12-05'
});

AWS.config.logger = console;
AWS.config.update({accessKeyId: 'AKIAIKAYQ5SLD5WJYL2A',
	secretAccessKey: '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', 
	region: 'us-west-2',
	dynamodb: '2011-12-05'});

ddb = new AWS.DynamoDB({apiVersion: '2012-10-08'});

var params = {
  TableName: 'Movies',
  Item: {
    'year' : {N: '001'},
    'title' : {S: 'Richard Roe'},
  }
};

// Call DynamoDB to add the item to the table
ddb.putItem(params, function(err, data) {
  if (err) {
    console.log("Error", err);
  } else {
    console.log("Success", data);
  }
});



