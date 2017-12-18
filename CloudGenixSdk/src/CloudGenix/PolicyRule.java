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

public class PolicyRule
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("app_def_id")
    public String applicationDefinitionId;

    @SerializedName("network_context_id")
    public String networkContextId;

    @SerializedName("priority_num")
    public int priorityNumber;

    @SerializedName("paths_allowed")
    public Object pathsAllowed;

    @SerializedName("service_context")
    public Object serviceContext;
}
