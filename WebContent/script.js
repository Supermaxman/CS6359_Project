function regValidate() {

	var username = document.forms["regform"]["username"].value;
	var password = document.forms["regform"]["password"].value;
	var rpassword = document.forms["regform"]["retry-password"].value;
	
	if (username == "") {
        alert("username must be filled out");
        document.forms["regform"]["username"].focus();
        return false;
    }else if (password== "") {
        alert("password must be filled out");
        document.forms["regform"]["password"].focus();
        return false;
    }else if (rpassword == "") {
        alert("retry-password must be filled out");
        document.forms["regform"]["retry-password"].focus();
        return false;
    }else if(password != rpassword){
    	alert("password doesnt match");
        document.forms["regform"]["password"].focus();
        return false;
    }else{
    	return true;
    }
}
function loginValidate(){
	var username = document.forms["loginform"]["username"].value;
	var password = document.forms["loginform"]["password"].value;
	
	if (username == "") {
        alert("username must be filled out");
        document.forms["loginform"]["username"].focus();
        return false;
    }else if (password== "") {
        alert("password must be filled out");
        document.forms["loginform"]["password"].focus();
        return false;
    }else if (rpassword == "") {
        alert("retry-password must be filled out");
        document.forms["loginform"]["retry-password"].focus();
        return false;
    }else if(password != rpassword){
    	alert("password doesnt match");
        document.forms["loginform"]["password"].focus();
        return false;
    }else{
    	return true;
    }
}
function paintValidate() {
	var name = document.forms["newpaintform"]["name"];
	var description = document.forms["newpaintform"]["description"];
	var price = document.forms["newpaintform"]["price"];
	var canvasType = document.forms["newpaintform"]["canvasType"];
	var paintType = document.forms["newpaintform"]["paintType"];
	var length = document.forms["newpaintform"]["length"];
	var width = document.forms["newpaintform"]["width"];
	
	if (name.value == "") {
        alert("Name must be filled out");
        name.focus();
        return false;
    }else if (description.value == "") {
        alert("Description must be filled out");
        description.focus();
        return false;
    }else if (price.value == "") {
        alert("Price must be filled out");
        price.focus();
        return false;
    }else if (canvasType.value == "") {
        alert("CanvasType must be filled out");
        canvasType.focus();
        return false;
    }else if (paintType.value == "") {
        alert("PaintType must be filled out");
        paintType.focus();
        return false;
    }else if (length.value == "") {
        alert("Length must be filled out");
        length.focus();
        return false;
    }else if (width.value == "") {
        alert("Width must be filled out");
        width.focus();
        return false;
    }else{
    	return true;
    }
}