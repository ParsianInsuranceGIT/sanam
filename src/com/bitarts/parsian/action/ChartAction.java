package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/7/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChartAction extends BaseAction {
    private static Random random = new Random(new Date().getTime());

    public static synchronized String createBarChart(Map<String, Map<String, Double>> data, String title) {
        try {

            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (String key : data.keySet()) {
                for (String key2 : data.get(key).keySet())
                    try {
                        dataset.addValue(data.get(key).get(key2), key, key2);
                    } catch (Exception ignore) {
                    }
            }
            final JFreeChart chart = ChartFactory.createBarChart(title, "", "", dataset, PlotOrientation.VERTICAL, true, true, false);
            chart.setTitle(new TextTitle(title, new Font("SansSerif", Font.PLAIN, 12)));
            final CategoryPlot plot = (CategoryPlot) chart.getPlot();
            final CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();
            DecimalFormat decimalformat1 = new DecimalFormat("##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            BarRenderer br = (BarRenderer) plot.getRenderer();
            br.setMaximumBarWidth(0.15);
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            final String strPath = ServletActionContext.getServletContext().getRealPath("/");
            final File homeDir = new File(strPath + "/temp/chart");
            final String chartFileName = "/chart" + random.nextLong() + ".png";
            final File png = new File(homeDir.getPath() + chartFileName);
            if (homeDir.exists())
                FileUtils.cleanDirectory(homeDir);
            FileUtils.touch(png);
            ChartUtilities.saveChartAsPNG(png, chart, 900, 400);
            png.deleteOnExit();
            return "/temp/chart" + chartFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
