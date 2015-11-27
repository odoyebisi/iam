package ng.odo.web.controllers;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ng.odo.iamcore.datamodel.Identity;
import ng.odo.iamcore.services.dao.impl.IdentityHibernateDAO;
import ng.odo.web.form.validation.IdentityFormValidator;
import ng.odo.web.services.IdentityService;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

//import ng.odo.iamcore.datamodel.Identity;
//import ng.odo.iamcore.services.dao.impl.IdentityHibernateDAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Oluwabusayo
 *
 */
@Controller
public class IdentityController {

	private final Logger logger = LoggerFactory.getLogger(IdentityController.class);

	private IdentityService identityService;

	@Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	@Autowired
	IdentityFormValidator identityFormValidator;

	/**
	 * Initialize binder
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// register form validator
		binder.setValidator(identityFormValidator);

		// Create a human friendly date editor
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);

		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * Welcome Page
	 * @return index
	 */
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String welcomePage() {
		return "index";

	}

	/**
	 *  Login Page
	 * @param logout
	 * @param model
	 * @return login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "logout", required = false) String logout, Model model) {
		logger.debug("loginPage() : {}", model);
		return "user/login";
	}

	/**
	 * Displays the list of identities
	 * 
	 * @param model
	 * @return the view for list page
	 */
	@RequestMapping(value = "/identity", method = RequestMethod.GET)
	public String identityPage(Model model) {

		logger.debug("identityPage()");
		model.addAttribute("identities", identityService.getIdentities());

		return "identity/list";
	}

	/**
	 * Show add identity form
	 * 
	 * @param model
	 * @return view for identity form
	 */
	@RequestMapping(value = "/identity/add", method = RequestMethod.GET)
	public String showAddIdentityForm(Model model) {

		logger.debug("showAddIdentityForm()");

		// create identity and set default values for new Identity
		Identity identity = new Identity();

		identity.setFirstName("");
		identity.setLastName("");
		identity.setEmail("");

		model.addAttribute("identityForm", identity);
		
		return "identity/identityform";

	}
	
	/**
	 * Save or update an identity
	 * 
	 * @param identity
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/identity", method = RequestMethod.POST)
	public String saveOrUpdateIdentity(@ModelAttribute("identityForm") @Validated Identity identity,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		logger.debug("saveOrUpdateIdentity() : {}", identity);

		if (result.hasErrors()) {		
				return "identity/identityform";		
		}

		else {

			redirectAttributes.addFlashAttribute("css", "success");
			if (identity.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Identity added successfully!");
				identityService.addIdentity(identity);
			} else {
				redirectAttributes.addFlashAttribute("msg", "Identity updated successfully!");
				identityService.updateIdentity(identity);
			}

			// POST/REDIRECT/GET
			return "redirect:/identity/" + identity.getId();
		}

	}

	/**
	 * Show search form
	 * 
	 * @param id of Identity to be updated
	 * @param model
	 * @return view of form
	 */
	@RequestMapping(value = "/identity/search", method = RequestMethod.GET)
	public String showSearchForm(Model model) {

		logger.debug("showSearchForm() : {}");

		// create identity and set default values for new Identity
		Identity identity = new Identity();

		identity.setFirstName(" ");
		identity.setLastName(" ");
		identity.setEmail(" ");
	
		// add identity to the form
		model.addAttribute("searchForm", identity);
		model.addAttribute("action", "search");

		return "identity/searchform";

	}	

	/**
	 * Show search result
	 * @param identity
	 * @param model
	 * @return identities list
	 */
	@RequestMapping(value = "/identity/search/result", method = RequestMethod.POST)
	public String searchIdentity(@ModelAttribute("searchForm") Identity identity, Model model) {

		logger.debug("identitiesPage()");
		logger.debug(identity.getFirstName());
		model.addAttribute("identities", identityService.searchIdentity(identity));
		return "identity/list";
	}

	/**
	 * Show update form
	 * 
	 * @param id of Identity to be updated
	 * @param model
	 * @return view of form
	 */
	@RequestMapping(value = "/identity/{id}/update", method = RequestMethod.GET)
	public String showUpdateIdentityForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateIdentityForm() : {}", id);

		Identity identiy = identityService.findIdentityByID(id);

		// add identity form to the form
		model.addAttribute("identityForm", identiy);
		// model.addAttribute("update", "action");

		return "identity/identityform";

	}

	/**
	 * Delete identity
	 * 
	 * @param id of Identity to be deleted
	 * @param redirectAttributes
	 * @return the Identity list view
	 */
	@RequestMapping(value = "/identity/{id}/delete", method = RequestMethod.POST)
	public String deleteIdentity(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

		logger.debug("deleteIdentity() : {}", id);

		identityService.deleteIdentity(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Identity is deleted!");

		return "redirect:/identity";
		// return "identity/list";
	}

	/**
	 * Show user
	 * 
	 * @param id
	 * @param model
	 * @return single Identity view
	 */
	@RequestMapping(value = "/identity/{id}", method = RequestMethod.GET)
	public String showIdentity(@PathVariable("id") int id, Model model) {

		logger.debug("showIdentity() id: {}", id);

		Identity identity = identityService.findIdentityByID(id);

		model.addAttribute("identity", identity);

		return "identity/show";
	}
	
	/**
	 * Return user to welcome page
	 * @return index
	 */
	@RequestMapping(value = "/j_spring_security_logout", method = RequestMethod.GET)
	public String logoutPage() {
		return "index";
	}

	/**
	 * Default exception handler
	 * @param req http servlet request
	 * @param ex exception
	 * @return error page
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleEmptyData(Model model, HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		model.addAttribute("msg", "identity not found");

		return "error";

	}

}
