package com.salesmanager.shop.store.api.v0.utility;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * In progress entry point for receiving various hooks and callback
 * from various web AP.
 * 
 * The first draft implementation is for facebook conversations
 * with messenger. The FB messanger API supports the possibility to track
 * all conversations messages which are sent back to the webhook. It accepts request parameters
 * and respond back simple acknowledge answer.
 * 
 *  A good enhancement would be to have sub-implementation by webhook so this controller could
 *  delegate to a worker according to a request parameter. But ... let's start exploring with FB first.
 * @author carlsamson
 *
 */
@Controller
@RequestMapping("/services")
public class CallbackController {
	
	private static final String VERIFY_MESSENGER_WEBHOOK = "VERIFY_MESSENGER_WEBHOOK";
	
	@RequestMapping( value="/public/callBack", method=RequestMethod.GET)
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String verificationToken = request.getParameter("hub.verify_token");
		
		if(!StringUtils.isBlank(verificationToken)) {

			if(verificationToken.equals(VERIFY_MESSENGER_WEBHOOK)) {
				String replyToken = request.getParameter("hub.challenge");

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(replyToken);
				response.getWriter().flush();
				response.getWriter().close();
			}
			
		}
		return;
	}
	
	public String verifyCallBack() {
		return null;
	}

}
