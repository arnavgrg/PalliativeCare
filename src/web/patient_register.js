$(document).ready(function(){
    $("#patient_submit").click(function(){
      var patient_username = $("#patient_username").val();
      var patient_password = $("#patient_password").val();
      var patient_info = $("#patient_info").val();
      $.ajax({
        url: 'https://jm9p05fq33.execute-api.us-west-2.amazonaws.com/beta/PalliativeCare?TableName=TestPatient',
        type: 'POST',
        data: JSON.stringify({"TableName": "TestPatient", "Item": 
          {"username": patient_username,
           "password": patient_password,
           "info": patient_info
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

