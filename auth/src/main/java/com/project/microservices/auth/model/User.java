package com.project.microservices.auth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

  private static final long serialVersionUID = -8818838625509872140L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private Boolean isAccountNonExpired;
  private Boolean isAccountNonLocked;
  private Boolean isCredentialsNonExpired;
  private Boolean isEnabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
      @JoinColumn(name = "permission_id") })
  private List<Permission> permissions;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.permissions;
  }

  public List<String> getRoles() {
    List<String> roles = new ArrayList<>();
    this.permissions.stream().forEach(permission -> {
      roles.add(permission.getDescription());
    });
    return roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }

}
