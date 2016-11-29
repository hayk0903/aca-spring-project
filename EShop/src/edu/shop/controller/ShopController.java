package edu.shop.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.entity.User;
import edu.shop.model.Balance;
import edu.shop.model.Login;
import edu.shop.model.OrderModel;
import edu.shop.model.SignUp;
import edu.shop.service.impl.UserServiceImpl;
import edu.shop.userapp.UserApp;

@Controller
@SessionAttributes("userApp")
public class ShopController {

	@Autowired
	UserServiceImpl userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("model", new Login());
		mav.addObject("login_message", session.getAttribute("login_message"));
		session.invalidate();
		mav.setViewName("login");

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute Login login, HttpSession session) {

		try {
			UserApp userApp = userService.login(login.getUsername(), login.getPassword());
			session.setAttribute("userApp", userApp);
			String redirectUrl = "success";
			return "redirect:" + redirectUrl;

		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			session.setAttribute("login_message", "Wrong email or password, try again");
			String redirectUrl = "login";
			return "redirect:" + redirectUrl;
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Model model, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("model", new SignUp());
		mav.addObject("signup_message", session.getAttribute("signup_message"));
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute SignUp signUp, HttpSession session) {
		if (signUp.isValide() && !userService.userExists(signUp.getEmail())) {
			try {
				User user = signUp.mapToUser();
				UserApp userApp = userService.signUp(user, signUp.getPassword());
				session.setAttribute("userApp", userApp);
				String redirectUrl = "success";
				return "redirect:" + redirectUrl;

			} catch (Exception e) {
				session.setAttribute("signup_message", "Please fill all fields.");
				String redirectUrl = "register";
				return "redirect:" + redirectUrl;
			}
		} else if(signUp.isValide() && userService.userExists(signUp.getEmail())){
			session.setAttribute("signup_message", "There is a user with such email");
			String redirectUrl = "register";
			return "redirect:" + redirectUrl;
		}else {
			session.setAttribute("signup_message", "Please fill all fields.");
			String redirectUrl = "register";
			return "redirect:" + redirectUrl;
		}

	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(Model model, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UserApp userApp = (UserApp) session.getAttribute("userApp");
		mav.setViewName("success");
		model.addAttribute("up", userApp);
		mav.addObject("up", userApp);
		return mav;

	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView account(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserApp userApp = (UserApp) session.getAttribute("userApp");
		mav.setViewName("account");
		model.addAttribute("up", userApp);
		mav.addObject("balance", new Balance());
		mav.addObject("up", userApp);
		mav.setViewName("account");
		mav.addObject("account_message", session.getAttribute("account_message"));
		return mav;

	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String fillBalance(Model model, HttpSession session, @ModelAttribute Balance balance) {

		if (balance.getAmmount() > 0) {
			UserApp userApp = (UserApp) session.getAttribute("userApp");
			Long ammount = balance.getAmmount();
			userApp.setAccount(userService.updateBalance(userApp.getUser().getId(), ammount));
			session.setAttribute("userApp", userApp);
			session.setAttribute("account_message", "Balance updated");
			session.setAttribute("account_message", "");
			String redirectUrl = "account";
			return "redirect:" + redirectUrl;
		} else {
			session.setAttribute("account_message", "Ammount must be positive number");
			String redirectUrl = "account";
			return "redirect:" + redirectUrl;
		}
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView history(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserApp userApp = (UserApp) session.getAttribute("userApp");
		mav.setViewName("history");
		model.addAttribute("up", userApp);
		List<Order> orderList = userService.viewHistory(userApp.getUser().getId());
		mav.addObject("orderList", orderList);
		mav.addObject("up", userApp);
		return mav;

	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView products(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserApp userApp = (UserApp) session.getAttribute("userApp");
		mav.setViewName("products");
		model.addAttribute("up", userApp);
		List<Product> productList = userService.viewAvailableProducts();
		mav.addObject("productList", productList);
		mav.addObject("up", userApp);
		mav.addObject("order", new OrderModel());
		mav.addObject("order_message", session.getAttribute("order_message"));
		session.setAttribute("productList", productList);
		return mav;

	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String makeOrder(HttpSession session, @ModelAttribute OrderModel orderModel) {
		LocalDate tommorrow = LocalDate.now().plusDays(1);
		UserApp userApp = (UserApp) session.getAttribute("userApp");
		@SuppressWarnings("unchecked")
		List<Product> productList = (List<Product>) session.getAttribute("productList");
		List<Long> idList = getIds(productList);

		if (!idList.contains(orderModel.getProductID())) {
			session.setAttribute("order_message", "Wrong ID");
			String redirectUrl = "products";
			return "redirect:" + redirectUrl;
		} else if (!orderModel.comesAfter(tommorrow)) {
			session.setAttribute("order_message", "Wrong Date");
			String redirectUrl = "products";
			return "redirect:" + redirectUrl;
		} else {
			try {
				userApp = userService.makeOrder(userApp, orderModel.getProductID(), orderModel.getDate());
				session.setAttribute("order_message", "");
			} catch (Exception e) {
				session.setAttribute("order_message", "Insufficient balance");
				String redirectUrl = "products";
				return "redirect:" + redirectUrl;
			}
		}

		session.setAttribute("userApp", userApp);

		String redirectUrl = "history";
		return "redirect:" + redirectUrl;
	}

	public List<Long> getIds(List<Product> productList) {
		List<Long> idList = new ArrayList<>();
		for (Product product : productList) {
			idList.add(product.getProductID());
		}
		return idList;
	};

	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public ModelAndView signOut(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate();
		mav.setViewName("index");
		return mav;

	}
}
