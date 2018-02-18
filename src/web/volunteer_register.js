$(document).ready(function(){
    $("#volunteer_submit").click(function(){
      var volunteer_username = $("#volunteer_username").val();
      var volunteer_password = $("#volunteer_password").val();
      var volunteer_info = $("#volunteer_info").val();
      $.ajax({
        url: 'https://jm9p05fq33.execute-api.us-west-2.amazonaws.com/beta/PalliativeCare?TableName=TestVolunteer',
        type: 'POST',
        data: JSON.stringify({"TableName": "TestVolunteer", "Item": 
          {"username": volunteer_username,
           "password": volunteer_password,
           "info": volunteer_info
          }
        }),
        success: function(data) {
          alert("POSTed successfully");
        },
        error: function(xhr, ajaxOptions, thrownError) {
          alert("Error");
        }
      });
    });
  });

