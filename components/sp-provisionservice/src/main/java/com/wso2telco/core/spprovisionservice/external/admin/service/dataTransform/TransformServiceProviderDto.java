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
package com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform;

import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.xsd.*;

public class TransformServiceProviderDto {

    private ServiceProvider serviceProvider = null;
    private ClaimConfig claimConfig = null;
    private InboundAuthenticationConfig inboundAuthenticationConfig = null;
    private InboundProvisioningConfig inboundProvisioningConfig = null;
    private LocalAndOutboundAuthenticationConfig localAndOutBoundAuthenticationConfig = null;
    private PermissionsAndRoleConfig permissionAndRoleConfig = null;
    private static Log log = LogFactory.getLog(TransformServiceProviderDto.class);


    /*
    * Transform ServiceProviderDto to create the service Provider
    * */
    public ServiceProvider transformToServiceProviderToCreateApplication(ServiceProviderDto serviceProviderDto) {

        serviceProvider = new ServiceProvider();
        serviceProvider.setApplicationName(serviceProviderDto.getApplicationName());
        serviceProvider.setApplicationID(serviceProviderDto.getApplicationId());
        return serviceProvider;
    }

    public ServiceProvider transformToServiceProviderToUpdateApplication(ServiceProviderDto serviceProviderDto) {

        serviceProvider = new ServiceProvider();
        claimConfig = new ClaimConfig();
        inboundAuthenticationConfig = new InboundAuthenticationConfig();
        inboundProvisioningConfig = new InboundProvisioningConfig();
        permissionAndRoleConfig = new PermissionsAndRoleConfig();
        localAndOutBoundAuthenticationConfig = new LocalAndOutboundAuthenticationConfig();

        serviceProvider.setApplicationName(serviceProviderDto.getApplicationName());
        serviceProvider.setApplicationID(serviceProviderDto.getApplicationId());
        serviceProvider.setDescription(serviceProviderDto.getDescription());
        serviceProvider.setSaasApp(serviceProviderDto.isSaasApp());

        claimConfig.setAlwaysSendMappedLocalSubjectId(serviceProviderDto.isAlwaysSendMappedLocalSubjectId());
        claimConfig.setLocalClaimDialect(serviceProviderDto.isLocalClaimDialect());
        serviceProvider.setClaimConfig(claimConfig);

        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig1 = new InboundAuthenticationRequestConfig();
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig2 = new InboundAuthenticationRequestConfig();
        inboundAuthenticationRequestConfig1.setInboundAuthKey(serviceProviderDto.getApplicationName());
        inboundAuthenticationRequestConfig1.setInboundAuthType("passivests");

        inboundAuthenticationRequestConfig2.setInboundAuthKey(serviceProviderDto.getInboundAuthKey());
        inboundAuthenticationRequestConfig2.setInboundAuthType(serviceProviderDto.getInboundAuthType());

        Property property1 = new Property();
        property1.setConfidential(serviceProviderDto.isConfidential());
        property1.setDefaultValue(serviceProviderDto.getDefaultValue());
        property1.setName(serviceProviderDto.getPropertyName());
        property1.setValue(serviceProviderDto.getPropertyValue());
        property1.setRequired(serviceProviderDto.isPropertyRequired());

        Property[] property = {property1};

        inboundAuthenticationRequestConfig2.setProperties(property);
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig[] = {inboundAuthenticationRequestConfig1, inboundAuthenticationRequestConfig2};

        inboundAuthenticationConfig.setInboundAuthenticationRequestConfigs(inboundAuthenticationRequestConfig);
        serviceProvider.setInboundAuthenticationConfig(inboundAuthenticationConfig);

        inboundProvisioningConfig.setProvisioningEnabled(serviceProviderDto.isProvisioningEnabled());
        inboundProvisioningConfig.setProvisioningUserStore(serviceProviderDto.getProvisioningUserStore());
        serviceProvider.setInboundProvisioningConfig(inboundProvisioningConfig);

        permissionAndRoleConfig.setIdpRoles(serviceProviderDto.getIdpRoles());
        serviceProvider.setPermissionAndRoleConfig(permissionAndRoleConfig);

        localAndOutBoundAuthenticationConfig.setAuthenticationType(serviceProviderDto.getLocalAuthenticatorConfigsAuthenticationType());

        LocalAuthenticatorConfig localAuthenticatorConfig1 = new LocalAuthenticatorConfig();
        localAuthenticatorConfig1.setDisplayName(serviceProviderDto.getLocalAuthenticatorConfigsDisplayName());
        localAuthenticatorConfig1.setEnabled(serviceProviderDto.isLocalAuthenticatorConfigsEnabled());
        localAuthenticatorConfig1.setName(serviceProviderDto.getLocalAuthenticatorConfigsName());
        localAuthenticatorConfig1.setValid(serviceProviderDto.isLocalAuthenticatorConfigsValid());

        LocalAuthenticatorConfig localAuthenticatorConfig[] = {localAuthenticatorConfig1};

        AuthenticationStep authenticationStep1 = new AuthenticationStep();
        authenticationStep1.setLocalAuthenticatorConfigs(localAuthenticatorConfig);

        AuthenticationStep authenticationStep[] = {authenticationStep1};

        localAndOutBoundAuthenticationConfig.setAuthenticationSteps(authenticationStep);
        serviceProvider.setLocalAndOutBoundAuthenticationConfig(localAndOutBoundAuthenticationConfig);

        return serviceProvider;
    }

    /*
    * Transform ServiceProvider data to ServiceProviderDTO
    * */
    public ServiceProviderDto transformToServiceProviderDto(ServiceProvider serviceProvider) {
        ServiceProviderDto serviceProviderDto = null;
        AuthenticationStep authenticationStep[];
        LocalAuthenticatorConfig localAuthenticatorConfig[];
        claimConfig = new ClaimConfig();
        inboundAuthenticationConfig = new InboundAuthenticationConfig();
        inboundProvisioningConfig = new InboundProvisioningConfig();
        permissionAndRoleConfig = new PermissionsAndRoleConfig();
        localAndOutBoundAuthenticationConfig = new LocalAndOutboundAuthenticationConfig();

        serviceProviderDto.setApplicationName(serviceProvider.getApplicationName());
        serviceProviderDto.setApplicationId(serviceProvider.getApplicationID());
        serviceProviderDto.setDescription(serviceProvider.getDescription());
        serviceProviderDto.setSaasApp(serviceProvider.getSaasApp());

        claimConfig = serviceProvider.getClaimConfig();
        serviceProviderDto.setAlwaysSendMappedLocalSubjectId(claimConfig.isAlwaysSendMappedLocalSubjectIdSpecified());
        serviceProviderDto.setLocalClaimDialect(claimConfig.getLocalClaimDialect());

        inboundAuthenticationConfig = serviceProvider.getInboundAuthenticationConfig();
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig[] = new InboundAuthenticationRequestConfig[2];
        inboundAuthenticationRequestConfig = inboundAuthenticationConfig.getInboundAuthenticationRequestConfigs();
        serviceProviderDto.setInboundAuthKey(inboundAuthenticationRequestConfig[1].getInboundAuthKey());
        serviceProviderDto.setInboundAuthType(inboundAuthenticationRequestConfig[1].getInboundAuthType());

        Property[] property;
        property = inboundAuthenticationRequestConfig[1].getProperties();
        serviceProviderDto.setConfidential(property[0].getConfidential());
        serviceProviderDto.setDefaultValue(property[0].getDefaultValue());
        serviceProviderDto.setPropertyName(property[0].getName());
        serviceProviderDto.setPropertyValue(property[0].getValue());
        serviceProviderDto.setPropertyRequired(property[0].getRequired());

        inboundProvisioningConfig = serviceProvider.getInboundProvisioningConfig();
        serviceProviderDto.setProvisioningEnabled(inboundProvisioningConfig.getProvisioningEnabled());
        serviceProviderDto.setProvisioningUserStore(inboundProvisioningConfig.getProvisioningUserStore());

        permissionAndRoleConfig = serviceProvider.getPermissionAndRoleConfig();

        serviceProviderDto.setLocalAuthenticatorConfigsAuthenticationType(localAndOutBoundAuthenticationConfig.getAuthenticationType());
        localAndOutBoundAuthenticationConfig = serviceProvider.getLocalAndOutBoundAuthenticationConfig();
        authenticationStep = localAndOutBoundAuthenticationConfig.getAuthenticationSteps();
        localAuthenticatorConfig = authenticationStep[0].getLocalAuthenticatorConfigs();
        serviceProviderDto.setLocalAuthenticatorConfigsDisplayName(localAuthenticatorConfig[0].getDisplayName());
        serviceProviderDto.setLocalAuthenticatorConfigsEnabled(localAuthenticatorConfig[0].getEnabled());
        serviceProviderDto.setLocalAuthenticatorConfigsName(localAuthenticatorConfig[0].getName());
        serviceProviderDto.setLocalAuthenticatorConfigsValid(localAuthenticatorConfig[0].getValid());

        return serviceProviderDto;
    }
}
