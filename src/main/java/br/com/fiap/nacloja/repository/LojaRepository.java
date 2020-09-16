package br.com.fiap.nacloja.repository;

import br.com.fiap.nacloja.model.LojaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface LojaRepository extends JpaRepository<LojaModel, Long> {
}
