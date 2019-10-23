package services.ravi.user.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import services.ravi.user.crm.constant.ErrorCodes;
import services.ravi.user.crm.constant.ModelObjects;
import services.ravi.user.crm.constant.RequestMappingUrls;
import services.ravi.user.crm.constant.ViewUrls;
import services.ravi.user.crm.dto.UserDto;
import services.ravi.user.crm.exception.EmailExistsException;
import services.ravi.user.crm.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(RequestMappingUrls.REGISTER)
public class RegistrationController extends AbstractViewController{

    @Autowired
    UserService userService;

    @Override
    protected String getViewName() {
        return ViewUrls.REGISTER;
    }

    @Override
    protected void fillModel(ModelAndView mav) {
        mav.addObject(ModelObjects.USER, UserDto.builder().build());
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, WebRequest request, Errors errors){
        if(bindingResult.hasErrors()){
            return new ModelAndView(ViewUrls.REGISTER, ModelObjects.USER, userDto);
        }else{
            try{
                userService.registerNewUser(userDto);
                return new ModelAndView(ViewUrls.LOGIN);
            }catch (EmailExistsException e){
                log.debug("An error occured while trying to save user: "+e.getMessage());
                bindingResult.reject(ErrorCodes.REGISTRATION_FAIL_EMAIL_EXIST);
                return new ModelAndView(ViewUrls.REGISTER, ModelObjects.USER, userDto);
            }
        }
    }

}
