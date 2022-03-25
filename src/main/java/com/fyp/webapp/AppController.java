package com.fyp.webapp;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.webapp.bean.Exam;
import com.fyp.webapp.bean.Objectivequestion;
import com.fyp.webapp.bean.Question;
import com.fyp.webapp.bean.Subject;
import com.fyp.webapp.bean.Subjectivequestion;
import com.fyp.webapp.bean.User;
import com.fyp.webapp.repository.ExamRepository;
import com.fyp.webapp.repository.UserRepository;
import com.fyp.webapp.service.ExamService;
import com.fyp.webapp.service.ObjectiveQuestionService;
import com.fyp.webapp.service.QuestionService;
import com.fyp.webapp.service.SubjectService;
import com.fyp.webapp.service.SubjectiveQuestionService;
import com.fyp.webapp.service.UserService;

@Controller
public class AppController {
	
	/**
	 * POST:   Creates a new resource
	 * GET:    Reads a resource
  	 * PUT:    Updates an existing resource
  	 * DELETE: Deletes a resource
	 * **/
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ObjectiveQuestionService objectiveQuestionService;
	
	@Autowired
	private SubjectiveQuestionService subjectiveQuestionService;
	
	
	
	
	
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/signup")
	public String displaySignUpPageForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@GetMapping("/login")
	public String displayLoginPageForm() {
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}*/
		return "login";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		userService.saveUserwithDefaultRole(user);
		return "register_success";
	}
	

	@GetMapping("/admin_Home")
	public String viewAdminHomepage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("exam", new Exam());
		model.addAttribute("subject", new Subject());
		
		List<Exam> listExam = examService.listAllCreateExam();
		List<User> listUsers = userService.listAll();
		List<Subject> listSubject = subjectService.listAllSubject();
		
		Integer totalUserCount = userService.calcTotalUser();
		Integer totalAdminUserCount = userService.calcTotalAdminUser();
		Integer totalLecturerUserCount = userService.calcTotalLecturerUser();
		Integer totalStudentUserCount = userService.calcTotalStudentUser();
		
		model.addAttribute("listUsers",listUsers);
		model.addAttribute("totalUserCount",totalUserCount);
		model.addAttribute("totalAdminUserCount",totalAdminUserCount);
		model.addAttribute("totalLecturerUserCount",totalLecturerUserCount);
		model.addAttribute("totalStudentUserCount",totalStudentUserCount);
		
		model.addAttribute("listExam",listExam);
		
		model.addAttribute("listSubject",listSubject);
		
		return "/admin/adminDashboard";
	}
	
	@GetMapping("/admin_ManageUser")
	public String viewUsersList(@Param("keyword") Model model,String keyword) {
		model.addAttribute("user", new User());
		
		List<User> listUsers = userService.listAll();
		List<User> listUserWithKeywordList = userService.findByKeyword(keyword);
		
		
		if(keyword != null) {
			model.addAttribute("listUsers",listUserWithKeywordList);
		}else {
			model.addAttribute("listUsers",listUsers);
		}
		
		
		return "/admin/adminManageUser";
	}
	
	@GetMapping("/admin_EnrollStudent")
	public String viewAdminEnrollStudentPage(Model model) {
		String role = "Student";
		
		model.addAttribute("user",new User());
		model.addAttribute("exam", new Exam());
		
		List<Exam> listExam = examService.listAllCreateExam();
		List<User> listStudentWithStudentRole = userService.findByStudentRole(role);
		
		model.addAttribute("listExam",listExam);
		model.addAttribute("listStudent",listStudentWithStudentRole);
		
		return "/admin/adminEnrollStudent";
	}
	
	
	@PostMapping("/process_AdminAddUser")
	public String processAdminAddNewUser(User user) {
		userService.saveUserwithDefaultRole(user);
		return "redirect:/admin_ManageUser";
	}
	
	@PostMapping("/process_AdminAddSubject")
	public String processAdminAddSubject(Subject subject) {
		subjectService.saveSubject(subject);
		return "redirect:/admin_Home";
	}
	
	@PostMapping("/process_AdminAddExam")
	public String processAdminAddNewExam(Exam exam) {
		examService.saveExamSchedule(exam);
		return "redirect:/admin_Manage_Schedule";
	}
	
	@GetMapping("/process_AdminEnrollStudent")
	public String processAdminEnrollStudent(@RequestParam("examId")Long examId,@RequestParam("userId") Long userId){
		examService.enrollStudentIntoExam(userId, examId);
		
		return "redirect:/admin_EnrollStudent";
	}
	
	@GetMapping("/admin_Manage_Schedule")
	public String viewAdminManageSchedulePage(@Param("keyword") Model model, String keyword) {
		model.addAttribute("exam", new Exam());
		
		List<Exam> listExam = examService.listAllCreateExam();
		List<Exam> listExamWithFoundKeyword = examService.findByKeyword(keyword);
		List<Subject> listSubject = subjectService.listAllSubject();
		
		model.addAttribute("subjectList",listSubject);
		
		if(keyword!=null) {
			model.addAttribute("listExam",listExamWithFoundKeyword);
		}else {
			model.addAttribute("listExam",listExam);
		}
		
		return "/admin/adminManageSchedule";
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<User> getOne(Long id){
		return userService.getOne(id);
	}
	
	@RequestMapping(value = "/updateUser", method = {RequestMethod.PUT, RequestMethod.GET})
	public String editUserForUpdate(User user){
		userService.update(user);
		return "redirect:/admin_ManageUser"; 
	}
	
	/**TODO:Exam schedule related REST API start here */
	@RequestMapping(value = "/updateExamSchedule", method = {RequestMethod.PUT, RequestMethod.GET})
	public String editUserForUpdate(Exam exam){
		examService.update(exam);
		return "redirect:/admin_Manage_Schedule";
	}
	
	@RequestMapping("/getOneExamSchedule")
	@ResponseBody
	public Optional<Exam> getOneExamSchedule(Long id){
		return examService.getOneExam(id);
	}
	
	
	@GetMapping("/deleteSchedule/{id}")
	public String deleteScheduleFromTableList(@PathVariable(name="id")Long id ){
		examService.deleteSubjectById(id);
		return "redirect:/admin_Manage_Schedule";
	}
	/*end here **/
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUserFromTableList(@PathVariable(name="id") Long id) {
		userRepo.deleteById(id);
		return "redirect:/admin_ManageUser";
	}
	
	
	/*lecturer related start here*/
	@GetMapping("/lecturer_Home")
	public String viewLecturerHomePage(Model model, Principal principal) {
		User user = userService.findUserByEmailPrincipal(principal.getName());
		
		String role = "Student";
		model.addAttribute("user",new User());
		model.addAttribute("exam", new Exam());
		
		List<Exam> listExam = examService.listAllCreateExam();
		List<User> listStudentWithStudentRole = userService.findByStudentRole(role);
		
	
		
		model.addAttribute("profile_Username", user.getUsername());
		
		model.addAttribute("listExam",listExam);
		model.addAttribute("listStudent",listStudentWithStudentRole);
		
		
		return "/lecturer/lecturerHomePage";
	}
	
	@GetMapping("/lecturer_QuestionBank")
	public String viewLecturerQuestionBankPage(@Param("keyword")  Model model, Principal principal,String keyword) {
		Integer amountObjectiveQuestion = questionService.findTotalObjectiveQuestion();
		Integer amountSubjectiveQuestion = questionService.findTotalSubjectiveQuestion();
		
		List<Question> listQuestion = questionService.listAll();
		List<Question> listQuestionWithKeyword = questionService.findByKeyword(keyword);
		List<Objectivequestion> listObjectiveQuestion = objectiveQuestionService.listAll();
		List<Subjectivequestion> listSubjectiveQuestion = subjectiveQuestionService.listAll();
		
		if(keyword!=null) {
			model.addAttribute("listQuestionWithKeyword",listQuestionWithKeyword);
			System.out.println(keyword);
		}else {
			model.addAttribute("listQuestion",listQuestion);
			model.addAttribute("listObjQuestion",listObjectiveQuestion);
			model.addAttribute("listSbjQuestiion",listSubjectiveQuestion);
			
			model.addAttribute("amtObjQuestion",amountObjectiveQuestion);
			model.addAttribute("amtSbjQuestion",amountSubjectiveQuestion);
		}
		
		model.addAttribute("question",new Question());
		model.addAttribute("objectivequestion",new Objectivequestion());
		model.addAttribute("subjectivequestion",new Subjectivequestion());
		/*
		 * principal.getName() will retrieve the current logged-in user name
		 * In this case, the name get from the principal represents the user email, as in this application the program
		 * take the email as the unique identity to register the account
		 * 		 * **/
		User user = userService.findUserByEmailPrincipal(principal.getName());
		
		model.addAttribute("profile_Username", user.getUsername());
		
		return "/lecturer/lecturerQuestionBank";
	}
	
	@GetMapping("/lecturer_ExamGrading")
	public String viewLecturerExamGradingPage(Model model, Principal principal) {
		User user = userService.findUserByEmailPrincipal(principal.getName());
		
		model.addAttribute("profile_Username", user.getUsername());
		return "/lecturer/lecturerExamGrading";
	}
	
	@PostMapping("/lecturer_AddNewQuestion")
	public String processAddNewObjectiveQuestion(@RequestParam("questionType") String questionType, Model model,Principal principal, Objectivequestion objectiveQuestion, Subjectivequestion subjectiveQuestion, Question question) {
		model.addAttribute("objectivequestion",objectiveQuestion);
		model.addAttribute("subjectivequestion",subjectiveQuestion);
		
		System.out.println(questionType);
		
		if(questionType.equals("objective")) {
			/*
			 * principal.getname equals to the user email, based on the current logged-in user email, retrieve its own user details 
			 * **/
			User user = userService.findUserByEmailPrincipal(principal.getName());
			String current_loggedIn_user_username = user.getUsername();
			
			System.out.println("this function is accessed! Congrat, boy.");
			
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur")); // Use KL's time zone to format the date
			String msiaFormattedTimeNow = df.format(date);
			
			
			//below is set the name and the time created of the user that create the new question 
			question.setCreatedBy(current_loggedIn_user_username);
			question.setCreatedTime(msiaFormattedTimeNow);
			question.setQuestionType("Objective Question");
			
			question.setObjQuestion(objectiveQuestion);
			//objectiveQuestion.setQuestion(question);
			
			questionService.saveQuestion(question);
			 
			objectiveQuestionService.saveNewCreatedObjQuestion(objectiveQuestion);
			
		}
		
		if(questionType.equals("subjective")) {
			User user = userService.findUserByEmailPrincipal(principal.getName());
			String current_loggedIn_user_username = user.getUsername();
			
			Date date = new Date(); //get the date
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //parse the date into this format
			df.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur")); // set the retrieved time zone to KL's time zone to format the date - 
			String msiaFormattedTimeNow = df.format(date); //format the retrieved date time with KL time zone
			
			//below is set the name and the time created of the user that create the new question 
			question.setCreatedBy(current_loggedIn_user_username);
			question.setCreatedTime(msiaFormattedTimeNow);
			question.setQuestionType("Subjective Question");
			
			question.setSbjQuestion(subjectiveQuestion);
			//subjectiveQuestion.setQuestion(question);
			
			questionService.saveQuestion(question);
			 
			subjectiveQuestionService.saveNewCreatedSbjQuestion(subjectiveQuestion);
		}
		
		return "redirect:/lecturer_QuestionBank";
	}
	
	
	@RequestMapping("/getOneQuestion")
	@ResponseBody
	public Optional<Question> getOneQuestion(Long id){
		return questionService.getOneQuestion(id);
	}
	
	@GetMapping("/student_Home")
	public String viewStudentHomePage(Model model) {
		return "/student/studentHomePage";
	}
	
	
}
