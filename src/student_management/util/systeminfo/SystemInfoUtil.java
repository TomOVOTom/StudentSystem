package student_management.util.systeminfo;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInfoUtil {

    public static String getSystemInfo() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        int availableProcessors = runtime.availableProcessors();

        String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String formattedMaxMemory = String.format("%.2f MB", maxMemory / (1024.0 * 1024.0));
        String formattedTotalMemory = String.format("%.2f MB", totalMemory / (1024.0 * 1024.0));
        String formattedUsedMemory = String.format("%.2f MB", usedMemory / (1024.0 * 1024.0));
        String formattedFreeMemory = String.format("%.2f MB", freeMemory / (1024.0 * 1024.0));

        com.sun.management.OperatingSystemMXBean osMxBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String formattedPhysicalMemory = String.format("%.2f GB", osMxBean.getTotalMemorySize() / (1024.0 * 1024.0 * 1024.0));

        long pid = ProcessHandle.current().pid();
        int threadCount = Thread.activeCount();

        return String.format("PONG\n" +
                        "时间: %s\n" +
                        "最大可用内存: %s\n" +
                        "当前分配内存: %s\n" +
                        "已用内存: %s\n" +
                        "空闲内存: %s\n" +
                        "系统总物理内存: %s\n" +
                        "CPU核心数: %d\n" +
                        "进程ID: %d\n" +
                        "Java线程数: %d\n" +
                        "说明: 这是一个单进程多线程的应用。进程ID是当前Java应用的进程ID，线程数是Java虚拟机中的活动线程数，包括主线程和其他后台线程。",
                formattedTime,
                formattedMaxMemory,
                formattedTotalMemory,
                formattedUsedMemory,
                formattedFreeMemory,
                formattedPhysicalMemory,
                availableProcessors,
                pid,
                threadCount);
    }
}