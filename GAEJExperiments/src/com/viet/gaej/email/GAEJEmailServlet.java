package com.viet.gaej.email;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class GAEJEmailServlet extends HttpServlet {
	@SuppressWarnings("serial")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = resp.getWriter();
		//out.print("dsafasf");
		String strCallResult = "";
		resp.setContentType("text/plain");
		try {
		//Extract out the To, Subject and Body of the Email to be sent
		String strTo = req.getParameter("txt-mailto");
		String strSubject = req.getParameter("txt-subject");
		String strBody = req.getParameter("txt-message");
		//Do validations here. Only basic ones i.e. cannot be null/empty
		//Currently only checking the To Email field
		if (strTo == null) throw new Exception("To field cannot be empty.");
		//Trim the stuff
		strTo = strTo.trim();
		if (strTo.length() == 0) throw new Exception("To field cannot be empty.");
		//Call the GAEJ Email Service
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ducviet2711@gmail.com"));
		msg.addRecipient(Message.RecipientType.TO,
		new InternetAddress(strTo));
		msg.setSubject(strSubject);
		msg.setText(strBody);
		Transport.send(msg);
		strCallResult = "Success: " + "Email has been delivered.";
		resp.getWriter().println(strCallResult);
		}
		catch (Exception ex) {
		strCallResult = "Fail: " + ex.getMessage();
		resp.getWriter().println(strCallResult);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
