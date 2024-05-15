package GUI.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.HoaDonChiTietDao;
import DAO.HoaDonDao;
import DAO.KhachHangDao;
import DAO.NhanVienDao;
import DAO.SanPhamDao;
import DAO.impl.HoaDonChiTietDaoImpl;
import DAO.impl.HoaDonDaoImpl;
import DAO.impl.KhachHangDaoImpl;
import DAO.impl.NhanVienDaoImpl;
import DAO.impl.SanPhamDaoImpl;
import Model.HoaDonChiTietModel;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.NhanVienModel;
import Ultils.MyUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class HoaDonController {
	private Integer userId;
	private JTextField txfEmp;
	private JTextField txfCus;
	private JTextField txfPhone;
	private JTextField txfIdBill;
	private JDateChooser txdate;
	private JTextField txfTotal;
	private JTable tableHistory;
	private JTextField txfFind;
	private JTable tableDetail;
	private JTextField txfIdEmp;
	private JComboBox cbFilter;
	private JButton btnFind;
        private JButton btnExport;
        private KhachHangDao cDao;
        private NhanVienDao eDao;
        private HoaDonDao bDao;
        private SanPhamDao pDao;
        private HoaDonChiTietDao bdDao;

	public HoaDonController(Integer userId, JTextField txfEmp, JTextField txfCus, JTextField txfPhone, JTextField txfIdBill,
			JDateChooser txdate, JTextField txfTotal, JTable tableHistory, JTextField txfFind, JTable tableDetail,
			JTextField txfIdEmp, JComboBox cbFilter, JButton btnFind, JButton btnExport) {
		this.userId = userId;
		this.txfEmp = txfEmp;
		this.txfCus = txfCus;
		this.txfPhone = txfPhone;
		this.txfIdBill = txfIdBill;
		this.txdate = txdate;
		this.txfTotal = txfTotal;
		this.tableHistory = tableHistory;
		this.txfFind = txfFind;
		this.tableDetail = tableDetail;
		this.txfIdEmp = txfIdEmp;
		this.cbFilter = cbFilter;
		this.btnFind = btnFind;
                this.btnExport = btnExport;
		
		bDao = new HoaDonDaoImpl();
		cDao = new KhachHangDaoImpl();
		pDao = new SanPhamDaoImpl();
		eDao = new NhanVienDaoImpl();
		bdDao = new HoaDonChiTietDaoImpl();
		
		loadData();
		setEvent();
		
		txfEmp.setEditable(false);
		txfCus.setEditable(false);
		txfPhone.setEditable(false);
		txfIdBill.setEditable(false);
		txfTotal.setEditable(false);
		txfIdEmp.setEditable(false);
		txdate.setEnabled(false);
	}

	public void loadData() {
		loadTable(bDao.getAll(userId.intValue() == -1 ? null : userId));
	}

	private void setEvent() {	
		tableHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadRow();
			}
		});
		
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable(find());
			}
		});
                 btnExport.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(tableHistory.getRowCount() == 0) {
                            MyUtils.showInfoMessage("Info", "No data to export!");
                       }
                       else {
                            String[] colNames = {"ID", "ID Employee", "ID Customer", "Create Date", "Total"};
                            Object[][] data = convertTableData(tableHistory);
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
    }

	private void loadTable(List<HoaDonModel> list) {
		String[] labels= {"ID", "ID Employee", "ID Customer", "Create date", "Total"};
		DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		
		try {
			for (HoaDonModel hoadon : list) {
				Object[] row = {hoadon.getId(), hoadon.getIdNV(), hoadon.getIdKH(), hoadon.getCreateDate(), String.format("%.0f", hoadon.getTotalPrice())};
				tableModel.addRow(row);
			}
			this.tableHistory.setModel(tableModel);
		}catch(Exception ex) {
			ex.printStackTrace();
			MyUtils.showErrorMessage("Error", ex.getMessage());
		}
	}
	
	private void loadChildTable(List<HoaDonChiTietModel> list) {
		String[] labels= {"ID Bill", "ID Product", "Product Name" ,"Price", "Quantity"};
		DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		
		try {
                    for (HoaDonChiTietModel hdct : list) {
                            Object[] row = {hdct.getIdHD(), hdct.getIdSP(), hdct.getTenSP(), String.format("%.0f", hdct.getPrice()), hdct.getQuantity()};
                            tableModel.addRow(row);
                    }
                    this.tableDetail.setModel(tableModel);
		}catch(Exception ex) {
			ex.printStackTrace();
			MyUtils.showErrorMessage("Error", ex.getMessage());
		}
	}
	
	private void loadRow() {
		int row = tableHistory.getSelectedRow();

		txfIdBill.setText(tableHistory.getValueAt(row, 0).toString());
		txfIdEmp.setText(tableHistory.getValueAt(row, 1).toString());
		txdate.setDate((java.util.Date) tableHistory.getValueAt(row, 3));
		txfTotal.setText(tableHistory.getValueAt(row, 4).toString());
		
		KhachHangModel kh = cDao.getById(Integer.parseInt(tableHistory.getValueAt(row, 2).toString()));
		NhanVienModel nv = eDao.getById(Integer.parseInt(tableHistory.getValueAt(row, 1).toString()));
		
		txfPhone.setText(kh.getPhone());
		txfEmp.setText(nv.getName());
		txfCus.setText(kh.getName());
		
		loadChildTable(bdDao.getByIdBill(Integer.parseInt(txfIdBill.getText())));
	}
	
	private List<HoaDonModel> find() {
		String kw = txfFind.getText();
		if (kw.equals("")) {
			return bDao.getAll(userId.intValue() == -1 ? null : userId);
		}
		
		List<HoaDonModel> list = new ArrayList<HoaDonModel>();
		
		if (cbFilter.getSelectedIndex() == 0) {
			HoaDonModel hoadon = bDao.getById(Integer.parseInt(kw), userId.intValue() == -1 ? null : userId);
			if ( hoadon != null) {
				list.add(hoadon);
			}
		}
		else if (cbFilter.getSelectedIndex() == 1) {
			list = bDao.getByIdEmployee(Integer.parseInt(kw));
		}
		return list;
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
