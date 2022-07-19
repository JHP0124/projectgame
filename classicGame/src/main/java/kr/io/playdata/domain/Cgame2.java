package kr.io.playdata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter, @Setter, @ToString, @RequiredArgsConstructor, @EqualsAndHashCode가 들어간것.
@NoArgsConstructor
@AllArgsConstructor

@Entity 
public class Cgame2 {
	@Id //id를 pk로 설정
	@GeneratedValue
	private int num; 
	private String nickname;
	private int score2; 
}
