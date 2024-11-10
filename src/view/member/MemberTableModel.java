package view.member;

import model.Member;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTableModel extends AbstractTableModel {
    private final String[] columns = {"Name", "Member Type"};
    private final List<Member> data;

    public MemberTableModel(List<Member> data) {
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
        Member rowItem = data.get(row);

        if (col == 0) {
            return rowItem.getName();
        } else if (col == 1) {
            return rowItem.getMemberType().getName();
        }

        return "";
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() -1);
    }
}
