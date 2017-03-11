package com.salesmanager.admin.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.data.system.SystemData;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.DumpEndpoint;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.Map;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Created by umesh on 3/10/17.
 */
@Component(value = "smAccurator")
public class SmAccurator {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DumpEndpoint dumpEndpoint;

    @Autowired
    private EnvironmentEndpoint envEndpoint;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @Autowired
    private HealthEndpoint healthEndpoint;



    public int getProcessCpuLoad() throws Exception {
        MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
        ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

        if (list.isEmpty())     return (int) Double.NaN;

        Attribute att = (Attribute)list.get(0);
        Double value  = (Double)att.getValue();

        // usually takes a couple of seconds before we get real values
        if (value == -1.0)      return (int) Double.NaN;
        // returns a percentage value with 1 decimal point precision
        return (int) ((value * 1000) / 10.0);
    }

    public String getEnvironment() throws JsonProcessingException {

        return mapper.writeValueAsString(envEndpoint.invoke());
    }

    public String getDump() throws JsonProcessingException {
        return mapper.writeValueAsString(dumpEndpoint.invoke());
    }

    public SystemData getMetrics() throws  JsonProcessingException{

        SystemData systemInfo=new SystemData();
        Map<String,Object> metrics = metricsEndpoint.invoke();
        if(MapUtils.isNotEmpty(metrics)){
            systemInfo.setMemory((Long) metrics.get("mem"));
            systemInfo.setFreeMemory((Long) metrics.get("mem.free"));
          //  systemInfo.setNumberOfProcessor((Long) metrics.get("processors"));
            systemInfo.setSystemLoad((Double) metrics.get("systemload.average"));
            //systemInfo.setHttpActiveSession((Long) metrics.get("httpsessions.active"));
            systemInfo.setNumberOfThread((Long) metrics.get("threads"));
        }
       // return mapper.writeValueAsString(metricsEndpoint.invoke());
        return systemInfo;
    }

    public String getHealthStatus() throws  JsonProcessingException{
        return mapper.writeValueAsString(healthEndpoint.invoke());
    }
}
