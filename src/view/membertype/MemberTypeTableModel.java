package view.membertype;

import model.MemberType;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTypeTableModel extends AbstractTableModel {
    private final String[] columns = {"Name"};
    private final List<MemberType> data;

    public MemberTypeTableModel(List<MemberType> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public String getValueAt(int row, int col) {
        MemberType rowItem = data.get(row);

        if (col == 0) {
            return rowItem.getName();
        }

        return "";
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(MemberType value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() -1);
    }
}
