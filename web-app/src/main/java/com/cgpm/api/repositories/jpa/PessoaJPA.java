package com.cgpm.api.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgpm.api.model.entities.Pessoa;

/**
 * @author michel.pech
 */

public interface PessoaJPA extends JpaRepository<Pessoa, Long> {

}
