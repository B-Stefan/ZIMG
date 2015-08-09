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

import ZIMG.exceptions.*;
import ZIMG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for creating a new user
 */
@Controller
public class SignupViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "signup";

    @Autowired
    private UserService userService;

    /**
     * Render the signup page only if the user is't already logged in
     * @param m
     * @return
     */
    @RequestMapping(value = JSP_PAGE_NAME, method = RequestMethod.GET)
    public String getView(Model m) {
        try {
            userService.getCurrentUser();
        }catch (SecurityException e){

            return "signup";
        }
        return "redirect:/" + HomeController.JSP_PAGE_NAME;

    }

    /**
     * Post method for creating a new user
     * @param userEmail The emailaddress of the user
     * @param username The username
     * @param password
     * @return
     */
    @RequestMapping(value=JSP_PAGE_NAME, method= RequestMethod.POST)
    public String postView(@RequestParam("userEmail") String userEmail,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password) {


        try {

            this.userService.register(userEmail, password, username);

        }catch (UserEmailAlreadyInUseException e){

            throw new SpringRuntimeExceptionForUser("An User with this email address might already exist",
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }catch (UserPasswordConstrainsException e){

            throw new SpringRuntimeExceptionForUser(e,
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }
        catch (EmailNotValidException e){

            throw new SpringRuntimeExceptionForUser("Your email address is not valid, please signup with a valid email adress",
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }
        catch (UserNameConstrainsException e){
            throw new SpringRuntimeExceptionForUser(e,
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }

        return "redirect:" + LoginViewController.JSP_PAGE_NAME;
    }
}
