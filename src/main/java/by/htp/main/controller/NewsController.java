package by.htp.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.main.entity.News;
import by.htp.main.entity.User;
import by.htp.main.service.NewsService;
import by.htp.main.service.ServiceException;
import by.htp.main.service.UserService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserService userService;
	
	private static final String WELCOME = "welcome";
	private static final String GUEST = "guest";
	
	@RequestMapping("/goToBasePage")
	public String goToBasePage(HttpServletRequest request, Model theModel) {
		
		try {
			List<News> latestNews = newsService.getLatestList(5);
			latestNews = (latestNews.size() == 0) ? null : latestNews;
			
			theModel.addAttribute(LIST_NEWS, latestNews);
			request.getSession().setAttribute(WELCOME, GUEST);
			return "baseLayout";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@RequestMapping("/goToErrorPage")
	public String goToErrorPage(Model theModel) {
		
		return "/WEB-INF/view/error.jsp";
	}
	
	private static final String REGISTRATION = "registration";
	private static final String PRESENTATION = "presentation";
	private static final String USER = "user";
	private static final String NOT_ACTIVE = "not active";
	private static final String FIRST = "first";
	private static final String ATTEMPT = "attempt";
	
	@RequestMapping("/goToRegistrationPage")
	public String goToRegistrationPage(HttpServletRequest request, Model theModel) {
		
		try {
			if (FIRST.equals(request.getParameter(ATTEMPT))) {
				User uCleanData = null;
				request.getSession().setAttribute(REGISTRATION_DATA, uCleanData);
			}
			
			User user = new User();
			theModel.addAttribute(USER, user);
			request.getSession().setAttribute(PRESENTATION, REGISTRATION);
			request.getSession().setAttribute(USER, NOT_ACTIVE);
			
			return "baseLayout";
		} catch (Exception e) {
			return "goToErrorPage";
		}
	}
		
	private static final String REGISTRATION_WARNING = "warningReg";
	
	private static final String NAME_INVALID = "name invalid";
	private static final String SURNAME_INVALID = "surname invalid";
	private static final String PHONE_INVALID = "phone invalid";
	private static final String EMAIL_INVALID = "email invalid";
	private static final String BIRTHDAY_INVALID = "birthday invalid";

	private static final String LOGIN_REG_INVALID_BUNDLE_KEY = "local.registration_form.login_invalid";
	private static final String PASSWORD_REG_INVALID_BUNDLE_KEY = "local.registration_form.password_invalid";
	private static final String NAME_REG_INVALID_BUNDLE_KEY = "local.registration_form.name_invalid";
	private static final String SURNAME_REG_INVALID_BUNDLE_KEY = "local.registration_form.surname_invalid";
	private static final String PHONE_REG_INVALID_BUNDLE_KEY = "local.registration_form.phone_invalid";
	private static final String EMAIL_REG_INVALID_BUNDLE_KEY = "local.registration_form.email_invalid";
	private static final String BIRTHDAY_REG_INVALID_BUNDLE_KEY = "local.registration_form.birthday_invalid";	
	
	private static final String SESSION_ATTRIBUTE_NAME_INVALID = "nameInvalid";
	private static final String SESSION_ATTRIBUTE_SURNAME_INVALID = "surnameInvalid";
	private static final String SESSION_ATTRIBUTE_PHONE_INVALID = "phoneInvalid";
	private static final String SESSION_ATTRIBUTE_EMAIL_INVALID = "emailInvalid";
	private static final String SESSION_ATTRIBUTE_BIRTHDAY_INVALID = "birthdayInvalid";
	
	private static final String REGISTRATION_DATA = "registrationData";
	private static final String REGISTRATION_WARNING_USER_EXISTS = "warningReg2";
	
	@PostMapping("/doRegistration")
	public String doRegistration(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {
	
		String check = "";
		
		try {
			if (check.equals(user.getLogin()) || check.equals(user.getPassword()) || user.getRole().getId() == 0 ||
					check.equals(user.getUserDetails().getSurname()) || check.equals(user.getUserDetails().getName()) ||
					check.equals(user.getUserDetails().getPhone()) || check.equals(user.getUserDetails().getEmail()) ||
					check.equals(user.getUserDetails().getBirthday())) {
				request.getSession().setAttribute(REGISTRATION_WARNING, WARNING);
				return "redirect:/news/goToRegistrationPage";
			}
			
			String loginInvalid = null;
			String passwordInvalid = null;
			String nameInvalid = null;
			String surnameInvalid = null;
			String phoneInvalid = null;
			String emailInvalid = null;
			String birthdayInvalid = null;
			
			String role1 = userService.registration(user);
			String[] errors = role1.split(", ");
			for (String error : errors) {
				if (LOGIN_INVALID.equals(error)) {
					loginInvalid = LOGIN_REG_INVALID_BUNDLE_KEY;
				}
				if (PASSWORD_INVALID.equals(error)) {
					passwordInvalid = PASSWORD_REG_INVALID_BUNDLE_KEY;
				}
				if (NAME_INVALID.equals(error)) {
					nameInvalid = NAME_REG_INVALID_BUNDLE_KEY;
				}
				if (SURNAME_INVALID.equals(error)) {
					surnameInvalid = SURNAME_REG_INVALID_BUNDLE_KEY;
				}
				if (PHONE_INVALID.equals(error)) {
					phoneInvalid = PHONE_REG_INVALID_BUNDLE_KEY;
				}
				if (EMAIL_INVALID.equals(error)) {
					emailInvalid = EMAIL_REG_INVALID_BUNDLE_KEY;
				}
				if (BIRTHDAY_INVALID.equals(error)) {
					birthdayInvalid = BIRTHDAY_REG_INVALID_BUNDLE_KEY;
				}
			}
			if (ADMIN.equals(role1) || REDACTOR.equals(role1) || USER.equals(role1)) {
				request.getSession().setAttribute(REGISTRATION, SUCCESS);
				request.getSession(true).setAttribute(USER, ACTIVE);
				request.getSession(true).setAttribute(ROLE, role1);
				User uCleanData = null;
				request.getSession().setAttribute(REGISTRATION_DATA, uCleanData);
				return "redirect:/news/goToNewsList";
			} else if (role1.equals(GUEST)) {
				request.getSession().setAttribute(REGISTRATION_WARNING_USER_EXISTS, WARNING);
				request.getSession().setAttribute(REGISTRATION_DATA, user);
				return "redirect:/news/goToRegistrationPage";
			} else {
				request.getSession().setAttribute(SESSION_ATTRIBUTE_LOGIN_INVALID, loginInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_PASSWORD_INVALID, passwordInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_INVALID, nameInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_SURNAME_INVALID, surnameInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_PHONE_INVALID, phoneInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_EMAIL_INVALID, emailInvalid);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_BIRTHDAY_INVALID, birthdayInvalid);
				request.getSession().setAttribute(REGISTRATION_DATA, user);
				return "redirect:/news/goToRegistrationPage";
			}
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@GetMapping("/goToMainPage")
	public String goToMainPage(HttpServletRequest request, Model theModel) {
		
		try {
			List<News> latestNews = newsService.getLatestList(5);
			latestNews = (latestNews.size() == 0) ? null : latestNews;
			
			theModel.addAttribute(LIST_NEWS, latestNews);
			request.getSession().setAttribute(PRESENTATION, "");
			return "baseLayout";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String NEWS_LIST = "newsList";

	@RequestMapping("/goToNewsList")
	public String goToNewsList(HttpServletRequest request, Model theModel) {
	
		try {
			List<News> listNews = newsService.getList();
			listNews = (listNews.size() == 0) ? null : listNews;
	
			theModel.addAttribute(LIST_NEWS, listNews);
			request.getSession().setAttribute(PRESENTATION, NEWS_LIST);
			return "baseLayout";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String WARNING = "warning";
	private static final String ADMIN = "admin";
	private static final String REDACTOR = "redactor";
	private static final String ROLE = "role";

	private static final String ACTIVE = "active";
	private static final String ID_USER = "idUser";
	private static final String AUTHENTIFICATION_ERROR = "AuthenticationError";

	private static final String LOGIN_INVALID = "login invalid";
	private static final String PASSWORD_INVALID = "password invalid";

	private static final String LOGIN_INVALID_BUNDLE_KEY = "local.registration_form.login_invalid";
	private static final String PASSWORD_INVALID_BUNDLE_KEY = "local.registration_form.password_invalid";
	private static final String AUTHENTIFICATION_ERROR_BUNDLE_KEY = "local.registration.auth_error";

	private static final String SESSION_ATTRIBUTE_LOGIN_INVALID = "loginInvalid";
	private static final String SESSION_ATTRIBUTE_PASSWORD_INVALID = "passwordInvalid";
	
	private static final String LOGIN_INV = "loginInv";
	private static final String PASSWORD_INV = "passwordInv";
	
	
	@PostMapping("/doSignIn")
	public String doSignIn(HttpServletRequest request, @RequestParam("login") String login, @RequestParam("password") String password, Model theModel) {
	
		int idUser = 0;

		try {
			String role = userService.signIn(login, password);
			
			if (ADMIN.equals(role) || REDACTOR.equals(role) || USER.equals(role)) {

				idUser = userService.findIdUser(login, password);
				request.getSession().setAttribute(USER, ACTIVE);
				request.getSession().setAttribute(WELCOME, login);
				request.getSession().setAttribute(ROLE, role);
				request.getSession().setAttribute(ID_USER, idUser);
				return "redirect:/news/goToNewsList";
			} else if (role.equals(GUEST)) {
				request.getSession().setAttribute(USER, NOT_ACTIVE);
				theModel.addAttribute(AUTHENTIFICATION_ERROR, AUTHENTIFICATION_ERROR_BUNDLE_KEY);
				theModel.addAttribute(LOGIN_INV, login);
				theModel.addAttribute(PASSWORD_INV, password);
				
				List<News> latestNews = newsService.getLatestList(5);
				latestNews = (latestNews.size() == 0) ? null : latestNews;
				theModel.addAttribute(LIST_NEWS, latestNews);

				return "baseLayout";
			} else {
				String loginInvalid = null;
				String passwordInvalid = null;
				String[] errors = role.split(", ");
				for (String error : errors) {
					if (LOGIN_INVALID.equals(error)) {
						loginInvalid = LOGIN_INVALID_BUNDLE_KEY;
					}
					if (PASSWORD_INVALID.equals(error)) {
						passwordInvalid = PASSWORD_INVALID_BUNDLE_KEY;
					}
				}

				request.getSession().setAttribute(USER, NOT_ACTIVE);
				theModel.addAttribute(SESSION_ATTRIBUTE_LOGIN_INVALID, loginInvalid);
				theModel.addAttribute(SESSION_ATTRIBUTE_PASSWORD_INVALID, passwordInvalid);
				theModel.addAttribute(LOGIN_INV, login);
				theModel.addAttribute(PASSWORD_INV, password);
				
				List<News> latestNews = newsService.getLatestList(5);
				latestNews = (latestNews.size() == 0) ? null : latestNews;
				theModel.addAttribute(LIST_NEWS, latestNews);
				
				return "baseLayout";
			}	
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@PostMapping("/doSignOut")
	public String doSignIn(HttpServletRequest request, Model theModel) {
	
		int idUser = 0;
			
		try {
			request.getSession().setAttribute(USER, NOT_ACTIVE);
			request.getSession().setAttribute(WELCOME, GUEST);
			request.getSession().setAttribute(ID_USER, idUser);
			return "redirect:/news/goToBasePage";
		} catch (Exception e) {
			return "goToErrorPage";
		}
	}
	
	private static final String NEWS_VIEW = "newsView";
	private static final String VIEW_NEWS = "viewNews";
	
	@GetMapping("/goToViewNews")
	public String goToViewNews(@RequestParam("id") String id, HttpServletRequest request, Model theModel) {
	
		News newsView;
		
		try {
			newsView = newsService.fetchById((int)Integer.valueOf(id));
			request.getSession().setAttribute(NEWS_VIEW, newsView);
			request.getSession().setAttribute(PRESENTATION, VIEW_NEWS);
			return "baseLayout";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String LIST_NEWS = "listNews";
	private static final String DELETE_NEWS = "delete_news";
	private static final String SUCCESS = "success";
	
	@PostMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") String id, HttpServletRequest request, Model theModel) {
		
		try {
			newsService.deleteNews((int)Integer.valueOf(id));
			request.getSession().setAttribute(DELETE_NEWS, SUCCESS);
			return "redirect:/news/goToNewsList";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String DELETE_SELECTED_NEWS = "delete_selected_news";
	private static final String NEWS = "news";
	
	@PostMapping("/doDeleteSelectedNews")
	public String doDeleteSelectedNews(@RequestParam("idNews") String[] idNews, HttpServletRequest request, Model theModel) {
	
		List<News> allNews;
		
		try {
			if (idNews == null) {
				request.getSession().setAttribute(DELETE_SELECTED_NEWS, WARNING);
				return "redirect:/news/goToNewsList";
			} else {
				newsService.deleteNewses(idNews);
				allNews = newsService.getList();
				request.getSession().setAttribute(NEWS, allNews);
				request.getSession().setAttribute(DELETE_SELECTED_NEWS, SUCCESS);
				return "redirect:/news/goToNewsList";
			}
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String ADD_NEWS_PAGE = "add";
	
	@GetMapping("/goToAddNewsForm")
	public String goToAddNewsForm(HttpServletRequest request, Model theModel) {
	
		try {
			News news = new News();
			theModel.addAttribute(NEWS, news);
			request.getSession().setAttribute(PRESENTATION, ADD_NEWS_PAGE);
			return "baseLayout";
		} catch (Exception e) {
			return "goToErrorPage";
		}
	}
	
	private static final String SESSION_ATTRIBUTE_ADD = "add";
	private static final String SESSION_ATTRIBUTE_ADD_NEWS = "add_news";
	private static final String SUCCESS_ADD_NEWS = "success";
	
	@PostMapping("/doAddNews")
	public String doAddNews(@ModelAttribute("news") News news, HttpServletRequest request, Model theModel) {
			
		int idUser = 0;
			
		try {
			if (news.getTitle().isEmpty() || news.getBrief().isEmpty() || news.getContent().isEmpty()) {
				request.getSession().setAttribute(SESSION_ATTRIBUTE_ADD, WARNING);
				return "redirect:/news/goToAddNewsForm";
			} else {
				
				idUser = (int) (request.getSession().getAttribute(ID_USER));
				User user = new User();
				user.setId(idUser);
				news.setUser(user);
				
				newsService.addNews(news);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_ADD_NEWS, SUCCESS_ADD_NEWS);
				return "redirect:/news/goToNewsList";
			}
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String EDIT = "edit";
	
	@GetMapping("/goToEditNewsForm")
	public String goToEditNewsForm(HttpServletRequest request, @RequestParam("id") String id, Model theModel) {
	
		News editNews;
		
		try {
			editNews = newsService.fetchById((int)Integer.parseInt(id));
			theModel.addAttribute(NEWS, editNews);
			request.getSession().setAttribute(PRESENTATION, EDIT);
			return "baseLayout";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	private static final String EDIT_RESULT = "edit_result";
	
	@PostMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") News news, HttpServletRequest request, Model theModel) {
	
		int idUser = 0;
				
		try {
			if (news.getTitle().isEmpty() || news.getBrief().isEmpty() || news.getContent().isEmpty()) {
				request.getSession().setAttribute(EDIT, WARNING);
				return "baseLayout";
			} else {
				
				idUser = (int) (request.getSession().getAttribute(ID_USER));
				User user = new User();
				user.setId(idUser);
				news.setUser(user);
				
				newsService.updateNews(news);
				request.getSession().setAttribute(EDIT_RESULT, SUCCESS);
				theModel.addAttribute(NEWS, news);
				return "redirect:/news/goToNewsList";
			}
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		News theNews = new News();
		theModel.addAttribute(NEWS, theNews);
		return "news-form";
	}
	
	@PostMapping("/saveNews")
	public String saveNews(@ModelAttribute("news") News theNews) {
		
		try {
			newsService.addNews(theNews);
			return "redirect:/news/list";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("newsId") int theId, Model theModel) {
		
		try {
			News theNews = newsService.fetchById(theId);
			theModel.addAttribute(NEWS, theNews);
			return "news-form";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
	
	@GetMapping("/delete")
	public String deleteNews(@RequestParam("newsId") int theId) {
	
		try {
			newsService.deleteNews(theId);
			return "redirect:/news/list";
		} catch (ServiceException e) {
			return "goToErrorPage";
		}
	}
}
