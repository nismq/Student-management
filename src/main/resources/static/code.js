function validateEnrollmentForm(){   
    var studentId = document.getElementById("studentId").value;
    var courseId = document.getElementById("courseId").value
    var alertfield = document.getElementById("alertfieldenrollment");
    if(courseId == null || courseId == "" || studentId == null || studentId == ""){
        alertfield.value = "Enter student id and course id!"
        return false;
    }
    return true;
}

function validateCourseForm(){
    var courseName = document.getElementById("courseName").value;
    var teacher = document.getElementById("teacher").value;
    var classroom = document.getElementById("classroom").value;
    var alertfield = document.getElementById("alertfieldcourse");
    if(courseName == null || courseName == "" || teacher == null || teacher =="" || classroom ==null || classroom==""){
        alertfield.value = "Fill all fields to add course!";
        return false;
    }
    return true;
}

function validateStudentForm(){
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value
    var alertfield = document.getElementById("alertfieldstudent");
    if(fname == null || fname == "" || lname == null || lname == ""){
        alertfield.value = "Enter first and last name to add student!"
        return false;
    }
    return true;
}

function getForm(){
    var select = document.getElementById("studentOrCourse").value;
    var id = document.getElementById("getId").value;
    var alertfield = document.getElementById("alertfieldGetForm");
    if(id == "" || id == null){
        alertfield.value = "Add id!"
        return false;
    }
    
    if(select == "students"){
        document.forms.getForm.action = "/courses/" + id;
        return true;
    }

    if(select == "courses"){
        document.forms.getForm.action = "students/" + id;
        return true;
    }
    return false;
}