package services.ravi.user.crm.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractViewController {

    @GetMapping
    public ModelAndView getMapping(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        ModelAndView mav = new ModelAndView();
        fillModel(mav);
        mav.setViewName(getViewName());
        return mav;
    }

    /**
     * Returns the view name without the html.
     * e.g for a file in the folder templates/common/home.html
     * The view should be common/home
     *
     * @return The View Name
     */
    protected abstract String getViewName();

    /**
     * Method to load the model to the View.
     *
     * @param mav: The model and view to fill.
     */
    protected abstract void fillModel(ModelAndView mav);


}
