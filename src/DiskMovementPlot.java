import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * {@link XYDataset}.
 *
 */
public class DiskMovementPlot extends JPanel {
	
	private DiskOptimizationAlgorithm doa;
	private boolean isPlottingFCFS;
	private boolean isPlottingSSTF;
	private boolean isPlottingSCAN;
	private boolean isPlottingLOOK;
	private boolean isPlottingCSCAN;
	private boolean isPlottingCLOOK;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public DiskMovementPlot(DiskOptimizationAlgorithm doa, boolean isPlottingFCFS,boolean isPlottingSSTF,boolean isPlottingSCAN,boolean isPlottingLOOK,boolean isPlottingCSCAN,boolean isPlottingCLOOK) {
    	this.doa = doa;
    	this.isPlottingFCFS = isPlottingFCFS;
    	this.isPlottingSSTF = isPlottingSSTF;
    	this.isPlottingSCAN = isPlottingSCAN;
    	this.isPlottingLOOK = isPlottingLOOK;
    	this.isPlottingCSCAN = isPlottingCSCAN;
    	this.isPlottingCLOOK = isPlottingCLOOK;
    	
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(960, 350));
        this.add(chartPanel);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYSeries createSeries(String label, List<Integer> sequence) {
        
        final XYSeries series = new XYSeries(label);
        
        for(int i=0;i<sequence.size();i++){
        	series.add(i+1, sequence.get(i));
        }
        
        return series;        
    }
    
    private XYDataset createDataset(){
    	final XYSeriesCollection dataset = new XYSeriesCollection();
    	if(isPlottingFCFS) dataset.addSeries(createSeries("FCFS", doa.fcfs()));
    	if(isPlottingSSTF) dataset.addSeries(createSeries("SSTF", doa.sstf()));
    	if(isPlottingSCAN) dataset.addSeries(createSeries("SCAN", doa.scan()));
    	if(isPlottingLOOK) dataset.addSeries(createSeries("LOOK", doa.look()));
    	if(isPlottingCSCAN) dataset.addSeries(createSeries("CSCAN", doa.cscanPlot()));
    	if(isPlottingCLOOK) dataset.addSeries(createSeries("CLOOK", doa.clookPlot()));
                
        return dataset;
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Line Chart Demo 6",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.HORIZONTAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.getDomainAxis().setInverted(true);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(1));
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }

}

