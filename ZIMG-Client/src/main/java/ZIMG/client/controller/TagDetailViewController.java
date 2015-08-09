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
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.services.TagService;
import ZIMG.services.UserService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagDetailViewController extends BaseController{

    static Logger log = Logger.getLogger(TagDetailViewController.class);

    public static final String JSP_PAGE_NAME = "tag";

    @Autowired
    private TagService tagService;

    @RequestMapping(value=JSP_PAGE_NAME  + "/{tagName}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String tagName, Model m) {

        try {
            Tag tag = tagService.getTagByTagAndLoadImages(tagName);

            m.addAttribute("tag", tag);

        } catch (NotFoundException e) {
            throw new SpringRuntimeExceptionForUser(e);
        }

        return JSP_PAGE_NAME;
    }
}