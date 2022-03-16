Mappa Strutturale e funzionale progetto `(Java 11, Eclipse IDE 4.22.0)`

		.ENTITY PRESENTI:

EntityId -> classe creata in quanto tutte le entity condividono un attributo (id), per evitare codice boilerplate, DRY,
ed utilizzare l'ereditarietà di java, estendiamo le classi a cui serve tale attributo, con quest'ultima

Cliente:
- un cliente ha una sede legale (OneToOne con Indirizzo)
- un cliente ha una sede operativa (OneToOne con Indirizzo)
- tanti clienti sono di una una tipologia (ManyToOne con TipoCliente)

TipologiaCliente: Enum che identifica il tipo di cliente

Indirizzo -> ManyToOne con Comune, piu indirizzi hanno un comune

Fatture -> ManyToOne con Cliente (più fatture possono essere di un cliente)

Comune -> ManyToOne con Provincia; più comuni hanno una provincia

Provincia -> (file.csv) ha 3 String, dobbiamo caricare dal file csv i dati ed inserirli nel database, in modo "manuale",
cio significa che andranno aggiunti in un controller e richiamati poi da un endpoint specifico

		
		.REPOSITORY (nessuna annotazione, sono interfacce)

-OGNI repository (ne va creata una per ogni entity) EXTENDS JpaRepository (spring data JPA),
una volta effettuata l'estensione, tra diamond operator vanno specificate:
la classe da referenziare, il tipo di chiave primaria (es. Clienti, Long).
Con questa estensione, rendiamo disponibili i metodi CRUD, una volta istanziata la repository.
Possiamo inoltre implementare i nostri metodi custom, detti "query methods", i quali avranno il nome dell'operazione
da effettuare + By "elemento", scritto con la prima lettera maiuscola, anche se nella classe è minuscola
(es. findByNome(String nome)).


		.SERVICE (@service / @autowired)

Vengono utilizzati poi nei controller:
si usa l'annotazione @autowired per istanziare una repository: in queste classi infatti andremo ad utilizzare i metodi di
ognuna di esse per effettuare operazioni. Si utilizza la classe Optional, in quanto si potrebbe NON restituire nulla.

		.CONTROLLER(@restController / @RequestMapping)

Ad ogni controller la sua entity; l'annotazione permette di risparmiare del codice, ed indica a Spring che quella classe
è un controller. Si utilizza anche la seconda annotazione per fornire un percorso che poi sarà utilizzato quando
andremo ad invocare il servizio da (es) Postman. 
Annotazione @autowired nella quale si istanzia un Service, al fine di richiamarne i metodi, i quali avranno un proprio
@***mapping [GET(cercare), POST(creare), PUT(aggiornare), DELETE(eliminare)]
Si usano metodi che restituiscono una ResponseEntity, che permette di restituire anche un codice html (errore/OK)


Indirizzo per frontend con Thymeleaf: 

- PAGINA PRINCIPALE - http://localhost:8080/

-----------------------------------------------***postman/swagger references***---------------------------------------------

	Indirizzo per Swagger: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config



							/////////////////////////////////////////////
							*******************FATTURE******************
							////////////////////////////////////////////




	---------------------------------POST - INSERIRE UNA FATTURA -testato --OK

{
  "anno": 2020,
  "data": "2020-03-15T10:28:54.746Z",
  "importo":20000,
  "numeroFattura": 21,
  "stato": "pagata",
  "cliente": {
    "ragioneSociale": "SPA",
    "iva": "02130120321",
    "email": "usernew@corp.com",
    "dataInserimento": "2020-04-15T10:28:54.746Z",
    "dataUltimoContatto": "2021-03-15T10:28:54.746Z",
    "fatturatoAnnuale": 13213,
    "pec": "loreggia@comune.it",
    "telefono": "3333333332",
    "tipologiaCliente": "SPA",
    "emailContatto": "contact@libero.it",
    "nomeContatto": "Angelo",
    "cognomeContatto": "Foregni",
    "numeroContatto": "33332222111",
    "sedeLegale": {
      "via": "via dante",
      "civico": 21,
      "localita": "Loreggia",
      "cap": 30303,
      "comune": {
        "nome": "Loreggia",
        "provincia": {
          "sigla": "LO",
          "nome": "Loreggia",
          "regione": "Veneto"
        }
      }
    },
    "sedeOperativa": {
      "via": "via delle string",
      "civico": 21,
      "localita": "Treviso",
      "cap": 30290,
      "comune": {
        "nome": "Treviso",
        "provincia": {
          "sigla": "TV",
          "nome": "Treviso",
          "regione": "Veneto"
        }
      }
    }
  }
}

	---------------------------------GET fattura By Stato - testato --OK

	---------------------------------GET tutte le fatture - testato --OK

	---------------------------------PUT aggiornamento di una fattura con body: - testato --OK
{
  "anno": 2021,
  "data": "2021-03-15T10:41:55.122Z",
  "importo": 33301,
  "numeroFattura": 22,
  "stato": "non pagata",
  "cliente": {
    "ragioneSociale": "SRL",
    "iva": "239482093423",
    "email": "string@java.fl",
    "dataInserimento": "2021-03-15T10:41:55.122Z",
    "dataUltimoContatto": "2022-03-15T10:41:55.122Z",
    "fatturatoAnnuale": 2000000,
    "pec": "string@pec.tt",
    "telefono": "323213213",
    "tipologiaCliente": "SPA",
    "emailContatto": "string@contactmail.cc",
    "nomeContatto": "Paolone",
    "cognomeContatto": "Ruzza",
    "numeroContatto": "3213212321",
    "sedeLegale": {
      "via": "via delle String",
      "civico": 32,
      "localita": "Ravenna",
      "cap": 32423,
      "comune": {
        "nome": "Ravenna",
        "provincia": {
          "sigla": "RA",
          "nome": "Ravenna",
          "regione": "Emilia-Romagna"
        }
      }
    },
    "sedeOperativa": {
      "via": "via string",
      "civico": 21,
      "localita": "Foggia",
      "cap": 32131,
      "comune": {
        "nome": "Foggia",
        "provincia": {
          "sigla": "FG",
          "nome": "Foggia",
          "regione": "Umbria"
        }
      }
    }
  }
}
	---------------------------------GET RANGE IMPORTI DELLA FATTURA - testato --OK

	---------------------------------DELETE fattura - testato --OK




							/////////////////////////////////////////////
							*******************COMUNI*******************
							////////////////////////////////////////////




	---------------------------------POST inserimento di un comune con body: - testato --OK
{
  "nome": "Pianiga",
  "provincia": {
    "sigla": "VE",
    "nome": "Pianiga",
    "regione": "Veneto"
  }
}
	---------------------------------GET tutti i comuni - testato --OK

	---------------------------------GET comune by id - testato --OK

	---------------------------------PUT comune con body: - testato --OK
{
  "nome": "Dolo",
  "provincia": {
    "sigla": "VE",
    "nome": "Venezia",
    "regione": "Veneto"
  }
}
	---------------------------------DELETE comune via id (elimina anche la relativa provincia) - testato --OK




							/////////////////////////////////////////////
							*******************CLIENTI******************
							////////////////////////////////////////////



	---------------------------------POST aggiunta cliente con body: - testato --OK
{
  "ragioneSociale": "SPA",
  "iva": "23412341231",
  "email": "stringLover@gmail.com",
  "dataInserimento": "2010-03-15T11:19:51.075Z",
  "dataUltimoContatto": "2021-03-15T11:19:51.075Z",
  "fatturatoAnnuale": 300000,
  "pec": "string@pec.com",
  "telefono": "3213231232",
  "tipologiaCliente": "SPA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Xieng",
  "cognomeContatto": "Zao",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Fatturato",
    "civico": 2333,
    "localita": "Umbria",
    "cap": 33043,
    "comune": {
      "nome": "Tao",
      "provincia": {
        "sigla": "TD",
        "nome": "Tredue",
        "regione": "Umbria"
      }
    }
  },
  "sedeOperativa": {
    "via": "Delle rose",
    "civico": 2,
    "localita": "Taormina",
    "cap": 42432,
    "comune": {
      "nome": "Tredue",
      "provincia": {
        "sigla": "TD",
        "nome": "Sasso",
        "regione": "Emilia-Romagna"
      }
    }
  }
}
	---------------------------------GET tutti i clienti presenti - testato --OK

	---------------------------------GET utente via sigla provincia (la prima inserita, NON case sensitive, la provincia deve essere maiuscola) - testato (td) --OK

	---------------------------------DELETE eliminazione cliente via id - testato --OK

	---------------------------------GET findAllSorted cliente - testato --OK

	---------------------------------PUT aggiornamento cliente con body: - testato --OK
{
  "ragioneSociale": "SPA",
  "iva": "23412341251",
  "email": "string@gmail.com",
  "dataInserimento": "2010-03-15T11:19:51.075Z",
  "dataUltimoContatto": "2021-03-15T11:19:51.075Z",
  "fatturatoAnnuale": 300000,
  "pec": "string@pec.com",
  "telefono": "3213231232",
  "tipologiaCliente": "SPA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Aronne",
  "cognomeContatto": "De Gasperi",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Fatturato",
    "civico": 2333,
    "localita": "Umbria",
    "cap": 33043,
    "comune": {
      "nome": "Tao",
      "provincia": {
        "sigla": "TD",
        "nome": "Tredue",
        "regione": "Umbria"
      }
    }
  },
  "sedeOperativa": {
    "via": "Delle rosine",
    "civico": 22,
    "localita": "Taormina",
    "cap": 42433,
    "comune": {
      "nome": "Tredu",
      "provincia": {
        "sigla": "TD",
        "nome": "Sasso",
        "regione": "Emilia-Romagna"
      }
    }
  }
}
	---------------------------------Aggiunta Multipla Di 6 clienti (o quanti si vuole) in una sola richiesta POST con body: -testato --OK

[
  {
  "ragioneSociale": "SPA",
  "iva": "23412341231",
  "email": "stringLover@gmail.com",
  "dataInserimento": "2010-03-15",
  "dataUltimoContatto": "2021-03-15",
  "fatturatoAnnuale": 300000,
  "pec": "string@pec.com",
  "telefono": "3213231232",
  "tipologiaCliente": "SPA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Xieng",
  "cognomeContatto": "Zao",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Fatturato",
    "civico": 2333,
    "localita": "Umbria",
    "cap": 33043,
    "comune": {
      "nome": "Tao",
      "provincia": {
        "sigla": "TD",
        "nome": "Tredue",
        "regione": "Umbria"
      }
    }
  },
  "sedeOperativa": {
    "via": "Delle rose",
    "civico": 2,
    "localita": "Taormina",
    "cap": 42432,
    "comune": {
      "nome": "Tredue",
      "provincia": {
        "sigla": "TD",
        "nome": "Sasso",
        "regione": "Emilia-Romagna"
      }
    }
  }
},
{
  "ragioneSociale": "SAS",
  "iva": "23412341231",
  "email": "Integer@gmail.com",
  "dataInserimento": "2011-03-15",
  "dataUltimoContatto": "2020-03-15",
  "fatturatoAnnuale": 90000,
  "pec": "Integer@pec.com",
  "telefono": "3013231232",
  "tipologiaCliente": "SAS",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Franco",
  "cognomeContatto": "Calzavara",
  "numeroContatto": "301232128",
  "sedeLegale": {
    "via": "A. Lofri",
    "civico": 23,
    "localita": "Toscana",
    "cap": 30323,
    "comune": {
      "nome": "Mina",
      "provincia": {
        "sigla": "FR",
        "nome": "Bragni",
        "regione": "Lazio"
      }
    }
  },
  "sedeOperativa": {
    "via": "Delle trovate",
    "civico": 21,
    "localita": "Bragni",
    "cap": 42430,
    "comune": {
      "nome": "Uno",
      "provincia": {
        "sigla": "UD",
        "nome": "Sassi",
        "regione": "Puglia"
      }
    }
  }
},
{
  "ragioneSociale": "SRL",
  "iva": "23412341231",
  "email": "bigdecimal@gmail.com",
  "dataInserimento": "2016-03-15",
  "dataUltimoContatto": "2020-03-15",
  "fatturatoAnnuale": 500000,
  "pec": "bigdecimal@pec.com",
  "telefono": "3210031232",
  "tipologiaCliente": "SRL",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Paolo",
  "cognomeContatto": "Costa",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Ventura",
    "civico": 19,
    "localita": "Umbria",
    "cap": 33040,
    "comune": {
      "nome": "Drano",
      "provincia": {
        "sigla": "DT",
        "nome": "Deturpio",
        "regione": "Piemonte"
      }
    }
  },
  "sedeOperativa": {
    "via": "Dante",
    "civico": 2,
    "localita": "Taormina",
    "cap": 42432,
    "comune": {
      "nome": "Trebase",
      "provincia": {
        "sigla": "TB",
        "nome": "Frico",
        "regione": "Toscana"
      }
    }
  }
},
{
  "ragioneSociale": "SPA",
  "iva": "23412341231",
  "email": "boolean@gmail.com",
  "dataInserimento": "2010-03-15",
  "dataUltimoContatto": "2021-03-15",
  "fatturatoAnnuale": 200000,
  "pec": "boolean@pec.com",
  "telefono": "3313231232",
  "tipologiaCliente": "SPA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Giulio",
  "cognomeContatto": "Giuliani",
  "numeroContatto": "321232120",
  "sedeLegale": {
    "via": "A. Giuliani",
    "civico": 233,
    "localita": "Altinate",
    "cap": 33044,
    "comune": {
      "nome": "Alto",
      "provincia": {
        "sigla": "AT",
        "nome": "Altino",
        "regione": "Umbria"
      }
    }
  },
  "sedeOperativa": {
    "via": "Dei massi",
    "civico": 21,
    "localita": "Remico",
    "cap": 42432,
    "comune": {
      "nome": "Frosinone",
      "provincia": {
        "sigla": "FR",
        "nome": "Sasso",
        "regione": "Emilia-Romagna"
      }
    }
  }
},
{
  "ragioneSociale": "PA",
  "iva": "23412341231",
  "email": "double@gmail.com",
  "dataInserimento": "2010-03-15",
  "dataUltimoContatto": "2021-03-15",
  "fatturatoAnnuale": 300000,
  "pec": "doubledouble@pec.com",
  "telefono": "3213231232",
  "tipologiaCliente": "PA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Saverio",
  "cognomeContatto": "Mulli",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Logica",
    "civico": 213,
    "localita": "Veneto",
    "cap": 33040,
    "comune": {
      "nome": "Frollo",
      "provincia": {
        "sigla": "SI",
        "nome": "Siviglia",
        "regione": "Veneto"
      }
    }
  },
  "sedeOperativa": {
    "via": "Leoni",
    "civico": 23,
    "localita": "Laonara",
    "cap": 42432,
    "comune": {
      "nome": "Tanzo",
      "provincia": {
        "sigla": "SI",
        "nome": "Siviglia",
        "regione": "Veneto"
      }
    }
  }
},
{
  "ragioneSociale": "SPA",
  "iva": "23412341231",
  "email": "long@gmail.com",
  "dataInserimento": "2010-03-15",
  "dataUltimoContatto": "2021-03-15",
  "fatturatoAnnuale": 300000,
  "pec": "long@pec.com",
  "telefono": "3213231232",
  "tipologiaCliente": "SPA",
  "emailContatto": "emailme@gmail.com",
  "nomeContatto": "Enrico",
  "cognomeContatto": "Piave",
  "numeroContatto": "321232123",
  "sedeLegale": {
    "via": "A. Fiume",
    "civico": 12,
    "localita": "Veneto",
    "cap": 33043,
    "comune": {
      "nome": "Taggi",
      "provincia": {
        "sigla": "PD",
        "nome": "Padova",
        "regione": "Veneto"
      }
    }
  },
  "sedeOperativa": {
    "via": "Volpe",
    "civico": 8,
    "localita": "Preganziol",
    "cap": 42432,
    "comune": {
      "nome": "Pregi",
      "provincia": {
        "sigla": "PG",
        "nome": "Sasso",
        "regione": "Emilia-Romagna"
      }
    }
  }
}
]

	---------------------------------Aggiunta province ed aggiunta comuni testati, prima vanno aggiunte le province (hint aggiunto quando si inseriscono i comuni) --OK

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

							/////////////////////////////////////////////
							 ****************KNOWN ISSUES****************
							////////////////////////////////////////////



-Errore quando si cerca di inserire un numero che inizia con '0'
-il comune risulta 'eliminato' quando si elimina con id, anche se non c'è effettivamente alcun comune con quell'id
-get by ragione sociale parziale non funzionante v2: ora mostra eccezione personalizzata
-qualche cascade risulta problematico, ad es. se aggiungo una fattura
ed elimino un cliente, avremo un errore


								@Author Daniele Carraro -BE-