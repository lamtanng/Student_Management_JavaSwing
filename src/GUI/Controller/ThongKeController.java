package GUI.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import DAO.ThongKeDao;
import DAO.impl.ThongKeDaoImpl;
import Ultils.MyUtils;

public class ThongKeController {
	private JTable table;
	private JTable tableDuration;
	private JButton btnPrint;
	private JButton btnCreate;
	private JButton btnCreateByTime;
	private JButton btnPrintByTime;
	private JComboBox cbReportType;
	private JYearChooser cbYear;
	private JDateChooser beginDate;
	private JDateChooser endDate;
	private ThongKeDao dao;
	
	public ThongKeController(JTable table, JTable tableDuration, JButton btnPrint, JButton btnCreate, JButton btnCreateByTime, JButton btnPrintByTime, JComboBox cbReportType, JYearChooser cbYear,
			JDateChooser beginDate, JDateChooser endDate) {
		this.table = table;
		this.tableDuration = tableDuration;
		this.btnPrint = btnPrint;
		this.btnCreate = btnCreate;
		this.btnCreateByTime = btnCreateByTime;
		this.btnPrintByTime = btnPrintByTime;
		this.cbReportType = cbReportType;
		this.cbYear = cbYear;
		this.beginDate = beginDate;
		this.endDate = endDate;
		dao = new ThongKeDaoImpl();
		setEvent();
	}
	
	public void clearData() {
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		
		this.table.setModel(tableModel);
		this.tableDuration.setModel(tableModel);
	}
	
	private void setEvent() {
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                            if(table.getRowCount() == 0) {
                                MyUtils.showInfoMessage("Info", "No data to export!");
                            }
                            else {
                                String firstCol = (String)cbReportType.getSelectedItem();
                                String[] colNames = {firstCol, "Price"};
                                Object[][] data = convertTableData(table);
                                DefaultTableModel model = new DefaultTableModel();
                                for(String colName : colNames) {
                                    model.addColumn(colName);
                                }
                                for (Object[] row : data) {
                                    model.addRow(row);
                                }
                                MyUtils.exportToExcel(model);
                            }
                            
			}
		});
		
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				report(cbReportType.getSelectedIndex(), cbYear.getYear());
			}
		});
		
		btnCreateByTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (beginDate.getDate() == null || endDate.getDate() == null) {
					MyUtils.showErrorMessage("Error", "Please choose a valid duration!");
				}
				else {
					report(new Date(beginDate.getDate().getTime()), new Date(endDate.getDate().getTime()));
				}
			}
		});
		
		btnPrintByTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (beginDate.getDate() == null || endDate.getDate() == null) {
					MyUtils.showErrorMessage("Error", "Please choose a valid duration!");
				}
				else {
					writeReport(new Date(beginDate.getDate().getTime()), new Date(endDate.getDate().getTime()));
				}
			}
		});
	}
	
	private void report(int type, int year) {
		String[] labels;
		Map<Integer, Integer> reportData;
		if (type == 0) {
			labels = new String[] {"Month", "Revenue"};
			reportData = dao.reportByMonthYear(year);
		}
		else if (type == 1) {
			labels = new String[] {"Quater", "Revenue"};
			reportData = dao.reportByQuaterYear(year);
		}
		else {
			labels = new String[] {"Year", "Revenue"};
			reportData = dao.reportAllYear();
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		
		for (Integer k : reportData.keySet()) {
			Object[] row = {k, reportData.get(k)};
			tableModel.addRow(row);
		}
		this.table.setModel(tableModel);
	}
	
	private void report(Date dateStart, Date dateEnd) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Revenue from " + dateFormat.format(dateStart) + " to " + dateFormat.format(dateEnd)}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		
		Object[] row = {dao.reportByTime(dateStart, dateEnd)};
		tableModel.addRow(row);
		this.tableDuration.setModel(tableModel);
	}
	
	private void writeReport(int type, int year) {
		String[] labels;
		String msg;
		Map<Integer, Integer> reportData;
		if (type == 0) {
			msg = "Report all month in " + String.valueOf(year);
			labels = new String[] {"Month", "Revenue"};
			reportData = dao.reportByMonthYear(year);
		}
		else if (type == 1) {
			msg = "Report all quarter in " + String.valueOf(year);
			labels = new String[] {"Quater", "Revenue"};
			reportData = dao.reportByQuaterYear(year);
		}
		else {
			msg = "Report all year";
			labels = new String[] {"Year", "Revenue"};
			reportData = dao.reportAllYear();
		}
		
		msg += "\n";
		for (Integer k : reportData.keySet()) {
			msg += labels[0] + " " +  String.valueOf(k) + ": " + reportData.get(k) + "\n";
		}
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
		chooser.setFileFilter(filter);
		
		
        int returnVal = chooser.showSaveDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath() + ".txt";
            try {
                Files.write(Paths.get(path), msg.getBytes());
                MyUtils.showInfoMessage("Information", "Saved report to " + path);
                } catch (IOException e1) {
                        e1.printStackTrace();
                        MyUtils.showErrorMessage("Error", "Can't not save report to " + path);
            }
        }
	}
	
	private void writeReport(Date dateStart, Date dateEnd) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String msg = "Report revenue from " + dateFormat.format(dateStart) + " to " + dateFormat.format(dateEnd) + ": " + String.valueOf(dao.reportByTime(dateStart, dateEnd));
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
		chooser.setFileFilter(filter);
		
            int returnVal = chooser.showSaveDialog(null);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath() + ".txt";
                    try {
                            Files.write(Paths.get(path), msg.getBytes());
                            MyUtils.showInfoMessage("Information", "Saved report to " + path);
                            } catch (IOException e1) {
                                    e1.printStackTrace();
                                    MyUtils.showErrorMessage("Error", "Can't not save report to " + path);
                            }
            }
        }
        private Object[][] convertTableData(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();
            Object[][] data = new Object[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = model.getValueAt(i, j); 
                }
            }
            return data;
        }
}
