package com.mercury.SpringBootRestDemo.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "MSI_User")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "msi_user_seq_gen", sequenceName = "MSI_USER_SEQ", allocationSize = 1)
    @GeneratedValue(generator="msi_user_seq_gen", strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String username;
    @Column
    private String password;

    //because we don't have respond user in profile so we don't need mapped by
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "MSI_USER_MSI_USER_PROFILE",
            //how current table middle table
            joinColumns = {
                    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
            },
            //how middle table join right table
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "ID")
            }
    )
    private List<Profile> profiles;


    public User() {
    }



    public User(int id, String username, String password, List<Profile> profiles ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //password expired
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
