package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.member.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor //빈생성자
@AllArgsConstructor ///플생성자
@Getter //게터
@Setter //세터
@ToString //투스트링
@Builder //객체생성 안전성보장
public class MemberDto  implements UserDetails {
    private int mno;
    private String memail;
    private String mpassword;

    private String mphone;

    private Set<GrantedAuthority> authorities;
    //dto --> entity로 변환
    public  MemberEntity toEntity(){
        return MemberEntity.builder()
                    .mno(this.mno)
                    .memail(this.memail)
                    .mpassword(this.mpassword)
                    .mphone(this.mphone)
                    .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.mpassword;
    }

    @Override
    public String getUsername() {
        return this.memail;
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
