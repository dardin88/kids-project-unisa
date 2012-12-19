/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
public class GetClassDivRegisterServlet extends HttpServlet {

    private IAccessFacade accessFacade;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<ClassBean> classList = accessFacade.getClasses();
            for (ClassBean clas : classList) {
                out.println(" <div id=\"insertActivityWindow" + clas.getIdClasse() + "\">\n"
                        +"    <form method=\"post\" action=\"InsertDailyActivity\" id=\"form"+clas.getIdClasse()+"\">"
                        +"         <input id=\"idClass"+clas.getIdClasse()+"\" type=\"hidden\" name=\"idClass\" value=\"\">"
                        + "        <table id=\"tableActivity" + clas.getIdClasse() + "\">\n"
                        + "                <thead>\n"
                        + "                    <tr>\n"
                        + "                        <th>Seleziona</th>\n"
                        + "                        <th>Nome</th>\n"
                        + "                        <th>Contenuto</th>\n"
                        + "\n"
                        + "                    </tr>\n"
                        + "                </thead>\n"
                        + "                <tbody>\n"
                        + "                </tbody>\n"
                        + "            </table>\n"
                        + "        <table>\n"
                        + "            <tr><td> Note:</td><td><textarea id=\"note\" name=\"nota\" rows=\"5\" cols=\"20\"></textarea></td></tr>\n"
                        + "        </table>\n"
                        +"         <input type=\"button\" id=\"insert"+clas.getIdClasse()+"\" name=\"insert\" value=\"Inserisci\" onclick=\"controlla(this,'"+clas.getIdClasse()+"')\">"
                        +"         </form>"
                        + "    </div>");
                out.println("<div id=\"" + clas.getIdClasse() + "\" style=\"width:97%\">");
                out.println("<input type=\"button\" id=\"insertActivityButton" + clas.getIdClasse() + "\" value=\"Inserisci Attivit&agrave\" onclick=\"openInsertActivity('" + clas.getIdClasse() + "')\">");
                out.println("<table id=\"table" + clas.getIdClasse() + "\">\n"
                        + "                <thead>\n"
                        + "                    <tr>\n"
                        + "                         <th>Giorno</th>\n  "
                        + "                        <th>Nome Attvit&agrave</th>\n"
                        + "                         <th>Educatore</th>\n"
                        + "                        <th>Operazioni</th>\n"
                        + "\n"
                        + "                    </tr>\n"
                        + "                </thead>\n"
                        + "                <tbody>\n"
                        + "                </tbody>\n"
                        + "            </table>"
                        +"");
                out.println("</div>");
                out.println("<script text=\"text/javascipt\">"
                        + "buildTable(\"" + clas.getIdClasse() + "\");"
                        + "$(\"#insertActivityButton" + clas.getIdClasse() + "\").button();"
                        + "$(\"#insertActivityWindow" + clas.getIdClasse() + "\").dialog({"
                        + "autoOpen: false,"
                        + " modal: true,"
                        + " resizable: false,"
                        + " width: 900"
                        + "});");
                out.println("$('#tableActivity"+clas.getIdClasse()+"').dataTable({\n"
                        + "        \"bJQueryUI\": true,\n"
                        + "        \"bServerSide\": true,\n"
                        + "        \"bProcessing\": true,\n"
                        + "        \"sAjaxSource\": \"GetActivityTable\",\n"
                        + "        \"bPaginate\": true,\n"
                        + "        \"bLengthChange\": false,\n"
                        + "        \"bFilter\": false,\n"
                        + "        \"fnServerParams\": function(aoData) {\n"
                        + "            aoData.push(\n"
                        + "                    {\n"
                        + "                        \"name\": \"id\",\n"
                        + "                        \"value\": "+clas.getIdClasse()+"\n"
                        + "                    }\n"
                        + "            );\n"
                        + "\n"
                        + "        },\n"
                        + "        \"bSort\": false,\n"
                        + "        \"bDestroy\": true,\n"
                        + "        \"bInfo\": true,\n"
                        + "        \"bAutoWidth\": true,\n"
                        + "        \"sPaginationType\": \"full_numbers\",\n"
                        + "        \"oLanguage\": {\n"
                        + "            \"sProcessing\": \"Caricamento...\",\n"
                        + "            \"sLengthMenu\": \"Visualizza _MENU_ link\",\n"
                        + "            \"sZeroRecords\": \"La ricerca non ha portato alcun risultato.\",\n"
                        + "            \"sInfo\": \"Vista da _START_ a _END_ di _TOTAL_ Attivita\",\n"
                        + "            \"sInfoEmpty\": \"Vista da 0 a 0 di 0 Attivita\",\n"
                        + "            \"sInfoFiltered\": \"(filtrati da _MAX_ link totali)\",\n"
                        + "            \"sInfoPostFix\": \"\",\n"
                        + "            \"oPaginate\": {\n"
                        + "                \"sFirst\": \"<<\",\n"
                        + "                \"sPrevious\": \"<\",\n"
                        + "                \"sNext\": \">\",\n"
                        + "                \"sLast\": \">>\"\n"
                        + "            }\n"
                        + "        },\n"
                        + "        \"aoColumns\": [\n"
                        + "            {\n"
                        + "                \"sWidth\": \"1%\"\n"
                        + "            },\n"
                        + "            {\n"
                        + "                \"sWidth\": \"10%\"\n"
                        + "            },\n"
                        + "            {\n"
                        + "                \"sWidth\": \"89%\"\n"
                        + "            }\n"
                        + "        ]\n"
                        + "    });"
                        + "$(\"#insert"+clas.getIdClasse()+"\").button()</script>");

            }


        } catch (SQLException ex) {
            Logger.getLogger(GetClassTabsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // out.close();
        }
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
