package br.ufscar.dc.pibd.controller.admin.motoristas;

import br.ufscar.dc.pibd.dao.admin.conveniadas.ConveniadasDAO;
import br.ufscar.dc.pibd.dao.admin.motoristas.MotoristasDAO;
import br.ufscar.dc.pibd.domain.admin.conveniadas.ResumoConveniada;
import br.ufscar.dc.pibd.domain.admin.motoristas.ResumoMotorista;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/motoristas"})
public class MotoristasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MotoristasDAO dao = new MotoristasDAO();
        List<ResumoMotorista> listagemMotoristas = dao.getAllResumosDeMotorista();
        req.setAttribute("motoristas", listagemMotoristas);

        RequestDispatcher rd = req.getRequestDispatcher("motoristas/index.jsp");
        rd.include(req, resp);
    }
}
