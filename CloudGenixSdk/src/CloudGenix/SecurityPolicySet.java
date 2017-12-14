
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SecurityPolicySet 
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("policyrule_order")
    public List<String> policyRuleOrder;
}
