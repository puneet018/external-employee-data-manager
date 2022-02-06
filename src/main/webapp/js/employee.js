window.onload = function() {
	
	sub_date = document.getElementById("sub_date");
	hour_input_field = document.getElementById("hour_input_field");
	button_field = document.getElementById("button_field");
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
	var yyyy = today.getFullYear();

	today = yyyy + '-' + mm + '-' + dd;
	console.log(today)
	console.log()
	var updatedTaskDate = sub_date.value;
	
	if(today==updatedTaskDate){
		hour_input_field.style.display="block";
		button_field.style.display="block";
	}
}