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

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Utility class for creating SQL statements.
 *
 * @author Frank Schroeder &gt;frank@go-left.com&lt;
 */
public class SQLBuilder {

    // --- FIELDS ---

    private List<String> select = new ArrayList<String>();
    private String insert;
    private List<String> from = new ArrayList<String>();
    private List<String> where = new ArrayList<String>();
    private List<String> groupBy = new ArrayList<String>();
    private List<String> orderBy = new ArrayList<String>();
    private SortedMap<String, Object> insertParams = new TreeMap<String, Object>();
    private SortedMap<String, Object> updateParams = new TreeMap<String, Object>();
    private SortedMap<String, Object> whereParams = new TreeMap<String, Object>();
    private List<String> update = new ArrayList<String>();

    // --- GETTER / SETTER METHODS ---

    public void setInsert(String s) {
        insert = s;
    }

    // --- OTHER METHODS ---

    public void addFrom(String s) {
        from.add(s);
    }

    public void addGroupBy(String s) {
        groupBy.add(s);
    }

    public void addInsertParam(Object o, String s) {
        if (o == null) {
            insertParams.put(s, null);
        } else if (o instanceof Boolean) {
            String yesno = ((Boolean) o) ? "Y" : "N";
            insertParams.put(s, yesno);
        } else if (o.getClass().isEnum()) {
            insertParams.put(s, o.toString());
        } else {
            insertParams.put(s, o);
        }
    }

    public void addOrderBy(String s) {
        orderBy.add(s);
    }

    public void addSelect(String s) {
        select.add(s);
    }

    public void addUpdate(String s) {
        update.add(s);
    }

    public void addUpdateParam(Object o, String s) {
        updateParams.put(s, o);
    }

    public void addWhere(String s) {
        where.add(s);
    }

    public void addWhereParam(Object o, String s) {
        whereParams.put(s, o);
    }

    public String buildInsert() {
        StringBuffer sql = new StringBuffer(100);

        sql.append("insert into ");
        sql.append(insert);
        sql.append(" set ");
        sql.append(StringUtils.join(paramList(insertParams), ", "));

        return sql.toString();
    }

    public String buildSelect() {
        StringBuffer sql = new StringBuffer(100);

        sql.append("select ");
        sql.append(StringUtils.join(select, ", "));
        sql.append(" from ");
        sql.append(StringUtils.join(from, ", "));
        sql.append(buildWhere());
        if (!groupBy.isEmpty()) {
            sql.append(" group by ");
            sql.append(StringUtils.join(groupBy, ", "));
        }
        if (!orderBy.isEmpty()) {
            sql.append(" order by ");
            sql.append(StringUtils.join(orderBy, ", "));
        }

        return sql.toString();
    }

    public String buildUpdate() {
        StringBuffer sql = new StringBuffer(100);

        sql.append("update ");
        sql.append(StringUtils.join(update, ", "));
        sql.append("set ");
        sql.append(StringUtils.join(paramList(updateParams), ", "));
        sql.append(buildWhere());

        return sql.toString();
    }

    private String buildWhere() {
        StringBuffer sql = new StringBuffer();

        List<String> s = new ArrayList<String>();
        s.addAll(where);
        s.addAll(paramList(whereParams));
        if (!s.isEmpty()) {
            sql.append("where ");
            sql.append(StringUtils.join(s, " and "));
        }

        return sql.toString();
    }

    private List<String> paramList(Map<String, Object> params) {
        List<String> columns = new ArrayList<String>();
        for (String column : params.keySet()) {
            columns.add(column + " = :" + column);
        }

        return columns;
    }

    public Map<String, Object> values() {
        Map<String, Object> allValues = new HashMap<String, Object>();
        allValues.putAll(insertParams);
        allValues.putAll(updateParams);
        allValues.putAll(whereParams);
        return allValues;
    }
}

