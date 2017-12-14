
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class PolicySet 
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("default_policy")
    public Boolean defaultPolicy;

    @SerializedName("bandwidth_allocation_schemes")
    public Object bandwidthAllocationSchemes;

    @SerializedName("business_priority_names")
    public Object businessPriorityNames;
}
