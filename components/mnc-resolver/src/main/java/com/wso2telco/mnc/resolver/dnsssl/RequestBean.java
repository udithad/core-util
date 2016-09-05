/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.mnc.resolver.dnsssl;

 



 

// TODO: Auto-generated Javadoc
/**
 * The Class RequestBean.
 */

@Deprecated
public class RequestBean {

	/** The country code. */
	private String countryCode;

	/** The tn. */
	private String tn;

	 
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestBean [countryCode = " + countryCode + ", TN = " + tn
				+ "]";
	}

	 
	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	 
	/**
	 * Sets the country code.
	 *
	 * @param countryCode the new country code
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	 
	/**
	 * Gets the tn.
	 *
	 * @return the tn
	 */
	public String getTn() {
		return tn;
	}

	 
	/**
	 * Sets the tn.
	 *
	 * @param number the new tn
	 */
	public void setTn(final String number) {
		this.tn = number;
	}
}
