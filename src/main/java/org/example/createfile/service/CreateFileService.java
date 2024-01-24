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


    public CreateFileService() {
    }


    public CreateFileService(CreateFileRepository createFileRepository, ClaveProductoServRepository claveProductoServRepository, CuentaContableRepository cuentaContableRepository) {
        this.createFileRepository = createFileRepository;
        this.claveProductoServRepository = claveProductoServRepository;
        this.cuentaContableRepository = cuentaContableRepository;
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
        System.out.println(rand_int1);

        ObjFile objFile = ParserFile.getParse(fileName);

        //System.out.println("CFDI -- " + cfdiRepository.usoCFDI(objFile.getPolicyObj().getUsoCFDI().toString()));
        Regimen regimen = getRegimens(objFile.getPolicyObj().getRegimen());
        //System.out.println("regimen - " + regimen.toString());
        ClaveProductoServ claveProductoServ = getClaveProductoService(objFile.getPolicyObj().getClaveProdServ());
        CuentaContable cuentaContable = cuentaContableRepository.getCuantaContable(claveProductoServ.getCuenta_contable());
        //System.out.println("cuenta -  " + cuentaContable.toString());
        CreateFilePDF.makeFile(objFile, rand_int1);

        return true;
    }

}
