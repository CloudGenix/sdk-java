
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class FlowResponse 
{ 
    @SerializedName("flows")
    public FlowCollection Flows;
 
    public FlowResponse()
    {
    }
 
    public class FlowCollection
    {
        @SerializedName("debug_level")
        public String debugLevel;

        @SerializedName("start_time")
        public Object startTime;

        @SerializedName("end_time")
        public Object endTime;

        @SerializedName("items")
        public List<Flow> items;

        public FlowCollection()
        {
            items = new ArrayList<>(); 
        }
 
        public class Flow
        {
            @SerializedName("app_id")
            public String appId;

            @SerializedName("average_ntt")
            public Object averageNetworkTransferTime;

            @SerializedName("average_pg")
            public Object averagePathGoodput;

            @SerializedName("average_rtt")
            public Object averageResponseTime;

            @SerializedName("average_srt")
            public Object averageServerResponseTime;

            @SerializedName("average_udp_trt")
            public Object averageUdpTransactionResponseTime;

            @SerializedName("avg_jitter_c2s")
            public Object averageJitterClientToServer;

            @SerializedName("avg_jitter_s2c")
            public Object averageJitterServerToClient;

            @SerializedName("avg_mos_c2s")
            public Object averageMosClientToServer;

            @SerializedName("avg_mos_s2c")
            public Object averageMosServerToClient;

            @SerializedName("avg_packet_loss_c2s")
            public Object averagePacketLossClientToServer;

            @SerializedName("avg_packet_loss_s2c")
            public Object averagePacketLossServerToClient;

            @SerializedName("bytes_c2s")
            public long bytesClientToServer;

            @SerializedName("bytes_s2c")
            public long bytesServerToClient;

            @SerializedName("codec_c2s")
            public List<Object> codecClientToServer;

            @SerializedName("codec_s2c")
            public List<Object> codecServerToClient;

            @SerializedName("destination_ip")
            public String destinationIp;

            @SerializedName("destination_port")
            public int destinationPort;

            @SerializedName("fc_app_id")
            public String flowControllerAppId;

            @SerializedName("fin_c2s")
            public int tcpFinClientToServer;

            @SerializedName("fin_s2c")
            public int tcpFinServerToClient;

            @SerializedName("flow_decision_metadata_list")
            public List<Object> flowDecisionMetadataList;

            @SerializedName("flow_end_time_ms")
            public long flowEndTimeMs;

            @SerializedName("flow_start_time_ms")
            public long flowStartTimeMs;

            @SerializedName("incomplete_transactions")
            public Object incompleteTransactions;

            @SerializedName("init_success")
            public Object initSuccess;

            @SerializedName("is_local_traffic")
            public Object isLocalTraffic;

            @SerializedName("is_security_policy_present")
            public Object securityPolicyPresent;

            @SerializedName("lan_to_wan")
            public Object lanToWan;

            @SerializedName("max_jitter_c2s")
            public Object maxJitterClientToServer;

            @SerializedName("max_jitter_s2c")
            public Object maxJitterServerToClient;

            @SerializedName("max_mos_c2s")
            public Object maxMosClientToServer;

            @SerializedName("max_mos_s2c")
            public Object maxMosServerToClient;

            @SerializedName("max_ntt")
            public Object maxNetworkTransferTime;

            @SerializedName("max_packet_loss_c2s")
            public Object maxPacketLossClientToServer;

            @SerializedName("max_packet_loss_s2c")
            public Object maxPacketLossServerToClient;

            @SerializedName("max_pg")
            public Object maxPacketGap;

            @SerializedName("max_rtt")
            public Object maxRoundTripTime;

            @SerializedName("max_srt")
            public Object maxServerResponseTime;

            @SerializedName("max_udp_trt")
            public Object maxUdpTransactionResponseTime;

            @SerializedName("media_type")
            public Object mediaType;

            @SerializedName("min_mos_c2s")
            public Object minMosClientToServer;

            @SerializedName("min_mos_s2c")
            public Object minMosServerToClient;

            @SerializedName("min_ntt")
            public Object minNetworkTransferTime;

            @SerializedName("min_pg")
            public Object minPacketGap;

            @SerializedName("min_rtt")
            public Object minRoundTripTime;

            @SerializedName("min_srt")
            public Object minServerResponseTime;

            @SerializedName("min_udp_trt")
            public Object minUdpTransactionResponseTime;

            @SerializedName("new_flow")
            public Object newFlow;

            @SerializedName("ooo_pkts_c2s")
            public long outOfOrderPacketsClientToServer;

            @SerializedName("ooo_pkts_s2c")
            public long outOfOrderPacketsServerToClient;

            @SerializedName("packets_c2s")
            public long packetsClientToServer;

            @SerializedName("packets_s2c")
            public long packetsServerToClient;

            @SerializedName("path_id")
            public String pathId;

            @SerializedName("path_type")
            public String pathType;

            @SerializedName("policy_id")
            public String policyId;

            @SerializedName("priority_class")
            public int priorityClass;

            @SerializedName("protocol")
            public int protocol;

            @SerializedName("reset_c2s")
            public long resetClientToServer;

            @SerializedName("reset_s2c")
            public long resetServerToClient;

            @SerializedName("retransmit_bytes_c2s")
            public long retransmitBytesClientToServer;

            @SerializedName("retransmit_bytes_s2c")
            public long retransmitBytesServerToClient;

            @SerializedName("retransmit_pkts_c2s")
            public long retransmitPacketsClientToServer;

            @SerializedName("retransmit_pkts_s2c")
            public long retransmitPacketsServerToClient;

            @SerializedName("sack_pkts_c2s")
            public long selectiveAckPacketsClientToServer;

            @SerializedName("sack_pkts_s2c")
            public long selectiveAckPacketsServerToClient;

            @SerializedName("source_ip")
            public String sourceIp;

            @SerializedName("source_port")
            public int sourcePort;

            @SerializedName("success_transactions")
            public Object successTransactions;

            @SerializedName("syn_c2s")
            public long synClientToServer;

            @SerializedName("syn_s2c")
            public long synServerToClient;

            @SerializedName("traffic_type")
            public String trafficType;

            @SerializedName("unknown_domain")
            public Object unknownDomain;

            @SerializedName("wan_path_change_reason")
            public Object wanPathChangeReason;

            public Flow()
            {

            } 
        }
    } 
}
