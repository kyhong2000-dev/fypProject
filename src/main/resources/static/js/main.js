/** find the status id element from html and assign it the incomplete status value */
//document.getElementById("status").defaultValue = "Incomplete";

/*once button in form clicked, trigger the webpage to submit the form*/
$(function() {
	$("#submitAddUserFormBtn").click(function() {
		$("#adminAddUserForm").submit();
	});
	$("#submitAddExamFormBtn").click(function() {
		$("#adminAddExamForm").submit();
	});
	$("#submitAddSubjectFormBtn").click(function() {
		$("#adminAddSubjectForm").submit();
	});
});

/* Pass the id of chosne desired deleted row*/
$('document').ready(function(){
	$('.table #deleteButton').on('click',function(event){ //delete user from click 		
		event.preventDefault(); //prevent the th:href of the delete URL to be submit
		var href = $(this).attr('href'); //retrieve the delete ID URL of the clicked row 
		$('#deleteUserModal #delRef').attr('href',href); //pass and replace the delete ID URL of the clicked row to the modal confirm delete button 
		$('#deleteUserModal').modal();//show the modal
	});	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault(); //prevent the th:href of the edit URL to be submit
		var href = $(this).attr('href'); //retrieve the edit ID URL of the clicked row 
		
		$.get(href,function(user,status){
			$('#idEdit').val(user.id);
			$('#passwordEdit').val(user.password);
			$('#emailEdit').val(user.email);
			$('#usernameEdit').val(user.username);
			$('#roleEdit').val(user.role);
			$('#genderEdit').val(user.gender);
			$('#edu-instEdit').val(user.eduInst);
		});
	 
		$('#editUserModal').modal();//show the modal
	});
	
	$('.table #editExamButton').on('click',function(event){		
		event.preventDefault(); //prevent the th:href of the edit URL to be submit
		var href = $(this).attr('href'); //retrieve the edit ID URL of the clicked row 
	
		$.get(href,function(exam,status){
			$('#examIDEdit').val(exam.id);
			$('#subjectEdit').val(exam.subject);
			$('#dateEdit').val(exam.date);
			$('#durationEdit').val(exam.duration);
			$('#startTimeEdit').val(exam.startTime);
			$('#endTimeEdit').val(exam.endTime);
			$('#statusEdit').val(exam.status);
			$('#questionTypeEdit').val(exam.questionType);
			$('#scoreEdit').val(exam.score);
		});
	 
		$('#editUserModal').modal();//show the modal
	});
	
	$('.table #enrollStudentButton').on('click',function(event){		
		event.preventDefault(); //prevent the th:href of the edit URL to be submit
		var href = $(this).attr('href'); //retrieve the edit ID URL of the clicked row 
	
		$.get(href,function(exam,status){
			$('#examId').val(exam.id);
		});
	 
		$('#enrollStudentModal').modal();//show the modal
	});
	
	$("#questionAnswer").richText();
	
	/*$('.table #studentListDetailBtn').on('click',function(event){		
		event.preventDefault(); //prevent the th:href of the edit URL to be submit
		var href = $(this).attr('href'); //retrieve the edit ID URL of the clicked row 
		$('#studentListModal .modal-body').load(href,function(user){
           	$('#studentName').val(user.username);
			$('#studentEmail').val(user.email);
			$('#studentEduInst').val(user.eduInst);
         
        });
        $('#studentListModal').modal();
	
		//$('#studentListModal').modal();//show the modal
	});*/
	
	
	$('.table #deleteExamSchButton').on('click',function(event){		
		event.preventDefault(); //prevent the th:href of the delete URL to be submit
		var href = $(this).attr('href'); //retrieve the delete ID URL of the clicked row 
		$('#deleteExamSchModal #delExamSchRef').attr('href',href); //pass and replace the delete ID URL of the clicked row to the modal confirm delete button 
		$('#deleteExamSchModal').modal();//show the modal
	});	
})

$("#clear-btn").on("click", function () {
    $('#categories').prop('selectedIndex',0);
    $("#objective").hide();
    $("#subjective").hide();
});

var ct = document.getElementById("qType");
ct.addEventListener("change", function() {
  var elems = document.querySelectorAll('#objective,#subjective')
  for (var i = 0; i < elems.length; i++) {
    elems[i].style.display = 'none'
  }
  
  if(this.selectedIndex === 1){
	  document.querySelector("#objective").style.display = 'block';
	  document.getElementById('questionType').value = 'objective';  
  }
  if (this.selectedIndex === 2){
	  document.querySelector("#subjective").style.display = 'block';
	  $('#addSbjQuestionForm #questionType').val('subjective');
	  //document.getElementById('questionType').value = 'subjective';
  }
}, false);

var co = document.getElementById("choices");
co.addEventListener("change", function() {
  if(co.options[co.selectedIndex].text == "Objective Question"){
	  document.getElementById('keyword').value = 'Objective Question';
  }else if(co.options[co.selectedIndex].text == "Subjective Question"){
	document.getElementById('keyword').value = 'Subjective Question'; 
  }else{
  	document.getElementById('keyword').value = '';
  }
}, false);


function showPassword() {
	var psw = document.getElementById("password");
	if (psw.type === "password") { //verify whether the type of psw is password or not
		psw.type = "text"; //if yes, turn it as text to make the password visible
	} else {
		psw.type = "password"; //otherwise, remain as password
	}
}

function clearFilter() {
	window.location = '/admin_ManageUser';
}

function clearScheudleListFilter(){
	window.location = '/admin_Manage_Schedule';
}
