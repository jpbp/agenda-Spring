package com.agenda.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agenda.models.Agenda;

import java.util.*;
public interface AgendaRepository extends CrudRepository<Agenda,String> {
	 //Atributo Endereco dentro de Empregado ele já implementa pra vc tudo!
	//são as querys do banco de dados
	Agenda findByCodigo(long codigo);
	List<Agenda> findByNome(String nome);
	List<Agenda> findByOrderByNomeAsc();
	List<Agenda> findByNomeStartsWith(String nome);
	

}
