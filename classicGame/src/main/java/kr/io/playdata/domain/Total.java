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
public class Total {
	@Id //id를 nickname으로 설정
	private String nickname;
	private int score1;
	private int score2;
	private int score3;
}
