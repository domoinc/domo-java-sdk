package com.domo.sdk.datasets.model;


import java.util.List;

public class DataSetAndPDP extends DataSet {
    private boolean pdpEnabled;
    private List<Policy> policies;

    public boolean isPdpEnabled() {
        return pdpEnabled;
    }

    public void setPdpEnabled(boolean pdpEnabled) {
        this.pdpEnabled = pdpEnabled;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }
}
