package com.taumaturgo.padroesprojetos.repositories;

import com.taumaturgo.padroesprojetos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
