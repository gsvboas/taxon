package br.ufscar.dc.pibd.controller.admin.conveniadas;


import br.ufscar.dc.pibd.dao.admin.conveniadas.ConveniadasDAO;
import br.ufscar.dc.pibd.domain.admin.conveniadas.ResumoConveniada;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/conveniadas"})
public class ConveniadasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConveniadasDAO dao = new ConveniadasDAO();
        List<ResumoConveniada> listagemConveniadas = dao.getAllResumosDeConveniada();
        req.setAttribute("conveniadas", listagemConveniadas);

        RequestDispatcher rd = req.getRequestDispatcher("conveniadas/index.jsp");
        rd.include(req, resp);
    }
}
