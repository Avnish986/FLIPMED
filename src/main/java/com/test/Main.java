package com.test;

import java.util.*;

public class Main {
    public static Doctor addDoctor() {
        System.out.println("registerDoc");
        try {
            Doctor doctor = new Doctor();
            Scanner doc11 = new Scanner(System.in);
            doctor.setName(doc11.nextLine());
            Scanner doc12 = new Scanner(System.in);
            String speciality1 = doc12.nextLine();
            if(speciality1.equalsIgnoreCase("cardiologist")) {
                doctor.setSpeciality(Speciality.CARDIOLOGIST);
            } else if(speciality1.equalsIgnoreCase("dermatologist")) {
                doctor.setSpeciality(Speciality.DERMATOLOGIST);
            } else if(speciality1.equalsIgnoreCase("orthopedic")) {
                doctor.setSpeciality(Speciality.ORTHOPEDIC);
            } else if(speciality1.equalsIgnoreCase("generic_physician")) {
                doctor.setSpeciality(Speciality.GENERIC_PHYSICIAN);
            } else {
                throw new Exception("Invalid Specialty");
            }
            System.out.println("Welcome Dr. " + doctor.getName() +" !!");
            return doctor;
        } catch (Exception ex) {
            System.out.println("Sorry, Doc for now we don't support this speciality.");
            return null;
        }
    }

    public static Doctor markAvailibility(Doctor doctor) {
        System.out.println("markDocAvail : " + doctor.getName());
        try {
            Scanner doc11 = new Scanner(System.in);
            String availibility = doc11.nextLine();
            String[] availList = availibility.split(",");
            List<String> docAvailList = new ArrayList<>();
            for(String avail : availList) {
                int i=Integer.parseInt(avail);
                if(i < 9 || i > 20) {
                    System.out.println("Sorry Dr. " + doctor.getName() + " slots are 60 mins only and from 9 - 20");
                } else {
                    docAvailList.add(avail);
                }
            }
            doctor.setAvailability(docAvailList);
            System.out.println("Done Doc!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return doctor;
    }

    public static void showAvailibility(List<Doctor> doctorList) {
        System.out.println("showAvailibility: ");
        Scanner sc = new Scanner(System.in);
        String speciality = sc.nextLine();
        System.out.println("showAvailibility: " + speciality);
        for(Doctor doc : doctorList) {
            if(speciality.equalsIgnoreCase(doc.getSpeciality())) {
                for(String avail : doc.getAvailability()) {
                    int st = Integer.parseInt(avail);
                    int end = st + 1;
                    System.out.println("Dr." + doc.getName() + " (" + st + ".00-" + end + ".00)");
                }
            }
        }
    }

    public static void printDocDetails(List<Doctor> doctorList) {
        for(Doctor doc : doctorList) {
            System.out.println(doc.getName());
            System.out.println(doc.getSpeciality());
            for(String avail : doc.getAvailability()) {
                System.out.println(avail);
            }
        }
    }

    public static Patient addPatient() {
        System.out.println("registerPatient");
        Patient patient = new Patient();
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        patient.setName(name);
        System.out.println(patient.getName() + " registered successfully");
        return patient;
    }

    public static void bookAppointment(Patient patient, List<Doctor> doctorList) {
        List<Appointment> appointmentList = patient.getAppointmentList() == null ? new ArrayList<>() : patient.getAppointmentList();
        Appointment appointment = new Appointment();
        System.out.println("bookAppointment : " + patient.getName());
        Scanner sc = new Scanner(System.in);
        String docFullName = sc.nextLine();
        String[] docFullName1 = docFullName.split("\\.");
        String docName = docFullName1.length == 1 ? docFullName1[0] : docFullName1[1];
        Scanner sc1 = new Scanner(System.in);
        String timeSlot = sc1.nextLine();
        String[] timeSlot1 = timeSlot.split(":");
        String slot = timeSlot1[0];
        int slot_i = Integer.parseInt(slot);
        for(Doctor doc : doctorList) {
            if(docName.equalsIgnoreCase(doc.getName())) {
                for(String avail : doc.getAvailability()) {
                    int avail_i = Integer.parseInt(avail);
                    if(avail_i == slot_i) {
                        appointment.setDoctor(doc);
                        appointment.setSlotTime(avail);
                        Random rand = new Random();
                        appointment.setId(rand.nextInt(1000));
                        List<String> availability = doc.getAvailability();
                        availability.remove(avail);
                        doc.setAvailability(availability);
                        break;
                    }

                }

            }
        }
        if(appointment.getId() != null) {
            System.out.println("Booked. Booking id: " + appointment.getId());
            appointmentList.add(appointment);
            patient.setAppointmentList(appointmentList);
        }
    }

    public static List<Doctor> cancelBooking(Patient patient, List<Doctor> doctorList) {
        System.out.println("cancelBookingId:");
        Scanner sc = new Scanner(System.in);
        String id_s = sc.nextLine();
        Integer id = Integer.parseInt(id_s);
        Appointment appointmentCancelled = null;
        List<Appointment> patientAppointment = patient.getAppointmentList();
        for(Appointment appointment : patientAppointment) {
            if(Objects.equals(appointment.getId(), id)) {
                appointmentCancelled = appointment;
                break;
            }
        }

        if(appointmentCancelled != null) {
            patient.getAppointmentList().remove(appointmentCancelled);
            for(Doctor doctor : doctorList) {
                if(doctor == appointmentCancelled.getDoctor()) {
                    List<String> updateAvailabilityList = doctor.getAvailability();
                    updateAvailabilityList.add(appointmentCancelled.getSlotTime());
                    doctor.setAvailability(updateAvailabilityList);
                    System.out.println("cancelBookingId: " + appointmentCancelled.getId() + " successfully");
                    break;
                }
            }
        }

        return doctorList;

    }

    public static void main(String[] args) {
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = addDoctor();
        markAvailibility(doctor1);
        doctorList.add(doctor1);
        Doctor doctor2 = addDoctor();
        markAvailibility(doctor2);
        doctorList.add(doctor2);
        showAvailibility(doctorList);
        //printDocDetails(doctorList);
        List<Patient> patientList = new ArrayList<>();
        Patient patient1 = addPatient();
        bookAppointment(patient1, doctorList);
        showAvailibility(doctorList);
        cancelBooking(patient1, doctorList);
        patientList.add(patient1);

        showAvailibility(doctorList);

    }
}