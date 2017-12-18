/*

    CloudGenix Controller SDK
    (c) 2017 CloudGenix, Inc.
    All Rights Reserved

    https://www.cloudgenix.com

    This SDK is released under the MIT license.
    For support, please contact us on:

        NetworkToCode Slack channel #cloudgenix: http://slack.networktocode.com
        Email: developers@cloudgenix.com

 */

package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class Site 
{
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("description")
    public String description;
    
    @SerializedName("address")
    public Address address;
    
    @SerializedName("location")
    public Location location;
    
    @SerializedName("policy_set_id")
    public String policySetId;
    
    @SerializedName("element_cluster_role")
    public String elementClusterRole;
    
    @SerializedName("security_policyset_id")
    public String securityPolicySetId;
    
    @SerializedName("service_binding")
    public String serviceBinding;    
}
