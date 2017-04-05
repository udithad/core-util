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

import com.wso2telco.core.spprovisionservice.admin.service.client.OauthAdminClient;
import com.wso2telco.core.spprovisionservice.external.admin.service.OauthAdminService;
import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

public class OauthAdminServiceImpl implements OauthAdminService {

    private static Log log = LogFactory.getLog(OauthAdminServiceImpl.class);
    private OauthAdminClient oauthAdminServiceClient = null;
    private OAuthConsumerAppDTO oAuthConsumerAppDTO = null;

    @Override
    public void removeOAuthApplicationData(String consumerKey) throws SpProvisionServiceException {

        oauthAdminServiceClient = new OauthAdminClient();
        try {
            oauthAdminServiceClient.removeOauthApplicationData(consumerKey);
        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in remove Oauth Application " + e.getMessage());
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    @Override
    public void registerOAuthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        oauthAdminServiceClient = new OauthAdminClient();
        try {
            oAuthConsumerAppDTO = oauthAdminServiceClient.getOauthApplicationDataByAppName(adminServiceDto.getApplicationName());
            if (oAuthConsumerAppDTO != null) {
                if (!(oAuthConsumerAppDTO.getOauthConsumerKey().equals(adminServiceDto.getOauthConsumerKey())) || !(oAuthConsumerAppDTO.getOauthConsumerSecret().equals(adminServiceDto.getOauthConsumerSecret()))) {
                    removeOAuthApplicationData(oAuthConsumerAppDTO.getOauthConsumerKey());
                    registerOAuthApplicationData(adminServiceDto);
                }
            } else {
                oauthAdminServiceClient.registerOauthApplicationData(adminServiceDto);
            }
        } catch (SpProvisionServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

}
