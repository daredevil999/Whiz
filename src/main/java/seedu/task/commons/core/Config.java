package seedu.task.commons.core;

import java.util.Objects;
import java.util.logging.Level;

/**
 * Configure values used by the app
 */
public class Config {

    public static final String DEFAULT_CONFIG_FILE = "config.json";

    // Config values customizable through config file
    private String appTitle = "Whiz";
    private Level logLevel = Level.INFO;
    private String userPrefsFilePath = "preferences.json";
    private String stockManagerFilePath = "Whiz.xml";
    private String stockManagerName = "TypicalStockManagerName";


    public Config() {
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public String getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    public void setUserPrefsFilePath(String userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    public String getStockManagerFilePath() {
        return stockManagerFilePath;
    }

    public void setStockManagerFilePath(String stockManagerFilePath) {
        this.stockManagerFilePath = stockManagerFilePath;
    }

    public String getStockManagerName() {
        return stockManagerName;
    }

    public void setStockManagerName(String stockManagerName) {
        this.stockManagerName = stockManagerName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { // null handler.
            return false;
        }

        Config o = (Config)other;

        return Objects.equals(appTitle, o.appTitle)
                && Objects.equals(logLevel, o.logLevel)
                && Objects.equals(userPrefsFilePath, o.userPrefsFilePath)
                && Objects.equals(stockManagerFilePath, o.stockManagerFilePath)
                && Objects.equals(stockManagerName, o.stockManagerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appTitle, logLevel, userPrefsFilePath, stockManagerFilePath, stockManagerName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("App title : " + appTitle);
        sb.append("\nCurrent log level : " + logLevel);
        sb.append("\nPreference file Location : " + userPrefsFilePath);
        sb.append("\nLocal data file location : " + stockManagerFilePath);
        sb.append("\nTaskBook name : " + stockManagerName);
        return sb.toString();
    }

}
