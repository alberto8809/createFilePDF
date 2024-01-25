package org.example.createfile.repository;

import org.example.createfile.model.CuentaContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentaContableRepository extends JpaRepository<CuentaContable, String> {


    @Query(value = "SELECT * FROM dbmaster.cuentaContable cc WHERE cc.codigo_agrupador =:codigo_agrupador ", nativeQuery = true)
    List<CuentaContable> getCuantaContable(String codigo_agrupador);

    @Query(value = "SELECT cc.nombre_cuenta FROM dbmaster.cuentaContable cc WHERE cc.codigo_agrupador =:codigo_agrupador ", nativeQuery = true)
    String getCuantaContableMethod(String codigo_agrupador);

    @Query(value = "SELECT c.nombre_cuenta FROM dbmaster.cuentaContable c WHERE c.codigo_agrupador =:tax_id ", nativeQuery = true)
    String getCuantaContableTax(String tax_id);

}
