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

public class Topology 
{
    @SerializedName("type")
    public String type;

    @SerializedName("nodes")
    public List<SiteNode> sites;

    @SerializedName("links")
    public List<WanLink> links;
     
    public class SiteNode
    {
        @SerializedName("id")
        public String siteId;

        @SerializedName("tenant_id")
        public String tenantId;

        @SerializedName("type")
        public String type;

        @SerializedName("name")
        public String name;

        @SerializedName("location")
        public Location geocoordinates;

        @SerializedName("address")
        public Address address;

        @SerializedName("state")
        public String state;

        @SerializedName("role")
        public String role; 
    }

    public class WanLink
    {
        @SerializedName("path_id")
        public String pathId;

        @SerializedName("source_node_id")
        public String sourceSiteId;

        @SerializedName("source_site_name")
        public String sourceSiteName;

        @SerializedName("target_node_id")
        public String targetSiteId;

        @SerializedName("target_site_name")
        public String targetSiteName;

        @SerializedName("status")
        public String status;

        @SerializedName("type")
        public String type;

        @SerializedName("sub_type")
        public String subType;

        @SerializedName("source_wan_if_id")
        public String sourceWanInterfaceId;

        @SerializedName("source_wan_network")
        public String sourceWanNetworkName;

        @SerializedName("source_wan_nw_id")
        public String sourceWanNetworkId;

        @SerializedName("target_wan_if_id")
        public String targetWanInterfaceId;

        @SerializedName("target_wan_network")
        public String targetWanNetworkName;

        @SerializedName("target_wan_nw_id")
        public String targetWanNetworkId;

        @SerializedName("source_circuit_name")
        public String sourceCircuitName;

        @SerializedName("target_circuit_name")
        public String targetCircuitName;

        @SerializedName("admin_up")
        public Object adminUp;

        @SerializedName("vpnlinks")
        public List<String> vpnLinkIds; 
    } 
}
