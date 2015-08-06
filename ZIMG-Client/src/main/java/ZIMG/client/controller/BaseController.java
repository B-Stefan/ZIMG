package ZIMG.client.controller;


import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.models.User;
import ZIMG.persistence.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

public abstract class BaseController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(SpringRuntimeExceptionForUser ex) {

        ModelAndView model = new ModelAndView(ex.getJspPageName());
        model.addObject("errType", ex.getType());
        model.addObject("errMsg", ex.getMessage());

        return model;

    }
}
