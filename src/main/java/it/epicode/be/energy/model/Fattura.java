package it.epicode.be.energy.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fattura extends EntityId {

	private int anno;

	private Date data;

	private BigDecimal importo;

	private int numeroFattura;

	private String stato;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Cliente cliente;

	public void setData() {
		this.data = new Date();
	}

	public void setAnno() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.data);
		var year = cal.get(Calendar.YEAR);
		this.anno = year;
	}
}
