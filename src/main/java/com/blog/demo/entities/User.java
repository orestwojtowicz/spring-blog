package com.blog.demo.entities;

import com.blog.demo.entities.validator.emailValidator.IEmailPattern;
import com.blog.demo.entities.validator.passwordValidator.passwordMatch.IPasswordMatch;

import com.blog.demo.entities.validator.passwordValidator.passwordStrength.IPasswordStrength;
import com.blog.demo.entities.validator.whiteSpaceValidator.IWhiteSpace;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by damiass on Sep, 2019
 */


@EqualsAndHashCode
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@IPasswordMatch
@IEmailPattern
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String publicUserID; // set in register service

    @NotNull
    @Size(min = 5, max = 50, message = "wrong email length")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(length = 100)
    @IPasswordStrength
    private String password;

    @IWhiteSpace
    @NotNull
    @Column(length = 100)
    private String nick;

    @NotNull
    @Column(nullable = false)
    private boolean enabled = false;

    @Column
    private String resetPasswordToken;

    @Column
    private String passwordForChange;

    @Column
    @Lob
    private String userAvatar;

    @Column
    private int userCommentCount;


    @NotEmpty(message = "Please enter Password Confirmation")
    private String confirmPassword;

    @Column
    private String activationCode;

    @ToString.Exclude
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",  cascade = CascadeType.REMOVE)
    private Set<Post> posts = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();



    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper)

    /*    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName())); przyjmuje Stringa z okreslona rola
        }
        return authorities;*/
    }


    public User(@NotNull @Size(min = 8, max = 20) String email,
                @NotNull String password, @NotNull String nick, @NotNull boolean isEnabled) {
        this.email = email;
        this.password = password;
        this.nick = nick;
        this.enabled = isEnabled;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return email;
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
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}













