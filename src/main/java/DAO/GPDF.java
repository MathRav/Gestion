
package DAO;
import Model.mvtotal;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Rectangle;
public class GPDF {

    public static ByteArrayInputStream generer(List<mvtotal> liste) {
      //lastCompte=null;
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
         String lastCompte=null;
        String lastCompteDtl=null;
        try {

          PdfPCell hcell=null;
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph titre=new Paragraph("Grands Livres ", headFont);
            document.add(titre);

            double solde=0;

            PdfPTable table=null;
            for (mvtotal ray : liste) {
              if(lastCompte!=null && lastCompte.charAt(0)!=(ray.getCodePlanComptable()+"").charAt(0)){
                hcell = new PdfPCell(new Phrase("Total compte (fin) "+lastCompte.charAt(0), headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                table.completeRow();
              }
              if(lastCompte==null || lastCompte.charAt(0)!=(ray.getCodePlanComptable()+"").charAt(0)){
                if(table!=null) document.add(table);
                table=newTable();
                lastCompte=ray.getCodePlanComptable()+"";
                hcell = new PdfPCell(new Phrase("Compte "+lastCompte.charAt(0), headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                table.completeRow();
              }

            if(lastCompteDtl!=null && !lastCompteDtl.equals(ray.getCodePlanComptable()+"")){
              hcell = new PdfPCell(new Phrase("Total "+ray.getIntitulePlanComptable()+" (fin)", headFont));
              hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
              table.addCell(hcell);
              table.completeRow();
            }
            if(lastCompteDtl==null || !lastCompteDtl.equals(ray.getCodePlanComptable()+"")){
              lastCompteDtl=ray.getCodePlanComptable()+"";
              hcell = new PdfPCell(new Phrase(lastCompteDtl+" "+ray.getIntitulePlanComptable(), headFont));
              hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
              table.addCell(hcell);
              table.completeRow();
            }

            PdfPCell cell;
            cell = new PdfPCell(new Phrase(ray.getDateMvt().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(ray.getCodeJournal().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell = new PdfPCell(new Phrase(ray.getReference().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(ray.getLibelle().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(ray.getDebit()+""));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            solde+=ray.getDebit();
            cell = new PdfPCell(new Phrase(ray.getCredit()+""));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            solde-=ray.getCredit();
            cell = new PdfPCell(new Phrase((solde)+""));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


          }


  //          PdfPTable table=newTable();


//            for (mvtotal ray : liste) {

    /*
                  if(lastCompte!=null && lastCompte.charAt(0)!=(ray.getCodePlanComptable()+"").charAt(0)){
                    hcell = new PdfPCell(new Phrase("Total compte (fin) "+lastCompte.charAt(0), headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);
                    table.completeRow();
                  }
                if(lastCompte==null || lastCompte.charAt(0)!=(ray.getCodePlanComptable()+"").charAt(0)){
                  lastCompte=ray.getCodePlanComptable()+"";
                  hcell = new PdfPCell(new Phrase("Compte "+lastCompte.charAt(0), headFont));
                  hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  table.addCell(hcell);
                  table.completeRow();
                }

                if(lastCompteDtl!=null && !lastCompteDtl.equals(ray.getCodePlanComptable()+"")){
                  hcell = new PdfPCell(new Phrase("Total "+ray.getIntitulePlanComptable()+" (fin)", headFont));
                  hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  table.addCell(hcell);
                  table.completeRow();
                }
                if(lastCompteDtl==null || !lastCompteDtl.equals(ray.getCodePlanComptable()+"")){
                  lastCompteDtl=ray.getCodePlanComptable()+"";
                  hcell = new PdfPCell(new Phrase(lastCompteDtl+" "+ray.getIntitulePlanComptable(), headFont));
                  hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  table.addCell(hcell);
                  table.completeRow();
                }
                */


//            }


            document.add(table);

            document.close();

        } catch (Exception ex) {

            Logger.getLogger(GPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static PdfPTable newTable() throws Exception{
      PdfPTable table = new PdfPTable(7);
      table.setWidthPercentage(90);
      table.setWidths(new int[]{5,3,3,3,5,5,5});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;

      hcell = new PdfPCell(new Phrase("Date", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);


                  hcell = new PdfPCell(new Phrase("Journal", headFont));
                  hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  table.addCell(hcell);


                              hcell = new PdfPCell(new Phrase("Reference", headFont));
                              hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                              table.addCell(hcell);


                                          hcell = new PdfPCell(new Phrase("Libelle", headFont));
                                          hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                          table.addCell(hcell);


                                                      hcell = new PdfPCell(new Phrase("Debit", headFont));
                                                      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                      table.addCell(hcell);


                                                                  hcell = new PdfPCell(new Phrase("Credit", headFont));
                                                                  hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                                  table.addCell(hcell);


                                                                              hcell = new PdfPCell(new Phrase("Solde", headFont));
                                                                              hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                                              table.addCell(hcell);
                                                                              return table;
    }
}
