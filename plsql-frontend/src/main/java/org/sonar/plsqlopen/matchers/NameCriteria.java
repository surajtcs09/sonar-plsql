/*
 * Sonar PL/SQL Plugin (Community)
 * Copyright (C) 2015-2018 Felipe Zorzo
 * mailto:felipebzorzo AT gmail DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plsqlopen.matchers;

import java.util.Arrays;

@FunctionalInterface
public interface NameCriteria {

    public boolean matches(String name);

    public static NameCriteria any() {
        return name -> true;
    }

    public static NameCriteria is(String exactName) {
    	return exactName::equalsIgnoreCase;
    }

    public static NameCriteria startsWith(String prefix) {
        return name -> name.toUpperCase().startsWith(prefix.toUpperCase());
    }
    
    public static NameCriteria in(String... prefix) {
        return name -> Arrays.asList(prefix).stream().anyMatch(name::equalsIgnoreCase);
    }
    
}
