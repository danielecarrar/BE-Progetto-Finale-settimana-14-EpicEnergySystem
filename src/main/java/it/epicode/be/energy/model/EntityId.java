package it.epicode.be.energy.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

// Classe in cui sfruttiamo l'ereditariet√† di Java (TUTTE le entity hanno un ID in comune)

@MappedSuperclass
@Data
public class EntityId {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
