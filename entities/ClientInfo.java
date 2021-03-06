package com.bharath.flightreservation.entities;

import javax.persistence.*;


@Entity
@Table(name = "clientinfo")
public class ClientInfo {

    @Id
    @Column(name = "Client_id")
    private long clientId;
    @Column(name = "C_name")
    private String name;
    @Column(name = "C_surename")
    private String surname;
    @Column(name = "Address")
    private String address;
    @Column(name = "Code")
    private String code;
    @Column(name = "C_Tel_H")
    private String tel_home;
    @Column(name = "C_Tel_W")
    private String tel_work;
    @Column(name = "C_Tel_Cell")
    private String tel_cell;
    @Column(name = "C_Email")
    private String email;


    @OneToOne
    @Column(name = "Reference_ID")
    private Reference reference;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTel_home() {
        return tel_home;
    }

    public void setTel_home(String tel_home) {
        this.tel_home = tel_home;
    }

    public String getTel_work() {
        return tel_work;
    }

    public void setTel_work(String tel_work) {
        this.tel_work = tel_work;
    }

    public String getTel_cell() {
        return tel_cell;
    }

    public void setTel_cell(String tel_cell) {
        this.tel_cell = tel_cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
