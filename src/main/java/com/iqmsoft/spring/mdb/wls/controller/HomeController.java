package com.iqmsoft.spring.mdb.wls.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmsoft.spring.mdb.wls.dao.PatientRecordsInserterDAO;
import com.iqmsoft.spring.mdb.wls.model.PatientDetails;
import com.iqmsoft.spring.mdb.wls.service.MyListener;
import com.iqmsoft.spring.mdb.wls.service.PatientRecordsInserterService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	PatientRecordsInserterDAO pdao;

	@Autowired
	PatientRecordsInserterService pservice;

	@Autowired
	private JmsTemplate invoiceQueueTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		PatientDetails p = new PatientDetails("Williams", "John", "test@test.com");

		pdao.saveDetails(p);

		try {
			pservice.splitString("first:second:test@test.com");
		} catch (ParseException e) {

			e.printStackTrace();
		}

	//	invoiceQueueTemplate.setDefaultDestination(destination);
		
		invoiceQueueTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("first:second:test@test.com");
			}
		});

		return "home";
	}

}
