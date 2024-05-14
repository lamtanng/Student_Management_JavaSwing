package DAO;

import java.util.Date;
import java.util.Map;

public interface ThongKeDao {
	Map<Integer, Integer> reportAllYear();
	Map<Integer, Integer> reportByMonthYear(int year);
	Map<Integer, Integer> reportByQuaterYear(int year);
	int reportByTime(Date dateStart, Date dateEnd);
}
