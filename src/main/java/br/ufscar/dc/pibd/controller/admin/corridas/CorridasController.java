package br.ufscar.dc.pibd.controller.admin.corridas;

import br.ufscar.dc.pibd.dao.admin.ResumoCorridaDAO;
import br.ufscar.dc.pibd.domain.admin.ResumoCorrida;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/corridas"})
public class CorridasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String corridaID = req.getParameter("id");
        String conveniada = req.getParameter("conv");

        if (corridaID == null || corridaID.isEmpty())
            this.doGetListagem(conveniada, req, resp);
        else
            this.doGetDetalhamento(corridaID, req, resp);

    }

    private void doGetListagem(String conveniada, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        ResumoCorridaDAO resumoCorridaDAO = new ResumoCorridaDAO();
        List<ResumoCorrida> listagemCorridas =
                conveniada == null || conveniada.isEmpty() ?
                        resumoCorridaDAO.getAllResumosDeCorrida() :
                        resumoCorridaDAO.getAllResumosDeCorridaPorConveniada(conveniada);

        req.setAttribute("corridas", listagemCorridas);
        //throw new RuntimeException(listagemCorridas.toString());

        RequestDispatcher rd = req.getRequestDispatcher("corridas/index.jsp");
        rd.include(req, resp);
    }

    private void doGetDetalhamento(String corridaID, HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        resp.getWriter().println(corridaID);
    }
}
