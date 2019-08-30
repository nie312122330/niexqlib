package scnxq.com.appbase;

public class AppBaseConfig {
    private String logTag = "niexq";
    private String sdcardBaseDir = "niexq";
    private String dbName = "niexqdb";
    private Boolean dbAllowTransaction = true;
    private Integer dbVersion = 1;

    public String getSdcardBaseDir() {
        return sdcardBaseDir;
    }

    public void setSdcardBaseDir(String sdcardBaseDir) {
        this.sdcardBaseDir = sdcardBaseDir;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Boolean getDbAllowTransaction() {
        return dbAllowTransaction;
    }

    public void setDbAllowTransaction(Boolean dbAllowTransaction) {
        this.dbAllowTransaction = dbAllowTransaction;
    }

    public Integer getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(Integer dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getLogTag() {
        return logTag;
    }

    public void setLogTag(String logTag) {
        this.logTag = logTag;
    }
}
