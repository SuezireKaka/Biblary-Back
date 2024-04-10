package www.bible.library.party.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import www.bible.library.framework.model.MappedTableDef;
import www.bible.library.framework.model.TimeEntity;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountVO extends TimeEntity implements MappedTableDef, UserDetails {

	private OrganizationVO owner;	//주인으로서
	private PersonVO response;	//대상으로서
	
	private int loginResultCode; // 문제없음 : 1, 탈퇴계정 : 2, 만료계정 : 3, 처벌계정 : 4
	private Collection<RoleVO> roleList;
	
	public String getMappedTableName() {
		return "T_account";
	}

	public String getKSuspectType() {
		return "사용자";
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoleList()
				.stream()
				.map(RoleVO::getAuthority)
				.collect(Collectors.toList());
	}

	public String getPassword() {
		return "";
	}

	public String getUsername() {
		return this.getId();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
