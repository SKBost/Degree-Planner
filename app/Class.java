private String dept;
private String code;
private GradingOption option;
private int units;

public Class(String myDept, String code, GradingOption myOption, int myUnits) {
        dept = myDept;
        code = myCode;
        option = myOption;
        units = myUnits;
}

public String getDept() {
        return dept;
}

public String getCode() {
        return code;
}

public GradingOption getOption() {
        return option;
}

public int getUnits() {
        return units;
}