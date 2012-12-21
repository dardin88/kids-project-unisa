package it.unisa.kids.accessManagement.registrationChildManagement;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletPdfRegistrationChild extends HttpServlet {
    private IAccountManager accountManager;
    private IRegistrationChildManager registrationChildManager;
    private final String NOMEFILE = "domanda_di_iscrizione.pdf";
    
    public void init(ServletConfig config) {
        accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
        registrationChildManager = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
    }

    private static Font FTITLE = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font text = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    private static Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            // campi necessari per prelevare le informazioni
            String sId = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID);
            if(sId != null && !sId.equals("")) {
                String file = request.getServletContext().getInitParameter("pdfFolder") + NOMEFILE;
                String downloadName = "Domanda di iscrizione.pdf";

                Document document = new Document(PageSize.A4, 85, 50, 70, 70);
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                this.addMetaData(document);
                this.addTitle(document);
                
                RegistrationChild tmpRegistrationChild = new RegistrationChild();
                tmpRegistrationChild.setId(Integer.parseInt(sId));
                tmpRegistrationChild = registrationChildManager.search(tmpRegistrationChild).get(0);
                
                Account tmpAccount = new Account();
                tmpAccount.setId(tmpRegistrationChild.getParentId());
                tmpAccount = accountManager.search(tmpAccount).get(0);
                this.addContent(document, tmpAccount, tmpRegistrationChild);
            
                document.close();
                this.doDownload(request, response, file, downloadName);
            }
             
        } catch(SQLException ex) {
            Logger.getLogger(ServletPdfRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("insuccesso.jsp");
        } catch(BadElementException ex) {
            Logger.getLogger(ServletPdfRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("insuccesso.jsp");
        } catch(DocumentException ex) {
            Logger.getLogger(ServletPdfRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("insuccesso.jsp");
        }
    }

    private void doDownload(HttpServletRequest request, HttpServletResponse response, String filename, String original_filename) throws IOException {
        File f = new File(filename);
        int length = 0;
        ServletOutputStream op = response.getOutputStream();
        ServletContext context = request.getServletContext();
        String mimetype = context.getMimeType(filename);
        response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
        response.setContentLength((int) f.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");
        byte[] bbuf = new byte[4 * 1024];
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        while ((in != null) && ((length = in.read(bbuf)) != -1)) {
            op.write(bbuf, 0, length);
        }
        in.close();
        op.flush();
        op.close();
    }

    private void addMetaData(Document document) {
        document.addTitle(NOMEFILE);
        document.addAuthor("Kids");
    }

    private void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Domanda di iscrizione", FTITLE);
        title.setSpacingAfter(10f);
        document.add(title);
    }

    private void addContent(Document document, Account tmpAccount, RegistrationChild tmpRegistrationChild) throws DocumentException, SQLException, BadElementException, IOException {
        Paragraph contenutoPagina = new Paragraph();
        // prima parte
        contenutoPagina.add(new Chunk("Presentata dal Genitore:" + newLine(), bold));
        contenutoPagina.add(new Chunk("Codice fiscale: " + tmpAccount.getTaxCode() + newLine(), text));
        contenutoPagina.add(new Chunk("Cognome: " + tmpAccount.getSurnameUser() + newLine(), text));
        contenutoPagina.add(new Chunk("Nome: " + tmpAccount.getNameUser() + newLine(), text));
        contenutoPagina.add(new Chunk("Data di nascita: " + CommonMethod.parseString(tmpAccount.getDataOfBirth()) + newLine(), text));
        contenutoPagina.add(new Chunk("Luogo di nascita: " + tmpAccount.getPlaceOfBirth() + newLine(), text));
        this.addEmptyLine(contenutoPagina, 2);
        // seconda parte
        contenutoPagina.add(new Chunk("Dettagli del Figlio:" + newLine(), bold));
        contenutoPagina.add(new Chunk("Cognome: " + tmpRegistrationChild.getSurname() + newLine(), text));
        contenutoPagina.add(new Chunk("Nome: " + tmpRegistrationChild.getName() + newLine(), text));
        contenutoPagina.add(new Chunk("Data di nascita: " + CommonMethod.parseString(tmpRegistrationChild.getBirthDate()) + newLine(), text));
        contenutoPagina.add(new Chunk("Luogo di nascita: " + tmpRegistrationChild.getBirthPlace() + newLine(), text));
        contenutoPagina.add(new Chunk("Codice fiscale: " + tmpRegistrationChild.getFiscalCode() + newLine(), text));
        contenutoPagina.add(new Chunk("Cittadinanza: " + tmpRegistrationChild.getCitizenship() + newLine(), text));
        contenutoPagina.add(new Chunk("Fascia d'utenza: " + tmpRegistrationChild.getUserRange() + newLine(), text));
        this.addEmptyLine(contenutoPagina, 2);
        // fine
        document.add(contenutoPagina);
        
        Paragraph data = new Paragraph();
        data.add(new Chunk("Data di presentazione della domanda" + newLine(), bold));
        contenutoPagina.add(new Chunk(CommonMethod.parseString(tmpRegistrationChild.getRegistrationDate()) + newLine(), text));
        this.addEmptyLine(data, 1);
        document.add(data);
        
        Paragraph firma = new Paragraph();
        firma.add(new Chunk("Firma:" + newLine(), bold));
        this.addEmptyLine(firma, 3);
        document.add(firma);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private String newLine() {
        return "\n";
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
