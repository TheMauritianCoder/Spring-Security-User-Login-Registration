package services.ravi.user.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ravi.user.crm.constant.ModelObjects;
import services.ravi.user.crm.constant.RequestMappingUrls;
import services.ravi.user.crm.constant.ViewUrls;
import services.ravi.user.crm.dto.UserDto;

@Controller
@RequestMapping(RequestMappingUrls.LOGIN)
public class LoginController extends AbstractViewController {

    @Override
    protected String getViewName() {
        return ViewUrls.LOGIN;
    }

    @Override
    protected void fillModel(ModelAndView mav) {
        mav.addObject(ModelObjects.USER, UserDto.builder().build());
    }
}
