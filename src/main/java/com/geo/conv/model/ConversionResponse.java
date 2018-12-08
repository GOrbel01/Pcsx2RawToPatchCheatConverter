package com.geo.conv.model;

public class ConversionResponse {
    private Boolean success;
    private PatchFile payload;

    public ConversionResponse(Boolean isSuccess, PatchFile payload) {
        this.success = isSuccess;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PatchFile getPayload() {
        return payload;
    }

    public void setPayload(PatchFile payload) {
        this.payload = payload;
    }
}
