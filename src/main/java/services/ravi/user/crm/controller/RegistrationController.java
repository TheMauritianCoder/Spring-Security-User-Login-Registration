package services.ravi.user.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import services.ravi.user.crm.constant.ModelObjects;
import services.ravi.user.crm.constant.RequestMappingUrls;
import services.ravi.user.crm.constant.ViewUrls;
import services.ravi.user.crm.dto.UserDto;

import javax.validation.Valid;

@Controller
@RequestMapping(RequestMappingUrls.REGISTER)
public class RegistrationController extends AbstractViewController{

    @Override
    protected String getViewName() {
        return ViewUrls.REGISTER;
    }

    @Override
    protected void fillModel(ModelAndView mav) {
        mav.addObject(ModelObjects.USER, UserDto.builder().build());
    }

    @PostMapping
    public String register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, WebRequest request, Errors errors){
        if(bindingResult.hasErrors()){
            return getViewName();
        }else{
            return ViewUrls.LOGIN_SUCCESS;
        }
    }

}
