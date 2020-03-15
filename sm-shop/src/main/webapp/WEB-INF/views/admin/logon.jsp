<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>

	<%
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<%@page contentType="text/html"%>
	<%@page pageEncoding="UTF-8"%>





	<head>
		<meta http-equiv="Pragma" content="no-cache">
			<meta http-equiv="expires" content="0">
				<title><s:message code="label.storeadministration"
						text="Store administration" />
				</title>




<style type=text/css>
#logon {
	margin: 0px auto;
	width: 550px
}

#login-box {
	width: 333px;
	height: 352px;
	padding: 58px 76px 0 76px;
	color: #ebebeb;
	font: 12px Arial, Helvetica, sans-serif;
	background: url('<c:url value="/resources/img/admin/login-box-backg.png" />') no-repeat left top;
}

#login-box h2 {
	padding: 0;
	margin: 0;
	color: #ebebeb;
	font: bold 36px "Calibri", Arial;
	border-bottom: 2px solid;
	padding-bottom: 0px;
}

#login-box {
	margin-left: 30px;
}

#controls {
	margin-left: -50px;
	margin-top: 30px;
}




</style>




				<link
					href="<c:url value="/resources/css/bootstrap/css/sm-bootstrap.css" />"
					rel="stylesheet" />
				<link href="<c:url value="/resources/css/shopizer-admin.css" />"
					rel="stylesheet" />


				<style type=text/css>
.sm label {
	color: #EBEBEB;
	font-size: 16px;
}

.sm a {
	color: #EBEBEB;
	font-size: 16px;
}





</style>

		<script src="<c:url value="/resources/js/bootstrap/jquery.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-cookie.js"/>"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js" />"></script>




				<script language="javascript">
				
				function getUserInformation() {
					 // get the form values 
					$('#securityQtn1Select').empty();
					$('#securityQtn2Select').empty();
					$('#securityQtn3Select').empty();
					$('#answer1').val('');
					$('#answer2').val('');
					$('#answer3').val('');
					var userName = $('#username').val();
		        if(!userName){
				    alert("<s:message code="message.username.required" text="User name is required" />");
			    }else{
					$.ajax({
							type: 'POST',
							dataType: "json",
							url: "<c:url value="/admin/users/resetPassword.html" />",
							data: "username="+ userName ,
							success: function(response) { 
								 var msg = isc.XMLTools.selectObjects(response, "/response/statusMessage");
								 var status = isc.XMLTools.selectObjects(response, "/response/status");
								 if(status==0 || status ==9999) {
									 $("#getPassword").modal('hide'),
									 $('#getSecurityQtn').modal({
    								 	backdrop: true
    						   		 })
    						
    								 var data = isc.XMLTools.selectObjects(response, "/response/data");
    						  	     if(data && data.length>0) {
    						  	     
    						  	     	$('#question1').text(data[0].question1);
		    						  	$('#question2').text(data[0].question2);
		    						  	$('#question3').text(data[0].question3);

    								 } 
								} else {
									if(msg!=null && msg !='') {
										alert(msg);
									}
								}
								
							},
							error: function(jqXHR,textStatus,errorThrown) { 
								alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
							}

							}); 
				}
 		        				}
				
				
				function doSecurityQtnSubmit() {
					
					var answer1 = $('#answer1').val();
					var answer2 = $('#answer2').val();
					var answer3 = $('#answer3').val();
				   		 
					 if(!answer1){
					    alert("<s:message code="security.answer.question1.message" text="Please answer to security question 1"/>");
					    
					 }else if(!answer2){
						 alert("<s:message code="security.answer.question2.message" text="Please answer to security question 2"/>");
						
					 }else if(!answer3){
						 alert("<s:message code="security.answer.question3.message" text="Please answer to security question 3"/>");
					   
					 }else{					 

						 $.ajax({
									type: 'POST',
									dataType: "json",
									url: "<c:url value="/admin/users/resetPasswordSecurityQtn.html" />",
									data: "answer1="+ answer1+"&answer2="+ answer2+"&answer3="+ answer3,
									success: function(response) { 
										 //console.log("responcesajid "+response);
										 //console.log(response);
										 var msg = isc.XMLTools.selectObjects(response, "/response/statusMessage");
										 var status = isc.XMLTools.selectObjects(response, "/response/status");
										 if(status==0 || status ==9999) {
											 $("#getSecurityQtn").modal('hide')
											  $('#finalWindow').modal({
		    									 backdrop: true
		    						   		 }) 
					 						 $("#finaltext").val (msg);
											 var div = document.getElementById('finaltext1');
											 div.innerHTML =  msg;		    						
		    								 var data = isc.XMLTools.selectObjects(response, "/response/data");
										  } else {
											if(msg!=null && msg !='') {
												 $("#getSecurityQtn").modal('hide')
												  $('#finalWindow').modal({
			    								 	backdrop: true
			    						   		 	}) 
						 						 $("#finaltext").val (msg);
												var div = document.getElementById('finaltext1');
												div.innerHTML =  msg;						 
											}
										}
										
									}  
								});
					 
				 }
			}

	$(document)
			.ready(
					function() {
						
						$('#changePassword').click(function() {
							//$('#getPassword').show();
							$('#username').val('');
							$('#getPassword').modal({
    							backdrop: true
    						})
						})
						

						var username = $.cookie('usernamecookie');
						if (username != null && username != '') {
							$('#username').val(username);
							$('#remember').attr('checked', true);
						}


						$("#formSubmitButton")
								.click(
										function() {
											
											

											var hasError = false;
											$('#username_help').html("");
											$('#password_help').html("");
											
											
											if ($('#remember').attr('checked')) {
												$.cookie('usernamecookie', $(
														'#username').val(), {
													expires : 1024,
													path : '/'
												});
											} else {
												$.cookie('usernamecookie',
														null, {
															expires : 1024,
															path : '/'
														});
											}
											if ($.trim($('#username').val()) == '') {
												hasError = true;
												$('#username_help')
														.html(
																"<font color='red' size='4'><strong>*</strong></font>");
											}

											if ($.trim($('#password').val()) == '') {
												hasError = true;
												$('#password_help')
														.html(
																"<font color='red' size='4'><strong>*</strong></font>");
											}

											if (!hasError) {
												$("#logonForm").submit();
											}

										});

					});
</script>
	</head>

	<body>

		<div id="tabbable" class="sm">

			<br />
			<br />

			<div id=logon>









				<div class="row">
					<c:if test="${not empty param.login_error}">
						<div class="alert alert-error">
							<s:message code="errors.invalidcredentials"
								text="Invalid username or password" />
						</div>
					</c:if>
				</div>



				<div id="login-box">


					<div class="row">
						<div style="float: left; width: 180px;">
							<p class="lead">
								<s:message code="button.label.logon" text="Logon" />
							</p>
						</div>
						<div style="float: right;">
							<img alt="go to www.shopizer.com"
								src="<c:url value="/resources/img/shopizer_small.png" />">
						</div>
					</div>

					<div class="row">
						<div id="controls">

							<form method="post" id="logonForm" class="form-horizontal" action="<c:url value="/admin/performUserLogin"/>">
								<div class="control-group">
									<label class="control-label" for="inputUser">
										<s:message code="label.username" text="Username" />
									</label>
									<div class="controls">
										<input type="text" id="username" name="username"
											placeholder="<s:message code="label.username" text="Username"/>">
											<span id="username_help" class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPassword">
										<s:message code="label.password" text="Password" />
									</label>

									<div class="controls">
										<input type="password" id="password" name="password"
											placeholder="<s:message code="label.password" text="Password"/>">
											<span id="password_help" class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="checkbox">
											<input type="checkbox" id="remember">
												<s:message code="label.logonform.rememberusername"
													text="Remember my user name" />
												<br/>
												<a href="#" id="changePassword"><s:message code="label.logonform.forgotpassword" text="Forgot Password"/>?</a>
										</label>
										<a href="#" class="btn" id="formSubmitButton"> <s:message
												code="button.label.logon" text="button.label.submit2" /> </a>
									</div>
								</div>
							</form>
						</div>
					</div>


		
					<!-- code for reset password-username prompt   sajid shajahan -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade" id="getPassword" style="display: none;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							×
						</button>
						<h3 id="myModalLabel" style="color:#333333;">
							<s:message code="label.logonform.resetpassword" text="Password reset"/>
						</h3>
					</div>
					<form method="post" id="resetPasswordForm" class="form-horizontal"
								action="#">
					<div class="modal-body">
						<p>

								<div class="control-group">
									<label class="control-label" for="inputUser" style="color:#333333;">
										<s:message code="label.username" text="Username" />
									</label>
									<div class="controls">
										<input type="text" id="username" name="username"
											placeholder="<s:message code="label.username" text="Username"/>">
											

											<span id="username_help" class="help-inline"></span>
									</div>
								</div>
								

							</form>
						</p>
					</div>
					<div class="modal-footer">
									<div class="controls">
										<input type="button" value="<s:message code="label.generic.next" text="Next" />" onclick="getUserInformation()" class="btn" >
									</div>
								</div>
					</div>
					</form>
				</div>
				
				


				<!-- code for reset password-security question prompt   sajid shajahan -->
				<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade" id="getSecurityQtn" style="display: none;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							×
						</button>
						<h3 id="myModalLabel" style="color:#333333;">
							<s:message code="label.logonform.securityquestion" text="Password reset"/>
						</h3>
					</div>
					<form method="post" id="resetPasswordForm" class="form-horizontal"
								action="#">
					<div class="modal-body">
						<p>
								
								<div class="control-group">
								
									<label class="control-label" for="inputUser" style="color:#333333;" id="question1">
										<s:message code="security.question1" text="" />
									</label>
									<div class="controls">
										<input type="text" id="answer1" name="answer1"
											placeholder="<s:message code="security.answer.question1.message" text="answer1"/>">
											

									</div>
									
									
								</div>
								
								<div class="control-group">
									<label class="control-label" for="inputUser" style="color:#333333;" id="question2">
										<s:message code="security.question2" text="" />
									</label>
									<div class="controls">
										<input type="text" id="answer2" name="answer2"
											placeholder="<s:message code="security.answer.question2.message" text="answer2"/>">
											

									</div>
								</div>
									
									<div class="control-group">
									<label class="control-label" for="inputUser" style="color:#333333;" id="question3">
										<s:message code="security.question3" text="" />
									</label>
									<div class="controls">
										<input type="text" id="answer3" name="answer3"
											placeholder="<s:message code="security.answer.question3.message" text="answer3"/>">
										
											

									</div>
								</div>
																							
							</form>
						</p>
					</div>
					<div class="modal-footer">
									<div class="controls">
										<!-- <a href="#" class="btn" id="passwordResetSubmitButton"> 
										<s:message code="button.label.submit2" text="Submit" /> </a> -->
										<input type="button" value="Submit" onclick="doSecurityQtnSubmit()" class="btn" >
									</div>
								</div>
					</div>
					</form>
				</div>



				<!-- code for reset password-final window   sajid shajahan -->
				<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade" id="finalWindow" style="display: none;">
				<!--<div class="modal" id="getPassword" tabindex="-1" role="dialog" class="modal hide fade" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">-->
					<div class="modal-header">
						  <button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							×
						</button>
						<h3 id="myModalLabel" style="color:#333333;">
						<s:message code="" text="Shopizer"/>
						</h3>
					</div>
					<form method="post" id="resetPasswordForm" class="form-horizontal"
								action="#">
					<div class="modal-body">
						<p>
								<h3><center><div id="finaltext1"></div></center></h3>
								<!-- <div class="control-group">
								
									<label class="control-label" for="inputUser" style="color:#333333;">
									<h3><div id="finaltext1"></div></h3>
									</label>
									<div class="controls">

									</div>
									
									
								</div>
								 -->
																							
							</form>
						</p>
					</div>
					<div class="modal-footer">
									<div class="controls">
										<button data-dismiss="modal" class="btn">Close</button>
									</div>
								</div>
					</div>
					</form>
				</div>
				




			</div>
	</body>
</html>