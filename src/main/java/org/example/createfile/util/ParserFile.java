package org.example.createfile.util;

import org.example.createfile.model.ObjFile;
import org.example.createfile.model.PolicyObj;
import org.example.createfile.repository.ClaveProductoServRepository;
import org.example.createfile.service.CreateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParserFile {
    private static final String PATH = "/";

    public ParserFile() {
    }

    public static ObjFile getParse(String FileName) {

        PolicyObj values = new PolicyObj();
        try {
            File archivoXML = new File(PATH + FileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
            Element comprobanteElement = doc.getDocumentElement();

            Element total = doc.getDocumentElement();
            //"------------- total  ---------------
            values.setTotalAmount(total.getAttribute("Total"));

            Element date = doc.getDocumentElement();
            //System.out.println(date.getAttribute("Fecha"));
            String currentDate = date.getAttribute("Fecha");



            Element payment = doc.getDocumentElement();
            String typeOfPayment = payment.getAttribute("FormaPago");
            Element comprobante = doc.getDocumentElement();

            // String typeOf = comprobante.getAttribute("TipoDeComprobante");

            Element method = doc.getDocumentElement();
            String methodPayment = method.getAttribute("MetodoPago");
            System.out.println(methodPayment);


            NodeList repectEgr = comprobanteElement.getElementsByTagName("cfdi:Receptor");
            Element rEgr = (Element) repectEgr.item(0);
            //System.out.println(" rEgr --- > "+rEgr.getAttribute("Nombre"));


            NodeList datoCli = comprobanteElement.getElementsByTagName("cfdi:Receptor");
            Element cliente = (Element) datoCli.item(0);
            //System.out.println(" cliente --- > "+cliente.getAttribute("Nombre"));
            String cli = cliente.getAttribute("Nombre");

            String typeOf = null;
            //System.out.println(methodPayment + rEgr.getAttribute("Nombre") +cli);
            if (methodPayment.equals("PUE") && rEgr.getAttribute("Nombre").equals(cli)) {
                typeOf = "Egreso";
                //System.out.println(" ---> Egreso");
            } else if (methodPayment.equals("PPD") && rEgr.getAttribute("Nombre").equals(cli)) {
                typeOf = "Diario";
                //System.out.println("---> Diario");
            }


            if (comprobante.getAttribute("TipoDeComprobante").equals("I")) {
                // System.out.println("inside I");
                NodeList impe = comprobanteElement.getElementsByTagName("cfdi:Impuestos");
                Element ime = (Element) impe.item(impe.getLength() - 1);
                System.out.println(ime.getAttribute("TotalImpuestosTrasladados").isEmpty() ? "0" : ime.getAttribute("TotalImpuestosTrasladados"));


                NodeList rgm = comprobanteElement.getElementsByTagName("cfdi:Emisor");
                Element rgim = (Element) rgm.item(0);
                String regimen = rgim.getAttribute("RegimenFiscal");
                //System.out.println(regimen);


            }


            //---------------

            NodeList impe = comprobanteElement.getElementsByTagName("cfdi:Impuestos");
            Element ime = (Element) impe.item(impe.getLength() - 1);
            values.setImpuestos(ime.getAttribute("TotalImpuestosTrasladados").isEmpty() ? "0" : ime.getAttribute("TotalImpuestosTrasladados"));


            //"------------- Nombre de empresa ----------------
            NodeList emisor = comprobanteElement.getElementsByTagName("cfdi:Emisor");
            Element rcp = (Element) emisor.item(0);
            String companyName = rcp.getAttribute("Nombre");

            //"------------- RegimenFiscalReceptor ----------------
            NodeList receptor = comprobanteElement.getElementsByTagName("cfdi:Receptor");
            Element regimen = (Element) receptor.item(0);
            values.setRegimen(regimen.getAttribute("RegimenFiscalReceptor"));


            //"------------- UsoCFDI ----------------
            Element useCFDI = (Element) receptor.item(0);
            values.setUsoCFDI(useCFDI.getAttribute("UsoCFDI"));

            // System.out.println(values.getUsoCFDI());


            //"------------- Concepto ----------------
            NodeList conceptosList = comprobanteElement.getElementsByTagName("cfdi:Concepto");
            Element description = (Element) conceptosList.item(0);
            values.setConcepto_Descripcion(description.getAttribute("Descripcion"));

            //"------------- amount ----------------
            Element amount = (Element) conceptosList.item(0);
            values.setAmoubnt(amount.getAttribute("Importe"));


            //"------------- ClaveProdServ ----------------

            NodeList ClaveProdServ = comprobanteElement.getElementsByTagName("cfdi:Concepto");
            Element claveProdServ = (Element) ClaveProdServ.item(0);
            values.setClaveProdServ(claveProdServ.getAttribute("ClaveProdServ"));


            //"------------- Traslado ----------------
            NodeList traslados = comprobanteElement.getElementsByTagName("cfdi:Traslado");

            List<String> translado = new ArrayList<>();
            for (int i = 0; i < traslados.getLength(); i++) {
                Element retencionR = (Element) traslados.item(i);
                translado.add(retencionR.getAttribute("Importe").isEmpty() ? "0" : retencionR.getAttribute("Importe"));

            }

            values.setTraslado(translado);


            //"------------- Retencion ----------------
            NodeList retencion = comprobanteElement.getElementsByTagName("cfdi:Concepto");
            List<String> retencion_importe = new ArrayList<>();
            for (int i = 0; i < retencion.getLength(); i++) {
                Element retencionR = (Element) retencion.item(i);
                retencion_importe.add(retencionR.getAttribute("Importe"));
            }
            values.setRetencion_importe(retencion_importe);

            //"------------- TimbreFiscalDigital ----------------
            NodeList timbre = comprobanteElement.getElementsByTagName("tfd:TimbreFiscalDigital");
            Element uudi = (Element) timbre.item(0);
            values.setTimbreFiscalDigital_UUID(uudi.getAttribute("UUID"));


            //"------------- Return the object  ----------------

            return new ObjFile(values, FileName, companyName, cli, currentDate.substring(0, 10), typeOf, typeOfPayment);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
