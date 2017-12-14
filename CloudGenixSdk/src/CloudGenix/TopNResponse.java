
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class TopNResponse 
{ 
    @SerializedName("top_n")
    public TopNData topn;
 
    public TopNResponse()
    {
        this.topn = new TopNData();
    }
  
    public class TopNData
    {
        @SerializedName("start_time")
        public String startTime;

        @SerializedName("end_time")
        public String endTime;

        @SerializedName("items")
        public List<String> items;

        @SerializedName("limit")
        public int limit;

        @SerializedName("topn_basis")
        public String basis;

        @SerializedName("type")
        public String type;

        public TopNData()
        {
            this.items = new ArrayList<>();   
        } 
    } 
}
