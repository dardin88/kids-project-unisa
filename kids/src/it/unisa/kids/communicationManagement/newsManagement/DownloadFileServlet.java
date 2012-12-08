/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.newsManagement;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author francesco di lorenzo
 */
public class DownloadFileServlet extends HttpServlet {

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
                    
        String uri = (String) getServletContext().getInitParameter("attachedFileFolder");                    
        String fileName=request.getParameter("nameFile");
        uri+="/"+fileName;
        
        File file = new File(uri);
        FileInputStream fileInput = new FileInputStream(file);
        response.setContentLength((int) file.length());
        response.setContentType(getMimeType(file));
        response.setHeader("Content-Disposition", "attachment; filename=\""+file.getName() + "\"");
        ServletOutputStream out = response.getOutputStream();
        BufferedOutputStream bufferOut = new BufferedOutputStream(out);
        int b = 0;
        byte[] bufferData = new byte[8192];
        while ((b = fileInput.read(bufferData)) != -1) {
            bufferOut.write(bufferData, 0, b);
        }
        bufferOut.flush();
        bufferOut.close();
        out.close();
        fileInput.close();
    }

    private String getMimeType(File file) {

        MimetypesFileTypeMap mime = new MimetypesFileTypeMap();

        return mime.getContentType(file);

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
