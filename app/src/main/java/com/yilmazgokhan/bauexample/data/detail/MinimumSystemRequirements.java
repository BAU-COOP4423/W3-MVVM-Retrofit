package com.yilmazgokhan.bauexample.data.detail;

import com.google.gson.annotations.SerializedName;

public class MinimumSystemRequirements {
    @SerializedName("os")
    private String os;
    @SerializedName("processor")
    private String processor;
    @SerializedName("memory")
    private String memory;
    @SerializedName("graphics")
    private String graphics;
    @SerializedName("storage")
    private String storage;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
