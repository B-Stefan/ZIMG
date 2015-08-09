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

import ZIMG.models.User;

import java.util.List;


public class MultipleUserForUserNameExistException extends Exception {
    /**
     * User name that exist at least twice user
     */
    private final String userName;

    /**
     * List of users with the @userName prop
     */
    private final List<User> userList;

    public MultipleUserForUserNameExistException(final String userName, final List<User> userList){
        super("For "  + userName + " exist multiple users " + userList.toString());
        this.userName = userName;
        this.userList = userList;
    }
    public List<User> getUserList() {
        return userList;
    }

    public String getUserName() {
        return userName;
    }
}
