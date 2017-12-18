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
import java.util.ArrayList;
import java.util.List;

public class MetricsResponse 
{
    @SerializedName("metrics")
    public List<MetricsContainer> metrics;
     
    public class MetricsContainer
    {
        @SerializedName("series")
        public List<SeriesContainer> series;

        public MetricsContainer()
        {
            series = new ArrayList<>();    
        }
 
        public class SeriesContainer
        {
            @SerializedName("data")
            public List<DataContainer> data;

            @SerializedName("interval")
            public String interval;

            @SerializedName("name")
            public String name;

            @SerializedName("unit")
            public String unit;

            @SerializedName("view")
            public String view;

            public SeriesContainer()
            {
                this.data = new ArrayList<>();
            }
  
            public class DataContainer
            {
                @SerializedName("datapoints")
                public List<DataPoint> dataPoints;

                @SerializedName("statistics")
                public String statistics;

                public DataContainer()
                {
                    this.dataPoints = new ArrayList<>();
                } 
                
                public class DataPoint
                {
                    @SerializedName("time")
                    public String time;

                    @SerializedName("value")
                    public String value; 
                }
            }
        }
    } 
}
