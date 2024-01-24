package org.example.createfile.repository;

import org.example.createfile.model.CuentaContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuentaContableRepository extends JpaRepository<CuentaContable, String> {


    @Query(value = "SELECT * FROM dbmaster.cuentaContable cc WHERE cc.codigo_agrupador =:codigo_agrupador " ,nativeQuery = true)
    CuentaContable getCuantaContable(String codigo_agrupador);
}
