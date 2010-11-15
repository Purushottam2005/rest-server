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

import com.goleft.rest.entity.Customer;
import com.goleft.rest.util.SQLBuilder;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO
        extends BaseDAO {

    // --- FIELDS ---

    private CustomerRowMapper rowMapper = new CustomerRowMapper();

    // --- GETTER / SETTER METHODS ---

    public List<Customer> getCustomers() {
        SQLBuilder sql = new SQLBuilder();
        sql.addSelect("firstName");
        sql.addSelect("lastName");
        sql.addSelect("email");
        sql.addFrom("customer");
        sql.addOrderBy("email");

        log.debug("CustomerDAO.getCustomers sql=%s", sql);
        return db.query(sql.toString(), rowMapper);
    }

    public Customer get(long id) {
        log.debug("CustomerDAO.get(%d)", id);
        String sql = "select * from customer where customerId = ? ";
        return getFirst(db.query(sql, rowMapper, id));
    }

    // --- INNER CLASSES ---

    class CustomerRowMapper
            implements ParameterizedRowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getLong("customerId"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            return customer;
        }
    }
}
