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

package com.goleft.rest.dao;

import com.goleft.rest.log.ServiceLogger;
import com.goleft.rest.log.ServiceLoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Base DAO which has a data source, a jdbc template and a logger.
 *
 * @author Frank Schroeder &gt;frank.schroeder@go-left.com&lt;
 */
public abstract class BaseDAO {

    protected ServiceLogger log;
    protected DataSource dataSource;
    protected SimpleJdbcTemplate db;

    // --- CONSTRUCTORS ---

    protected BaseDAO() {
        this.log = ServiceLoggerFactory.getLogger(this.getClass());
    }

    // --- GETTER / SETTER METHODS ---

    @Required
    public void setDataSource(DataSource dataSource) {
        this.db = new SimpleJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    // --- OTHER METHODS ---

    /**
     * Return the first object from a list if the list isn't empty.
     *
     * @param list the list
     * @param <E>  the type of the objects in the list
     * @return the first object or null
     */
    public <E> E getFirst(List<E> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }
}
