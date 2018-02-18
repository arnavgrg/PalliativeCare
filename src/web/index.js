var params = {
  TableName: 'Movies',
  Item: {
    'year' : {N: '001'},
    'title' : {S: 'Richard Roe'},
  }
};

$(document).ready(function(){
  $("#getReq").click(function(){
    $.ajax({
      url: 'https://jm9p05fq33.execute-api.us-west-2.amazonaws.com/beta/PalliativeCare?TableName=TestPatient',
      type: 'POST',
      data: JSON.stringify({"TableName": "TestPatient", "Item": {"username":"Paul", "password":"1111", "interest":"basketball"}}),
      success: function(data) {
        alert("POSTed successfully");
      },
      error: function(xhr, ajaxOptions, thrownError) {
        alert("Error");
      }
    });
  });
});


