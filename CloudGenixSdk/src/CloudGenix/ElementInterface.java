
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ElementInterface 
{
    @SerializedName("id")
    public String id;

    @SerializedName("type")
    public String type;

    @SerializedName("attached_lan_networks")
    public Object attachedLanNetworks;

    @SerializedName("site_wan_interface_ids")
    public List<String> siteWanInterfaceIds;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("mac_address")
    public String macAddress;

    @SerializedName("mtu")
    public int mtu;

    @SerializedName("ipv4_config")
    public Object ipV4Config;

    @SerializedName("dhcp_relay")
    public Object dhcpRelay;

    @SerializedName("ethernet_port")
    public Object ethernetPort;

    @SerializedName("admin_up")
    public Boolean adminUp;

    @SerializedName("nat_address")
    public String natAddress;

    @SerializedName("nat_port")
    public int natPort;

    @SerializedName("used_for")
    public String usedFor;

    @SerializedName("bypass_pair")
    public Object bypassPair;

    @SerializedName("bound_interfaces")
    public Object boundInterfaces;

    @SerializedName("sub_interface")
    public Object subInterface;

    @SerializedName("pppoe_config")
    public Object pppoeConfig;

    @SerializedName("parent")
    public Object parent;
}
