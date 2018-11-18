package com.refunits.web.servlet;

import com.refunits.database.enumeration.Limit;
import com.refunits.service.dto.SearchUnitDto;
import com.refunits.database.enumeration.BoilingPoint;
import com.refunits.database.enumeration.UnitRange;
import com.refunits.service.service.CatalogService;
import com.refunits.web.util.JspPathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.refunits.web.util.EmptyStringUtil.isNotEmpty;

@WebServlet(value = "/catalog", name = "Catalog")
public class CatalogServlet extends HttpServlet {

    @Autowired
    private CatalogService catalogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("boilingPoints", BoilingPoint.values());
        req.setAttribute("ranges", UnitRange.values());
        req.setAttribute("limits", Limit.values());
        req.setAttribute("units", catalogService.getAll());
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
        searchUnitDto.setLimit(Integer.valueOf(Limit.valueOf(req.getParameter("limit")).getNumber()));

        req.setAttribute("boilingPoints", BoilingPoint.values());
        req.setAttribute("ranges", UnitRange.values());
        req.setAttribute("limits", Limit.values());
        req.setAttribute("units", catalogService.getFiltered(searchUnitDto));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("catalog"))
                .forward(req, resp);
    }
}