package models;

public enum OrganizationType {
    COMMERCIAL("Commercial"),
    PUBLIC("Public"),
    GOVERNMENT("Government"),
    TRUST("Trust"),
    OPEN_JOINT_STOCK_COMPANY("OJSC");

    private final String name;

    OrganizationType(String name){
        this.name = name;
    }


}
