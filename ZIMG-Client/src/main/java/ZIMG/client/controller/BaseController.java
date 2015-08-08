package ZIMG.client.controller;


import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView(SpringRuntimeExceptionForUser.DEFAULT_JSP_NAME);
        model.addObject("errType", SpringRuntimeExceptionForUser.TYPE.ERROR);
        model.addObject("errMsg", ex.getMessage());

        return model;

    }

    @ExceptionHandler(SpringRuntimeExceptionForUser.class)
    public ModelAndView handleAllOwn(SpringRuntimeExceptionForUser ex) {

        ModelAndView model = new ModelAndView(ex.getJspPageName());
        model.addObject("errType", ex.getType());
        model.addObject("errMsg", ex.getMessage());

        return model;

    }
}
