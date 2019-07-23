package Model;

import java.util.Comparator;

public class RowServiceComparator implements Comparator<RowService> {
    @Override
    public int compare(RowService o1, RowService o2) {
        if (o1.getEmployee() == null && o2.getEmployee() == null) return 0;

        else if (o1.getEmployee() != null && o2.getEmployee() == null) return 1;

        else if (o1.getEmployee() == null && o2.getEmployee() != null) return -1;

        else
        {
            if (o1.getEmployee().getDivision() != null && o2.getEmployee().getDivision() != null){
                return o1.getEmployee().getDivision().compareTo(o2.getEmployee().getDivision());
            }
            else if (o1.getEmployee().getDivision() != null && o2.getEmployee().getDivision() == null){
                return 1;
            }
            else if (o1.getEmployee().getDivision() == null && o2.getEmployee().getDivision() != null){
                return -1;
            }
            else if(o1.getEmployee().getDivision() == null && o2.getEmployee().getDivision() == null){
                return 0;
            }
            else
            {
                return 0;
            }
        }
    }
}
