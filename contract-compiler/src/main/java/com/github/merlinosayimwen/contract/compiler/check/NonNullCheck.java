//  Copyright (c) 2019 Merlin Osayimwen
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
//  IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.github.merlinosayimwen.contract.compiler.check;

import com.github.merlinosayimwen.contract.NonNull;
import com.github.merlinosayimwen.contract.compiler.*;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;

/**
 * Checks that an argument is not null.
 *
 * @see Precondition
 * @see PreconditionFactory
 * @see NonNull
 */
public final class NonNullCheck extends AbstractPreconditionFactory {
  private JCTree.JCLiteral nil;

  private NonNullCheck(ErrorMessage message, JCTree.JCLiteral nil) {
    super(NonNull.class, message);
    this.nil = nil;
  }

  @Override
  protected JCTree.JCExpression createCondition(TreeMaker builder, MethodParameter parameter) {
    JCTree.JCIdent name = builder.Ident(parameter.id());
    return builder.Binary(JCTree.Tag.EQ, name, nil);
  }

  @Override
  protected Class<?> createErrorType(MethodParameter parameter) {
    return NullPointerException.class;
  }

  public static NonNullCheck create(TreeMaker builder) {
    JCTree.JCLiteral nil = builder.Literal(null);
    return new NonNullCheck(ErrorMessage.of("the argument {0} may not be null"), nil);
  }
}
