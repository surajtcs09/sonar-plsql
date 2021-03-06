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
package org.sonar.plugins.plsqlopen.api.sql;

import static org.sonar.sslr.tests.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.sonar.plugins.plsqlopen.api.DmlGrammar;
import org.sonar.plugins.plsqlopen.api.RuleTest;

public class OrderByClauseTest extends RuleTest {

    @Before
    public void init() {
        setRootRule(DmlGrammar.ORDER_BY_CLAUSE);
    }
    
    @Test
    public void matchesSimpleOrderBy() {
        assertThat(p).matches("order by 1");
    }
    
    @Test
    public void matchesSimpleOrderByAsc() {
        assertThat(p).matches("order by 1 asc");
    }
    
    @Test
    public void matchesSimpleOrderByDesc() {
        assertThat(p).matches("order by 1 desc");
    }
    
    @Test
    public void matchesSimpleOrderByColumn() {
        assertThat(p).matches("order by col");
    }
    
    @Test
    public void matchesOrderByWithMultipleValuesAndOrdering() {
        assertThat(p).matches("order by col1 asc, col2 desc, col3 desc");
    }
    
    @Test
    public void matchesSimpleOrderByTableColumn() {
        assertThat(p).matches("order by tab.col");
    }
    
    @Test
    public void matchesSimpleOrderByTableColumnWithSchema() {
        assertThat(p).matches("order by sch.tab.col");
    }
    
    @Test
    public void matchesSimpleOrderByFunctionCall() {
        assertThat(p).matches("order by func(var)");
    }
    
    @Test
    public void matchesOrderSiblingsBy() {
        assertThat(p).matches("order siblings by col");
    }
    
    @Test
    public void matchesOrderByNullsFirst() {
        assertThat(p).matches("order by col nulls first");
    }
    
    @Test
    public void matchesOrderByNullsLast() {
        assertThat(p).matches("order by col nulls last");
    }

}
