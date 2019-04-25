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

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;

public abstract class AbstractPreconditionFactory implements PreconditionFactory {
  private Class<?> annotation;
  private ErrorMessage message;

  protected AbstractPreconditionFactory(Class<?> annotation, ErrorMessage message) {
    this.annotation = annotation;
    this.message = message;
  }

  @Override
  public Precondition create(TreeMaker builder, MethodParameter parameter) {
    return Precondition.create(createCondition(builder, parameter), createErrorMessage(parameter));
  }

  private String createErrorMessage(MethodParameter parameter) {
    return parameter
        .getAnnotation(annotation)
        .map(this::findMessageAttribute)
        .orElseGet(message::fallback);
  }

  protected Class<?> createErrorType(MethodParameter parameter) {
    return IllegalArgumentException.class;
  }

  private String findMessageAttribute(AnnotationTree annotation) {
    for (ExpressionTree attribute : annotation.getArguments()) {}

    return null;
  }

  protected abstract JCTree.JCExpression createCondition(
      TreeMaker builder, MethodParameter parameter);
}
