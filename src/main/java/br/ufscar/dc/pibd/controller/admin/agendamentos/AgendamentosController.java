package br.ufscar.dc.pibd.controller.admin.agendamentos;

import br.ufscar.dc.pibd.dao.admin.agendamentos.AgendamentoDAO;
import br.ufscar.dc.pibd.domain.admin.agendamentos.ResumoAgendamento;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/agendamentos"})
public class AgendamentosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgendamentoDAO dao = new AgendamentoDAO();
        List<ResumoAgendamento> listagemAgendamentos = dao.getAllResumosDeAgendamento();
        req.setAttribute("agendamentos", listagemAgendamentos);

        RequestDispatcher rd = req.getRequestDispatcher("agendamentos/index.jsp");
        rd.include(req, resp);
    }


}
