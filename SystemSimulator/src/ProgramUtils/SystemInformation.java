package ProgramUtils;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * A list of operating system classes which get some important information
 * @author Dylan
 */
public class SystemInformation {

    public static boolean isWindows() {

        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        //mac
        return (os.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }
    public static double getRam() {
  OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    double amount = operatingSystemMXBean.getTotalPhysicalMemorySize()-operatingSystemMXBean.getFreePhysicalMemorySize();
    double percentage = (amount/operatingSystemMXBean.getTotalPhysicalMemorySize())*100;
    return percentage;
    } // if
  } // for


