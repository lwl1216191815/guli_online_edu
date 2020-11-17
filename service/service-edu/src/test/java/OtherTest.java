import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class OtherTest {
    /**
     * 测试获取本周一
     */
    @Test
    public void testWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        System.out.println(date);
    }
}
