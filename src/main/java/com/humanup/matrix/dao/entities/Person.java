package com.humanup.matrix.dao.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  private String firstName;
  private String lastName;
  private String mailAdresses;
  private Date birthDate;

  @ManyToOne
  @JoinColumn(name = "profileId")
  private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "person_skill",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") })
    private Set<Skill> skills = new HashSet<>();


    public Person() {
    }

    public Person(String firstName, String lastName, String mailAdresses, Date birthDate, Profile profile, Set<Skill> skills) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mailAdresses = mailAdresses;
    this.birthDate = birthDate;
    this.profile = profile;
    this.skills = skills;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMailAdresses() {
    return mailAdresses;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public Profile getProfile() {
        return this.profile;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public static class Builder{
          private Long id;
          private String firstName;
          private String lastName;
          private String mailAdresses;
          private Date birthDate;
          private Profile profile;
          private Set<Skill> skills;

           public Builder() {
           }

      public Builder setId(Long id) {
          this.id = id;
          return this;
      }
           public Builder setFirstName(String firstName) {
             this.firstName = firstName;
             return this;
           }

           public Builder setLastName(String lastName) {
             this.lastName = lastName;
             return this;
           }

           public Builder setMailAdresses(String mailAdresses) {
             this.mailAdresses = mailAdresses;
             return this;
           }

           public Builder setBirthDate(Date birthDate) {
             this.birthDate = birthDate;
             return this;
           }

           public Builder setProfile(Profile profile) {
            this.profile = profile;
            return this;
            }
        public Builder setSkills(Set<Skill> skills) {
            this.skills = skills;
            return this;
        }
           public Person build(){
             return new Person( firstName,  lastName,  mailAdresses, birthDate,profile,skills);
           }
         }
}