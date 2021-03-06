/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.printer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ConcreteSyntaxModelAcceptanceTest {

    private String prettyPrint(Node node) {
        return ConcreteSyntaxModel.genericPrettyPrint(node);
    }

    private String prettyPrintedExpectation(String name) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/github/javaparser/printer/" + name + "_prettyprinted");
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        inputStream.close();
        return result;
    }

    @Test
    public void printingExamplePrettyPrintVisitor() throws IOException {
        CompilationUnit cu = JavaParser.parseResource("com/github/javaparser/printer/PrettyPrintVisitor.java");
        assertEquals(prettyPrintedExpectation("PrettyPrintVisitor"), prettyPrint(cu));
    }

    @Test
    public void printingExampleJavaConcepts() throws IOException {
        CompilationUnit cu = JavaParser.parseResource("com/github/javaparser/printer/JavaConcepts.java");
        //System.out.println(cu.toString());
        assertEquals(prettyPrintedExpectation("JavaConcepts"), prettyPrint(cu));
    }

}
