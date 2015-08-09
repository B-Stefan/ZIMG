/*
 * ZIMG-JAVA - Game, Copyright 2014  Nils Oke S., Fabian J., Chris P., Stefan Bieliauskas  -  All Rights Reserved.
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

import ZIMG.models.Image;
import ZIMG.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    static final int imagesPerSite = 3;
    static final String JSP_PAGE_NAME = "home";
    @Autowired
    ImageService imageService;

    @RequestMapping(JSP_PAGE_NAME)
    public String loadHomepage(Model m) {

        Pageable pageable = new PageRequest(0, imagesPerSite, new Sort(Sort.Direction.DESC, "createdAt"));
        Page<Image> imagePage = this.imageService.findAll(pageable);
        m.addAttribute("imagePage", imagePage);

        return this.loadHomepagePage("0", m);
    }

    @RequestMapping(value="/"+JSP_PAGE_NAME+"/{page}", method= RequestMethod.GET)
    public String loadHomepagePage(@PathVariable String page, Model m) {

        int pageNumber = Integer.parseInt(page);

        Pageable pageable = new PageRequest(pageNumber, imagesPerSite, new Sort(Sort.Direction.DESC, "createdAt"));
        Page<Image> imagePage = this.imageService.findAll(pageable);
        m.addAttribute("imagePage", imagePage);

        m.addAttribute("pagePrevious", pageable.previousOrFirst().getPageNumber());
        m.addAttribute("pageNext", pageable.next().getPageNumber());

        if(pageable.equals(pageable.first())) {
            m.addAttribute("pagePreviousDisable", true);
        } else {
            m.addAttribute("pagePreviousDisable", false);
        }

        Page<Image> imageNextPage = this.imageService.findAll(pageable.next());

        if(imageNextPage.getSize() == 0) {
            m.addAttribute("pageNextDisable", true);
        } else {
            m.addAttribute("pageNextDisable", false);
        }

        return JSP_PAGE_NAME;
    }


    @RequestMapping("")
    public String defaultPage(Model m) {
        return this.loadHomepagePage("0", m);
    }

}