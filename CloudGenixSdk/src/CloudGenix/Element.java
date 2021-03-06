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
import java.util.List;

public class Element 
{ 
    @SerializedName("id")
    public String id;

    @SerializedName("_etag")
    public int etag;

    @SerializedName("software_version")
    public String softwareVersion;

    @SerializedName("hw_id")
    public String hardwareId;

    @SerializedName("serial_number")
    public String serialNumber;

    @SerializedName("model_name")
    public String modelName;

    @SerializedName("name")
    public String name;

    @SerializedName("site_id")
    public String siteId;

    @SerializedName("description")
    public String description;

    @SerializedName("role")
    public String role;

    @SerializedName("state")
    public String state;

    @SerializedName("allowed_roles")
    public List<String> allowedRoles;

    @SerializedName("cluster_insertion_mode")
    public String clusterInsertionMode;

    @SerializedName("cluster_member_id")
    public String clusterMemberId;

    @SerializedName("connected")
    public Boolean connected;

    @SerializedName("admin_action")
    public String adminAction;

    @SerializedName("deployment_op")
    public String deploymentOp;   
}
