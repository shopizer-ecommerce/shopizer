package com.salesmanager.admin.data.system;

/**
 * Created by umesh on 3/10/17.
 */
public class SystemData {

    private long memory;
    private long freeMemory;
    private long numberOfProcessor;
    private long instanceUptime;
    private double systemLoad;
    private long shopizerCacheSize;
    private long httpActiveSession;
    private long numberOfThread;


    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getNumberOfProcessor() {
        return numberOfProcessor;
    }

    public void setNumberOfProcessor(long numberOfProcessor) {
        this.numberOfProcessor = numberOfProcessor;
    }

    public long getInstanceUptime() {
        return instanceUptime;
    }

    public void setInstanceUptime(long instanceUptime) {
        this.instanceUptime = instanceUptime;
    }

    public double getSystemLoad() {
        return systemLoad;
    }

    public void setSystemLoad(double systemLoad) {
        this.systemLoad = systemLoad;
    }

    public long getShopizerCacheSize() {
        return shopizerCacheSize;
    }

    public void setShopizerCacheSize(long shopizerCacheSize) {
        this.shopizerCacheSize = shopizerCacheSize;
    }

    public long getHttpActiveSession() {
        return httpActiveSession;
    }

    public void setHttpActiveSession(long httpActiveSession) {
        this.httpActiveSession = httpActiveSession;
    }

    public long getNumberOfThread() {
        return numberOfThread;
    }

    public void setNumberOfThread(long numberOfThread) {
        this.numberOfThread = numberOfThread;
    }
}
