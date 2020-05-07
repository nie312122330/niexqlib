/**
 * 2015-1-29   下午6:11:19
 * Created By niexiaoqiang
 */

package scnxq.com.niexqlib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 文件路径
 *
 * @author niexiaoqiang
 */
public class SdCardUtils {
    public static String ROOT_DIR = "test";

    public static File getAppRootDir(Context context) {
        File sdcard = getSdCardRootDir(context);
        File file = new File(sdcard, ROOT_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getACacheFileDir(Context context) {
        return new File(getAppRootDir(context), "cache");
    }

    public static File getCrashDir(Context context) {
        File sdcard = getAppRootDir(context);
        File file = new File(sdcard, "crash");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getDbDir(Context context) {
        File sdcard = getAppRootDir(context);
        File file = new File(sdcard, "db");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static File getUploadImageTempDir(Context context) {
        File sdcard = getAppRootDir(context);
        File file = new File(sdcard, "uploadImage");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static File getImageLoaderCache(Context context) {
        File sdcard = getAppRootDir(context);
        File imbar = new File(sdcard, "imageLoaderCach");
        if (!imbar.exists()) {
            imbar.mkdirs();
        }
        return imbar;
    }


    public static File getDownLoadApkDir(Context context) {
        File sdcard = getAppRootDir(context);
        File file = new File(sdcard, "DownApk");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * pictureviewer 中长按保存图片的路径
     */
    public static File getPhotoDir(Context context) {
        File sdCard = getAppRootDir(context);
        File photoDir = new File(sdCard, "SavePic");
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
        return photoDir;
    }

    private static File getSdCardRootDir(Context context) {
        File sdcard = Environment.getExternalStorageDirectory();
        if (null == sdcard || !sdcard.exists()) {
            sdcard = context.getCacheDir();
        }
        return sdcard;
    }

    public static String getDirSizeString(File... dirs) {
        long dirSize = getDirSize(dirs);
        if (dirSize > 1024 * 1024) {
            //kb
            BigDecimal bigDecimal = BigDecimal.valueOf(dirSize / (1024f * 1024));
            return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        } else {
            //kb
            BigDecimal bigDecimal = BigDecimal.valueOf(dirSize / 1024f);
            return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
    }

    public static long getDirSize(File... dirs) {
        long size = 0;
        for (File file : dirs) {
            size += getTotalSizeOfFilesInDir(file);
        }
        return size;
    }

    private static long getTotalSizeOfFilesInDir(final File file) {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<>();
                for (final File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirectoriesAndSize>() {
                        public SubDirectoriesAndSize call() {
                            return getTotalAndSubDirs(directory);
                        }
                    }));
                }
                directories.clear();
                for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture.get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        } catch (Exception e) {
            return 0;
        } finally {
            service.shutdown();
        }
    }

    private static SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null)
                for (final File child : children) {
                    if (child.isFile())
                        total += child.length();
                    else
                        subDirectories.add(child);
                }
        }
        return new SubDirectoriesAndSize(total, subDirectories);
    }

    static class SubDirectoriesAndSize {
        final public long size;
        final public List<File> subDirectories;

        public SubDirectoriesAndSize(final long totalSize, final List<File> theSubDirs) {
            size = totalSize;
            subDirectories = Collections.unmodifiableList(theSubDirs);
        }
    }
}
