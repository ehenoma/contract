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
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import java.util.Collection;
import java.util.Optional;

public final class MethodParameter {
  private Names symbols;
  private VariableTree tree;
  private Collection<AnnotationTree> annotations;

  private MethodParameter(
      Names symbols, VariableTree tree, Collection<AnnotationTree> annotations) {
    this.symbols = symbols;
    this.tree = tree;
    this.annotations = annotations;
  }

  public VariableTree getTree() {
    return tree;
  }

  public Names getSymbols() {
    return symbols;
  }

  public Optional<AnnotationTree> getAnnotation(Class<?> type) {
    return Optional.empty();
  }

  public Name id() {
    return symbols.fromString(tree.getName().toString());
  }
}
