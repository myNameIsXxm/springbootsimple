package gr.xxm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "YDZF_DZ")
@Data
public class YdzfDz implements Comparable<YdzfDz>{
	@Id
	@Column
	private Long AAE074;
	@Column
	private Date EKD001;
	@Column
	private Integer TOTAL;
	@Column
	private Integer TIAOSHU;
	@Column
	private String BODY;
	@Column
	private String RETCODE;
	@Column
	private String RETMSG;
	
	@Override
	public int compareTo(YdzfDz o) {
		return (int) (this.getEKD001().getTime()-o.getEKD001().getTime());
	}
}
