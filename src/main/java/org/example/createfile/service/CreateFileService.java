package org.example.createfile.service;

import org.example.createfile.model.ClaveProductoServ;
import org.example.createfile.model.CuentaContable;
import org.example.createfile.model.ObjFile;
import org.example.createfile.model.Regimen;
import org.example.createfile.repository.*;
import org.example.createfile.util.CreateFilePDF;
import org.example.createfile.util.ParserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
//@Configuration
public class CreateFileService {


    @Autowired
    CreateFileRepository createFileRepository;
    @Autowired
    ClaveProductoServRepository claveProductoServRepository;
    @Autowired
    CuentaContableRepository cuentaContableRepository;
    @Autowired
    CFDIRepository cfdiRepository;
    @Autowired
    MethodOfPaymentRepository methodOfPaymentRepository;


    public CreateFileService() {
    }

    public CreateFileService(CreateFileRepository createFileRepository, ClaveProductoServRepository claveProductoServRepository, CuentaContableRepository cuentaContableRepository, CFDIRepository cfdiRepository, MethodOfPaymentRepository methodOfPaymentRepository) {
        this.createFileRepository = createFileRepository;
        this.claveProductoServRepository = claveProductoServRepository;
        this.cuentaContableRepository = cuentaContableRepository;
        this.cfdiRepository = cfdiRepository;
        this.methodOfPaymentRepository = methodOfPaymentRepository;
    }

    public Regimen getRegimens(String regimen) {
        return createFileRepository.getRegimen(regimen);
    }


    public ClaveProductoServ getClaveProductoService(String c_claveprodserv) {
        return claveProductoServRepository.getClaveProducto(c_claveprodserv);
    }

    public boolean createFile(String fileName) {
        //make logic to get all values and then pass to front
        Random rand = new Random();

        int rand_int1 = rand.nextInt(1000000000);

        ObjFile objFile = ParserFile.getParse(fileName);
        //Regimen regimen = getRegimens(objFile.getPolicyObj().getRegimen());

        ClaveProductoServ claveProductoServ = getClaveProductoService(objFile.getPolicyObj().getClaveProdServ());
        List<CuentaContable> cuentaContable = cuentaContableRepository.getCuantaContable(claveProductoServ.getCuenta_contable());

        //method payment (Abono)
        objFile.setCuenta_method(methodOfPaymentRepository.getCuantaContable(objFile.getTypeOfPayment()));
        objFile.setDescription_methods(cuentaContableRepository.getCuantaContableMethod(objFile.getCuenta_method()));

        // (Cargo)
         objFile.setTax_id(methodOfPaymentRepository.getCuentaContableByTax("002"));
         objFile.setTax_description(cuentaContableRepository.getCuantaContableTax(objFile.getTax_id()));

        CreateFilePDF.makeFile(objFile, rand_int1, cuentaContable);

        return true;
    }

}
