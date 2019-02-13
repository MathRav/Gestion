package Controllers;
import DAO.GPDF;
import DAO.mvtotalDao;
import Model.mvtotal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class PDFController {

  @Autowired
  mvtotalDao mvtdao;

    @RequestMapping(value = "/bilan", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> testr(@RequestParam(value="mois",defaultValue="1") int mois, @RequestParam(value="idex",defaultValue="1") int idExercice ) throws IOException {
List<mvtotal> liste=this.mvtdao.findAllZavatra(new Integer(mois),new Long(idExercice));
        ByteArrayInputStream bis = GPDF.generer(liste);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
