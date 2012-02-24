/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.copa.controller.EstadioController;
import br.copa.dao.EstadioDAO;
import br.copa.model.Estadio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.util.Base64;

/**
 *
 * @author George
 */
public class principal extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
           
            
        } finally {            
            out.close();
        }
    }
    
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        
        JSONObject jsonObj = new JSONObject();
        JSONArray arrayEstadio = new JSONArray();
        Estadio estadio = new Estadio();
        EstadioDAO est = new EstadioDAO();
        EstadioController estadioC = new EstadioController();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String opcao = request.getParameter("op");
        
        if (opcao.equals("listarEstadios")) {
             arrayEstadio = estadioC.getEstadioList();
             out.println(arrayEstadio);
        }
        
        if (opcao.equals("estadio")) {
            String resposta = request.getParameter("nomeEstadio");

            try {

                if (resposta.equals("Fonte Nova")) {
                    jsonObj = estadioC.getEstadioByName("Fonte Nova");
                }

                if (resposta.equals("Maracanã")) {
                    jsonObj = estadioC.getEstadioByName("Maracanã");
                }

                if (resposta.equals("Garrincha")) {
                    jsonObj = estadioC.getEstadioByName("Mané Garrincha");
                }

                if (resposta.equals("Mineirão")) {
                    jsonObj = estadioC.getEstadioByName("Mineirao");
                }

              out.println(jsonObj);//Escreve a resposta no formato JSON na Stream de saída que será recebida pela aplicação cliente
                              
            } catch (Exception e) {
                System.out.println("Erro" + e.getMessage());

            }                
                out.flush();
                out.close();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
