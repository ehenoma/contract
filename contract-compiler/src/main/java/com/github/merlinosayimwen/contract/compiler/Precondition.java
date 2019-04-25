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

package com.github.merlinosayimwen.contract.compiler;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;

import java.util.Objects;

public class Precondition implements AstGenerator {
  private String errorMessage;
  private JCTree.JCExpression condition;

  private Precondition(String errorMessage, JCTree.JCExpression condition) {
    this.errorMessage = errorMessage;
    this.condition = condition;
  }

  @Override
  public JCTree generate(TreeMaker builder) {
    return condition;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public JCTree.JCExpression getCondition() {
    return condition;
  }

  public static Precondition create(JCTree.JCExpression expression, String errorMessage) {
    Objects.requireNonNull(expression);
    Objects.requireNonNull(errorMessage);
    return new Precondition(errorMessage, expression);
  }
}
