package ZIMG.services.security;

import ZIMG.models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setAdmin(user.getAdmin());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(userRole);

        if(this.getAdmin()){
            SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(admin);
        }

        return authorities;
    }

    public boolean isUserInRolePresent(String role) {
        boolean isRolePresent = false;
        for (GrantedAuthority grantedAuthority : this.getAuthorities()) {
            isRolePresent = grantedAuthority.getAuthority().equals(role);
            Logger.getLogger(SecurityUser.class).log(Priority.DEBUG,"ROLE:::=> " + grantedAuthority.getAuthority());
            if (isRolePresent) break;
        }
        return isRolePresent;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}