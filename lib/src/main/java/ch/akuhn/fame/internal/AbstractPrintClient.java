//  Copyright (c) 2007-2008 Adrian Kuhn <akuhn(a)iam.unibe.ch>
//  
//  This file is part of 'Fame (for Java)'.
//  
//  'Fame (for Java)' is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or (at your
//  option) any later version.
//  
//  'Fame (for Java)' is distributed in the hope that it will be useful, but
//  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
//  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
//  License for more details.
//  
//  You should have received a copy of the GNU Lesser General Public License
//  along with 'Fame (for Java)'. If not, see <http://www.gnu.org/licenses/>.
//  

package ch.akuhn.fame.internal;

import java.io.IOException;

import ch.akuhn.fame.parser.AbstractParserClient;
import ch.akuhn.util.Files;

/**
 * Writes MSE document on output-stream.
 * 
 * 
 * @author akuhn
 * 
 */
public abstract class AbstractPrintClient extends AbstractParserClient {

    protected int indentation;
    protected Appendable stream;
    private boolean wasln;

    public AbstractPrintClient(Appendable stream) {
        this.stream = stream;
        this.indentation = 0;
        this.wasln = true;
    }

    protected void append(char ch) {
        try {
            stream.append(ch);
            wasln = false;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void append(CharSequence characters) {
        try {
            stream.append(characters);
            wasln = false;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void appendJSONString(String value) {
        append('"');
        for (char character : value.toCharArray()) {
            switch (character) {
                case '"':
                    append("\\\"");
                    break;
                case '\\':
                    append("\\\\");
                    break;
                case '\b':
                    append("\\b");
                    break;
                case '\f':
                    append("\\f");
                    break;
                case '\n':
                    append("\\n");
                    break;
                case '\r':
                    append("\\r");
                    break;
                case '\t':
                    append("\\t");
                    break;
                default:
                    if (character < ' ') {
                        append("\\u00");
                        append(Character.forDigit(character >>> 4, 16));
                        append(Character.forDigit(character & 0xf, 16));
                    } else {
                        append(character);
                    }
                    break;
            }
        }
        append('"');
    }

    protected void close() {
        Files.close(stream);
    }

    protected void lntabs() {
        if (!wasln) {
            try {
                stream.append('\n');
                for (int n = 0; n < indentation; n++) {
                    stream.append('\t');
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        wasln = true;
    }

}
