package org.example.createfile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "policy")
public class PolicyObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String regimen;
    private String usoCFDI;

    private String ClaveProdServ;
    private String concepto_Descripcion;

    private String amoubnt;
    private List<String> traslado;
    private String impuestos;
    private List<String> retencion_importe;
    private String timbreFiscalDigital_UUID;

    private String totalAmount;


    public PolicyObj() {
    }

    public PolicyObj(String regimen, String usoCFDI, String claveProdServ, String concepto_Descripcion, String amoubnt, List<String> traslado, String impuestos, List<String> retencion_importe, String timbreFiscalDigital_UUID, String totalAmount) {
        this.regimen = regimen;
        this.usoCFDI = usoCFDI;
        ClaveProdServ = claveProdServ;
        this.concepto_Descripcion = concepto_Descripcion;
        this.amoubnt = amoubnt;
        this.traslado = traslado;
        this.impuestos = impuestos;
        this.retencion_importe = retencion_importe;
        this.timbreFiscalDigital_UUID = timbreFiscalDigital_UUID;
        this.totalAmount = totalAmount;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getUsoCFDI() {
        return usoCFDI;
    }

    public void setUsoCFDI(String usoCFDI) {
        this.usoCFDI = usoCFDI;
    }

    public String getClaveProdServ() {
        return ClaveProdServ;
    }

    public void setClaveProdServ(String claveProdServ) {
        ClaveProdServ = claveProdServ;
    }

    public String getConcepto_Descripcion() {
        return concepto_Descripcion;
    }

    public void setConcepto_Descripcion(String concepto_Descripcion) {
        this.concepto_Descripcion = concepto_Descripcion;
    }

    public String getAmoubnt() {
        return amoubnt;
    }

    public void setAmoubnt(String amoubnt) {
        this.amoubnt = amoubnt;
    }

    public List<String> getTraslado() {
        return traslado;
    }

    public void setTraslado(List<String> traslado) {
        this.traslado = traslado;
    }

    public String getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }

    public List<String> getRetencion_importe() {
        return retencion_importe;
    }

    public void setRetencion_importe(List<String> retencion_importe) {
        this.retencion_importe = retencion_importe;
    }

    public String getTimbreFiscalDigital_UUID() {
        return timbreFiscalDigital_UUID;
    }

    public void setTimbreFiscalDigital_UUID(String timbreFiscalDigital_UUID) {
        this.timbreFiscalDigital_UUID = timbreFiscalDigital_UUID;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PolicyObj{" +
                "regimen='" + regimen + '\'' +
                ", usoCFDI='" + usoCFDI + '\'' +
                ", ClaveProdServ='" + ClaveProdServ + '\'' +
                ", concepto_Descripcion='" + concepto_Descripcion + '\'' +
                ", amoubnt='" + amoubnt + '\'' +
                ", traslado=" + traslado +
                ", impuestos='" + impuestos + '\'' +
                ", retencion_importe=" + retencion_importe +
                ", timbreFiscalDigital_UUID='" + timbreFiscalDigital_UUID + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
