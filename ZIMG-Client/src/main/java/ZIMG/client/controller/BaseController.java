/*
 * ZIMG-JAVA - Game, Copyright 2015  Nils Oke S., Fabian J., Chris P., Stefan Bieliauskas  -  All Rights Reserved.
 * Hochschule Bremen - University of Applied Sciences
 *
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 * Web:
 *     https://github.com/B-Stefan/ZIMG
 *
 */
package ZIMG.client.controller;


import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class for all controllers, provide a global error handling
 * Example @see ZIMG.clinet.controller.SignupViewController
 */
public abstract class BaseController {


    /**
     * Exception handling only for ZIMG exceptions.
     * @param ex
     * @return
     */
    @ExceptionHandler(SpringRuntimeExceptionForUser.class)
    public ModelAndView handleAllOwn(SpringRuntimeExceptionForUser ex) {

        ModelAndView model = new ModelAndView(ex.getJspPageName());
        model.addObject("errType", ex.getType());
        model.addObject("errMsg", ex.getMessage());

        return model;

    }

    /**
     * General exception handling
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView(SpringRuntimeExceptionForUser.DEFAULT_JSP_NAME);
        model.addObject("errType", SpringRuntimeExceptionForUser.TYPE.ERROR);
        model.addObject("errMsg", ex.getMessage());

        return model;

    }
}
