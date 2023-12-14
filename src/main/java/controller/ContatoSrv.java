package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contato;
import model.dao.ContatoDaoJpa;
import model.dao.InterfaceDao;

public class ContatoSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            ContatoDaoJpa dao = new ContatoDaoJpa();
            RequestDispatcher rd;
            Contato c = null;
            
            switch (acao) {
                case "inclusao":
                    c = new Contato(nome, email, telefone);

                    dao.incluir(c);


                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
                    
                case "pre-edicao":
                    c = (Contato) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("Formulario.jsp?acao=edicao"
                            + "&id=" + c.getId()
                            + "&nome=" + c.getNome()
                            + "&email=" + c.getEmail()
                            + "&telefone=" + c.getTelefone());
                    rd.forward(request, response);
                    break;
                    
                case "edicao":
                    c = new Contato(nome, email, telefone);
                    c.setId(Integer.parseInt(id));

                    dao.editar(c);


                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
                    
                case "exclusao":
                    c = new Contato();
                    c.setId(Integer.parseInt(id));
                    dao.excluir(c);
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
                    
                case "listagem":
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ContatoSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String listagem() {
        ContatoDaoJpa dao = new ContatoDaoJpa();
        List<Contato> lista = null;
        try {
            lista = dao.listar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String listaHTML = "";
        for (Contato contato : lista) {
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + contato.getNome()     + "</td>"
                    + "<td>" + contato.getEmail()    + "</td>"
                    + "<td>" + contato.getTelefone() + "</td>"
                    
                    + "<td><form action=ContatoSrv?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value=" + 
                        contato.getId() + "><input type='submit' value=editar>"
                    + "</form></td>"
                    
                    + "<form action=ContatoSrv?acao=exclusao method='POST'>"
                    + "<td><input type='hidden' name='id' value=" + 
                        contato.getId() + "><input type='submit' value=excluir></td>"
                    + "</form>"
                    
                    + "</tr>";
        }
        return listaHTML;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
