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
package ZIMG.exceptions;

public class SpringRuntimeExceptionForUser extends RuntimeException {

    public enum TYPE {
        WARNING,
        ERROR,
    }
    public final static String DEFAULT_JSP_NAME = "error";
    private final TYPE type;
    private final String jspPageName;
    public SpringRuntimeExceptionForUser(Throwable e){
        super(e);
        this.type = TYPE.ERROR;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(Throwable e, TYPE type){
        super(e);
        this.type = type;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(Throwable e, TYPE type, String useJSPPage){
        super(e);
        this.type = type;
        this.jspPageName = useJSPPage;
    }

    public SpringRuntimeExceptionForUser(String e){
        super(e);
        this.type = TYPE.ERROR;
        this.jspPageName = DEFAULT_JSP_NAME;
    }

    public SpringRuntimeExceptionForUser(String e, TYPE type){
        super(e);
        this.type = type;
        this.jspPageName = DEFAULT_JSP_NAME;
    }
    public SpringRuntimeExceptionForUser(String e, TYPE type, String useJSPPage){
        super(e);
        this.type = type;
        this.jspPageName = useJSPPage;
    }
    public String getMsg(){
        return this.getMessage();
    }
    public TYPE getType(){
        return this.type;
    }
    public String getJspPageName(){
        return this.jspPageName;
    }
}
