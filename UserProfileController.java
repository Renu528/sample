package com.avengers.bus.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.avengers.bus.entityModels.User;
import com.avengers.bus.entityModels.UserPassengers;
import com.avengers.bus.services.CancelTicketService;
import com.avengers.bus.services.FetchUser;
import com.avengers.bus.services.FetchUserCounts;
import com.avengers.bus.services.FetchUserPassengers;
import com.avengers.bus.services.FetchUserTickets;

@Controller
public class UserProfileController {
	@Autowired
	private FetchUser fetchUser;
	@Autowired
	private FetchUserTickets fetchUserTickets;
	@Autowired
	private FetchUserPassengers fetchUserPassengers;
	@Autowired
	private FetchUserCounts fetchUserCounts;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private CancelTicketService cancelTicketService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView admin(Model model) {
		int userId = (Integer) httpSession.getAttribute("userId");
		ModelAndView mv = new ModelAndView("UserDashBoard");
		mv.addObject("userCounts", fetchUserCounts.getUserCounts(userId));
		return mv;
	}

	@RequestMapping(value = "/UserList", method = RequestMethod.GET)
	@ResponseBody
	public String UserDetailsController() {
		int userId = (Integer) httpSession.getAttribute("userId");
		String userJson = fetchUser.getUserList(userId);
		return userJson;

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public String Userupdate(@RequestBody User user) {
		fetchUser.add(user);
		return "updatedSuccessfully";

	}

	@RequestMapping(value = "/ticketList", method = RequestMethod.GET)
	@ResponseBody
	public String allTicketsDetails() {
		int userId = (Integer) httpSession.getAttribute("userId");
		String userJson = fetchUserTickets.getticketList(userId);
		return userJson;

	}

	@RequestMapping(value = "/pastticketList", method = RequestMethod.GET)
	@ResponseBody
	public String pastTicketsDetails() {
		int userId = (Integer) httpSession.getAttribute("userId");
		String userJson = fetchUserTickets.getPastTicketList(userId);
		return userJson;

	}

	@RequestMapping(value = "/futureticketList", method = RequestMethod.GET)
	@ResponseBody
	public String futureTicketsDetails() {
		int userId = (Integer) httpSession.getAttribute("userId");
		String userJson = fetchUserTickets.getFutureTicketList(userId);
		return userJson;

	}

	@RequestMapping(value = "/passengersList", method = RequestMethod.GET)
	@ResponseBody
	public String passengersDetails() {
		int userId = (Integer) httpSession.getAttribute("userId");
		String userJson = fetchUserPassengers.getPassengersList(userId);
		return userJson;

	}

	@RequestMapping(value = "/deletePassenger", method = RequestMethod.POST)
	@ResponseBody
	public String deletepassengersDetails(@RequestBody UserPassengers userpass) {

		fetchUserPassengers.deleteUserpassengers(userpass);
		return "deleteSuccess";

	}

	@RequestMapping(value = "/addPassenger", method = RequestMethod.POST)
	@ResponseBody
	public String addPassengersDetails(@RequestBody UserPassengers userpass) {
		fetchUserPassengers.addUserpassengers(userpass);
		return "addSuccess";

	}

	@RequestMapping(value = "/cancelTicket", method = RequestMethod.POST)
	@ResponseBody
	public String cancelTicket(String booking_id) {
		System.out.println("in controller cancel ticket");
		System.out.println(booking_id);

		if (cancelTicketService.cancelTicket(booking_id)) {
			System.out.println("in if controller cancel ticket");

			return "cancelTicketSuccess";
		} else {
			System.out.println("in else controller cancel ticket");

			return "cancelTicketFailed";
		}

	}

}
