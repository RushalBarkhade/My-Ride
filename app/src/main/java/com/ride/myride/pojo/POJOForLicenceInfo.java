package com.example.myride.pojo;

public class POJOForLicenceInfo {
    private String userName;
    private String licenceNo;
    private String Address;
    private String issueData;
    public  POJOForLicenceInfo(){}
    public POJOForLicenceInfo(String userName, String licenceNo, String address, String issueData, String currentStatus) {
        this.userName = userName;
        this.licenceNo = licenceNo;
        Address = address;
        this.issueData = issueData;
        this.currentStatus = currentStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIssueData() {
        return issueData;
    }

    public void setIssueData(String issueData) {
        this.issueData = issueData;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    private String currentStatus;

}
