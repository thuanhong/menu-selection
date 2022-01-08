package com.project.coffee.Model;

public class Staff {
    private Integer staffId;
    private String name;
    private String gender;
    private String birthDay;
    private String homeTown;
    private String department;
    private String onboarding;

    public Staff() {

    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOnboarding() {
        return onboarding;
    }

    public void setOnboarding(String onboarding) {
        this.onboarding = onboarding;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", department='" + department + '\'' +
                ", onboarding='" + onboarding + '\'' +
                '}';
    }
}
