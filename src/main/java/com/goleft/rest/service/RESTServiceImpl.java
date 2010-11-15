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

package com.goleft.rest.service;

import com.goleft.rest.dao.AddressDAO;
import com.goleft.rest.dao.CustomerDAO;
import com.goleft.rest.entity.Address;
import com.goleft.rest.entity.Customer;
import com.goleft.rest.log.ServiceLogger;
import com.goleft.rest.log.ServiceLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.PathParam;
import java.util.List;

public class RESTServiceImpl
        implements RESTService {

    // --- FIELDS ---

    ServiceLogger log = ServiceLoggerFactory.getLogger(RESTServiceImpl.class);

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private CustomerDAO customerDAO;


    // --- INTERFACE METHODS ---

    // --- Interface RESTService ---

    @Override
    public List<Customer> getAllCustomers() {
        log.debug("RESTService.getAllCustomers()");
        return customerDAO.getCustomers();
    }

    @Override
    public Customer getCustomer(@PathParam("id") Long customerId) {
        log.debug("RESTService.getCustomer(%d)", customerId);
        return customerDAO.get(customerId);
    }

    @Override
    public List<Address> getAddressesForCustomer(@PathParam("id") Long customerId) {
        log.debug("RESTService.getAddressesForCustomer(%d)", customerId);
        return addressDAO.getForCustomer(customerId);
    }
}
