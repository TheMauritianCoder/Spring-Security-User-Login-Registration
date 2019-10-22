package services.ravi.user.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ravi.user.crm.constant.RequestMappingUrls;
import services.ravi.user.crm.constant.ViewUrls;

@Controller
@RequestMapping(RequestMappingUrls.HOME)
public class HomeController extends AbstractViewController {

    @Override
    protected String getViewName() {
        return ViewUrls.HOME;
    }

    @Override
    protected void fillModel(ModelAndView mav) {

    }
}
