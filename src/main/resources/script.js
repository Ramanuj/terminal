/**
 * 
 */
$( document ).ready(function() {
	console.log('on ready');
  	document.getElementById("demo").innerHTML = "Javascript is working!!!";

	$("#addtextbutton").click(function() {
		appendtext("Sample Text appended");
	});

});

function appendtext(message){
	console.log("message -> " + message);

	$("#consolebody").append( "<div>" + message + "</div>" );
}

function clearConsole(){
	$("#consolebody").empty();
}

