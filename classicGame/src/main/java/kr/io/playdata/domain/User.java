package kr.io.playdata.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter, @Setter, @ToString, @RequiredArgsConstructor, @EqualsAndHashCode가 들어간것.
@NoArgsConstructor
@AllArgsConstructor

@Entity 
public class User {
	@Id //id를 pk로 설정
	private String id; 
	private String pw;
	private String mail;
	private String name;
	private String nickname;
}

