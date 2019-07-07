$(document).ready(function(){
});
function saveMessage() {
   var message=$('#tempMessage').val();
   $.ajax({
      url : "../api/saveMessage.do",
      type : "POST",
      data:"message="+message,
      dataType:"JSON",
      success : function(response) {
         alert(response.resultMessage);
      }
   });
}
