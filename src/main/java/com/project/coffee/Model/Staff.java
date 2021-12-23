package com.project.coffee.Model;

public class Staff {
    private String staffId;
    private String name;
    private String gender;
    private String birthDay;
    private String homeTown;


    public Staff(String staffId, String name, String gender, String birthDay, String homeTown) {
        this.staffId = staffId;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.homeTown = homeTown;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
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

}
