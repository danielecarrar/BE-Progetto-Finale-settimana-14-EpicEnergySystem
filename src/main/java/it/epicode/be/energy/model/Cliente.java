package it.epicode.be.energy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntityId {
	
	private String ragioneSociale;
	
	private String iva;
	
	private String email;
	
	private Date dataInserimento;
	
	private Date dataUltimoContatto;
	
	private BigDecimal fatturatoAnnuale;
	
	private String pec;
	
	private String telefono;
	
	@Enumerated(EnumType.STRING)
	private TipologiaCliente tipologiaCliente;
	
	private String emailContatto;
	
	private String nomeContatto;
	
	private String cognomeContatto;
	
	private String numeroContatto;

	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeLegale;

	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeOperativa;

	//genera una nuova data ad ogni richiamo (set)
	public final void setDataInserimento() {
		this.dataInserimento = new Date();
	}
}