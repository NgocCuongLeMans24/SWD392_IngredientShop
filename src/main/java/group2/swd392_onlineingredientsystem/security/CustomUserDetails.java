package group2.swd392_onlineingredientsystem.security;

import group2.swd392_onlineingredientsystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Trả về danh sách quyền của user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole() != null) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()));
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // đã được mã hóa
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Các trạng thái tài khoản. Có thể thêm logic nếu cần
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // Nếu bạn muốn lấy lại entity User ở controller
    public User getUser() {
        return user;
    }
}
