
  $( function() {
    var dialog;
    
    // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
                
        
     
    $(".dialoog").hide();  
 
    dialog = $( ".dialoog" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: false,
      closeOnEscape:true,
      buttons: {
        "Reserveren": function() {
            $("#doen").submit();
        }
        
      },
      close: function() {
        dialog.dialog("close");
       
      }
    });
 

        
    
      $( ".create" ).on( "click", function(event) {
          event.preventDefault();
        dialog.dialog( "open" );
    });
  } 
          );