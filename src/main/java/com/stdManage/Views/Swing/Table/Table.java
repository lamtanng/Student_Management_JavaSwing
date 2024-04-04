package com.stdManage.Views.Swing.Table;

import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Utils.U_Styles;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import com.stdManage.Views.Swing.JTable.PanelAction;
import com.stdManage.Views.Swing.JTable.TableActionCellEditor;
import com.stdManage.Views.Swing.ScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(U_Styles.COLOR_WHITE);
        setRowHeight(U_Styles.HEIGHT_ROW);
        getTableHeader().setReorderingAllowed(true);

        //set header column title
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                header.setHorizontalAlignment(JLabel.LEFT);
                header.setForeground(U_Styles.COLOR_GRAY3);
                return header;
            }
        });

        //paint row
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return paintRow(com, isSelected, row, column);
            }
        });
    }

    public void initTable(Object[] colsHeader, Object[][] dataTable) {
        deleteRows();
        fixTable();
        createColumns(colsHeader, dataTable);
        createOrderColumn();
    }

    /**
     * @param colTitle
     * @return Index of column
     */
    public int getColumnByName(String colTitle) {
        TableColumnModel colModel = this.getColumnModel();
        return colModel.getColumnIndex(colTitle);
    }

    /**
     * @param arrIdx int[]
     * @see hide all index column
     */
    public void hideColumnAt(int[] arrIdx) {
        for (int idx : arrIdx) {
            getColumnModel().getColumn(idx).setMinWidth(0);
            getColumnModel().getColumn(idx).setMaxWidth(0);
        }
    }

    public void createColumns(Object[] colsHeader, Object[][] dataTable) {
        setModel(new javax.swing.table.DefaultTableModel(
                dataTable,
                colsHeader
        )
        );
    }

    public void createOrderColumn() {
        TableColumn col = new TableColumn(0, U_Styles.HEIGHT_ROW, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                value = row + 1;
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return paintRow(com, isSelected, row, column);
            }
        }, new DefaultCellEditor(new JTextField()));
        col.setHeaderValue(U_ColumnTitles.ORDER_TITLE);
        addColumn(col);
        moveColumn(getColumnModel().getColumnIndex(U_ColumnTitles.ORDER_TITLE), 0);
    }

    public void createActionColumn(ITableActionEvent event, int typeAction) {
        TableColumn col = new TableColumn(0, U_Styles.HEIGHT_ROW, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                PanelAction action = new PanelAction(typeAction);

                if (isSelected) {
                    action.setBackground(U_Styles.COLOR_PRIMARY);
                    action.setForeground(U_Styles.COLOR_WHITE);
                } else {
                    if (row % 2 == 0) {
                        action.setBackground(Color.WHITE);
                    } else {
                        action.setBackground(U_Styles.COLOR_GRAY1);
                    }
                }
                return action;
            }
        }, new TableActionCellEditor(event, typeAction));
        col.setHeaderValue(U_ColumnTitles.ACTION_TITLE);
        addColumn(col);
    }

    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    public void deleteColumns() {
        for (int i = getColumnCount() - 1; i >= 0; i--) {
            this.removeColumn(getColumnModel().getColumn(i));
        }
    }

    public void deleteRows() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.setRowCount(0);

    }

    /**
     * Custom Table
     */
    public void fixTable() {
        try {
            Component parentViewPort = getParent();

            if (parentViewPort instanceof JViewport) {
                JScrollPane parentScroll = (JScrollPane) parentViewPort.getParent();
                parentViewPort.setBackground(U_Styles.COLOR_WHITE);

                parentScroll.setVerticalScrollBar(new ScrollBarCustom());
                JPanel p = new JPanel();
                p.setBackground(U_Styles.COLOR_WHITE);
                parentScroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
                parentScroll.setBorder(new EmptyBorder(5, 10, 5, 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Component paintRow(Component com, boolean isSelected, int row, int column) {
        com.setForeground(U_Styles.COLOR_GRAY4);
        setBorder(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        com.setFont(U_Styles.TEXT_PLAIN_MEDIUM);
        setCellSelectionEnabled(false);
        if (isSelected) {
            com.setFocusable(false);
            com.setBackground(U_Styles.COLOR_WHITE);
            com.setForeground(U_Styles.COLOR_BLACK);
//                    setBorder(new LineBorder(Styles.COLOR_PRIMARY));
//                    setCellSelectionEnabled(false);
        } else {
            if (row % 2 == 0) {
                com.setBackground(Color.WHITE);
            } else {
                com.setBackground(U_Styles.COLOR_GRAY1);
            }
        }
        return com;
    }
}
