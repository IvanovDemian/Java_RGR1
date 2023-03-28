package com.javafxapplication.models;

public class User {
  private Integer iduser;
  private String fName;
  private String lName;
  private String pName;
  private String email;
  private String password;
  private String role;

  public User(Integer iduser, String fName, String lName, String pName, String email, String password, String role) {
    this.iduser = iduser;
    this.fName = fName;
    this.lName = lName;
    this.pName = pName;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public User() {

  }

  public User (int idUser) {

  }

  public Integer getIduser() {
    return iduser;
  }

  public void setIduser(Integer iduser) {
    this.iduser = iduser;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public String getpName() {
    return pName;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
