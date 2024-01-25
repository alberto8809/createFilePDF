package org.example.createfile.repository;

import org.example.createfile.model.CuentaContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface MethodOfPaymentRepository extends JpaRepository<CuentaContable, String> {

    @Query(value = "SELECT cc.cuentaContable FROM dbmaster.payment_methods cc WHERE cc.payment_id =:typeOfPayment " ,nativeQuery = true)
    String getCuantaContable(String typeOfPayment);


    @Query(value = "SELECT tx.cuenta_contable  FROM dbmaster.tax tx WHERE tx.tax_id =:tax", nativeQuery = true)
    String getCuentaContableByTax(String tax);


}
