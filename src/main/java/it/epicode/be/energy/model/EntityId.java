package it.epicode.be.energy.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
//classe in cui sfruttiamo l'ereditarietà di Java (TUTTE le entity hanno un ID in comune)

@MappedSuperclass
@Data
public class EntityId {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
