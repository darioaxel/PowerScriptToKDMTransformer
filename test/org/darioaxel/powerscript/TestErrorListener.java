/*
 * The MIT License
 *
 * Copyright 2015 darioaxel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.darioaxel.powerscript;

import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

/**
 *
 * @author darioaxel
 */
public class TestErrorListener implements ANTLRErrorListener {

    private boolean fail = false;

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
            int arg3, String arg4, RecognitionException arg5) {
        setFail(true);
    }

    @Override
    public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
            int arg3, int arg4, ATNConfigSet arg5) {
        setFail(true);
    }

    @Override
    public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
            int arg3, BitSet arg4, ATNConfigSet arg5) {
        setFail(true);
    }

    @Override
    public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
            boolean arg4, BitSet arg5, ATNConfigSet arg6) {
        setFail(true);
    }
}
