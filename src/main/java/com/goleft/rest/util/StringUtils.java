/*
 * Copyright (c) 2010, Frank Schroeder <frank.schroeder@go-left.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * * Neither the name of Frank Schroeder nor the names of its contributors may
 *    be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.goleft.rest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Documentation goes here.
 *
 * @author Frank Schroeder &gt;frank.schroeder@go-left.com&lt;
 * @version SVN $Id: StringUtils.java 1 2010-05-28 08:02:32Z frank $
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String paramList(int count) {
        if (count == 0) {
            return "() ";
        }

        StringBuffer sb = new StringBuffer(2 * count + 2);
        sb.append("(");
        for (int i = 0; i < count - 1; i++) {
            sb.append("?,");
        }
        sb.append("?) ");

        return sb.toString();
    }

    public static String valueList(String[] values) {
        return valueList(Arrays.asList(values));
    }

    public static String valueList(List<String> values) {
        if (values == null || values.size() == 0) return "";

        StringBuffer sb = new StringBuffer(100);
        sb.append("(");
        for (String s : values) {
            sb.append("'").append(s).append("',");
        }
        // replace the last comma with a closing parenthesis
        sb.replace(sb.length() - 1, sb.length(), ")");

        return sb.toString();
    }

    public static List<String> splitCommaSeparatedList(String s) {
        return splitToList(s, ",");
    }

    public static List<String> splitToList(String s, String separator) {
        if (isEmpty(s)) return new ArrayList<String>();

        return Arrays.asList(s.split(String.format("\\s*%s\\s*", separator)));
    }
}
