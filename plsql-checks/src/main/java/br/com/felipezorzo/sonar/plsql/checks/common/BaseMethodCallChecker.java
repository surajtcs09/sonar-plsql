/*
 * Sonar PL/SQL Plugin (Community)
 * Copyright (C) 2015 Felipe Zorzo
 * felipe.b.zorzo@gmail.com
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
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package br.com.felipezorzo.sonar.plsql.checks.common;

import java.util.List;

import com.sonar.sslr.api.AstNode;

import br.com.felipezorzo.sonar.plsql.api.PlSqlGrammar;
import br.com.felipezorzo.sonar.plsql.checks.AbstractBaseCheck;

public abstract class BaseMethodCallChecker extends AbstractBaseCheck {
    
    private AstNode currentNode;
    
    public AstNode getCurrentNode() {
        return currentNode;
    }
    
    @Override
    public void init() {
        subscribeTo(PlSqlGrammar.METHOD_CALL);
    }
    
    @Override
    public void visitNode(AstNode node) {
        currentNode = node;
        AstNode identifier = node.getFirstChild();
        
        if (identifier.is(PlSqlGrammar.PRIMARY_EXPRESSION)) {
            identifier = identifier.getFirstChild();
        }
        
        if (!isMethod(identifier)) return;
        
        AstNode arguments = node.getFirstChild(PlSqlGrammar.ARGUMENTS);
        if (arguments == null) return;
        
        List<AstNode> allArguments = arguments.getChildren(PlSqlGrammar.ARGUMENT);
        checkArguments(allArguments);
    }
    
    protected abstract boolean isMethod(AstNode identifier);
    
    protected abstract void checkArguments(List<AstNode> arguments);

}