/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(
  
  function () {
    
      $( "#txtAankoopdatum" ).datepicker({
        changeMonth: true,
        changeYear: true, 
        dateFormat: "yy-mm-dd"
      });
     $("#txtAankoopdatum").mask("9999-99-99", {placeholder: "____-__-__"});
  }

);
