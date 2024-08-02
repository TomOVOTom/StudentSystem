package student_management.util.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockUtil {
    public static FileLock lockFile(FileChannel channel, boolean shared) throws IOException {
        return channel.lock(0, Long.MAX_VALUE, shared);
    }

    public static void releaseFileLock(FileLock lock) throws IOException {
        if (lock != null) {
            lock.release();
        }
    }

    public static void closeFileChannel(FileChannel channel) throws IOException {
        if (channel != null) {
            channel.close();
        }
    }

    public static void closeFileInputStream(FileInputStream fileIn) throws IOException {
        if (fileIn != null) {
            fileIn.close();
        }
    }

    public static void closeFileOutputStream(FileOutputStream fileOut) throws IOException {
        if (fileOut != null) {
            fileOut.close();
        }
    }
}