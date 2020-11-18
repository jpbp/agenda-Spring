package com.agenda.repository;

import org.springframework.data.repository.CrudRepository;

import com.agenda.models.Agenda;

import antlr.collections.List;
import java.util.*;
public interface AgendaRepository extends CrudRepository<Agenda,String> {
	 //Atributo Endereco dentro de Empregado ele já implementa pra vc tudo!
	Agenda findByCodigo(long codigo);
	java.util.List<Agenda> findByNome(String nome);
}