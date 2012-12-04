/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.newsManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 *
 * @author francesco
 */
@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFile"})
@MultipartConfig
public class UploadFileServlet extends HttpServlet {

    private static final Logger LOGGER =Logger.getLogger(UploadFileServlet.class.getCanonicalName());
    
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
//        PrintWriter pw = response.getWriter();
//        
//      //  System.out.print(getServletContext().getRealPath("/")+"attached");
//      
//        DiskFileUpload fileUpload = new DiskFileUpload();
//        List list = null;
//        try {
//            list = fileUpload.parseRequest(request);
//        } catch (FileUploadException ex) {
//            throw new ServletException("Wrapped", ex);
//        }
//        Iterator iter = list.iterator();
//
//        while (iter.hasNext()) {
//            FileItem item = (FileItem) iter.next();
//            if (!item.isFormField()) {
//                File itemFile = new File(item.getName());
//                File destDir = new File(getServletContext().getRealPath("/")+"attached");
//                if (!destDir.exists()) {
//                    destDir.mkdirs();                    
//                }
//                File destFile = new File(getServletContext().getRealPath("/")
//                        + "attached" + File.separator + itemFile.getName());
//                pw.println(item);
//                pw.println(destFile);
//                System.out.println(destFile);
//                try {
//                    item.write(destFile);
//                } catch (Exception ex) {
//                    //throw new ServletException("Wrapped",ex);
//                    pw.println(ex.getMessage());
//                }
//            }
//        }
//        pw.close();

        // Create path components to save the file
        final String path = getServletContext().getRealPath("/")+"attached";
        System.out.print(path);
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        System.out.print(path+fileName);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                    new Object[]{fileName, path});

        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are " +
                   "trying to upload a file to a protected or nonexistent location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
