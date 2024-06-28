package com.test;

import java.util.List;

public class Patient extends Person{
    List<Appointment> appointmentList;

    public Patient() {

    }
//    public Patient(List<Appointment> appointmentList) {
//        this.appointmentList = appointmentList;
//    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
