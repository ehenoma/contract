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

import com.github.merlinosayimwen.contract.Unsigned;
import com.github.merlinosayimwen.contract.compiler.AbstractPreconditionFactory;
import com.github.merlinosayimwen.contract.compiler.ErrorMessage;
import com.github.merlinosayimwen.contract.compiler.MethodParameter;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;

/**
 * Creates a precondition which ensures that an argument is positive.
 *
 * @see Unsigned
 */
public final class UnsignedCheck extends AbstractPreconditionFactory {
  private UnsignedCheck(ErrorMessage message) {
    super(Unsigned.class, message);
  }

  @Override
  protected JCTree.JCExpression createCondition(TreeMaker builder, MethodParameter parameter) {
    return builder.Binary(
        JCTree.Tag.GE, builder.Ident(parameter.id()), builder.Literal(TypeTag.INT, 0));
  }

  public static UnsignedCheck create() {
    return new UnsignedCheck(ErrorMessage.of("the argument {0} can not be negative"));
  }
}
