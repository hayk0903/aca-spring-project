package edu.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.entity.Storage;
import edu.shop.lib.OrderDistribution;
import edu.shop.model.Login;
import edu.shop.service.impl.AdminServiceImpl;
import edu.shop.userapp.AdminApp;

@Controller
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView login(Model model, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("model", new Login());
		mav.addObject("message", session.getAttribute("message"));
		session.invalidate();
		mav.setViewName("adminlogin");

		return mav;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute Login login, HttpSession session) {
		try {
			AdminApp adminApp = adminService.login(login.getUsername(), login.getPassword());
			session.setAttribute("adminApp", adminApp);
			String redirectUrl = "adminaccount";
			return "redirect:" + redirectUrl;

		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("adminlogin");
			session.setAttribute("message", "Wrong email or password, try again");
			String redirectUrl = "admin";
			return "redirect:" + redirectUrl;
		}
	}

	@RequestMapping(value = "/adminaccount", method = RequestMethod.GET)
	public ModelAndView adminAccount(Model model, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminaccount");
		return mav;
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView products(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manage");
		List<Product> productList = adminService.getProducts();
		mav.addObject("productList", productList);
		session.setAttribute("productList", productList);
		mav.addObject("manage_message", session.getAttribute("manage_message"));
		mav.addObject("product", new Product());
		return mav;

	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String products(HttpSession session, @ModelAttribute Product product) {
		@SuppressWarnings("unchecked")
		List<Product> productList = (List<Product>) session.getAttribute("productList");
		List<Long> idList = getIds(productList);
		if (!idList.contains(product.getProductID())) {
			session.setAttribute("manage_message", "Wrong ID");
			String redirectUrl = "manage";
			return "redirect:" + redirectUrl;
		} else if (product.getQuantity() < 0) {
			session.setAttribute("manage_message", "Number must be positive");
			String redirectUrl = "manage";
			return "redirect:" + redirectUrl;
		} else {
			try {
				adminService.changeStatus(product.getProductID(), product.getAvailable());
				if (product.getQuantity() > 0) {
					adminService.setQuantity(product.getProductID(), product.getQuantity());
				}
				session.setAttribute("manage_message", "");
			} catch (Exception e) {

				e.printStackTrace();
			}

			String redirectUrl = "manage";
			return "redirect:" + redirectUrl;
		}
	}
	
	public List<Long> getIds(List<Product> productList) {
		List<Long> idList = new ArrayList<>();
		for (Product product : productList) {
			idList.add(product.getProductID());
		}
		return idList;
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public ModelAndView addProduct(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addproduct");
		List<Product> productList = adminService.getProducts();
		mav.addObject("productList", productList);
		mav.addObject("product", new Product());
		mav.addObject("product_message", session.getAttribute("product_message"));
		return mav;

	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProduct(HttpSession session, @ModelAttribute Product product) {
		if (product.getType().length()<1) {
			session.setAttribute("product_message", "Type not set");
			String redirectUrl = "addproduct";
			return "redirect:" + redirectUrl;
		} else if (product.getPrice() <= 0) {
			session.setAttribute("product_message", "Price must be positive");
			String redirectUrl = "addproduct";
			return "redirect:" + redirectUrl;
		} else {
			try {
				adminService.addProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("product_message", "");
			String redirectUrl = "addproduct";
			return "redirect:" + redirectUrl;
		}
	}

	@RequestMapping(value = "/deliver", method = RequestMethod.GET)
	public ModelAndView deliver(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("distribution");
		List<Order> orderList = adminService.getOrdered();
		mav.addObject("orderList", orderList);
		session.setAttribute("orderList", orderList);
		@SuppressWarnings("unchecked")
		List<Order> deliveredOrders = (List<Order>) session.getAttribute("deliveredOrders");
		mav.addObject("deliveredOrders", deliveredOrders);
		return mav;

	}

	@RequestMapping(value = "/distribute", method = RequestMethod.GET)
	public String deliver(HttpSession session) {

		Storage storage = adminService.getStorage();
		OrderDistribution orderDistribution = new OrderDistribution(storage);
		@SuppressWarnings("unchecked")
		List<Order> orderList = (List<Order>) session.getAttribute("orderList");
		List<Order> deliveredOrders = orderDistribution.sortOrders(orderList);
		adminService.setStatus(deliveredOrders);
		session.setAttribute("deliveredOrders", deliveredOrders);
		String redirectUrl = "deliver";
		return "redirect:" + redirectUrl;
	}
}
