package servlet;

import dto.SearchUnitDto;
import enumeratiom.BoilingPoint;
import enumeratiom.UnitRange;
import service.CatalogService;
import util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.EmptyStringUtil.isNotEmpty;

@WebServlet(value = "/catalog", name = "Catalog")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("boilingPoints", BoilingPoint.values());
        req.setAttribute("ranges", UnitRange.values());
        req.setAttribute("units", CatalogService.getInstance().getAll());
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("catalog"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchUnitDto searchUnitDto = SearchUnitDto.builder()
                .build();
        if (isNotEmpty(req.getParameter("boilingPoint"))) {
            searchUnitDto.setBoilingPoint(BoilingPoint.valueOf(req.getParameter("boilingPoint")));
        }
        if (isNotEmpty(req.getParameter("range"))) {
            searchUnitDto.setRange(UnitRange.valueOf(req.getParameter("range")));
        }
        if (isNotEmpty(req.getParameter("minRefCapacity")) && isNotEmpty(req.getParameter("maxRefCapacity"))) {
            searchUnitDto.setMinRefCapacity(Double.valueOf(req.getParameter("minRefCapacity")));
            searchUnitDto.setMaxRefCapacity(Double.valueOf(req.getParameter("maxRefCapacity")));
        }
        req.setAttribute("boilingPoints", BoilingPoint.values());
        req.setAttribute("ranges", UnitRange.values());
        req.setAttribute("units", CatalogService.getInstance().getFiltered(searchUnitDto));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("catalog"))
                .forward(req, resp);
    }
}