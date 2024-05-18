package edu.hcmut.bookstore.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import edu.hcmut.bookstore.config.IgniteConfig;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import javax.sql.DataSource;

public class DbManager {
    private static Ignite ignite = null;
    private static IgniteConfiguration igniteCfg = IgniteConfig.igniteConfig();

    public static Ignite getIgniteNode() throws Exception {
        if (ignite == null) {
            ignite = Ignition.start(igniteCfg);
        }

        return ignite;
    }

    public static DataSource getMySqlDataSrc() {
        var dataSrc = new MysqlDataSource();
        dataSrc.setUrl("jdbc:mysql://localhost:3306/bookstore");
        dataSrc.setUser("root");
        dataSrc.setPassword("");
        return dataSrc;
    }
}
