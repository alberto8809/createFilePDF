package org.example.createfile.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.createfile.model.ObjFile;
import org.example.createfile.model.PolicyObj;
import org.example.createfile.repository.ClaveProductoServRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;


public class CreateFilePDF {
    private static final String EXT = ".pdf";
    private static final String FILENAME = "Ingreso";

    public static void makeFile(ObjFile objFile, int rand_int1) {


        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(FILENAME + EXT));
            document.open();


            PdfPTable table = new PdfPTable(2);

            PdfPCell cell1 = new PdfPCell(new Paragraph(objFile.getClient(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorderColor(BaseColor.WHITE);
            cell1.setBackgroundColor(new BaseColor(182, 208, 226));

            PdfPCell cell2 = new PdfPCell(new Paragraph("P ó l i z a" + "\n" + " Tipo: " + objFile.getTypeOf() + "  Folio: " + rand_int1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK.darker())));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorderColor(BaseColor.WHITE);
            cell2.setBackgroundColor(new BaseColor(182, 208, 226));

            //get month of query
            PdfPCell cell3 = new PdfPCell(new Paragraph(objFile.getCompanyName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBorderColor(BaseColor.WHITE);
            cell3.setBackgroundColor(new BaseColor(229, 231, 233));


            //param initialDate and endDate
            PdfPCell cell4 = new PdfPCell(new Paragraph(objFile.getDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK)));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBorderColor(BaseColor.WHITE);
            cell4.setBackgroundColor(new BaseColor(229, 231, 233));


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);


            table.setHorizontalAlignment(2);
            table.setWidthPercentage(60);


            PdfPTable headerTable = new PdfPTable(4);

            headerTable.setWidthPercentage(100);

            PdfPCell cellBody = new PdfPCell(new Paragraph("Cuenta", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellBody.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBody.setBorderColor(BaseColor.WHITE);
            cellBody.setBackgroundColor(new BaseColor(182, 208, 226));

            PdfPCell cellName = new PdfPCell(new Paragraph("Concepto", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellName.setPaddingRight(40);
            cellName.setBorderColor(BaseColor.WHITE);
            cellName.setBackgroundColor(new BaseColor(182, 208, 226));


            PdfPCell cellCharge = new PdfPCell(new Paragraph("Cargo", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCharge.setBorderColor(BaseColor.WHITE);
            cellCharge.setBackgroundColor(new BaseColor(182, 208, 226));

            PdfPCell cellAb = new PdfPCell(new Paragraph("Abono", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellAb.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellAb.setBorderColor(BaseColor.WHITE);
            cellAb.setBackgroundColor(new BaseColor(182, 208, 226));


            headerTable.addCell(cellBody);
            headerTable.addCell(cellName);
            headerTable.addCell(cellCharge);
            headerTable.addCell(cellAb);

            System.out.println("values " + objFile.getPolicyObj());

            PdfPTable bodyTable = new PdfPTable(4);

            bodyTable.setWidthPercentage(100);

            double totalR = 0;
            double total = 0;

            if (!objFile.getPolicyObj().getRetencion_importe().isEmpty()) {
                for (int i = 0; i < objFile.getPolicyObj().getRetencion_importe().size(); i++) {
                    totalR += Double.parseDouble(objFile.getPolicyObj().getRetencion_importe().get(i).isEmpty() || objFile.getPolicyObj().getRetencion_importe().get(i) == null ? "0" : objFile.getPolicyObj().getRetencion_importe().get(i));
                }
                total = totalR + Double.parseDouble(objFile.getPolicyObj().getImpuestos());

            }


            for (int i = 0; i < objFile.getPolicyObj().getRetencion_importe().size(); i++) {

                if ((objFile.getPolicyObj().getRegimen().equals("REGIMEN") || objFile.getPolicyObj().getRegimen().equals("REGIMEN2") || objFile.getPolicyObj().getRegimen().equals("REGIMEN20") || objFile.getPolicyObj().getRegimen().equals("REGIMEN22") || objFile.getPolicyObj().getRegimen().equals("REGIMEN23") || objFile.getPolicyObj().getRegimen().equals("REGIMEN24")) && objFile.getPolicyObj().getUsoCFDI().equals("CFDI")) {
                    PdfPCell accountBody = new PdfPCell();
                    PdfPCell descriptionBody = new PdfPCell();
                    if (objFile.getNameFile().equals("e1.xml")) {
                        accountBody = new PdfPCell(new Paragraph("601.34", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                        descriptionBody = new PdfPCell(new Paragraph("Honorarios a personas físicas residentes nacionales" + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));

                        accountBody.setBorderColorBottom(BaseColor.BLACK);
                        accountBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                        descriptionBody.setBorderColorBottom(BaseColor.BLACK);
                        descriptionBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                    } else if (objFile.getNameFile().equals("e2.xml") || objFile.getNameFile().equals("e3.xml") || objFile.getNameFile().equals("e4.xml") || objFile.getNameFile().equals("e6.xml") || objFile.getNameFile().equals("e7.xml")) {
                        accountBody = new PdfPCell(new Paragraph("601.84", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                        descriptionBody = new PdfPCell(new Paragraph("Otros gastos generales" + "\n" + objFile.getCompanyName() + "\n" + "(" + objFile.getPolicyObj().getTimbreFiscalDigital_UUID() + ")", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                        accountBody.setBorderColorBottom(BaseColor.BLACK);
                        accountBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                        descriptionBody.setBorderColorBottom(BaseColor.BLACK);
                        descriptionBody.setHorizontalAlignment(Element.ALIGN_CENTER);
                    }


                    PdfPCell paymentBody = new PdfPCell(new Paragraph(objFile.getPolicyObj().getRetencion_importe().get(i), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    paymentBody.setBorderColorLeft(BaseColor.WHITE);
                    paymentBody.setBorderColorRight(BaseColor.WHITE);
                    paymentBody.setBorderColorTop(BaseColor.WHITE);
                    paymentBody.setBorderColorBottom(BaseColor.BLACK);
                    paymentBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPCell Body = new PdfPCell(new Paragraph("0.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    Body.setBorderColorBottom(BaseColor.BLACK);
                    Body.setHorizontalAlignment(Element.ALIGN_CENTER);

                    bodyTable.addCell(accountBody);
                    bodyTable.addCell(descriptionBody);
                    bodyTable.addCell(paymentBody);
                    bodyTable.addCell(Body);

                } else if (objFile.getPolicyObj().getRegimen().equals("") && objFile.getPolicyObj().getUsoCFDI().equals("CFD")) {

                    PdfPCell accountBody = new PdfPCell(new Paragraph("503.01", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    accountBody.setBorderColorBottom(BaseColor.BLACK);
                    accountBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPCell descriptionBody = new PdfPCell(new Paragraph("Devoluciones, descuentos o bonificaciones sobre compras" + "\n" + objFile.getCompanyName() + "\n" + "(" + objFile.getPolicyObj().getTimbreFiscalDigital_UUID() + ")", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    descriptionBody.setBorderColorBottom(BaseColor.BLACK);
                    descriptionBody.setHorizontalAlignment(Element.ALIGN_CENTER);


                    PdfPCell paymentBody = new PdfPCell(new Paragraph(objFile.getPolicyObj().getRetencion_importe().get(i), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    paymentBody.setBorderColorLeft(BaseColor.WHITE);
                    paymentBody.setBorderColorRight(BaseColor.WHITE);
                    paymentBody.setBorderColorTop(BaseColor.WHITE);
                    paymentBody.setBorderColorBottom(BaseColor.BLACK);
                    paymentBody.setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPCell Body = new PdfPCell(new Paragraph("0.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
                    Body.setBorderColorBottom(BaseColor.BLACK);
                    Body.setHorizontalAlignment(Element.ALIGN_CENTER);

                    bodyTable.addCell(accountBody);
                    bodyTable.addCell(descriptionBody);
                    bodyTable.addCell(paymentBody);
                    bodyTable.addCell(Body);


                }

            }


            // ----------- cargoTable
            PdfPTable cargoTable = new PdfPTable(4);
            cargoTable.setWidthPercentage(100);

            PdfPCell accountCargo = new PdfPCell(new Paragraph("118.01", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            accountCargo.setBorderColorBottom(BaseColor.BLACK);
            accountCargo.setHorizontalAlignment(Element.ALIGN_CENTER);
            accountCargo.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell descripcionCargo = new PdfPCell(new Paragraph("IVA acreditable pagado", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            descripcionCargo.setBorderColorBottom(BaseColor.BLACK);
            descripcionCargo.setHorizontalAlignment(Element.ALIGN_CENTER);
            descripcionCargo.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell cargoCargo = new PdfPCell(new Paragraph(objFile.getPolicyObj().getImpuestos(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            cargoCargo.setBorderColorBottom(BaseColor.BLACK);
            cargoCargo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cargoCargo.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell cargo = new PdfPCell(new Paragraph("0.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            cargo.setBorderColorBottom(BaseColor.BLACK);
            cargo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cargo.setHorizontalAlignment(Element.ALIGN_CENTER);


            cargoTable.addCell(accountCargo);
            cargoTable.addCell(descripcionCargo);
            cargoTable.addCell(cargoCargo);
            cargoTable.addCell(cargo);


            //------- abonoTable

            PdfPTable abonoTable = new PdfPTable(4);
            abonoTable.setWidthPercentage(100);

            PdfPCell accountAbono = new PdfPCell(new Paragraph("102.01", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            accountAbono.setBorderColorBottom(BaseColor.BLACK);
            accountAbono.setHorizontalAlignment(Element.ALIGN_CENTER);
            accountAbono.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell descripcionAbono = new PdfPCell(new Paragraph("Bancos nacionales", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            descripcionAbono.setBorderColorBottom(BaseColor.BLACK);
            descripcionAbono.setHorizontalAlignment(Element.ALIGN_CENTER);
            descripcionAbono.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell cargoAbono = new PdfPCell(new Paragraph("0.00", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            cargoAbono.setBorderColorBottom(BaseColor.BLACK);
            cargoAbono.setHorizontalAlignment(Element.ALIGN_CENTER);
            cargoAbono.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell abono = new PdfPCell(new Paragraph("" + total, FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            abono.setBorderColorBottom(BaseColor.BLACK);
            abono.setHorizontalAlignment(Element.ALIGN_CENTER);
            abono.setHorizontalAlignment(Element.ALIGN_CENTER);


            abonoTable.addCell(accountAbono);
            abonoTable.addCell(descripcionAbono);
            abonoTable.addCell(cargoAbono);
            abonoTable.addCell(abono);


            //------------ footer

            PdfPTable footer = new PdfPTable(3);

            PdfPCell sumFooter = new PdfPCell(new Paragraph("SUMAS IGUALES", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            sumFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
            sumFooter.setBackgroundColor(new BaseColor(182, 208, 226));


            PdfPCell sumCargo = new PdfPCell(new Paragraph("$" + total, FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            sumCargo.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell sumAbono = new PdfPCell(new Paragraph("$" + objFile.getPolicyObj().getTotalAmount(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK)));
            sumAbono.setHorizontalAlignment(Element.ALIGN_CENTER);


            footer.addCell(sumFooter);
            footer.addCell(sumCargo);
            footer.addCell(sumAbono);


            footer.setTotalWidth(523);
            footer.setHorizontalAlignment(2);
            footer.setWidthPercentage(60);

            //------------ lastValues


            PdfPTable headerLastValues = new PdfPTable(4);
            headerLastValues.setWidthPercentage(100);

            PdfPCell cellDate = new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellDate.setBorderColor(BaseColor.WHITE);
            cellDate.setBackgroundColor(new BaseColor(182, 208, 226));

            PdfPCell cellConcept = new PdfPCell(new Paragraph("Proveedor / Cliente / Nómina", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellConcept.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellConcept.setBorderColor(BaseColor.WHITE);
            cellConcept.setBackgroundColor(new BaseColor(182, 208, 226));

            PdfPCell cellFolio = new PdfPCell(new Paragraph("Folio Fiscal", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellFolio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellFolio.setBorderColor(BaseColor.WHITE);
            cellFolio.setBackgroundColor(new BaseColor(182, 208, 226));


            PdfPCell cellTotal = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTotal.setBorderColor(BaseColor.WHITE);
            cellTotal.setBackgroundColor(new BaseColor(182, 208, 226));

            headerLastValues.addCell(cellDate);
            headerLastValues.addCell(cellConcept);
            headerLastValues.addCell(cellFolio);
            headerLastValues.addCell(cellTotal);


            PdfPTable lastValues = new PdfPTable(4);

            lastValues.setWidthPercentage(100);

            PdfPCell cellUUID = new PdfPCell(new Paragraph(objFile.getPolicyObj().getTimbreFiscalDigital_UUID(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK)));
            cellUUID.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUUID.setBorderColor(BaseColor.BLACK);

            PdfPCell totalAmount = new PdfPCell(new Paragraph("$" + objFile.getPolicyObj().getTotalAmount(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK)));
            totalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalAmount.setBorderColor(BaseColor.BLACK);


            PdfPCell provedor = new PdfPCell(new Paragraph(objFile.getCompanyName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK)));
            provedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            provedor.setBorderColor(BaseColor.BLACK);


            PdfPCell end_date = new PdfPCell(new Paragraph(objFile.getDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK)));
            end_date.setHorizontalAlignment(Element.ALIGN_CENTER);
            end_date.setBorderColor(BaseColor.BLACK);


            lastValues.addCell(end_date);
            lastValues.addCell(provedor);
            lastValues.addCell(cellUUID);
            lastValues.addCell(totalAmount);


            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(headerTable);
            document.add(bodyTable);
            document.add(cargoTable);
            document.add(abonoTable);
            document.add(footer);
            document.add(new Paragraph("\n\n\n\n"));
            document.add(headerLastValues);
            document.add(lastValues);


            document.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());


        }

    }

}
