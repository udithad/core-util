/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.spprovisionservice.external.admin.service.impl;

import com.wso2telco.core.spprovisionservice.admin.service.client.ApplicationManagementClient;
import com.wso2telco.core.spprovisionservice.external.admin.service.SpAppManagementService;
import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.xsd.ServiceProvider;

public class SpAppManagementServiceImpl implements SpAppManagementService {

    ApplicationManagementClient applicationManagementServiceClient = null;
    private static Log log = LogFactory.getLog(SpAppManagementServiceImpl.class);

    public SpAppManagementServiceImpl() {
        applicationManagementServiceClient = new ApplicationManagementClient();
    }

    @Override
    public boolean createSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        boolean success =  true;
        boolean failure = false;
        boolean status;

//        if((serviceProviderDto != null) && (getSpApplicationData(serviceProviderDto.getApplicationName())) == null){

        if(serviceProviderDto != null){
            try {
                applicationManagementServiceClient.createSpApplication(serviceProviderDto);
                status = success;
            } catch (SpProvisionServiceException e) {
                log.error("Error occurred in remove create Service Provider Application " + e.getMessage());
                throw new SpProvisionServiceException(e.getMessage());
            }
        }
        else
        {
            log.error("Service provider details are null");
            status = failure;
        }
        return status;
    }

    @Override
    public void updateSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        try {
            ServiceProvider serviceProvider = applicationManagementServiceClient.getSpApplicationData(serviceProviderDto.getApplicationName());
            serviceProviderDto.setApplicationId(serviceProvider.getApplicationID());
            applicationManagementServiceClient.updateSpApplication(serviceProviderDto);
        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in update Service Provider Application " + e.getMessage());
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    @Override
    public ServiceProvider getSpApplicationData(String applicationName) throws SpProvisionServiceException {

        ServiceProvider serviceProvider;

        try {
            serviceProvider = applicationManagementServiceClient.getSpApplicationData(applicationName);
        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in getting application data" + e.getMessage());
            throw new SpProvisionServiceException(e.getMessage());
        }
        return serviceProvider;
    }

    @Override
    public void deleteSpApplication(String applicationName) throws SpProvisionServiceException {

        try {
            applicationManagementServiceClient.deleteSpApplication(applicationName);
        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in deleting application data" + e.getMessage());
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

}
