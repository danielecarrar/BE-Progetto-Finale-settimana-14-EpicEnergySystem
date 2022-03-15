package it.epicode.be.energy.model;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Province extends EntityId {

	private String sigla;
	
	private String nome;
	
	private String regione;

	@Override
	public String toString() {
		return " di " + nome + " (" + sigla + ")" + ", regione: " + regione;
	}

	
}
