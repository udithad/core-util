package com.wso2telco.core.spprovisionservice.external.admin.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.spprovisionservice.external.admin.services.AouthAdminService;

public class AouthAdminServiceImpl implements AouthAdminService {

    private static Log log = LogFactory.getLog(AouthAdminServiceImpl.class);

    @Override
    public boolean testMethod(String clientId) {
        log.info("========== AouthAdminServiceImpl ========");
        return false;
    }

}
