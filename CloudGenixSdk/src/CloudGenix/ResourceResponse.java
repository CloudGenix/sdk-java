
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResourceResponse<T>
{
    @SerializedName("items")
    public List<T> items;  
}
